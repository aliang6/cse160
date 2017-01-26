/**
* A class that maintains information on a book.
* This might form part of a larger application such
* as a library system, for instance.
*
* @author (Insert your name here.)
* @version (Insert today’s date here.)
*/
public class Book
{
    // The fields.
    private String author;
    private String title;
    private int pages;
    private String refNumber;
    private int borrowed;
    
    /**
    * Set the author and title fields when this object
    * is constructed.
    */
    public Book(String bookAuthor, String bookTitle, int bookPages)
    {
        author = bookAuthor;
        title = bookTitle;
        pages = bookPages;
        refNumber = "";
        borrowed = 0;
    }
    
    // Methods
    public String getAuthor()
    {
        return author;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void printAuthor()
    {
        System.out.println(author);
    }
    
    public void printTitle()
    {
        System.out.println(title);
    }
    
    //Book pages are immutable because there is no way to change the value.
    public int getPages(){
        return pages;
    }
    
    public void printDetails(){
        if (refNumber.length() == 0){
            System.out.println("Title: " + title + ", Author: " + author + ", Pages: " + pages + ", Reference Number: ZZZ");
        }
        else{
            System.out.println("Title: " + title + ", Author: " + author + ", Pages: " + pages + ", Reference Number: " + refNumber);
        }
        System.out.println("This book was borrowed " + borrowed + " times.");
    }
    
    public void setRefNumber(String ref){
        if (ref.length() < 3){
            System.out.println("Error. Reference Number needs to be at least 3 characters long.");
        }
        else{
            refNumber = ref;
        }
    }
    
    public String getRefNumber(){
        return refNumber;
    }
    
    public void borrow(){
        borrowed += 1;
    }
    
    public int getBorrowed(){
        return borrowed;
    }
}