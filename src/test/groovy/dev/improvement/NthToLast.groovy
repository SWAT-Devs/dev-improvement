package dev.improvement

import spock.lang.*

class NthToLast extends Specification {

	public static class Node{
		int val
		Node next
		public Node(int val){
			this.val = val;
		}

		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append(val);
			Node n = next;
			while(n != null){
				sb.append(" -> " + n.val)
				n = n.next
			}
			sb.toString()
		}
	}


    @Unroll
  def test(){
	Node n1 = new Node(1);
	Node n2 = new Node(7)
	n1.next = n2
	Node n3 = new Node(2)
	n2.next = n3
	Node n4 = new Node(6)
	n3.next = n4
	Node n5 = new Node(42)
	n4.next = n5
	Node n6 = new Node(5)
	n5.next = n6
	Node n7 = new Node(21)
	n6.next = n7
	// 1 -> 7 -> 2 -> 6 -> 42 -> 5 -> 21
	// println n1

	expect:
    	getNthToLast(x, n1) == expected
    where:
		x || expected
		0 || 21
		1 || 5
		2 || 42
		6 || 1
		7 || null
  }

  public static Integer fake_getNthToLast(int n, Node root){
	  ArrayList<Integer> arr = new ArrayList<Integer>()
	  Node current = root;
	  arr.add(current.val)
	  while(current.next != null) {
		  arr.add(current.next.val)
		  current = current.next
	  }
	  if(n > arr.size()-1)
	  	return null
	  else {
		  return arr[-n-1]
	  }
  }
  
  public static Integer getNthToLast(int n, Node root) {
	  Node tailNode = root
	  int counter = 0
	  while (root.next != null) {
		  root = root.next
		  counter++
		  if (counter > n) {
			  tailNode = tailNode.next
		  }
	  }
	  if (counter < n)
	  	return null
	
	  return tailNode.val
  }
}