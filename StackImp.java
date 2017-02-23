/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author administrator
 */
public class StackImp<E> implements Stack,Serializable,Cloneable {
	
   /**
	 * 
	 */
	private static final long serialVersionUID = 7792835352678527420L;
	private int size;
   private int count=0;
   private HashMap hm = new HashMap(); 
   StackImp(int size){
       this.size = size;
   }

    @Override
    public boolean push(Object obj) throws StackOverflowException {
        if(checkCount()){
            count++;
            hm.put(count, obj);
            return true;
        }
        else
            throw new StackOverflowException("StackOverflowException");
    }

    @Override
    public Object pop() throws StackUnderflowException {
      if(count>0){
      Object obj= hm.get(count);
      count--;
      return obj;//Should throw Stack Underflow ---> Will work on it.
      }
      else
    	  throw new StackUnderflowException("StackUnderflowException");
    }

    @Override
    public int getSize() {
        return size;
    }

    
    @Override
    public Object peep(int position) throws InvalidOperationException{
    	if(hm.containsKey(position)){
    		Object obj= hm.get(position);
    		return obj;	
    	}
    	else
    		throw new InvalidOperationException("InvalidOperationException");
    }
    
    private boolean checkCount(){
        if((count>=size) && !(count<0) ){
            return false;
        }
        else
            return true;
    }
   
}
