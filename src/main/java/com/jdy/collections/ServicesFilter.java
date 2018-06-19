package com.jdy.collections;

/*
 * 
 * 酒店服务内容数据结构
 * 
 * */

public class ServicesFilter
{
	private int page=0;//
	private String date="";//
	private int status=-2;//
	
	public int  getstatus(){
		return status;
	}
	public void  setstatus(int statusValue){
		status = statusValue;
	}
	
	public int  getpage(){
		return page;
	}
	
	public void  setpage(int pagevalue){
		page = pagevalue;
	}
	
	
	//添加时间
	public String getdate(){
		return date;
	}
	
	public void  setdate(String datevalue){
		date = datevalue;
	}
	
}