class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public static ListNode deserialize(String data) {
        
        data = data.substring(1, data.length() - 1);
        String[] values = data.split(",");
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (String value : values) {
            current.next = new ListNode(Integer.parseInt(value.trim()));
            current = current.next;
        }
        return dummy.next;
    }
}

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            current.next = new ListNode(sum % 10);
            carry = sum / 10;
            current = current.next;
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return dummy.next;
    }

    public static void main(String[] args) {
      
        Solution solution = new Solution();

       
        ListNode l1 = ListNode.deserialize("[2,4,3]");
        ListNode l2 = ListNode.deserialize("[5,6,4]");

        ListNode result1 = solution.addTwoNumbers(l1, l2);
        printLinkedList(result1); 

        ListNode l3 = ListNode.deserialize("[0]");
        ListNode l4 = ListNode.deserialize("[0]");

        ListNode result2 = solution.addTwoNumbers(l3, l4);
        printLinkedList(result2); 

        ListNode l5 = ListNode.deserialize("[9,9,9,9,9,9,9]");
        ListNode l6 = ListNode.deserialize("[9,9,9,9]");

        ListNode result3 = solution.addTwoNumbers(l5, l6);
        printLinkedList(result3); 
    }

    private static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}