
import java.util.*;
public class BST<T extends Comparable<T>> { 
    protected WordPair<T> root;  
    public BST() {
    }

    public BST(WordPair root) {
         this.root = root;
    }

    public void purge() {
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }
    public boolean insertBST(WordPair el) {
        if(!search(el)){
        WordPair<T> p = root, prev = null;  
        while (p != null) {  // find a place for inserting new node;
            prev = p;
			int result = el.word.compareTo(p.word);
			if(result == 0)
				throw new IllegalArgumentException("Duplicate key.");
            else if (result < 0)
                 p = p.left;
            else 
                 p = p.right;
        }
        if (root == null){    // tree is empty;
             root = new WordPair<T>((T)el.word,(T)el.wordMeanings);
             return true;
             }
        else if (el.word.compareTo(prev.word) < 0){
        	prev.left  = new WordPair<T>((T)el.word,(T)el.wordMeanings);
        	return true;
        	}
        else if (el.word.compareTo(prev.word) > 0){
        	prev.right = new WordPair<T>((T)el.word,(T)el.wordMeanings);
        	return true;
        	}
        else{
        	System.out.println("will not be ADDED  ");
        	return false;
        	} 

    }
        else{System.out.println(" This word is already added, it will not be ADDED twice");
        return false;
        }
        
    }
    public boolean search(T el) {
    	return search(new WordPair(el, "  "));
    	}
    public boolean search(WordPair el) {               
        WordPair<T> p = root;
        while (p != null){

            if (el.word.equals(p.word)){
                 System.out.print(p.word+" : "+p.wordMeanings + "  ");
                 return  true;                     
                   
            }
            else if (el.word.compareTo(p.word) < 0){
                 p = p.left;
    }
            else {
                 p = p.right;}
        }
        return false;                  
    }
    
    public void deleteByCopying(T el) {
        WordPair<T> node, p = root, prev = null;
        while (p != null && !p.word.equals(el)) {  // find the node p
             prev = p;                           // with element el;
             if (el.compareTo(p.word) < 0)
                  p = p.left;
             else 
                  p = p.right;
        }
        node = p;
        if (p != null && p.word.equals(el)) {
             if (node.right == null)             // node has no right child;
                  node = node.left;
             else if (node.left == null)         // no left child for node;
                  node = node.right;
             else {
                  WordPair<T> tmp = node.left;    // node has both children;
                  WordPair<T> previous = node;    // 1.
                  while (tmp.right != null) {    // 2. find the rightmost
                      previous = tmp;            //    position in the
                      tmp = tmp.right;           //    left subtree of node;
                  }
                  node.word = tmp.word;              // 3. overwrite the reference
                                                 //    to the element being deleted;
                  if (previous == node)          // if node's left child's
                      previous.left  = tmp.left; // right subtree is null;
                 else 
                      previous.right = tmp.left; // 4.
             }
             if (p == root)
                  root = node;
             else if (prev.left == p)
                  prev.left = node;
             else prev.right = node;
        }
        else if (root != null)
             throw new java.util.NoSuchElementException("el " + el + " is not in the tree");
        else 
            throw new UnsupportedOperationException("the tree is empty");
    }

  }