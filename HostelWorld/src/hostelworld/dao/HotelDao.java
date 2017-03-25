package hostelworld.dao;

import java.util.List;

import hostelworld.model.Hotel;

public interface HotelDao {
	
	public List<Hotel> Find(String id);
	public List<Hotel> Find();
	public List<Hotel> FindNoApproved();
	public boolean Add(Hotel hotel);
	public boolean Update(Hotel hotel);
	
}
