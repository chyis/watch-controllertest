package com.jdy.collections;


/* *
 * 接口命令数据结构
 * 
 * */

public class Actions extends Object
{
	private String actiontype;
	private String appid;
	private String sign;
	private String hotelid;
	private String roomid;
	private String hoteluserid;
	private String appversion;
	private Services data;
	
	public String  getappid() {
		return appid;
	}
	
	public void  setappid(String appId) {
		appid = appId;
	}

	public String  gethoteluserid() {
		return hoteluserid;
	}
	
	public void  sethoteluserid(String hoteluserID) {
		hoteluserid = hoteluserID;
	}
	
	public String  getroomid() {
		return roomid;
	}
	
	public void  setroomid(String roomID) {
		roomid = roomID;
	}
	
	public String  gethotelid() {
		return hotelid;
	}
	
	public void  sethotelid(String hotelID) {
		hotelid = hotelID;
	}
	
	
	public String  getsign() {
		return sign;
	}
	
	public void  setsign(String Sign) {
		sign = Sign;
	}
	
	
	public String  getactiontype() {
		return actiontype;
	}
	
	public void  setactiontype(String actionType) {
		actiontype = actionType;
	}
	
	public String  getappversion() {
		return appversion;
	}
	
	public void  setappversion(String appVersion) {
		appversion = appVersion;
	}
	
	public Services getdata() {
		
		return data;
	}
	
	public void setdata(Services Data){
		
		data = Data;
	}
}