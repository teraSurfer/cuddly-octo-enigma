package linkedlist;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
	
	/**
	 * Defining Class Variables
	 */
	private Node<T> head;
	/**
	 * Empty List Constructor
	 */
	public LinkedList(){
		head = null;
	}
	
	public boolean isEmpty(){
		return head==null;
	}
	
	public void addFirst(T obj){
		head = new Node(obj,head);
	}
	
	public T getFirst(){
		T obj = (head==null)?null:head.data;//This should throw exception. Will add the exception classes soon. NoSuchElementException.
		return obj;
	}
	
	public void add(T obj){
		if(head==null){
			addFirst(obj);
		}
		else{
			Node<T> node = head;
			while(node.nextNode!=null)node=node.nextNode;
			node.nextNode = new Node(obj,null);
		}
	}
	public T getLast(){
		if(head==null) return null;//Again should throw exception. Will add them soon. NoSuchElementException.
		else{
			Node<T> node = head;
			while(node.nextNode!=null)node=node.nextNode;
			return node.data;
		}
	}
	public T get(int position){
		if(head==null) return null;//IndexOutOfBoundsException.
		Node<T> node =head;
		for(int c=0;c<position;c++)node=node.nextNode;
		if(node==null) return null;//IndexOutOfBoundsException.
		return node.data;
	}
	/**
	 * Deletes all elements! Caution!!
	 */
	public void clear(){
		head=null;
	}
	public boolean contains(T element){
		if(head==null)return false;
		for(T tmp:this){
			if(tmp.equals(element))return true;
		}
		return false;
	}
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		for(Object obj:this){
			stringBuilder.append(obj+" ");
		}
		return stringBuilder.toString();
	}
	public void insertAfter(T key, T obj){
		Node<T> node=head;
		while(node!=null &&!node.data.equals(key))node=node.nextNode;
		if(node!=null)
			node.nextNode = new Node(obj,node.nextNode);
	}
	public void insertBefore(T key,T obj){
		if(head==null)return;//EmptyListException? or something else... Not too sure.
		if(head.data.equals(key)){ addFirst(obj); return;}
		Node<T> prev = null;
		Node<T> node = head;
		while(node!=null && !node.data.equals(key)){
			prev=node;
			node=node.nextNode;
		}
		if(node!=null){
			prev.nextNode = new Node(obj,node);
		}	
	}
	/**
	 * 
	 * Three types of copy-->Deep Copy O(n)
	 *  -->Deep Copy O(n^2)
	 *  -->Deep Copy O(n) reverse.
	 *  -->Reverse Method.
	 */
	/*public LinkedList<T> copy(){
		if(head==null)return null;//Empty List.
		else{
			Node<T> node = head;
			LinkedList<T> copyList = new LinkedList<>();
			while(node.nextNode!=null){
				
			}
		}
	*/

	
	public void remove(T key){
		if(head==null)return;
		if(head.data.equals(key)){head=head.nextNode; return;}
		Node<T> prev = null;
		Node<T> node = head;
		while(node!=null && !node.data.equals(key)){
			prev = node;
			node = node.nextNode;
		}
		if(node==null)return;
		//deleting the current node.
		prev.nextNode=node.nextNode;
		
	}
		
	/**
	 * Node Class --> changed constructor to public as a private constructor was not needed.
	 */
	private static class Node<T>{
		private T data;
		private Node<T> nextNode;
		
		public Node(T data, Node<T> nextNode){
			this.data = data;
			this.nextNode=nextNode;
		}
	} 
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator();
	}
	
			
	/**
	 * Iterator Class. 
	 */
	
	private class LinkedListIterator implements Iterator<T>{
		private Node<T> nextNode;
		
		public LinkedListIterator(){
			nextNode = head;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return nextNode!=null;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(!hasNext())return null;//Should throw NoSuchElementException
			T obj = nextNode.data;
			nextNode = nextNode.nextNode;
			return obj;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			//Will add soon.
		}
		
	}

}
