package hostelworld.dao;

import java.util.List;

import hostelworld.model.PlanRoom;
import hostelworld.model.RoomOrder;

public interface RecordDao {

	public boolean add(String hotelId, String roomId, String date, String state, int price);
	public PlanRoom find(String hotelId, String roomId, String date);
	public List<PlanRoom> find(String hotelId, String date);
	public boolean update(String hotelId, String roomId, String date, String state);
	public List<RoomOrder> findByMember(String memberId);
	public int findAll();
}
