package com.zcc.algorithm.primary_algorithm;

import org.junit.Test;

import java.util.*;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/10/15 13:30
 */
public class TreeTest {
    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    /**
     * 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度 3 。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnd69e/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        // 递归
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        /**
         * DFS 深度优先搜索
         */
        // 深度优先搜索的步骤分为
        // 1.递归下去
        // 2.回溯上来。
        // 顾名思义，深度优先，则是以深度为准则，先一条路走到底，直到达到目标。这里称之为递归下去
//            if (root == null) return 0;
//            //stack记录的是节点，而level中的元素和stack中的元素
//            //是同时入栈同时出栈，并且level记录的是节点在第几层
//            Stack<TreeNode> stack = new Stack<>();
//            Stack<Integer> level = new Stack<>();
//            stack.push(root);
//            level.push(1);
//            int max = 0;
//            while (!stack.isEmpty()) {
//                //stack中的元素和level中的元素同时出栈
//                TreeNode node = stack.pop();
//                int temp = level.pop();
//                max = Math.max(temp, max);
//                if (node.left != null) {
//                    //同时入栈
//                    stack.push(node.left);
//                    level.push(temp + 1);
//                }
//                if (node.right != null) {
//                    //同时入栈
//                    stack.push(node.right);
//                    level.push(temp + 1);
//                }
//            }
//            return max;

        /**
         * BFS 广度优先算法
         */
        // 广度优先搜索较之深度优先搜索之不同在于，深度优先搜索旨在不管有多少条岔路，先一条路走到底，
        // 不成功就返回上一个路口然后就选择下一条岔路，
        // 而广度优先搜索旨在面临一个路口时，把所有的岔路口都记下来，然后选择其中一个进入，
        // 然后将它的分路情况记录下来，然后再返回来进入另外一个岔路，并重复这样的操作
//        if (root == null){
//            return 0;
//        }
//        //创建一个队列
//        Deque<TreeNode> deque = new LinkedList<>();
//        deque.push(root);
//        int count = 0;
//        while (!deque.isEmpty()) {
//            //每一层的个数
//            int size = deque.size();
//            while (size-- > 0) {
//                TreeNode cur = deque.pop();
//                if (cur.left != null)
//                    deque.addLast(cur.left);
//                if (cur.right != null)
//                    deque.addLast(cur.right);
//            }
//            count++;
//        }
//        return count;
        /**
         * 3.总结
         * 对于这两个搜索方法，其实我们是可以轻松的看出来，他们有许多差异与许多相同点的。
         *
         * 1.数据结构上的运用
         *
         * DFS用递归的形式，用到了栈结构，先进后出。
         *
         * BFS选取状态用队列的形式，先进先出。
         *
         * 2.复杂度
         *
         * DFS的复杂度与BFS的复杂度大体一致，不同之处在于遍历的方式与对于问题的解决出发点不同，DFS适合目标明确，而BFS适合大范围的寻找。
         *
         * 3.思想
         *
         * 思想上来说这两种方法都是穷竭列举所有的情况。
         */
//        作者：数据结构和算法
//        链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnd69e/?discussion=1Pu6Hw
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 有效 二叉搜索树定义如下：
     * <p>
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：root = [2,1,3]
     * 输出：true
     * 示例 2：
     * <p>
     * <p>
     * 输入：root = [5,1,4,null,null,3,6]
     * 输出：false
     * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
     *  
     * <p>
     * 提示：
     * <p>
     * 树中节点数目范围在[1, 104] 内
     * -231 <= Node.val <= 231 - 1
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn08xg/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
//    public boolean isValidBST(TreeNode root,long min,long  max) {
//        while( root!=null ){
//            if (root.left!= null && root.left.val>= max) return false;
//            if (root.right!= null && root.right.val <= min) return false;
//           return isValidBST(root.left,root.left.val,root.val)  &&  isValidBST(root.right,root.val,root.right.val);
//        }
//        return true;
//    }

    /**
     * 递归解法
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null)
            return true;
        //每个节点如果超过这个范围，直接返回false
        if (root.val >= maxVal || root.val <= minVal)
            return false;
        //这里再分别以左右两个子节点分别判断，
        //左子树范围的最小值是minVal，最大值是当前节点的值，也就是root的值，因为左子树的值要比当前节点小
        //右子数范围的最大值是maxVal，最小值是当前节点的值，也就是root的值，因为右子树的值要比当前节点大
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    public boolean isValidBST_Two(TreeNode root) {
        if (root == null)
            return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.val <= pre.val)
                return false;
            //保存前一个访问的结点
            pre = root;
            root = root.right;
        }
        return true;
    }


    /**
     * 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     * <p>
     *  
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     *  
     * <p>
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     * <p>
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     *  
     * <p>
     * 进阶：
     * <p>
     * 你可以运用递归和迭代两种方法解决这个问题吗？
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn7ihv/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null || left.val != right.val)
            return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     *二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     *  
     *
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层序遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnldjj/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    /**
     * BFS
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> resList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode node = queue.poll();
                subList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            resList.add(subList);
        }
        return resList;
    }

    /**
     * DFS
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelHelper(res, root, 0);
        return res;
    }

    public void levelHelper(List<List<Integer>> list, TreeNode root, int level) {
        //边界条件判断
        if (root == null)
            return;
        //level表示的是层数，如果level >= list.size()，说明到下一层了，所以
        //要先把下一层的list初始化，防止下面add的时候出现空指针异常
        if (level >= list.size()) {
            list.add(new ArrayList<>());
        }
        //level表示的是第几层，这里访问到第几层，我们就把数据加入到第几层
        list.get(level).add(root.val);
        //当前节点访问完之后，再使用递归的方式分别访问当前节点的左右子节点
        levelHelper(list, root.left, level + 1);
        levelHelper(list, root.right, level + 1);
    }

    /**
     * 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * <p>
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：nums = [-10,-3,0,5,9]
     * 输出：[0,-3,9,-10,null,5]
     * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
     * <p>
     * 示例 2：
     * <p>
     * <p>
     * 输入：nums = [1,3]
     * 输出：[3,1]
     * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xninbt/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return null;
    }

    public TreeNode sortTransferBST(int[] nums) {
        TreeNode treeNode = new TreeNode();
        int length = 0;
        int half = 0;
        if (nums != null) {
            length = nums.length;
            half = nums.length / 2;
            treeNode.val = nums[half];
        } else {
            return null;
        }
        if (half != 0) {
            int[] int1 = Arrays.copyOfRange(nums, 0, half);
            if (half + 1 < length) {
                int[] int2 = Arrays.copyOfRange(nums, half + 1, length);
                treeNode.right = sortTransferBST(int2);
            }
            treeNode.left = sortTransferBST(int1);
        }
        return treeNode;
    }

    @Test
    public void testMaxDepth() {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(1);
        TreeNode treeNode6 = new TreeNode(1);
        TreeNode treeNode7 = new TreeNode(1);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        treeNode4.left = treeNode5;
        treeNode4.right = treeNode6;
        treeNode6.left = treeNode7;
        System.out.println(maxDepth(treeNode));
    }

    @Test
    public void TestIsValidBST() {
        TreeNode treeNode = new TreeNode(2);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(1);
        TreeNode treeNode7 = new TreeNode(6);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        System.out.println(isValidBST(treeNode));

        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        treeNode5.left = treeNode6;
        treeNode5.right = treeNode7;
        System.out.println(isValidBST(treeNode3));
    }

    @Test
    public void testIsSymmetric() {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(3);

        TreeNode treeNodex = new TreeNode(1);
        TreeNode treeNode1x = new TreeNode(2);
        TreeNode treeNode2x = new TreeNode(2);
        TreeNode treeNode3x = new TreeNode(3);
        TreeNode treeNode4x = new TreeNode(4);
        TreeNode treeNode5x = new TreeNode(4);
        TreeNode treeNode6x = new TreeNode(3);

        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;


        treeNodex.left = treeNode1x;
        treeNodex.right = treeNode2x;
        treeNode1x.left = treeNode3x;
//        treeNode1x.right = treeNode4x;
        treeNode2x.left = treeNode5x;
//        treeNode2x.right = treeNode6x;

        System.out.println(isSymmetric(treeNode));
        System.out.println(isSymmetric(treeNodex));
    }

    @Test
    public void testLevelOrder() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        treeNode7.left = treeNode8;
        System.out.println(levelOrderDFS(treeNode1));
    }

    @Test
    public void testSortedArrayToBST() {
        int[] arr = {-10, -3, 0, 5, 9};
        System.out.println(sortTransferBST(arr));
    }
}
