package stack;

import java.io.Serializable;

/*
 * @author teraSurfer
 */
public class StackImpl <T> implements Stack1<T>, Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -783392848793617992L;
	private int size;
	private Node<T>[] node;
	private int count=0;
	/**
	 * Node Class
	 */
	private static class Node<T>{
		private T head=null;
		private Node nextNode=null;
		private Node(T head,Node nextNode){
			this.head=head;
			this.nextNode=nextNode;
		}
		static Node newNode(Object head , Node nextNode){//can't put T for head because T is not static.
			return new Node(head,nextNode);
		}
		T getHead(){
			return head;
		}
		Node getNextNode(){
			return nextNode;
		}
		void setNextNode(Node nextNode){
			this.nextNode = nextNode;
		}
		void setHead(T head){
			this.head = head;
		}
	}
	/*
	 * Constructor
	 */
	public StackImpl(int size){
		this.size=size;
		node = new Node[size];
	}
	@Override
	public boolean push(Object obj) throws StackOverflowException {
		if(count<=size){
			if((count>0)){
			node[count]=Node.newNode(obj, null);
			node[count-1].setNextNode(node[count]);
			}
			else if(count==0){				
				node[count]=Node.newNode(obj, null);				
			}
			count++;			
			return true;
		}
		else
			throw new StackOverflowException("StackOverflowException");
	}
	@Override
	public Object pop() throws StackUnderflowException {
		// TODO Auto-generated method stub
		if(count>0){
			count--;
			Object obj = node[count].getHead();
			System.out.println(node[count].getNextNode());
			//System.out.println(obj);
			node[count]=null;			
			return obj;
		}
		else
			throw new StackUnderflowException("StackUnderflowException");
	}
	@Override
	public Object peep(int position) throws InvalidOperationException {
		if((position-1<=size-1)&&(position-1>=0)){
		Object obj = node[position-1].getHead();
		return obj;
		}
		else
			throw new InvalidOperationException("Invalid Operation");
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return this.size;
	}
}
