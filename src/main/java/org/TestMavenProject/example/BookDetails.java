package org.TestMavenProject.example;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="bookdetails")
@NamedQueries({
	@NamedQuery(name = "BookDetails.getById" , query = "SELECT u FROM BookDetails u WHERE u.bookId = :bookId"),
	@NamedQuery(name = "BookDetails.getAll" , query = "FROM BookDetails")
})
public class BookDetails implements Serializable {
	
	private static final long serialVersionUID = -3951505564221486546L;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_isbn", unique=true)
	private long bookId;
	
	@Column( name = "book_name")
	private String bookName;
	
	@Column( name = "author_name")
	private String authorName;
	
	@Column( name = "no_of_copies")
	private int noOfCopies;
	
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	public BookDetails(Long bookId, String bookName, String authorName, int noOfCopies) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.noOfCopies = noOfCopies;
	}
	public BookDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
