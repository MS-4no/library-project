package la.bean;

import java.io.Serializable;

public class HistoryBean implements Serializable {
    private int code;
    private int customer;
    private int itemCode;
    private String name;
    private String author;
    private String publisher;
    private String orderedDate;
    private String reserveDate;
    private String checkoutDate;
    private String returnDate;
    private String status;
    
      
	public HistoryBean() {
		
	}
	


	public HistoryBean(int code, int customer, int itemCode, String name, String author, String publisher,
			String orderedDate, String reserveDate, String checkoutDate, String returnDate, String status) {
		super();
		this.code = code;
		this.customer = customer;
		this.itemCode = itemCode;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.orderedDate = orderedDate;
		this.reserveDate = reserveDate;
		this.checkoutDate = checkoutDate;
		this.returnDate = returnDate;
		this.status = status;
	}



	public HistoryBean(int code, int customer, String name, String author, String publisher, String orderedDate,
			String reserveDate, String checkoutDate, String returnDate, String status) {
		super();
		this.code = code;
		this.customer = customer;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.orderedDate = orderedDate;
		this.reserveDate = reserveDate;
		this.checkoutDate = checkoutDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	
	



	public HistoryBean(int code, int customer, String name, String author, String publisher, String orderedDate,
			String reserveDate, String checkoutDate, String returnDate) {
		super();
		this.code = code;
		this.customer = customer;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.orderedDate = orderedDate;
		this.reserveDate = reserveDate;
		this.checkoutDate = checkoutDate;
		this.returnDate = returnDate;
	}



	public HistoryBean(int code, int customer, String name, String author, String publisher, String orderedDate, String status) {
		super();
		this.code = code;
		this.customer = customer;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.orderedDate = orderedDate;
		this.status = status;
	}



	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public int getCustomer() {
		return customer;
	}


	public void setCustomer(int customer) {
		this.customer = customer;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getOrderedDate() {
		return orderedDate;
	}


	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}


	public String getReserveDate() {
		return reserveDate;
	}


	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}


	public String getCheckoutDate() {
		return checkoutDate;
	}


	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}


	public String getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getItemCode() {
		return itemCode;
	}



	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	
	

}