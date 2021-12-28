package com.zcc.thread;

import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @ Author Zcc
 * @ Date 2021/12/27 21:41
 * @ Describe : CompletableFutureTest
 */
public class CompletableFutureTest {
    private static Function<Integer, Integer> f1 = (a)-> a++;
    private static Function<Integer, Integer> f2 = (a)-> a--;
    private static BiFunction<Integer, Integer,Integer> f3 = (a,b)-> a+b;
    private static ExecutorService threadPoolExecutor = new ThreadPoolExecutor(3, 30, 0, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>());


    /**
     * 2.1 创建异步对象
     * CompletableFuture 提供了四个静态方法来创建一个异步操作。
     *
     * static CompletableFuture<Void> runAsync(Runnable runnable)
     * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
     * 没有指定Executor的方法会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码。如果指定线程池，则使用指定的线程池运行。以下所有的方法都类同。
     *
     * runAsync方法不支持返回值。
     * supplyAsync可以支持返回值。
     *
     *
     * 2.2 计算完成时回调方法
     * 当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action。主要是下面的方法：
     *
     * public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action);
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action);
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor);
     *
     * public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn);
     * whenComplete可以处理正常和异常的计算结果，exceptionally处理异常情况。BiConsumer<? super T,? super Throwable>可以定义处理业务
     *
     * whenComplete 和 whenCompleteAsync 的区别：
     *  whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
     *  whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
     *  方法不以Async结尾，意味着Action使用相同的线程执行，而Async可能会使用其他线程执行（如果是使用相同的线程池，也可能会被同一个线程选中执行）
     *
     *
     * 2.3 线程串行化方法
     * thenApply 方法：当一个线程依赖另一个线程时，获取上一个任务返回的结果，并返回当前任务的返回值。
     *
     * thenAccept方法：消费处理结果。接收任务的处理结果，并消费处理，无返回结果。
     *
     * thenRun方法：只要上面的任务执行完成，就开始执行thenRun，只是处理完任务后，执行 thenRun的后续操作
     *
     * 带有Async默认是异步执行的。这里所谓的异步指的是不在当前线程内执行。
     * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
     *
     * public CompletionStage<Void> thenAccept(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
     *
     * public CompletionStage<Void> thenRun(Runnable action);
     * public CompletionStage<Void> thenRunAsync(Runnable action);
     * public CompletionStage<Void> thenRunAsync(Runnable action,Executor executor);
     *
     * 2.4 多任务组合
     *
     * public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs);
     *
     * public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs);
     * allOf：等待所有任务完成
     *
     * anyOf：只要有一个任务完成
     *
     * demo:
     *
     * @Service
     * public class ArticleService {
     *        @Autowired
     *     private ArticleClient articleClient;
     *     @Autowired
     *     private UserClient userClient;
     *     @Autowired
     *     private ThreadPoolExecutor threadPoolExecutor;
     *
     *  	public ItemVo load(Long id) {
     * 	// 1. 查询文章详情 0.5s
     * 	// 下面的查询需要用到文章对应的发布用户，所以这里需要使用CompletableFuture.supplyAsync
     * 	CompletableFuture<ArticleEntity> articleCompletableFuture = CompletableFuture.supplyAsync(() -> {
     *             ResponseVo<ArticleEntity> skuEntityResponseVo = this.articleClient.getArticleById(id);
     *             ArticleEntity articleEntity = skuEntityResponseVo.getData();
     *             if (articleEntity == null) {
     *                 return null;
     *             }
     *             itemVo.setId(id);
     *             itemVo.setTitle(articleEntity.getTitle());
     *             itemVo.setDefaltImage(articleEntity.getDefaultImage());
     *             return articleEntity;
     *         }, threadPoolExecutor);
     * 	// 2. 查询文章博主个人信息 0.5s
     * 	// 这里查询需要依赖文章关联的用户id，所以需要使用articleCompletableFuture.thenAcceptAsync()
     *     CompletableFuture<Void> userCompletableFuture = articleCompletableFuture.thenAcceptAsync(articleEntity -> {
     *         ResponseVo<UserEntity> categoryResponseVo = this.userClient.queryUserInfoById(articleEntity.getUserId());
     *         UserEntity userEntity = categoryResponseVo.getData();
     *         itemVo.setUserInfo(userEntity);
     *     }, threadPoolExecutor);
     * 	// 3. 查询博主相关文章分类 1s
     * 	// 这里查询需要依赖文章关联的用户id，所以需要使用articleCompletableFuture.thenAcceptAsync()
     *     CompletableFuture<Void> userOtherArticleCompletableFuture = articleCompletableFuture.thenAcceptAsync(articleEntity -> {
     *         ResponseVo<List<UserAuserOtherArticleEntity>> categoryResponseVo = this.articleClient.queryUserAuserOtherArticleById(articleEntity.getUserId());
     *         UserAuserOtherArticleEntity userAuserOtherArticleEntity = categoryResponseVo.getData();
     *         itemVo.setUserAuserOtherArticleList(userAuserOtherArticleEntity);
     *     }, threadPoolExecutor);
     *     // 4. 查询文章评论 1s
     *     // 不需要依赖其他请求返回值，可以使用新的异步对象 CompletableFuture.runAsync()
     *     CompletableFuture<Void> commentsCompletableFuture =  CompletableFuture.runAsync(() -> {
     *         ResponseVo<List<UserArticleCategoryEntity>> userArticleCategoryVo = this.userClient.queryCommentsByArticleId(id);
     *         UserArticleCategoryEntity userArticleCategoryEntity = userArticleCategoryVo.getData();
     *         itemVo.setUserArticleCategoryList(userArticleCategoryEntity);
     *     }, threadPoolExecutor);
     * 	// 5. 相关推荐文章 1s
     * 	// 不需要依赖其他请求返回值，可以使用新的异步对象 CompletableFuture.runAsync()
     * 	CompletableFuture<Void> relatedArticlesCompletableFuture =  CompletableFuture.runAsync(() -> {
     *         ResponseVo<List<RelatedArticlesEntity>> userArticleCategoryVo = this.articleClient.queryRelatedArticles(id);
     *         UserArticleCategoryEntity userArticleCategoryEntity = userArticleCategoryVo.getData();
     *         itemVo.setUserArticleCategoryList(userArticleCategoryEntity);
     *     }, threadPoolExecutor);
     *    }
     * 	// 多任务执行组合 CompletableFuture.allOf()
     * 	CompletableFuture.allOf(articleCompletableFuture, userCompletableFuture, userOtherArticleCompletableFuture,
     *                 commentsCompletableFuture, relatedArticlesCompletableFuture).join();
     *      return itemVo;
     * }
     *
     * 上面例子完美呈现，计算完成时回调方法CompletableFuture.supplyAsync()、
     * 线程串行化方法articleCompletableFuture.thenAcceptAsync()、
     * 创建异步对象CompletableFuture.runAsync()、
     * 多任务组合CompletableFuture.allOf()
     *
     */

    public static void main(String[] args) {
        Integer s  = 0;
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() ->
                f1.andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).andThen(f1)
                        .andThen(f1).andThen(f1).apply(s), threadPoolExecutor);
        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> f2.andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .apply(s), threadPoolExecutor);
        CompletableFuture<Integer> integerCompletableFuture2 = CompletableFuture.supplyAsync(() -> f3.andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2).andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f2).andThen(f2).andThen(f2)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .andThen(f1).andThen(f1).andThen(f1)
                .apply(s,s), threadPoolExecutor);
        CompletableFuture.allOf(integerCompletableFuture, integerCompletableFuture1, integerCompletableFuture2).join();
//        integerCompletableFuture.whenCompleteAsync((s)->s);
//        integerCompletableFuture.whenCompleteAsync((s)->s.)
        System.out.println(s);
    }
}
