package hostelworld.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Record implements Serializable{

	private static final long serialVersionUID = 1L;
	String storeId;
	String date;
	ArrayList<PlanRoom> rooms = new ArrayList<PlanRoom>();
	
	public String getStoreId() {
		return storeId;
	}
	public String getDate() {
		return date;
	}
	public ArrayList<PlanRoom> getRooms() {
		return rooms;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setRooms(ArrayList<PlanRoom> rooms) {
		this.rooms = rooms;
	}
	
	
}
