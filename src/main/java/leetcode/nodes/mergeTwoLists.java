package leetcode.nodes;


import javax.swing.*;
import java.util.List;

public class mergeTwoLists {

    public static void main(String[] args) {
        print(mergeTwoLists(createNode1(), createNode2()));
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        ListNode rslHead;
        if (l1.val >= l2.val) {
            rslHead = l1;
            l1 = l1.next;
        } else  {
            rslHead = l2;
            l2 = l2.next;
        }
        ListNode rslCurrent = rslHead;
        while (l1 != null && l2 != null) {
            ListNode tsm = null;
            if (l1.val <= l2.val) {
                rslCurrent.next = l1;
                rslCurrent = l1;
                l1 = l1.next;
            } else {
                rslCurrent.next = l2;
                rslCurrent = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            rslCurrent.next = l1;
        } else if (l2 != null) {
            rslCurrent.next = l2;
        }
        return rslHead;
    }

    public static ListNode createNode1() {
        return new ListNode(1, new ListNode(2, new ListNode(4)));
    }

    public static ListNode createNode2() {
        return new ListNode(1, new ListNode(3, new ListNode(4)));
    }

    public static void print(ListNode node) {
        while (node != null) {
            System.out.println("===> " + node.val + " <===");
            node = node.next;
        }
    }
}



