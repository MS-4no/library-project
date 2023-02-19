package la.bean;

import java.io.Serializable;

public class ReviewBean implements Serializable {
	private int code;
	private int item_code;
	private String item_name;
	private String content;
	private int custome;
	private String review_date;
	private int review_score;
	
	
	
	
	public ReviewBean(int code, int item_code, String item_name, String content, int custome, String review_date,
			int review_score) {
		super();
		this.code = code;
		this.item_code = item_code;
		this.item_name = item_name;
		this.content = content;
		this.custome = custome;
		this.review_date = review_date;
		this.review_score = review_score;
	}

	public ReviewBean(int code, int item_code, String content, int custome, String review_date, int review_score) {
		super();
		this.code = code;
		this.item_code = item_code;
		this.content = content;
		this.custome = custome;
		this.review_date = review_date;
		this.review_score = review_score;
	}

	public ReviewBean(int code, int item_code, String content, int review_score) {
		this.code = code;
		this.item_code = item_code;
		this.content = content;
		this.review_score = review_score;
	}
	
	public ReviewBean(int item_code, String content, String review_date, int review_score) {
		this.item_code = item_code;
		this.content = content;
		this.review_date = review_date;
		this.review_score = review_score;
	}

	public ReviewBean(int code, int item_code, String content) {
		this.code = code;
		this.item_code = item_code;
		this.content = content;
	}
	

	public ReviewBean(int custome, String review_date, int review_score) {
		this.custome = custome;
		this.review_date = review_date;
		this.review_score = review_score;
	}

	public ReviewBean(int code, int item_code, String content, String review_date, int review_score) {
		this.code = code;
		this.item_code = item_code;
		this.content = content;
		this.review_date = review_date;
		this.review_score = review_score;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getItem_code() {
		return item_code;
	}

	public void setItem_code(int item_code) {
		this.item_code = item_code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCustome() {
		return custome;
	}

	public void setCustome(int custome) {
		this.custome = custome;
	}

	public String getReview_date() {
		return review_date;
	}

	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}

	public int getReview_score() {
		return review_score;
	}

	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
}