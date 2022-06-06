package com.zcc.algorithm.middle_algorithm;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.algorithm.middle_algorithm
 * @author: zcc
 * @date: 2022/5/23 14:09
 * @version:
 * @Describe:
 */
public class LinkedListTest {


    /**
     * Definition for singly-linked list.
     */
    static class Solution {
        static class ListNode {
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
        }

        public static ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode oddNumH = head;
            ListNode evenNumH = head.next;
            ListNode oddTemp = oddNumH;
            ListNode evenTemp = evenNumH;
            while (oddTemp.next != null && evenTemp.next != null) {
                oddTemp.next = oddTemp.next.next;
                evenTemp.next = evenTemp.next.next;
                oddTemp = oddTemp.next;
                evenTemp = evenTemp.next;
            }
            oddTemp.next = evenNumH;
            return oddNumH;
        }

        public static void main(String[] args) {
            ListNode l1 = new ListNode(1);
            ListNode l2 = new ListNode(2);
            ListNode l3 = new ListNode(3);
            ListNode l4 = new ListNode(4);
//            ListNode l5 = new ListNode(5);
            l1.next = l2;
            l2.next = l3;
            l3.next = l4;
//            l4.next = l5;
            ListNode listNode = oddEvenList(l1);
            System.out.println(listNode);
        }
    }

}
