package q3;
/**
 * <p>A class keeps general information of a book, offering methods for getting 
 * and setting information of the book.
 * @author Hai Hua, Tan
 * @version 1.0
 *</p>
 */

public class Book {
    /**
     * <p>The title of the book.</p>
     */
    private String title = "";
    
    /**
     * <p>The author of the book.</p>
     */
    private String author = "";
    
    /**
     * <p>The publisher of the book.</p>
     */
    private String publisher = "";
    
    /**
     * <p>The copyright date of the book.</p>
     */
    private String copyrightDate = "";
    
    /**
     * Non-argument constructor.
     */
    Book() {
        /*
         * Assignment requires this constructor does nothing.
         */
    }
    
    /**
     * Constructor with arguments.
     * @param title the title of the book
     * @param author the author info of the book
     * @param publisher the publisher info of the book;
     * @param copyrightDate the copyright date info of the book.
     */
    Book(String title, String author, String publisher, String copyrightDate) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.copyrightDate = copyrightDate;
    }
    
    /**
     * Getter of title.
     * @return title of the book.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Setter of title.
     * @param title : String of the book title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Getter of author.
     * @return title of the book.
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Setter of title.
     * @param author sting of the author info
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * Getter of publisher.
     * @return string of publisher info
     */
    public String getPublisher() {
        return publisher;
    }
    
    /**
     * Setter of publisher.
     * @param publisher string of the publisher info.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    /**
     * Getter of copyright date.
     * @return string of copyright date info
     */
    public String getCopyrightDate() {
        return copyrightDate;
    }
    
    /**
     * Setter of copyright date.
     * @param copyrightDate string of copyright date info
     */
    public void setCopyrightDate(String copyrightDate) {
        this.copyrightDate = copyrightDate;
    }
    
    /**
     *Override toString method to return description of the class.
     *@return the description of the Book objects. 
     */
    public String toString() {
        String info = "|  Title             |  " 
                       + getTitle() 
                       + insertSpaces(getTitle())
                       + "|\n" 
                       
                       + "|  Author            |  "
                       + getAuthor() 
                       + insertSpaces(getAuthor())
                       + "|\n"
                                             
                       + "|  Publisher         |  " 
                       + getPublisher()
                       + insertSpaces(getPublisher())
                       + "|\n"
                       
                       + "|  Copyright Date    |  " 
                       + getCopyrightDate()
                       + insertSpaces(getCopyrightDate())
                       + "|";
        
        if (getTitle().length() 
            + getAuthor().length()
            + getPublisher().length()
            + getCopyrightDate().length() == 0) {
            info = "|  Empty Book        |  " 
                   + "*************"
                   + insertSpaces("*************")
                   + "|";
        }
        return info;
    }
    
    /**
     * A method to automatically insert space to keep string in fixed length.
     * @param s String need to insert space.
     * @return space string which is needed to insert to the original string.
     */
    private String insertSpaces(String s) {
        final int length = 53;
        String spaces = "";
        for (int i = 0; i < length - s.length(); i++) {
            spaces += " ";
        }
        return spaces;
        
    }

}
