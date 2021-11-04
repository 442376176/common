package com.zcc.algorithm.primary_algorithm;

import org.junit.Test;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/10/13 14:35
 */
public class LinkedListTest {


    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public int length(ListNode node) {
        int i = 1;
        while (node.next != null) {
            i++;
            node = node.next;
        }
        return i;
    }
/**
 * Definition for singly-linked list.
 * */

    /**
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
     * <p>
     *  
     * <p>
     * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
     * <p>
     * <p>
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：head = [4,5,1,9], node = 5
     * 输出：[4,1,9]
     * 解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2：
     * <p>
     * 输入：head = [4,5,1,9], node = 1
     * 输出：[4,5,9]
     * 解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *  
     * <p>
     * 提示：
     * <p>
     * 链表至少包含两个节点。
     * 链表中所有节点的值都是唯一的。
     * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     * 不要从你的函数中返回任何结果。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnarn7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param node
     */

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;

    }

    /**
     * 删除链表的倒数第N个节点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <p>
     * 进阶：你能尝试使用一趟扫描实现吗？
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * 示例 2：
     * <p>
     * 输入：head = [1], n = 1
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn2925/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 普通
        int length = length(head) - n;
        if (length == 0)
            return head.next;
        ListNode temp = head;
        for (int i = 0; i < length - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return head;

    }

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     * <p>
     * 输入：head = [1,2]
     * 输出：[2,1]
     * <p>
     * 输入：head = []
     * 输出：[]
     * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        // 1.头插(空间效率极低)
        if (head == null || head.next == null) return head;
        ListNode listNode = new ListNode(head.val);
        head = head.next;
        while (head.next != null) {
            ListNode temp = head.next;
            // 插入
            head.next = listNode;
            listNode = head;
            // 移位
            head = temp;
        }
        ListNode res = new ListNode(head.val, listNode);
        return res;
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * 示例 2：
     * <p>
     * 输入：l1 = [], l2 = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     *  
     * <p>
     * 提示：
     * <p>
     * 两个链表的节点数目范围是 [0, 50]
     * -100 <= Node.val <= 100
     * l1 和 l2 均按 非递减顺序 排列
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnnbp2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 尾插
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode res = null;
        // 定义头结点
        if (l1.val - l2.val > 0) {
            res = l2;
            l2 = l2.next;
        } else {
            res = l1;
            l1 = l1.next;
        }
        ListNode resNode = res;
        while (l1 != null && l2 != null) {

            if (l1.val - l2.val > 0) {
                res.next = l2;
                l2 = l2.next;
            } else {
                res.next = l1;
                l1 = l1.next;
            }
            res = res.next;
        }
        if (l1 == null) {
            res.next = l2;
        }
        if (l2 == null) {
            res.next = l1;
        }
        return resNode;
    }



    /**
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
//         自己的时间效率一般  空间效率较高
        ListNode temp = head;
        int length = length(head);
        if (length==1) return true;
        for (int i = 0; i <length/2 ; i++) {
            temp = temp.next;
        }
        temp = length%2==0?temp:temp.next;
        ListNode listNode = reverse(temp);
        while(listNode!=null) {
            if (head.val == listNode.val) {
                head = head.next;
                listNode = listNode.next;
                continue;
            }
            return false;

//        return true;
//        public boolean isPalindrome(ListNode head) {
//            ListNode fast = head, slow = head;
//            //通过快慢指针找到中点
//            while (fast != null && fast.next != null) {
//                fast = fast.next.next;
//                slow = slow.next;
//            }
//            //如果fast不为空，说明链表的长度是奇数个
//            if (fast != null) {
//                slow = slow.next;
//            }
//            //反转后半部分链表
//            slow = reverse(slow);
//
//            fast = head;
//            while (slow != null) {
//                //然后比较，判断节点值是否相等
//                if (fast.val != slow.val)
//                    return false;
//                fast = fast.next;
//                slow = slow.next;
//            }
//            return true;
//        }
//
        }
        return true;
    }

    /**
     * 环形链表
     * 给定一个链表，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     *
     *  输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     * 输入：head = [1,2], pos = 0
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     *
     * 输入：head = [1], pos = -1
     * 输出：false
     * 解释：链表中没有环。
     *
     * 链表中节点的数目范围是 [0, 104]
     * -105 <= Node.val <= 105
     * pos 为 -1 或者链表中的一个 有效索引 。
     *
     * 进阶：
     *
     * 你能用 O(1)（即，常量）内存解决此问题吗？
     *
     *  
     *
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnwzei/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head==null ||head.next==null)   return false;
        if (head.next == head) return true;
        ListNode reverse = reverseList(head);
        if (reverse==head) return true;
        else return false;
    }
    /**
     * 反序表
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    /**
     * 添加测试用例
     * @param arr
     * @return
     */
    public ListNode addTestDemo(int[] arr ){
        ListNode head = new ListNode(arr[0]);
        ListNode resNode = head;
        for (int i = 1; i < arr.length; i++) {
            head.next = new ListNode(arr[i]);
            head = head.next;
        }
        return resNode;
    }

    @Test
    public void testHasCycle(){
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(2);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        System.out.println(hasCycle(node));
    }


    @Test
    public void testisPalindrome() {
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(2);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        System.out.println(isPalindrome(node));
        node4.next = node5;
        node5.next = node6;
        System.out.println(isPalindrome(node4));
        int[] s = {8,0,7,1,7,7,9,7,5,2,9,1,7,3,7,0,6,5,1,7,7,9,3,8,1,5,7,7,8,4,0,9,3,7,3,4,5,7,4,8,8,5,8,9,8,5,8,8,4,7,5,4,3,7,3,9,0,4,8,7,7,5,1,8,3,9,7,7,1,5,6,0,7,3,7,1,9,2,5,7,9,7,7,1,7,0,8};
        ListNode listNode = addTestDemo(s);
        System.out.println(isPalindrome(listNode));

//        System.out.println(s.length);
    }

    @Test
    public void testMergeTwoLists() {
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);

        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);

        node.next = node1;
        node1.next = node2;

        node3.next = node4;
        node4.next = node5;

        ListNode listNode = mergeTwoLists(node, node3);
        System.out.println(listNode);
    }

    @Test
    public void testReverseList() {
        ListNode node = new ListNode(9);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(5);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode listNode = reverseList(node);
        System.out.println(listNode);
    }

    @Test
    public void testDelete() {
        ListNode node = new ListNode(9);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(5);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        deleteNode(node);
        System.out.println(node1.next);
    }

    @Test
    public void demo(){
        ListNode node = new ListNode(1);
        node.next = node;
        while(node.next!=null){
            System.out.println(node.val);
            node = node.next;
        }
    }
}


