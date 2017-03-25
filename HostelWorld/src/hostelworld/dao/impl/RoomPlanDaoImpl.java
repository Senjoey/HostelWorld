package hostelworld.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hostelworld.dao.DaoHelper;
import hostelworld.dao.RoomPlanDao;
import hostelworld.model.RoomPlan;

public class RoomPlanDaoImpl implements RoomPlanDao{

	public static RoomPlanDaoImpl roomPlanDao = new RoomPlanDaoImpl();
	
	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();
	
	public static RoomPlanDaoImpl getInstance() {
		return roomPlanDao;
	}
	
	@Override
	public List find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findPlanRoom(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String hotelId, String date) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		ArrayList<RoomPlan> list = new ArrayList<RoomPlan>();
		try 
		{
			stmt = connection.prepareStatement("select plan_id, hotel_id, date, create_date from roomPlan where hotel_id=? and date=? group by plan_id, hotel_id, date, create_date");
			stmt.setString(1,hotelId);
			stmt.setString(2, date);
			resultSet=stmt.executeQuery();
			while(resultSet.next()){
				RoomPlan plan = new RoomPlan();

				plan.setCreateDate(resultSet.getString("create_date"));
				plan.setDate(resultSet.getString("date"));
				plan.setHotelId(resultSet.getString("hotel_id"));
				plan.setPlanId(resultSet.getString("plan_id"));
				list.add(plan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(resultSet);
		}
		return list;
	}

	@Override
	public List findByCreateDate(String createDate) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		ArrayList<RoomPlan> list = new ArrayList<RoomPlan>();
		try 
		{
			stmt = connection.prepareStatement("select plan_id, hotel_id, date, create_date from roomPlan where create_date=? group by plan_id, hotel_id, date, create_date");
			stmt.setString(1,createDate);
			resultSet=stmt.executeQuery();
			while(resultSet.next()){
				RoomPlan plan = new RoomPlan();

				plan.setCreateDate(resultSet.getString("create_date"));
				plan.setDate(resultSet.getString("date"));
				plan.setHotelId(resultSet.getString("hotel_id"));
				plan.setPlanId(resultSet.getString("plan_id"));
				list.add(plan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(resultSet);
		}
		return list;
	}

	@Override
	public List find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(String id, String hotelId, String date, String createDate, String roomId, int price) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
		try 
		{
			stmt=connection.prepareStatement("insert into roomPlan(plan_id, hotel_id, date, create_date, room_id, price) values(?,?,?,?,?,?)");
			stmt.setString(1, id);
			stmt.setString(2, hotelId);
			stmt.setString(3, date);
			stmt.setString(4, createDate);
			stmt.setString(5, roomId);
			stmt.setInt(6, price);;
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally
		{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(resultSet);
		}
		return true;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
