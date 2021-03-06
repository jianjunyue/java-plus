package com.java.plus.leetcode;

import com.java.plus.leetcode.model.ListNode; 

public class ListNodeTest {

	public static void main(String[] args) {
		ListNode node = createListNode();
		print(node);
		print(reverse(node));
	}
	
	public static ListNode createListNode() {
		ListNode head = new ListNode(1);//创建头节点 
		ListNode t = head;
		for(int i=2;i<10;i++) {//创建一个简单的链表{1,2,3,4,5,...,9} 
			t.next = new ListNode(i);
			t = t.next;
			
		}
		return head;
	}
	
	public static void print(ListNode node) {
		while(node!=null) {
			System.out.print(node.val+"->");
			node=node.next;
		}
		System.out.println();
	}
	
	public static ListNode reverse(ListNode node) {
		if(node.next==null)
			return node;
		ListNode last=reverse(node.next);
		node.next.next=node;
		node.next=null;
		return last; 
	}

}
