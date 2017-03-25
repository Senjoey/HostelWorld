package hostelworld.model;

import java.io.Serializable;

public class Hotel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public enum HotelState {
		approved, processed, disapproved;
	}
	
	private String hotelId;
	private String password;
	private String name;
	private String address;
	private String tel;
	private String bankCard;
	private double balance;
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	HotelState state;
	
	public void init() {
		this.state = HotelState.processed;
	}
	
	public void setState(String s) {
		if (s.equals("approved")) {
			this.state = HotelState.approved;
		} 
		if (s.equals("processed")) {
			this.state = HotelState.processed;
		} 
		if (s.equals("disapproved")) {
			this.state = HotelState.disapproved;
		}
	}
	
	public HotelState getState() {
		 return this.state;
	}
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
}
