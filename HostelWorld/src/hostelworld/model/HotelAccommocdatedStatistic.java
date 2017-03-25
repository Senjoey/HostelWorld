package hostelworld.model;

import java.io.Serializable;

public class HotelAccommocdatedStatistic implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String hotelId;
	private String hotelName;
	private int orderNum;
	public String getHotelId() {
		return hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
}
