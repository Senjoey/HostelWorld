package hostelworld.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.VoiceStatus;

import hostelworld.dao.DaoHelper;
import hostelworld.dao.HotelDao;
import hostelworld.model.Hotel;
import hostelworld.model.Hotel.HotelState;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

public class HotelDaoImpl implements HotelDao{
	
	private static HotelDaoImpl hotelDao = new HotelDaoImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	public static HotelDaoImpl getInstance() {
		return hotelDao;
	}

	@Override
	public List<Hotel> Find(String id) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList<Hotel> list=new ArrayList<Hotel>();
		try 
		{
			stmt=con.prepareStatement("select * from hotel where hotel_id=?");
			stmt.setString(1,id);
			result=stmt.executeQuery();
			while(result.next())
			{
				Hotel hotel = new Hotel();
				hotel.setHotelId(result.getString("hotel_id"));
				hotel.setPassword(result.getString("password"));
				hotel.setName(result.getString("name"));
				hotel.setAddress(result.getString("address"));
				hotel.setTel(result.getString("tel"));
				hotel.setBankCard(result.getString("bank_card"));
				hotel.setState(result.getString("state"));
				hotel.setBalance(result.getDouble("balance"));
				list.add(hotel);
				
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
		return list;
	}

	@Override
	public List<Hotel> Find() {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList<Hotel> list=new ArrayList<Hotel>();
		try 
		{
			stmt=con.prepareStatement("select * from hotel");
			result=stmt.executeQuery();
			while(result.next())
			{
				Hotel hotel = new Hotel();
				hotel.setState(result.getString("state"));
				hotel.setHotelId(result.getString("hotel_id"));
				hotel.setPassword(result.getString("password"));
				hotel.setName(result.getString("name"));
				hotel.setAddress(result.getString("address"));
				hotel.setTel(result.getString("tel"));
				hotel.setBankCard(result.getString("bank_card"));
				hotel.setBalance(result.getDouble("balance"));
				if (hotel.getState() == HotelState.approved) {
					list.add(hotel);
				} 
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
	public List<Hotel> FindNoApproved() {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList<Hotel> list=new ArrayList<Hotel>();
		try 
		{
			stmt=con.prepareStatement("select * from hotel");
			result=stmt.executeQuery();
			while(result.next())
			{
				Hotel hotel = new Hotel();
				hotel.setState(result.getString("state"));
				hotel.setHotelId(result.getString("hotel_id"));
				hotel.setPassword(result.getString("password"));
				hotel.setName(result.getString("name"));
				hotel.setAddress(result.getString("address"));
				hotel.setTel(result.getString("tel"));
				hotel.setBankCard(result.getString("bank_card"));
				hotel.setBalance(result.getDouble("balance"));
				if (hotel.getState() == HotelState.processed) {
					list.add(hotel);
				} 
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
		return list;
	}

	@Override
	public boolean Add(Hotel hotel) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		try {	
			stmt=con.prepareStatement("insert into hotel(hotel_id,password,name,address,tel,bank_card) values(?,?,?,?,?,?)");
			stmt.setString(1, hotel.getHotelId());
			stmt.setString(2, hotel.getPassword());
			stmt.setString(3, hotel.getName());
			stmt.setString(4, hotel.getAddress());
			stmt.setString(5, hotel.getTel());
			stmt.setString(6, hotel.getBankCard());
			stmt.executeUpdate();

		} catch (SQLException e) {
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
	public boolean Update(Hotel hotel) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		try {	
			stmt=con.prepareStatement("update hotel set password=?, name=?,address=?, tel=?, bank_card=?, state=?, balance = ? where hotel_id=?");
			stmt.setString(1, hotel.getPassword());
			stmt.setString(2, hotel.getName());
			stmt.setString(3, hotel.getAddress());
			stmt.setString(4, hotel.getTel());
			stmt.setString(5, hotel.getBankCard());
			stmt.setString(6, hotel.getState().toString());
			stmt.setDouble(7, hotel.getBalance());
			stmt.setString(8, hotel.getHotelId());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
		}
		return true;
	}
}
