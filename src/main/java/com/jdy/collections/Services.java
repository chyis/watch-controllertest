package com.jdy.collections;

import java.util.Calendar;
import com.jdy.collections.ServicesFilter;

/*
 * 
 * 酒店服务内容数据结构
 * 
 * */


public class Services
{
	private String hotelid;
	private String hotelname;
	private String roomid;
	private String roomname;
	private String hoteluserid;
	private int serviceid;//
	private int orderid;//
	private String servicename;
	private String add_time;//
	private int status;//
	private String orderdata;//
	private ServicesFilter filter;//
	private String appversion;
	
	public String  getappversion() {
		return appversion;
	}
	
	public void  setappversion(String appVersion) {
		appversion = appVersion;
	}
	
	public String  gethotelid(){
		return hotelid;
	}
	public void  sethotelid(String hotelID){
		hotelid = hotelID;
	}

	public String getroomid(){
		return roomid;
	}
	public void  setroomid(String roomID){
		roomid = roomID;
	}
	
	public String  gethoteluserid(){
		return hoteluserid;
	}
	public void  sethoteluserid(String userID){
		hoteluserid = userID;
	}
	
	public int  getserviceid(){
		return serviceid;
	}
	public void  setserviceid(int serviceID){
		serviceid = serviceID;
	}
	

	public int  getstatus(){
		return status;
	}
	public void  setstatus(int statusValue){
		status = statusValue;
	}

	public int  getorderid(){
		return orderid;
	}
	
	public void  setorderid(int orderID){
		orderid = orderID;
	}
	
	//添加时间
	public String  getadd_time(){
		return add_time;
	}
	
	public void  setadd_time(){
		java.util.Calendar c = Calendar.getInstance();   
		
		add_time = new String(""+c.getTime());
	}
	
	public String getorderdata(){
		
		return orderdata;
	}
	public void setorderdata(String orderData){
		
		orderdata = orderData;
	}
	

	public String getservicename(){
		
		return servicename;
	}
	public void setservicename(String serviceName){
		
		servicename = serviceName;
	}
	


	public String gethotelname(){
		
		return hotelname;
	}
	public void sethotelname(String hotelName){
		
		hotelname = hotelName;
	}
	
	
	public String getroomname(){
		
		return roomname;
	}
	public void setroomname(String roomName){
		
		roomname = roomName;
	}
	

	public ServicesFilter getfilter(){
		
		return filter;
	}
	
	public void setfilter(ServicesFilter filterObject){
		
		filter = filterObject;
	}
	
	
}