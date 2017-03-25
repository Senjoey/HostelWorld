package hostelworld.logic.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hostelworld.dao.RecordDao;
import hostelworld.dao.RoomDao;
import hostelworld.dao.RoomPlanDao;
import hostelworld.factory.DaoFactory;
import hostelworld.logic.RoomPlanManager;
import hostelworld.model.PlanRoom;
import hostelworld.model.RoomPlan;
import hostelworld.model.PlanRoom.RoomState;

public class RoomPlanManagerImpl implements RoomPlanManager{

	DaoFactory factory = new DaoFactory();
	RoomPlanDao planDao = factory.getRoomPlanDao();
	RoomDao roomDao = factory.getRoomDao();
	RecordDao recordDao = factory.getRecordDao();
 	
	@Override
	public List<RoomPlan> getAllPlan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomPlan> getPlan(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(RoomPlan roomPlan) {
		//判断数据库中是否已有相关的计划
		List list = planDao.find(roomPlan.getHotelId(), roomPlan.getDate());
		if (list.isEmpty()) {//无相关计划
			for(int i = 0; i < roomPlan.getRooms().size(); i++) {
				PlanRoom room = roomPlan.getRooms().get(i);
				System.out.println("planid: " + roomPlan.getPlanId() + " hotelId: " + roomPlan.getHotelId() + " roomId: "+ room.getId());
				planDao.add(roomPlan.getPlanId(), roomPlan.getHotelId(), roomPlan.getDate(), roomPlan.getCreateDate(), room.getId(), room.getPrice());
				//更新库存
				recordDao.add(roomPlan.getHotelId(), room.getId(), roomPlan.getDate(), RoomState.accommodating.toString(), room.getPrice());
			}
			return true;
		} else {//已有相关计划
			return false;
		}
	}

	@Override
	public boolean update(RoomPlan roomPlan) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String GetNewId() {
		DateFormat df=new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		String s = df.format(today);
		DateFormat df2=new SimpleDateFormat("yyyy-MM-dd");
		List list = planDao.findByCreateDate(df2.format(today));
		int num = list.size()+1;
		String str = String.format("%03d", num);
		return s+str;
	}

}
