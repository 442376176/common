package com.zcc.entity;

import javax.persistence.Table;
import java.io.Serializable;

/**
*@author zcc
*Create on 2021-07-09 13:47:20
*/
@Table(name = "book")
public class Book implements Serializable {

	private static final long serialVersionUID = 1591790566327L;

	private Integer id;
	private String name;
	private String author;
	private String publish;
	private Integer pages;
	private Float price;
	private Integer bookcaseid;
	private Integer orderid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getBookcaseid() {
		return bookcaseid;
	}

	public void setBookcaseid(Integer bookcaseid) {
		this.bookcaseid = bookcaseid;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	 public String toString(){	
		 StringBuffer str=new StringBuffer();	
		 str.append("Book[");			
		 str.append("id=").append(id);		 
		 str.append(",name=").append(name);		 
		 str.append(",author=").append(author);		 
		 str.append(",publish=").append(publish);		 
		 str.append(",pages=").append(pages);		 
		 str.append(",price=").append(price);		 
		 str.append(",bookcaseid=").append(bookcaseid);		 
		 str.append(",orderid=").append(orderid);		 
		 str.append("]");			 
		 return str.toString();			 
	 }		 
}