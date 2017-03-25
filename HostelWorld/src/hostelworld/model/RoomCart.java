package hostelworld.model;

import java.io.Serializable;
import java.util.ArrayList;

import hostelworld.model.PlanRoom.RoomState;

public class RoomCart implements Serializable{

	private static final long serialVersionUID = 1L;

	ArrayList<PlanRoom> planRooms;
	ArrayList<RoomOrder> roomOrders;
	public ArrayList<RoomOrder> getRoomOrders() {
		return roomOrders;
	}

	public void setRoomOrders(ArrayList<RoomOrder> roomOrders) {
		this.roomOrders = roomOrders;
	}

	int total;
	boolean hidePayPart;
	boolean hideEditTenantName;
	public boolean isHideEditTenantName() {
		return hideEditTenantName;
	}

	public void setHideEditTenantName(boolean hideEditTenantName) {
		this.hideEditTenantName = hideEditTenantName;
	}

	public boolean isHidePayPart() {
		return hidePayPart;
	}

	public void setHidePayPart(boolean hidePayPart) {
		this.hidePayPart = hidePayPart;
	}

	
	
	public ArrayList<PlanRoom> getPlanRooms() {
		return planRooms;
	}

	public int getTotal() {
		return total;
	}

	public void setPlanRooms(ArrayList<PlanRoom> planRooms) {
		this.planRooms = planRooms;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void init() {
		planRooms = new ArrayList<PlanRoom>();
		roomOrders = new ArrayList<RoomOrder>();
		total = 0;
		hidePayPart = true;
		hideEditTenantName = true;
	}
	
	public void add(Room room, RoomState state) {
		boolean find = false;
		for(int i = 0; i < planRooms.size(); i++) {
			if (planRooms.get(i).getId().equals(room.getId())) {
				find = true;
			}
		}
		if (!find) {
			PlanRoom planRoom = new PlanRoom();
			planRoom.setId(room.getId());
			planRoom.setPrice(room.getPrice());
			planRoom.setRoomState(state.toString());
			planRooms.add(planRoom);
			total += room.getPrice();
		}
	}
	
	public void addRoomOrder(RoomOrder roomOrder) {
		roomOrders.add(roomOrder);
	}
	
	public void delete(Room room) {
		total = 0;
		for (int i = 0; i < planRooms.size(); i++) {
			if (planRooms.get(i).getId().equals(room.getId())) {
				planRooms.remove(i);
			}
		}
		for(int i = 0; i < planRooms.size(); i++) {
			total = total + planRooms.get(i).getPrice();
		}
	}
	
	public void deleteRoomOrder(RoomOrder roomOrder) {
		roomOrders.remove(0);
	}
	
	public void modify(PlanRoom planRoom) {
		for(int i = 0; i < planRooms.size(); i++) {
			PlanRoom originalRoom = planRooms.get(i);
			if (originalRoom.getId().equals(planRoom.getId())) {
				originalRoom.setRoomState(planRoom.getRoomState().toString());
			}
		}
	}
	
	public void modify(RoomOrder roomOrder) {
		
	}
}
