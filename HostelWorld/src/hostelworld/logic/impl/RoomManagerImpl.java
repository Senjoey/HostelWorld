package hostelworld.logic.impl;

import java.util.List;

import hostelworld.dao.RoomDao;
import hostelworld.factory.DaoFactory;
import hostelworld.logic.RoomManager;
import hostelworld.model.Room;

public class RoomManagerImpl implements RoomManager{

	DaoFactory factory = new DaoFactory();
	RoomDao roomDao = factory.getRoomDao();
	
	@Override
	public List getRoomList() {
		List<Room> list = roomDao.find();
		return list;
	}

	@Override
	public Room getRoom(String id) {
		return roomDao.find(id);
	}

	@Override
	public boolean add(Room room) {
		return roomDao.Add(room);
	}

	@Override
	public boolean update(Room room) {
		return roomDao.Update(room);
	}

	@Override
	public boolean delete(String id) {
		return roomDao.Delete(id);
	}

}
