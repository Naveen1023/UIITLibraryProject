package application;

public class Users {
	private int accNo;
	private String BookName;
	private String author;
	private String availability;
	
	public Users(int accNo, String BookName, String author, String availability){
		this.accNo =accNo;
		this.BookName= BookName;
		this.author=author;
		this.availability =availability;
	}
	
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
}
