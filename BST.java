package bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import queue.OverflowException;

/**
 *
 * @author teraSurfer
 * @param <K> stands for Key
 * @param <V> stands for Value
 */

public class BST<K extends Comparable<K>, V> {
    
    //Defining the root element.
    private Node root;
    
    /*
    *Node class, I didn't want it to be static as i'd have to give the generics for the node again which doesn't seem right.
    *if there are any issues i'll probably change it accordingly.
    *key has the the unique key which is sorted accordingly.
    *value contains the data assigned to the key.
    *left and right are the respective nodes.
    *size is the total size of the sub tree.
    */
    private class Node{
        private K key; 
        private V value; 
        private Node left,right; 
        private int size;
        public Node(K key,V value,int size){
            this.key=key;
            this.value=value;
            this.size=size;
        }
    }
        
    public BST(){
        root = null; //making the root node null;
    }
    //Basic methods
    public int size(){
        return size(root);
    }
    
    private int size(Node node){
        if(node==null)return 0;
        else return node.size;
    }
    
    public boolean isEmpty(){
        return size()==0;
    }
    
    public boolean contains(K key){
        if(key==null) throw new IllegalArgumentException("IllegalArgumentException the key is null");
        return get(key)!=null;
    }
    /*
    Get methods.
    */
    public V get(K key){
        return get(root,key);
    }
    
    private V get(Node node, K key){
        if(key==null) throw new IllegalArgumentException("IllegalArgumentException, the key is null");
        if(node==null) return null;
        int cmp = key.compareTo(node.key);
        if(cmp<0)return get(node.left,key);
        else if (cmp>0)return get(node.right,key);
        else return node.value;
    }
    /*
    Put methods
    */
    public void put(K key,V val){
        if(key==null) throw new IllegalArgumentException("IllegalArgumentException, the key is null");
        if(val==null){
            delete(key);
            return;
        }
        root = put(root,key,val);
        //assert check();//to verify the integrity of the BST
    }
    private Node put(Node node,K key,V val){
        if(node==null) return new Node(key,val,1);
        int cmp = key.compareTo(node.key);
        if(cmp<0) node.left = put(node.left,key,val);
        if(cmp>0) node.right = put(node.right,key,val);
        else node.value = val;
        node.size = 1+ size(node.left)+size(node.right);
        return node;
    }
    public void deleteMin(){
        if(isEmpty()) throw new NoSuchElementException("Tree Underflow");
        root = deleteMin(root);
        //assert check();
    }
    private Node deleteMin(Node node){
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        //assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("called delete() with a null key");
        root = delete(root, key);
        //assert check();
    }
    private Node delete(Node x, K key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        } 
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    public K min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    } 

    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 
    public K max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    } 

    private Node max(Node x) {
        if (x.right == null) return x; 
        else                 return max(x.right); 
    }
    public K floor(K key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    } 

    private Node floor(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp <  0) return floor(x.left, key);
        Node t = floor(x.right, key); 
        if (t != null) return t;
        else return x; 
    }
    public K ceiling(K key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) { 
            Node t = ceiling(x.left, key); 
            if (t != null) return t;
            else return x; 
        } 
        return ceiling(x.right, key); 
    }
    public K select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        Node x = select(root, k);
        return x.key;
    }

    // Return key of rank k. 
    private Node select(Node x, int k) {
        if (x == null) return null; 
        int t = size(x.left); 
        if      (t > k) return select(x.left,  k); 
        else if (t < k) return select(x.right, k-t-1); 
        else            return x; 
    }
    public int rank(K key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    } 

    // Number of keys in the subtree less than key.
    private int rank(K key, Node x) {
        if (x == null) return 0; 
        int cmp = key.compareTo(x.key); 
        if      (cmp < 0) return rank(key, x.left); 
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
        else              return size(x.left); 
    }
    public int size(K lo, K hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }
    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }
    
    public Iterable<K> keys() throws OverflowException {
        return keys(min(), max());
    }
    public Iterable<K> keys(K lo, K hi) throws OverflowException {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        List<K> queue = new ArrayList<>();
        keys(root, queue, lo, hi);
        return queue;
    } 

    private void keys(Node x, List<K> queue, K lo, K hi) throws OverflowException { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key); 
        if (cmphi > 0) keys(x.right, queue, lo, hi); 
    }
    public Iterable<K> levelOrder() {
        LinkedList<K> keys = new LinkedList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node x = queue.remove();
            if (x == null) continue;
            keys.add(x.key);
            queue.add(x.left);
            queue.add(x.right);
        }
        return keys;
    }
}


