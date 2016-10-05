package q3;

/**
 * <p>A driver class that creates Book objects and tests methods 
 * of class Book.</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class Bookshelf {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //Declare three book objects using different constructor
        Book book1 = new Book();
        Book book2 = new Book("Contemporary business", 
                                "Louis E. Boone", 
                                "John Wiley & Sons Canada, Ltd.",
                                "Copyright 2013");
        Book book3 = new Book();
        
        //Print the book object info before modify.
        
        System.out.println("Initial:");
        System.out.println();
        
        System.out.println("|==Bookshelf=========================="
                + "=======================================|");
        System.out.println("|--Book1-------------|----------------"
                        + "---------------------------------------|");
        System.out.println(book1.toString());
        System.out.println("|--Book2-------------|----------------"
                        + "---------------------------------------|");
        System.out.println(book2.toString());
        System.out.println("|--Book3-------------|----------------"
                        + "---------------------------------------|");
        System.out.println(book3.toString());
        System.out.println("======================================="
                        + "=======================================");
        
        //modify book3 with setters.
        book3.setTitle("Java Software Solutions Foundations of Program Design");
        book3.setAuthor("John Lewis & William Loftus");
        book3.setPublisher("Pearson Education Limited");
        book3.setCopyrightDate("Copyright 2015");
        
        System.out.println();
        
        System.out.println("Update book3 info:");
        System.out.println();
        
        System.out.println("|==Bookshelf=========================="
                + "=======================================|");
        System.out.println("|--Book1-------------|----------------"
                        + "---------------------------------------|");
        System.out.println(book1.toString());
        System.out.println("|--Book2-------------|----------------"
                        + "---------------------------------------|");
        System.out.println(book2.toString());
        System.out.println("|--Book3-------------|----------------"
                        + "---------------------------------------|");
        System.out.println(book3.toString());
        System.out.println("======================================="
                        + "=======================================");

        System.out.println("Question three was called and ran sucessfully.");
    }
};
