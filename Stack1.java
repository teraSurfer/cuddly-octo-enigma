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
public interface Stack1<T> {
    public boolean push(Object obj) throws StackOverflowException;
    public Object pop() throws StackUnderflowException;
    public Object peep(int position) throws InvalidOperationException;
    public int getSize();
}
