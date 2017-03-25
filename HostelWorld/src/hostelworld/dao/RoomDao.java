package hostelworld.dao;

import java.util.List;

import hostelworld.model.Room;

public interface RoomDao{

	public List<Room> find();
	public Room find(String id);
	public boolean Add(Room room);
	public boolean Update(Room room);
	public boolean Delete(String id);
}
