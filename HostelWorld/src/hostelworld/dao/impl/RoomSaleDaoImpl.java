package hostelworld.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hostelworld.dao.DaoHelper;
import hostelworld.dao.RoomSaleDao;
import hostelworld.model.RoomOrder;
import hostelworld.model.RoomSaleState;

public class RoomSaleDaoImpl implements RoomSaleDao{
	
	public static RoomSaleDaoImpl roomSaleDao = new RoomSaleDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();
	
	public static RoomSaleDaoImpl getInstance() {
		return roomSaleDao;
	}
	@Override
	public boolean add(String saleId, String date, String hotelId, String roomId, int price, String saleState,
			String createDate, int isMember, String memberId, int isPay, String teanatName) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		try 
		{
			stmt=connection.prepareStatement("insert into roomSale(sale_id, hotel_id, room_id, price, sale_state, create_date, is_member, member_id, is_pay, tenant_name) values(?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, saleId);
			stmt.setString(2, hotelId);
			stmt.setString(3, roomId);
			stmt.setInt(4, price);
			stmt.setString(5, saleState);
			stmt.setString(6, createDate);
			stmt.setInt(7, isMember);
			stmt.setString(8, memberId);
			stmt.setInt(9, isPay);
			stmt.setString(10, teanatName);
			
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally
		{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return true;
	}
	@Override
	public boolean update(String id, String saleState, int isPay) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		try {	
			stmt=connection.prepareStatement("update roomSale set sale_state=? and isPay = ? where sale_id=?");
			stmt.setString(1, saleState);
			stmt.setInt(2, isPay);
			stmt.setString(3, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
		}
		return true;
	}
	@Override
	public List findByCreateDate(String day) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<RoomOrder> list = new ArrayList<RoomOrder>();
		try {
			stmt = connection.prepareStatement("select sale_id, hotel_id, room_id, create_date, is_member, member_id, sale_state, is_pay, price from roomSale where create_date=? group by sale_id, hotel_id, room_id, create_date, is_member, member_id, is_pay");
			stmt.setString(1, day);
			result = stmt.executeQuery();
			while(result.next()) {
				RoomOrder order = new RoomOrder();
				order.setCreateDate(result.getString("create_date"));
				order.setHotelId(result.getString("hotel_id"));
				order.setIsMemeber(result.getInt("is_member"));
				order.setMemberId(result.getString("member_id"));
				order.setRoomId(result.getString("room_id"));
				order.setSaleId(result.getString("sale_id"));
				order.setSaleState(result.getString("sale_state"));
				order.setIsPay(result.getInt("is_pay"));
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
	public List findTodayByHotel(String hotelId, String saleState, String date) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<RoomOrder> list = new ArrayList<RoomOrder>();
		try {
			stmt = connection.prepareStatement("select sale_id, hotel_id, room_id, create_date, is_member, member_id, sale_state, price from roomSale where hotel_id=? and sale_state = ? and create_date = ? group by sale_id, hotel_id, room_id, create_date, is_member, member_id");
			stmt.setString(1, hotelId);
			stmt.setString(2, saleState);
			stmt.setString(3, date);
			result = stmt.executeQuery();
			while(result.next()) {
				RoomOrder order = new RoomOrder();
				order.setCreateDate(result.getString("create_date"));
				order.setHotelId(result.getString("hotel_id"));
				order.setIsMemeber(result.getInt("is_member"));
				order.setMemberId(result.getString("member_id"));
				order.setRoomId(result.getString("room_id"));
				order.setSaleId(result.getString("sale_id"));
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
	public List findByMember(String memberId) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<RoomOrder> list = new ArrayList<RoomOrder>();
		try {
			stmt = connection.prepareStatement("select sale_id, hotel_id, room_id, create_date, is_member, member_id, sale_state, price from roomSale where member_id=?");
			stmt.setString(1, memberId);
			result = stmt.executeQuery();
			while(result.next()) {
				RoomOrder order = new RoomOrder();
				order.setCreateDate(result.getString("create_date"));
				order.setHotelId(result.getString("hotel_id"));
				order.setIsMemeber(result.getInt("is_member"));
				order.setMemberId(result.getString("member_id"));
				order.setRoomId(result.getString("room_id"));
				order.setSaleId(result.getString("sale_id"));
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
	public List statistic() {
		return null;
	}
	@Override
	public RoomOrder find(String saleId) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		RoomOrder roomOrderresult = null;
		try 
		{
			stmt=con.prepareStatement("select * from roomSale where sale_id=?");
			stmt.setString(1,saleId);
			result=stmt.executeQuery();
			while(result.next()){
				roomOrderresult = new RoomOrder();
				roomOrderresult.setCreateDate(result.getString("create_date"));
				roomOrderresult.setHotelId(result.getString("hotel_id"));
				roomOrderresult.setRoomId(result.getString("room_id"));
				roomOrderresult.setPrice(result.getInt("price"));
			}
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
			return roomOrderresult;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean delete(String saleId) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try 
		{
			stmt=con.prepareStatement("delete from roomSale where sale_id=?");
			stmt.setString(1,saleId);
			stmt.executeUpdate();
			System.out.println("成功删除销售记录一条！");
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
	@Override
	public List<RoomOrder> find(int isMember, int isPay) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<RoomOrder> list = new ArrayList<RoomOrder>();
		try {
			stmt = connection.prepareStatement("select sale_id, hotel_id, room_id, create_date, is_member, member_id, sale_state, price from roomSale where is_member = ? and is_pay = ?");
			stmt.setInt(1, isMember);
			stmt.setInt(2, isPay);
			result = stmt.executeQuery();
			while(result.next()) {
				RoomOrder order = new RoomOrder();
				order.setCreateDate(result.getString("create_date"));
				order.setHotelId(result.getString("hotel_id"));
				order.setIsMemeber(result.getInt("is_member"));
				order.setMemberId(result.getString("member_id"));
				order.setRoomId(result.getString("room_id"));
				order.setSaleId(result.getString("sale_id"));
				order.setSaleState(result.getString("sale_state"));
				order.setIsPay(isPay);
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
	public RoomOrder find(String hotelId, String roomId, String date) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null; 
		try {
			stmt = connection.prepareStatement("select sale_id, hotel_id, room_id, create_date, is_member, member_id, sale_state, price, is_pay, tenant_name from roomSale where hotel_id = ? and room_id = ? and create_date=?");
			stmt.setString(1, hotelId);
			stmt.setString(2, roomId);
			stmt.setString(3, date);
			result = stmt.executeQuery();
			while(result.next()) {
				RoomOrder order = new RoomOrder();
				order.setCreateDate(result.getString("create_date"));
				order.setHotelId(result.getString("hotel_id"));
				order.setIsMemeber(result.getInt("is_member"));
				order.setMemberId(result.getString("member_id"));
				order.setRoomId(result.getString("room_id"));
				order.setSaleId(result.getString("sale_id"));
				order.setSaleState(result.getString("sale_state"));
				order.setIsPay(result.getInt("is_pay"));
				order.setPrice(result.getInt("price"));
				order.setTenantName(result.getString("tenant_name"));
				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
 		return null;
	}
	@Override
	public boolean update(String id, int isPay) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		try {	
			stmt=connection.prepareStatement("update roomSale set is_pay = ? where sale_id=?");
			stmt.setInt(1, isPay);
			stmt.setString(2, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
		}
		return true;
	}
	@Override
	public boolean updateTenantName(RoomOrder roomOrder) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		try {	
			stmt=connection.prepareStatement("update roomSale set tenant_name = ? where sale_id=?");
			stmt.setString(1, roomOrder.getTenantName());
			System.out.println("In RoomSaleDaoImpl: " + roomOrder.getSaleId()+" " + roomOrder.getTenantName());
			stmt.setString(2, roomOrder.getSaleId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
		}
		return true;
	}
	@Override
	public boolean updateSaleState(RoomOrder roomOrder) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		try {	
			stmt=connection.prepareStatement("update roomSale set sale_state = ? where sale_id=?");
			stmt.setString(1, roomOrder.getSaleState().toString());
			stmt.setString(2, roomOrder.getSaleId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
		}
		return true;
	}
	@Override
	public List<RoomOrder> findSaleStatistic(String hotelId) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<RoomOrder> list = new ArrayList<RoomOrder>();
		try {
			stmt = connection.prepareStatement("select sale_id, hotel_id, room_id, create_date, is_member, member_id, sale_state, price, is_pay, tenant_name from roomSale where hotel_id = ?");
			stmt.setString(1, hotelId);
			result = stmt.executeQuery();
			while(result.next()) {
				RoomOrder order = new RoomOrder();
				order.setCreateDate(result.getString("create_date"));
				order.setHotelId(result.getString("hotel_id"));
				order.setIsMemeber(result.getInt("is_member"));
				order.setMemberId(result.getString("member_id"));
				order.setRoomId(result.getString("room_id"));
				order.setSaleId(result.getString("sale_id"));
				order.setSaleState(result.getString("sale_state"));
				order.setIsPay(result.getInt("is_pay"));
				order.setPrice(result.getInt("price"));
				order.setTenantName(result.getString("tenant_name"));
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
	public List<RoomOrder> findMemberOrder() {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<RoomOrder> list = new ArrayList<RoomOrder>();
		try {
			stmt = connection.prepareStatement("select sale_id, hotel_id, room_id, create_date, is_member, member_id, sale_state, price, is_pay, tenant_name from roomSale where is_member = ?");
			stmt.setInt(1, 1);
			result = stmt.executeQuery();
			while(result.next()) {
				RoomOrder order = new RoomOrder();
				order.setCreateDate(result.getString("create_date"));
				order.setHotelId(result.getString("hotel_id"));
				order.setIsMemeber(result.getInt("is_member"));
				order.setMemberId(result.getString("member_id"));
				order.setRoomId(result.getString("room_id"));
				order.setSaleId(result.getString("sale_id"));
				order.setSaleState(result.getString("sale_state"));
				order.setIsPay(result.getInt("is_pay"));
				order.setPrice(result.getInt("price"));
				order.setTenantName(result.getString("tenant_name"));
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
	public List<RoomOrder> findAccomodatedNotMemberOrder() {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<RoomOrder> list = new ArrayList<RoomOrder>();
		try {
			stmt = connection.prepareStatement("select sale_id, hotel_id, room_id, create_date, is_member, member_id, sale_state, price, is_pay, tenant_name from roomSale where is_member = ? and sale_state=?");
			stmt.setInt(1, 0);
			stmt.setString(2, RoomSaleState.sold.toString());
			result = stmt.executeQuery();
			while(result.next()) {
				RoomOrder order = new RoomOrder();
				order.setCreateDate(result.getString("create_date"));
				order.setHotelId(result.getString("hotel_id"));
				order.setIsMemeber(result.getInt("is_member"));
				order.setMemberId(result.getString("member_id"));
				order.setRoomId(result.getString("room_id"));
				order.setSaleId(result.getString("sale_id"));
				order.setSaleState(result.getString("sale_state"));
				order.setIsPay(result.getInt("is_pay"));
				order.setPrice(result.getInt("price"));
				order.setTenantName(result.getString("tenant_name"));
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
	
}
