package hostelworld.model;

import java.io.Serializable;

import hostelworld.model.Room.RoomType;

public class PlanRoom implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private int price;
	private RoomState roomState;
	private RoomType roomType;
	
	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public enum RoomState{
		accommodating, ordered, accommodated, left
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

	public RoomState getRoomState() {
		return roomState;
	}

	public void setRoomState(String s) {
		if (s.equals("accommodating")) {
			roomState = RoomState.accommodating;
		}
		else if (s.equals("ordered")) {
			roomState = RoomState.ordered;
		}
		else if (s.equals("accommodated")) {
			roomState = RoomState.accommodated;
		} else {
			roomState = RoomState.left;
		}
	}
	
}
