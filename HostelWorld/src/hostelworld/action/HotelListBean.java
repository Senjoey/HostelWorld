package hostelworld.action;

import java.io.Serializable;
import java.util.List;

import hostelworld.model.Hotel;

public class HotelListBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List hotelList;

	public List getHotelList() {
		return hotelList;
	}

	public void setHotelList(List hotelList) {
		this.hotelList = hotelList;
	}
	
	public void setHotelList(Hotel hotel, int index) {
		hotelList.set(index, hotel);
	}
	
	public Hotel getHotel(int index) {
		return (Hotel)hotelList.get(index);
	}
}
