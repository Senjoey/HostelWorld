package hostelworld.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hostelworld.dao.DaoHelper;
import hostelworld.dao.RecordDao;
import hostelworld.model.PlanRoom;
import hostelworld.model.RoomOrder;

public class RecordDaoImpl implements RecordDao {

	public static RecordDaoImpl recordDao = new RecordDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();

	public static RecordDaoImpl getInstance() {
		return recordDao;
	}

	@Override
	public boolean add(String hotelId, String roomId, String date, String state, int price) {
		Connection con = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			stmt = con.prepareStatement(
					"insert into record(hotel_id, room_id, date, state, price) values(?, ?, ?, ?, ?)");

			stmt.setString(1, hotelId);
			stmt.setString(2, roomId);
			stmt.setString(3, date);
			stmt.setString(4, state);
			stmt.setInt(5, price);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return true;
	}

	@Override
	public PlanRoom find(String hotelId, String roomId, String date) {
		Connection con = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		PlanRoom planRoom = new PlanRoom();

		try {
			stmt = con.prepareStatement("select * from record where hotel_id=? and date=? and room_id=?");
			stmt.setString(1, hotelId);
			stmt.setString(2, date);
			stmt.setString(3, roomId);
			result = stmt.executeQuery();
			if (result.next()) {
				planRoom.setId(result.getString("room_id"));
				planRoom.setPrice(result.getInt("price"));
				planRoom.setRoomState(result.getString("state"));
				return planRoom;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return null;
	}

	@Override
	public List<PlanRoom> find(String hotelId, String date) {
		Connection con = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;

		ArrayList<PlanRoom> list = new ArrayList<PlanRoom>();
		try {
			stmt = con.prepareStatement("select * from record where hotel_id=? and date=?");
			stmt.setString(1, hotelId);
			stmt.setString(2, date);
			System.out.println("hotelId: " + hotelId + " date: " + date);
			result = stmt.executeQuery();
			if (result.next()) {
				PlanRoom room = new PlanRoom();
				room.setId(result.getString("room_id"));
				room.setPrice(result.getInt("price"));
				room.setRoomState(result.getString("state"));
				list.add(room);
				System.out.println("找到了一个房间！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return list;
	}

	@Override
	public boolean update(String hotelId, String roomId, String date, String state) {
		Connection con = daoHelper.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("update record set state=? where hotel_id=? and date=? and room_id=?");
			stmt.setString(1, state);
			stmt.setString(2, hotelId);
			stmt.setString(3, date);
			stmt.setString(4, roomId);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			return true;
		}
	}

	@Override
	public List<RoomOrder> findByMember(String memberId) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<RoomOrder> list = new ArrayList<RoomOrder>();
		try {
			stmt = connection.prepareStatement(
					"select sale_id, hotel_id, room_id, price,sale_state, create_date, is_member, member_id, is_pay from roomSale where member_id=?");
			stmt.setString(1, memberId);
			result = stmt.executeQuery();
			while (result.next()) {
				RoomOrder order = new RoomOrder();
				order.setCreateDate(result.getString("create_date"));
				order.setHotelId(result.getString("hotel_id"));
				order.setIsMemeber(result.getInt("is_member"));
				order.setIsPay(result.getInt("is_pay"));
				order.setMemberId(memberId);
				order.setRoomId(result.getString("room_id"));
				order.setSaleId(result.getString("sale_id"));
				System.out.println("Find an order and the sale_id is " + order.getSaleId());
				order.setSaleState(result.getString("sale_state"));
				order.setPrice(result.getInt("price"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return list;
	}

	@Override
	public int findAll() {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		int num = 0;
		try {
			stmt = connection.prepareStatement(
					"select * from rooSale");
			result = stmt.executeQuery();
			while (result.next()) {
				num++;
			}
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return 0;
	}

}
