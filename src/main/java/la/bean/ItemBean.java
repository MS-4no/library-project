package la.bean;

import java.io.Serializable;

public class ItemBean implements Serializable {
    private int code;
    private int category_code;
    private String name;
    private String author;
    private String publisher;
    private String status;
    private String img;
    
    
	public ItemBean(int code,int category_code,String name, String author, String publisher, String status, String img) {
		super();
		this.code = code;
		this.category_code = category_code;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.status = status;
		this.img = img;
	}
	
	
	
	
	public ItemBean() {
		
	}




	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCategory_code() {
		return category_code;
	}
	public void setCategory_code(int category_code) {
		this.category_code = category_code;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
    
    







}

    
