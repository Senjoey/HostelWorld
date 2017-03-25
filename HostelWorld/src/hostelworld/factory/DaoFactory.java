package hostelworld.factory;

import hostelworld.dao.ChargeDao;
import hostelworld.dao.HotelDao;
import hostelworld.dao.RecordDao;
import hostelworld.dao.RoomDao;
import hostelworld.dao.RoomPlanDao;
import hostelworld.dao.RoomSaleDao;
import hostelworld.dao.UserDao;
import hostelworld.dao.impl.ChargeDaoImpl;
import hostelworld.dao.impl.HotelDaoImpl;
import hostelworld.dao.impl.PointDaoImpl;
import hostelworld.dao.impl.RecordDaoImpl;
import hostelworld.dao.impl.RoomDaoImpl;
import hostelworld.dao.impl.RoomPlanDaoImpl;
import hostelworld.dao.impl.RoomSaleDaoImpl;
import hostelworld.dao.impl.UserDaoImpl;

public class DaoFactory {
	public static UserDao getUserDao(){
		return UserDaoImpl.getInstance();
	}
	public static ChargeDao getChargeDao(){
		return ChargeDaoImpl.getInstance();
	}
	public static HotelDao getHotelDao(){
		return HotelDaoImpl.getInstance();
	}
	public static RoomDao getRoomDao(){
		return RoomDaoImpl.getInstance();
	}
	public static RecordDao getRecordDao(){
		return RecordDaoImpl.getInstance();
	}
	public static RoomSaleDao getRoomSaleDao(){
		return RoomSaleDaoImpl.getInstance();
	}
	public static RoomPlanDao getRoomPlanDao(){
		return RoomPlanDaoImpl.getInstance();
	}
	public static PointDaoImpl getPointDao(){
		return PointDaoImpl.getInstance();
	}
}
