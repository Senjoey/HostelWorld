package hostelworld.action;

import java.io.Serializable;
import java.util.List;

import hostelworld.model.RoomOrder;

public class MemberOrderListBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<RoomOrder> memberOrderList;

	public List<RoomOrder> getMemberOrderList() {
		return memberOrderList;
	}

	public void setMemberOrderList(List<RoomOrder> memberOrderList) {
		this.memberOrderList = memberOrderList;
	}
	
	public void setMemberOrderList(RoomOrder roomOrder, int index) {
		memberOrderList.set(index, roomOrder);
	}
	
	public RoomOrder getRoomOrder(int index) {
		return (RoomOrder)memberOrderList.get(index);
	}
}
