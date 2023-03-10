import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class dictionaryDriver {
  public static void main(String[] args) {
    try {

      AVLTree < String > avlTree = new AVLTree < String > ();
      File file = new File("C:\\Users\\musta\\eclipse-workspace\\ICS202-Project\\src\\dictionary.txt");
      Scanner rfile = new Scanner(file);
      String st, word, meaning;
      int index = 0;
      while (rfile.hasNextLine()) {
        st = rfile.nextLine();
        index = st.indexOf(" ");
        word = st.substring(0, index);
        meaning = st.substring(index + 1);
        avlTree.insert(new WordPair(word, meaning));
      }

      menu(avlTree);
    } catch (FileNotFoundException e) {
      System.out.println("Error has occurred.");
    } catch (IOException e) {
      System.out.println("Error has occurred.");
      e.printStackTrace();
    }

  }
  public static void menu(AVLTree avlTree) {
    while (true) {
      System.out.println("Enter your choise:  ");
      System.out.println("1.	Insert a new word with its meanings\r\n" +
        "2.	Search for a word\r\n" +
        "3.	Delete a word and its meanings\r\n" +
        "4.	Modify the meanings of a word\r\n" +
        "5.	Print all words with a given prefix and their meanings\r\n" +
        "6.	Print the contents of the dictionary sorted in lexicographic order\r\n" +
        "7.	Exit\r\n" +
        "");
      Scanner input = new Scanner(System.in);
      int choice = input.nextInt();
      Scanner sc = new Scanner(System.in);

      switch (choice) {
      case 1:

        System.out.print("Enter the new word:  ");
        String nword = sc.next();
        System.out.print("Enter the meaning of the word:  ");
        sc.nextLine();
        String meaning = sc.nextLine();
        avlTree.insert(new WordPair(nword, meaning));
        break;

      case 2:

        System.out.print("Enter the word:");
        WordPair fword = avlTree.find(sc.next());
        if (fword == null) {
          System.out.println("Word not found.");
        }
        else {
          System.out.print(fword.word + " : " + fword.wordMeanings + "\n");
        }
        break;

      case 3:

        System.out.print("Enter the word: ");
        if (avlTree.delete(sc.next()) == false) {
          System.out.println("Word not found. ");
        }
        else {
          System.out.print("Deleted......");
          System.out.println(" ");
        }
        break;

      case 4:

        System.out.print("Enter the word:  ");
        String word = sc.next();
        System.out.print("Enter the new meaning: ");
        sc.nextLine();
        String nmeaning = sc.nextLine();
        avlTree.modifyWord(word, nmeaning);
        break;

      case 5:

        System.out.print("Enter the prefix: ");
        String pref = sc.next();
        avlTree.printAll(pref);
        break;

      case 6:
        avlTree.printSorted();
        break;

      case 7:
        try {
          FileWriter wfile = new FileWriter("C:\\Users\\musta\\eclipse-workspace\\ICS202-Project\\src\\dictionary.txt");
          avlTree.Close(wfile);
          wfile.close();
          System.out.println("File closed.");
          return;

        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }

        default:
          System.out.println("Enter a valid choice\n");

      }
    }
  }

}