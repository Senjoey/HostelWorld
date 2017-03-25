package hostelworld.model;

import java.io.Serializable;
import java.util.ArrayList;

import hostelworld.logic.HotelManager;
import hostelworld.logic.impl.HotelManagerImpl;

public class RoomOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	String saleId;
	String hotelId;
	String roomId;
	String createDate;
	String saleState;
	int isMemeber;
	String memberId;
	int isPay;
	int price;
	String tenantName;
	
	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getHotelName(String hotelID) {
		HotelManager hotelManager = new HotelManagerImpl();
		Hotel hotel = hotelManager.getHotel(hotelId);
		return hotel.getName();
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getIsPay() {
		return isPay;
	}
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}
	ArrayList<PlanRoom> rooms = new ArrayList<PlanRoom>();
	public String getSaleId() {
		return saleId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public String getRoomId() {
		return roomId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public String getSaleState() {
		return saleState;
	}
	public int getIsMemeber() {
		return isMemeber;
	}
	public String getMemberId() {
		return memberId;
	}
	public ArrayList<PlanRoom> getRooms() {
		return rooms;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void setSaleState(String saleState) {
		this.saleState = saleState;
	}
	public void setIsMemeber(int isMemeber) {
		this.isMemeber = isMemeber;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public void setRooms(ArrayList<PlanRoom> rooms) {
		this.rooms = rooms;
	}
	
	public String getSaleStateByEnglish(String str) {
		if (str.equals("ordered")) {
			return "已预订";
		} else if (str.equals("accommodated")) {
			return "已入住";
		} else {
			return "已离店";
		}
	}
	
	public String getHotelNameByHotelId(String hotelId) {
		HotelManager hotelManager = new HotelManagerImpl();
		Hotel hotel = hotelManager.find(hotelId).get(0);
		return hotel.getName();
	}
}

