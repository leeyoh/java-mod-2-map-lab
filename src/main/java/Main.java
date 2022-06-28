import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Runner.start(sc);
        sc.close();
    }
}
class Runner{
    private static String title, genre;
    private static int pageNum; 

    public static void selection(Scanner sc){
        System.out.println("S = Search, A = Add, [Enter] Quit");
        switch(sc.nextLine().toLowerCase()){
            case "s":
                getBook(sc);
                break;
            case "a":
                addBook(sc);
                break;
            default:
                System.exit(0);
                return;
        }
    }

    public static void getBook(Scanner sc){
        System.out.print("Title: ");
        Library.getBook(sc.nextLine());
    }

    public static void addBook(Scanner sc){
        String[][] userPrompts = {
            {"title", "Book Title: "},
            {"genre","Book Genre: "},
            {"pageNum", "Number of Pages: "},
        };
        String input;
        for(String[] prompt: userPrompts){
            System.out.print(prompt[1]);
            input = sc.nextLine();
            if(input.equals("")){
                return;
            }
            switch(prompt[0]){
                case "title":
                    title = input;
                    break;
                case "genre":
                    genre = input;
                    break;
                case "pageNum":
                    pageNum = Integer.parseInt(input);
                    break;
            }
        }
        Book book = new Book(title,genre,pageNum);
        Library.addBook(book);
    }
    
    public static void start(Scanner sc){
        while(true){
            selection(sc);
        }
    }
}

class Library {
    static Map<String,Book> books = new HashMap<String,Book>();

    public static void addBook(Book book){

        if(books.containsKey(book.title)){
            System.out.println("Book Already Exists");
            return;
        }
  
        books.put(book.title,book);
        System.out.println("Added Book " + book);
        System.out.println("Collection: " + books);
       
    }

    public static void getBook(String title){
        try{
            System.out.println("Book Found: " + books.get(title));
        } catch (Exception e ){
            System.out.println("Book doesn't exist");
        }
    }
}

class Book {
    public String title,genre;
    private int numPages;
    public Book(String title, String genre, int numPages){
        this.title = title;
        this.genre = genre;
        this.numPages = numPages;
    }
    @Override
    public String toString(){
        return "Title: " + this.title + " Genre: " + this.genre + " Pages: " + this.numPages;
    }
}