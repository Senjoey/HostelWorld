package hostelworld.action;

import java.io.Serializable;
import java.util.List;

import hostelworld.model.RoomOrder;

public class RoomOrderListBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List roomOrderList;
	
	public List getRoomOrderList() {
		return roomOrderList;
	}
	
	public void setRoomOrderList(List roomOrderList) {
		this.roomOrderList = roomOrderList;
	}
	
	public void setRoomOrderList(RoomOrder roomOrder, int index) {
		roomOrderList.set(index, roomOrder);
	}
	
	public RoomOrder getRoomOrder(int index) {
		return (RoomOrder) roomOrderList.get(index);
	}
}
