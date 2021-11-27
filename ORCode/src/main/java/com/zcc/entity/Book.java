package com.zcc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
*@author zcc
*Create on 2021-07-09 13:47:20
*/
@Table(name = "book")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}