/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;

/**
 *
 * @author administrator
 */
public class Client {
    public static void main(String[] arg) throws StackOverflowException{
        Stack1 st = new StackImpl(3);
        String x="Hello!";
        String y="Hi!";
        String z="How are you?";
        //String w="This will fail";
        //Push
        boolean s= st.push(z);
        boolean i= st.push(x);
        boolean j= st.push(y);
        //boolean fail=st.push(w);
       System.out.println(s+" "+i+" "+j);
        //Peep
        try{
        	String s1 = (String)st.peep(1);
        	String s2 = (String)st.peep(2);
        	String s3 = (String)st.peep(3);
        	//String s4 = (String)st.peep(4);
        	System.out.println(s1+" "+s2+" "+s3);      	
        }catch(InvalidOperationException ioe){
        	ioe.printStackTrace();
        }
        //Pop
        try{
        String s1 = (String)st.pop();
        String s2 = (String)st.pop();
        String s3 = (String)st.pop();
        //String s4 = (String)st.pop();
        System.out.println(s1+" "+s2+" "+s3);
        }catch(StackUnderflowException sue){
        	sue.printStackTrace();
        }
        
        
    }
}
