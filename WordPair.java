public class WordPair  <T extends Comparable<T>> {
            protected T word;
            protected T  wordMeanings;

     protected WordPair<T> left, right; 
      

     public WordPair() {
        left = right = null;
    }
     public WordPair(WordPair E) {
        left = right = null;
    }      
    public WordPair(T word ,T wordMeanings ) {
        this(word,wordMeanings,null,null);
    }

    public WordPair(T word,T wordMeanings, WordPair<T> left, WordPair<T> right) {
        this.word = word; 
        this.wordMeanings = wordMeanings; 
        this.left = left; 
        this.right = right;
    }
} 