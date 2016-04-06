package com.chenningzhang.model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Tweet {
	private int id;
	
	@NotNull
	private int userId;
	
	@NotNull
	@Size(min=1, max=1000)
	private String content;
	
	@Future
	@DateTimeFormat(style="S-")
	private Date date = new Date(new Date().getTime());
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", userId=" + userId + ", content=" + content + ", date=" + date + "]";
	}
}
