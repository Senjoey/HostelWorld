package hostelworld.logic;

import java.util.List;

import hostelworld.model.Room;

public interface RoomManager {
	public List getRoomList();
	public Room getRoom(String id);
	public boolean add(Room room);
	public boolean update(Room room);
	public boolean delete(String id);
}
