package hostelworld.model;

import java.io.Serializable;

public class Room implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private int price;
	private RoomType type;
	
	public enum RoomType {
		bigBedroom, standardRoom, businessRoom
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(String s) {
		if (s.equals("bigBedroom")) {
			this.type = RoomType.bigBedroom;
		}
		if (s.equals("standardRoom")) {
			this.type = RoomType.standardRoom;
		}
		if (s.equals("businessRoom")) {
			this.type = RoomType.businessRoom;
		}
	}
}
