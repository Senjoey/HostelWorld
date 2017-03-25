package hostelworld.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hostelworld.dao.RoomDao;
import hostelworld.factory.DaoFactory;

public class RoomPlan implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String planId;
	private String hotelId;
	private String date;
	private String createDate;
	ArrayList<PlanRoom> rooms = new ArrayList<PlanRoom>();
	DaoFactory factory = new DaoFactory();
	RoomDao roomDao = factory.getRoomDao();
	
	public void initRoom() {
		List<Room> roomList = roomDao.find();
		ArrayList<PlanRoom> planRoomList = new ArrayList<PlanRoom>();
		for(int i = 0; i < roomList.size(); i++) {
			PlanRoom planRoom = new PlanRoom();
			planRoom.setId(roomList.get(i).getId());
			planRoom.setPrice(roomList.get(i).getPrice());
			planRoom.setRoomState("accommodating");
			planRoom.setRoomType(roomList.get(i).getType());
			planRoomList.add(planRoom);
		}
		this.rooms = planRoomList;
	}
	
	public void initToday(){
		DateFormat df=new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		String s = df.format(today);
		this.createDate = s;
	}

	public String getPlanId() {
		return planId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public String getDate() {
		return date;
	}

	public String getCreateDate() {
		return createDate;
	}

	public ArrayList<PlanRoom> getRooms() {
		return rooms;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setRooms(ArrayList<PlanRoom> rooms) {
		this.rooms = rooms;
	}

}
