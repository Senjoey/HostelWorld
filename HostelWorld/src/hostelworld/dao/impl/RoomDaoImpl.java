package hostelworld.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hostelworld.dao.DaoHelper;
import hostelworld.dao.RoomDao;
import hostelworld.model.Room;

public class RoomDaoImpl implements RoomDao{
	private static RoomDaoImpl roomDao = new RoomDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();
	
	public static RoomDaoImpl getInstance() {
		return roomDao;
	}

	@Override
	public List<Room> find() {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList<Room> list=new ArrayList<Room>();
		try 
		{
			stmt=con.prepareStatement("select * from room");
			result=stmt.executeQuery();
			while(result.next())
			{
				Room room = new Room();
				room.setId(result.getString("room_id"));
				room.setPrice(result.getInt("price"));
				room.setType(result.getString("type"));
				list.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return list;
	}

	@Override
	public Room find(String id) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		Room room = new Room();
		try 
		{
			stmt=con.prepareStatement("select * from room where room_id=?");
			stmt.setString(1,id);
			result=stmt.executeQuery();
			while(result.next())
			{
				room.setId(result.getString("room_id"));
				room.setPrice(result.getInt("price"));
				room.setType(result.getString("type"));
				return room;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return null;
	}

	@Override
	public boolean Add(Room room) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		try {	
			stmt=con.prepareStatement("insert into room(room_id, price, type) values(?,?,?)");
			stmt.setString(1, room.getId());
			stmt.setInt(2, room.getPrice());
			stmt.setString(3, room.getType().toString());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			return true;
		}
	}

	@Override
	public boolean Update(Room room) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		try {	
			stmt=con.prepareStatement("update member set price=?, state=? where room_id=?");
			stmt.setInt(1, room.getPrice());
			stmt.setString(2, room.getType().toString());
			stmt.setString(3, room.getId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			return true;
		}
	}

	@Override
	public boolean Delete(String id) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try 
		{
			stmt=con.prepareStatement("delete from room where room_id=?");
			stmt.setString(1,id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally
		{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return true;
	}

}
