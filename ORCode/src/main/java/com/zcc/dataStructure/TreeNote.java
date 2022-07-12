package com.zcc.dataStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.dataStructure
 * @author: zcc
 * @date: 2022/6/20 13:27
 * @version:
 * @Describe:
 */
public class TreeNote {

    private static Tree root;

    static class Tree {
        private Tree left;
        private Tree right;
        private int value;

        public Tree getLeft() {
            return left;
        }

        public void setLeft(Tree left) {
            this.left = left;
        }

        public Tree getRight() {
            return right;
        }

        public void setRight(Tree right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Tree(Tree left, Tree right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public Tree() {
        }

        public Tree(int value) {
            this.value = value;
        }
    }

    static {
        List<Tree> treeList = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            treeList.add(new Tree(i));
        }
        treeList.get(0).setLeft(treeList.get(1));
        treeList.get(0).setRight(treeList.get(2));
        treeList.get(1).setLeft(treeList.get(3));
        treeList.get(1).setRight(treeList.get(4));
        treeList.get(2).setLeft(treeList.get(5));
        treeList.get(2).setRight(treeList.get(6));
        root = treeList.get(0);
    }

    /**
     * 什么是树？
     * 树是一种类似于链表的数据结构，不同于链表的是树的一个结点可以指向多个结点。
     * 树是一种典型的非线性数据结构，树结构是表达具有层次特性的图结构的一种方法
     * 对于树ADT，元素的顺序不是考虑的重点。如果需要用到元素的顺序信息可以使用链表、栈、队列等线性数据结构
     * <p>
     * 相关名词：
     * 根结点：根结点是一个没有双亲的结点。一棵树中最多有一个根结点
     * 边：边表示从双亲结点到孩子结点的链接
     * 叶子结点：没有孩子的结点
     * 兄弟结点：有用相同双亲结点的所有孩子结点叫做兄弟结点
     * 祖先结点：如果存在一条从根结点到结点q的路径，且结点p出现在这条路径上，那么就可以把结点P叫做结点q的祖先结点
     * 结点的大小：只子孙的个数，包括自身
     * 树的层：位于相同深度的所有结点的结合叫树的层，根结点位于0层
     * 结点的深度：从根结点到该结点的路径长度
     * 结点的高度：该节点到最深结点的路径长度
     * 树的高度：从根结点到最深结点的路径长度
     * 斜树：如果树中，除了叶子结点外，其余每一个结点只有一个孩子结点 全部只有左节点的是左斜树，全部只有右结点的叫右斜树，其他则是斜树
     * 二叉树：
     * 如果一棵树的每个节点只有0、1或2个孩子结点，那么这棵树被成为二叉树。空树也是一颗有效二叉树。
     * <p>
     * 二叉树的类型：
     * 1.严格二叉树
     * 二叉树中的每个节点要么有两个孩子结点，要么没有孩子结点
     * 2.满二叉树
     * 二叉树中的每个节点都有两个孩子结点，且叶子结点在同一层
     * 3.完全二叉树
     * 叶子结点只能出现在最下层和次下层，且最下层的叶子结点杰总在树的左部。满二叉树一定是完全二叉树，完全二叉树不一定是满二叉树
     * <p>
     * <p>
     * 性质：
     * 1.具有n个结点的完全二叉树的深度log2+1
     * 2.如果有一颗n个结点的完全二叉树的节点按层序编号，则对任意结点i(1<=i<=n)有
     * a. 如果i=1，则结点i是二叉树的根，无双亲;如果i》1，则其双亲parent(i)是节点i/2
     * b. 如果2i>n，则结点i无左孩子,否则其左孩子lChild(i)是节点2i
     * c. 如果2i+1>n，则结点i无有孩子，否则其有孩子rChild(i)是节点2i+1
     * <p>
     * 二叉树的操作：
     * a. 基本操作
     * 1.插入
     * 2.删除
     * 3.查找
     * 4.遍历
     * b. 辅助操作
     * 1.获取树的大小
     * 2.获取树的高度
     * 3.获取其和的最大的层
     * 4.对于给定两个或多个结点，找出它们的最近公共祖先
     * 二叉树的应用：
     * a.编译器中的表达式树
     * b.用于数据压缩算法中的哈夫曼编码树
     * c.支持在结合中查找、插入、删除，其平均时间复杂度为O(logn)的二叉搜索树（BST）
     * d.优先队列(PQ) 他支持以对数时间对集合中的最小(最大)数据元素进行搜索和删除
     * <p>
     * 二叉树的遍历：
     * 1.遍历方式
     * 前序遍历（DLR）：根左右
     * 中序遍历 (LDR): 左根右
     * 后序遍历 (LRD)：左右根
     * 层次遍历
     */


    public static void antecedentErgodic(Tree rootNode) {
        if (rootNode == null) return;
        Stack<Tree> stack = new Stack();
        while (true) {
            while (rootNode != null) {
                System.out.println(rootNode.value);
                stack.push(rootNode);
                rootNode = rootNode.getLeft();
            }
            if (stack.isEmpty()) {
                break;
            }
            rootNode = stack.pop();
            rootNode = rootNode.getRight();
        }

    }

    public static void middleOrderErgodic(Tree rootNode) {
        if (rootNode == null) return;
        Stack<Tree> stack = new Stack();
        while (true) {
            while (rootNode != null) {
                stack.push(rootNode);
                rootNode = rootNode.getLeft();
            }
            if (stack.isEmpty()) {
                break;
            }
            rootNode = stack.pop();
            System.out.println(rootNode.value);
            rootNode = rootNode.getRight();
        }

    }

    public static void afterwordErgodic(Tree rootNode) {
        if (rootNode == null) return;
        Stack<Tree> stack = new Stack();
        while (true) {
            if (rootNode != null) {
                stack.push(rootNode);
                rootNode = rootNode.getLeft();
            } else {
                if (stack.isEmpty()) {
                    System.out.println("Error");
                }
                if (stack.get(stack.size() - 1).getRight() == null) {
                    rootNode = stack.pop();
                    System.out.println(rootNode.value);
                }
                if (!stack.isEmpty()) {

                    rootNode = stack.get(stack.size() - 1).getRight();

                } else {
                    rootNode = null;
                }

            }
            if (stack.isEmpty()) {
                break;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("前序遍历:------------------------");
        antecedentErgodic(root);
        System.out.println("中序遍历:------------------------");
        middleOrderErgodic(root);
        System.out.println("后序遍历:------------------------");
        afterwordErgodic(root);
    }

}
