package org.TestMavenProject.example;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App 
{
	private SessionFactory sessionFactory;
    private Session session;
    
    public static void main( String[] args )
    {
    	App app = new App();
    	Scanner scanner = new Scanner(System.in);
    	char proceed = 'n';
    	do{
	    	System.out.println("Enter your choice : \n1:Save or Update Book Details\n2:Get Book Details by ISBN\n3:Delete Book Details\n4:Get All Book Details\n0:EXIT\n");
	    	int choice = scanner.nextInt();
	    	app.performActionOnBookDetails(choice);
	    	if(choice!=0){
		    	System.out.println("Want to do more transactions? \nenter 'y'or'Y' to proceed, any other key to abort ..:");
		    	proceed = scanner.next().charAt(0);
	    	}else{
	    		proceed = 'n';
	    	}
    	}while(proceed=='y' ||proceed=='Y');
    }
    
    public void performActionOnBookDetails(int choice){
    	try{
    		openConnection();
    		session.beginTransaction();
	    	switch(choice){
	    	case 1:
	    	{
	    		System.out.println("Saving details");
	    		BookDetails bd = getBookDetails();
	    		session.merge(bd);
		    	System.out.println("Transaction committed.!");
	    		break;
	    	}
	    	case 2:
	    	{
	    		System.out.println("Reading details");
	    		BookDetails bookDetails = getBookIsbn();
	    		Query query = session.getNamedQuery("BookDetails.getById").setParameter("bookId", bookDetails.getBookId());
	    		List<BookDetails> booksList = query.list();
	    		if(booksList.size()>0){
	    			bookDetails = booksList.get(0);
		    		printBookDetails(bookDetails);
			    	System.out.println("Transaction Successful.!");
	    		}else{
	    			System.out.println("No Such ISBN exists");
	    		}
	    		break;
	    	}
	    	case 3:
	    	{
	    		System.out.println("Deleting details");
	    		BookDetails bookDetails = getBookIsbn();
	    		BookDetails newBookDetails =  (BookDetails) session.get(BookDetails.class, bookDetails.getBookId());
	    		if(newBookDetails !=null){
		    		session.delete(newBookDetails);
			    	System.out.println("Transaction committed.!");
	    		}else{
	    			System.out.println("ISBN not found");
	    		}
	    		break;
	    	}
	    	case 4:
	    	{
	    		System.out.println("Getting all details");
	    		Query query = session.getNamedQuery("BookDetails.getAll");
	    		List<BookDetails> booksList = query.list();
	    		if(booksList.size()>0){
	    			for(BookDetails book:booksList){
	    				printBookDetails(book);
	    			}
	    		}else{
	    			System.out.println("Table is empty..!!");
	    		}
	    		break;
	    	}
	    	case 0:
	    	{
	    		System.out.println("Exiting application");
	    		hibernateConnection.closeConnection();
	    		break;
	    	}
	    	default:
	    		System.out.println("Wrong choice..Enter Again");
	    		break;
	    	}
	    	if(choice<4 &&choice>0){
		    	session.getTransaction().commit();
	    	}
    	}catch(Exception e){
    		System.out.println("Error occurred : " + e);
    	}
    }
    
    private BookDetails getBookDetails(){
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Enter values for BOok in order : ");
    	System.out.println("Book-ISBN : ");
    	Long bookId = Long.valueOf(scanner.next());
    	System.out.println("Book-Name : ");
    	String bookName = scanner.next();
    	System.out.println("Book-Author-Name : ");
    	String authorName = scanner.next();
    	System.out.println("Book-Count : ");
    	int noOfCopies = scanner.nextInt();
    	BookDetails bookDetails = new BookDetails(bookId, bookName, authorName, noOfCopies);
    	return bookDetails;
    }
    
    private BookDetails getBookIsbn(){
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Enter Book-ISBN : ");
    	Long bookId = Long.valueOf(scanner.next());
    	BookDetails bookDetails = new BookDetails();
    	bookDetails.setBookId(bookId);
    	return bookDetails;
    }
    
    private void printBookDetails(BookDetails bookDetails){
    	System.out.println("-----------------------------------------");
    	System.out.println("BookDetails are : ");
    	System.out.println("Book ISBN : " + bookDetails.getBookId());
    	System.out.println("Book Name : " + bookDetails.getBookName());
    	System.out.println("Book Author Name : " + bookDetails.getAuthorName());
    	System.out.println("Book Copies : " + bookDetails.getNoOfCopies());
    	System.out.println("-----------------------------------------");
    }
    
    public void openConnection(){
    	if(sessionFactory==null){
    		System.out.println("I am inside sessionfactory");
    		sessionFactory = hibernateConnection.getSessionFactory();
    	}
        if(session==null){
        	System.out.println("I am inside session");
        	session = sessionFactory.openSession();
        }
    }
}
