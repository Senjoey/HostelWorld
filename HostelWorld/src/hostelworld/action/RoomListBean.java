package hostelworld.action;

import java.io.Serializable;
import java.util.List;

import hostelworld.model.Room;

public class RoomListBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List roomList;

	public List getRoomList() {
		return roomList;
	}

	public void setRoomList(List roomList) {
		this.roomList = roomList;
	}
	
	public void setRoomList(Room room, int index) {
		roomList.set(index, room);
	}
	
	public Room getRoom(int index) {
		return (Room)roomList.get(index);
	}
}
