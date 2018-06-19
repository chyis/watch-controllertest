package com.jdy.collections;

public class Orderdata{
	
	private String item="";
	private String area="";
	private String time="";
	private int number = 0;

	public void setitem(String itemName){
		item = itemName;
	}
	
	public String getitem(){
		return item;
	}

	public void setarea(String areaName){
		area = areaName;
	}
	
	public String getarea(){
		return area;
	}

	public void settime(String timeValue){
		time = timeValue;
	}
	
	public String gettime(){
		return time;
	}
	
	public void setnumber(int itemNumber){
		number = itemNumber;
	}
	
	public int getnumber(){
		
		return number;
	}
}