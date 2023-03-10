
import java.io.FileWriter;
import java.io.IOException;

public class AVLTree<T extends Comparable<T>> extends BST<T> {
	
   protected int height;
	
	public AVLTree() {
		super();
		height = -1;
	}
	
	public AVLTree(WordPair<T> root) {
		super(root);
		height = -1;
	}

public boolean isEmpty() {
            return root == null;
    }

public int getSize(){
        return getSize(root.left)+getSize(root.right);
        }

private int getSize(WordPair node){
        if(node!=null){return 1+getSize(node.left)+getSize(node.right);}
        return 0;
        }

        
public WordPair find(String word){
    WordPair<T> proot = root;
        while (proot != null){

            if (word.equals(proot.word)){

                 return  proot;                     
                    
            }else if (word.compareTo((String) proot.word) < 0){
                 proot = proot.left;
    }
            else {
                 proot = proot.right;}
        }
        return null;          
    
    }


   
public boolean insert(WordPair el){
      if(insertBST(el)==true){
    	  this.balance();
    	  return true;
    	  }
      else {
    	  return false;
    	  }
    }
  
public boolean delete(String word) {
    	if(find(word)==null){
    		return false;
    		}
        else{
        	super.deleteByCopying((T)word);
        	this.balance();
        	return true;
        	}
    	}

public boolean modifyWord(String word, String newMeanings){
       WordPair fword =find(word);
       if(fword!=null){
    	   fword.wordMeanings=newMeanings;
       return true ;
       }
       return false;
}

public void   printAll( String prefix){
    printAll(root,prefix);
}

private void   printAll(WordPair proot, String prefix){
    if(proot!=null){
    	String a = (String) proot.word;
    	if(a.startsWith(prefix)) {
    		System.out.println(proot.word+" : "+proot.wordMeanings);
    		printAll(proot.left, prefix);
    		printAll(proot.right, prefix);
    	}
    	else {
    		printAll(proot.left, prefix);
    		printAll(proot.right, prefix);
    	}
}
    }

    public void printSorted(){
        inorderTraversalPrint(root);
        }


        void inorderTraversalPrint(WordPair node) { 
                if (node == null) 
                    return; 
                inorderTraversalPrint(node.left); 
                visit(node); 
                inorderTraversalPrint(node.right); 
            }

        protected void visit(WordPair<T> p) {
                System.out.println(p.word + " : " + p.wordMeanings);
            }
        
   void Close(FileWriter myWriter) throws IOException {
                 Close(root,myWriter);
                 myWriter.close();
            }
   void Close(WordPair<T> proot ,FileWriter myWriter) throws IOException {
       if(proot!=null){
         Close(proot.left,myWriter);
         Close(proot.right,myWriter);
         myWriter.write(proot.word  +" "+ proot.wordMeanings);
         myWriter.write("\n");
       }
       return ;
   }
        

 
    public int getHeight() {
		return getHeight(root);
	}
	
   protected int getHeight(WordPair<T> node) {
      if(node == null)
         return -1;
      else
         return 1 + Math.max(getHeight(node.left), getHeight(node.right));
   }
	
   protected AVLTree<T> getLeftAVL() {
      AVLTree<T> leftsubtree = new AVLTree<T> (root.left);
      return leftsubtree;
   }

   protected AVLTree<T> getRightAVL() {
      AVLTree<T> rightsubtree = new AVLTree<T> (root.right);
      return rightsubtree;
    }
    
   protected int getBalanceFactor() {
      if(isEmpty())
         return 0;
      else
         return getRightAVL().getHeight() - getLeftAVL().getHeight();
    }
    
 
    
    
    protected void balance()
    {
      if(!isEmpty())
      {
         getLeftAVL().balance();
    	   getRightAVL().balance();

         adjustHeight();
        
         int balanceFactor = getBalanceFactor();
        
         if(balanceFactor == -2) {

			
			
			if(getRightAVL().getBalanceFactor() == 0 && getLeftAVL().getBalanceFactor() == -1)    /// special case
				  rotateRight();                                
            else if(getLeftAVL().getBalanceFactor() <= 0)     
			      rotateRight();
            else
               rotateLeftRight();
         }
		
         else if(balanceFactor == 2) {

			
			if(getRightAVL().getBalanceFactor() == 0)         
				  rotateLeft();                                
            else if(getRightAVL().getBalanceFactor() > 0)   
               rotateLeft();
            else
               rotateRightLeft();
         }
      }
   }
    
   protected void adjustHeight()
   {
      if(isEmpty())
         height = -1;
      else
         height = 1 + Math.max(getLeftAVL().getHeight(), getRightAVL().getHeight());   
   }
    
   protected void rotateRight() {
	
      WordPair<T> tempNode = root.right;
      root.right = root.left;
      root.left = root.right.left;
      root.right.left = root.right.right;
      root.right.right = tempNode;
            
      T valw = (T) root.word;
      T valm = (T) root.wordMeanings;
      
      root.word = root.right.word;
      root.right.word = valw;
      root.wordMeanings = root.right.wordMeanings;
      root.right.wordMeanings = valm;
            
      getRightAVL().adjustHeight();
      adjustHeight();
   }
    
   protected void rotateLeft() {
      WordPair<T> tempNode = root.left;
      root.left = root.right;
      root.right = root.left.right;
      root.left.right = root.left.left;
      root.left.left = tempNode;
            
      T valw = (T) root.word;
      T valm = (T) root.wordMeanings;
      
      root.word = root.left.word;
      root.left.word = valw;
            
      root.wordMeanings = root.left.wordMeanings;
      root.left.wordMeanings = valm;
            
      getLeftAVL().adjustHeight();
      adjustHeight();
	}
	
	protected void rotateLeftRight()
   {

 	  getLeftAVL().rotateLeft();
      getLeftAVL().adjustHeight();
      this.rotateRight();
      this.adjustHeight();
  }

   protected void rotateRightLeft()
   {
      // to be completed by students
	   getRightAVL().rotateRight();
	   getRightAVL().adjustHeight();
	   this.rotateLeft();
	   this.adjustHeight();
	  
   }
   
      
}