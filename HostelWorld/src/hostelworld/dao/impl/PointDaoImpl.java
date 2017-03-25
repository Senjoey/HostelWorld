package hostelworld.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import hostelworld.dao.DaoHelper;
import hostelworld.dao.PointDao;
import hostelworld.model.Point;

public class PointDaoImpl implements PointDao{

	public static PointDaoImpl pointDao = new PointDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();
	
	public static PointDaoImpl getInstance() {
		return pointDao;
	}
	
	@Override
	public List find(String userId) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<Point> list = new ArrayList<Point>();
		try 
		{
			stmt=connection.prepareStatement("select * from point where user_id=? order by date desc");
			stmt.setString(1, userId);
			result=stmt.executeQuery();
			while(result.next()){
				Point point = new Point();
				point.setBalance(result.getDouble("balance"));
				point.setDate(result.getString("date"));
				point.setPontisSum(result.getDouble("point_sum"));
				point.setUserId(result.getString("user_id"));
				point.setMoneySum(formatDouble(result.getDouble("money_sum")));
				
				list.add(point);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return list;
	}
	
	private double formatDouble(double num) {
		DecimalFormat df = new DecimalFormat("#.##");
		double result = Double.parseDouble(df.format(num));
		return result;
	}

	@Override
	public boolean add(String userId, String date, double pointSum, double moneySum, double balance) {
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		try 
		{
			stmt=connection.prepareStatement("insert into point(user_id, date, point_sum, money_sum, balance) values(?,?,?,?,?)");
			stmt.setString(1,userId);
			stmt.setString(2,date);
			stmt.setDouble(3, pointSum);
			stmt.setDouble(4, moneySum);
			stmt.setDouble(5,balance);
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

}
