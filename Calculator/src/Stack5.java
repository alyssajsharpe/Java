//Object stacks (nodes, no arrays, dynamic)

//Alyssa Sharpe
//Lab 5 5.31.19
//Stack5.java
//
import java.util.EmptyStackException;
public class Stack5<D> {    
   
	private class Node {	//keeps track of the data (stack element)
       private D data; //D = any object
       private Node next; //element below the top 
      
       private Node (D value, Node link) {          
    	   data = value;
    	   next = link;
       }
   }
   private Node top;	//above

   public Stack5() {
	   top = null;
   }
   public void push(D value) {
	   //placing something on TOP of the stack
	   Node n = new Node(value,top);
	   n.next = top; //setting next to the top
       top = n; //setting node as last element
   }
   public D pop () throws EmptyStackException {
	   //last in, first out : taking something off the stack
	   if (!isEmpty()) {
		   D d = top.data;	//recieving data from top.data
			top = top.next;	//setting top of stack to the next item below it
			return d;	//returning value but we want it out of the stack
	   }
		else throw new EmptyStackException();
   }

   public D peek() throws EmptyStackException {
	   //returning the top pringle
	   if (!isEmpty()) {
		   D d = top.data;
			return d;
	   }
		else throw new EmptyStackException();

   }
   
   public boolean isEmpty() {
	   if(top == null) {
		   return true;
	   }
	   else 
		   return false;
   }
	private String toSR(Node n) {
		if (n == null)
			return "";
		return n.data.toString() + ";" +
			toSR(n.next);
	}
	
	public String toString() {
		return toSR(top);
	}
} 