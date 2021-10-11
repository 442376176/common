package com.zcc.entity;

import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
*@author zcc
*Create on 2021-07-15 09:03:47
*/
@Table(name = "test_user")
public class TestUser implements Serializable {

	private static final long serialVersionUID = 1592788650456L;

//	private Long id;
	private String tEmail;
	private String lastName;
	private String sName;
	private String password;
	private String userName;
	private Timestamp createTime;
	private Timestamp lastUpdateTime;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getTEmail() {
		return tEmail;
	}

	public void setTEmail(String tEmail) {
		this.tEmail = tEmail;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	 public String toString(){	
		 StringBuffer str=new StringBuffer();	
		 str.append("TestUser[");			
//		 str.append("id=").append(id);
		 str.append(",tEmail=").append(tEmail);		 
		 str.append(",lastName=").append(lastName);		 
		 str.append(",sName=").append(sName);		 
		 str.append(",password=").append(password);		 
		 str.append(",userName=").append(userName);		 
		 str.append(",createTime=").append(createTime);		 
		 str.append(",lastUpdateTime=").append(lastUpdateTime);		 
		 str.append("]");			 
		 return str.toString();			 
	 }		 
}