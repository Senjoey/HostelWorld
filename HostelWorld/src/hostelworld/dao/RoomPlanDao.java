package hostelworld.dao;

import java.util.List;

public interface RoomPlanDao {

	public List find();
	public List findPlanRoom(String id);
	public List find(String hotelId, String date);
	public List findByCreateDate(String createDate);
	public List find(String id);
	public boolean add(String id, String hotelId, String date, String createDate, String roomId, int price);
	public boolean delete(String id);
}
