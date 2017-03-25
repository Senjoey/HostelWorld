package hostelworld.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hostelworld.dao.HotelDao;
import hostelworld.dao.RecordDao;
import hostelworld.dao.RoomDao;
import hostelworld.dao.RoomSaleDao;
import hostelworld.dao.impl.HotelDaoImpl;
import hostelworld.factory.DaoFactory;
import hostelworld.logic.RecordManager;
import hostelworld.logic.UserManager;
import hostelworld.model.Hotel;
import hostelworld.model.PlanRoom;
import hostelworld.model.Room;
import hostelworld.model.RoomCart;
import hostelworld.model.RoomOrder;
import hostelworld.model.User;
import hostelworld.model.PlanRoom.RoomState;

public class RecordManagerImpl implements RecordManager {

	DaoFactory factory = new DaoFactory();
	RecordDao recordDao = factory.getRecordDao();
	RoomDao roomDao = factory.getRoomDao();
	RoomSaleDao saleDao = factory.getRoomSaleDao();

	@Override
	public RoomState getRoomState(String hotelId, String roomId, String date) {
		PlanRoom planRoom = recordDao.find(hotelId, roomId, date);
		if (planRoom == null) {
			return null;
		}
		return planRoom.getRoomState();
	}

	@Override
	public Room getRoom(String hotelId, String roomId, String date) {
		PlanRoom planRoom = recordDao.find(hotelId, roomId, date);
		Room room = roomDao.find(roomId);
		if (planRoom != null) {
			room.setPrice(planRoom.getPrice());
			return room;
		}
		return null;
	}

	@Override
	public PlanRoom modifyRoom(String hotelId, String roomId, String state, String date) {
		if (recordDao.update(hotelId, roomId, date, state)) {
			Room room = getRoom(hotelId, roomId, date);
			PlanRoom planRoom = new PlanRoom();
			planRoom.setId(roomId);
			planRoom.setPrice(room.getPrice());
			planRoom.setRoomState(state);
			return planRoom;
		}
		return null;
	}

	@Override
	public List<Room> getList(String hotelId, String date, String type) {
		List<PlanRoom> list = recordDao.find(hotelId, date);
		List<Room> rlist = roomDao.find();
		List<Room> resultlist = new ArrayList<Room>();
		if (list.isEmpty()) {
			return null;
		} else {
			for (int i = 0; i < rlist.size(); i++) {
				if (rlist.get(i).getType().toString().equals(type)) {
					resultlist.add(rlist.get(i));
				}
			}
			for (int i = 0; i < resultlist.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					if (resultlist.get(i).getId().equals((list.get(j)).getId())) {
						resultlist.get(i).setPrice(list.get(j).getPrice());
					}
				}
			}
		}
		return resultlist;
	}

	@Override
	public List<Room> getList(String hotelId, String date) {
		List<PlanRoom> list = recordDao.find(hotelId, date);
		List<Room> rList = roomDao.find();
		if (list.isEmpty()) {
			return null;
		} else {
			for (int i = 0; i < rList.size(); i++) {
				for (int j = 0; j < rList.size(); j++) {
					if (rList.get(i).getId().equals((list.get(j)).getId())) {
						rList.get(i).setPrice(list.get(i).getPrice());
					}
				}
			}
		}
		return rList;
	}

	@Override
	public RoomOrder shopping(String hotelId, RoomCart cart, String saleState, int isMember, String memberId, int isPay,
			String date, String tenantName) {
		String saleId = getSaleNum();
		RoomOrder roomOrder = new RoomOrder();
		for (int i = 0; i < cart.getPlanRooms().size(); i++) {
			// 添加一条销售记录
			PlanRoom planRoom = (PlanRoom) cart.getPlanRooms().get(i);
			roomOrder.setSaleId(saleId);
			roomOrder.setCreateDate(date);
			roomOrder.setHotelId(hotelId);
			roomOrder.setIsMemeber(isMember);
			roomOrder.setIsPay(isPay);
			roomOrder.setMemberId(memberId);
			roomOrder.setPrice(planRoom.getPrice());
			roomOrder.setRoomId(planRoom.getId());
			roomOrder.setTenantName(tenantName);
			System.out.println("add a room: " + saleId + " " + date + " " + hotelId + " " +  planRoom.getId() + " " + planRoom.getPrice() + " "+ saleState) ;
			saleDao.add(saleId, date, hotelId, planRoom.getId(), planRoom.getPrice(), saleState, date, isMember,
					memberId, isPay, tenantName);
			if (isMember == 1) {
				recordDao.update(hotelId, planRoom.getId(), date, RoomState.ordered.toString());
			} else {
				recordDao.update(hotelId, planRoom.getId(), date, RoomState.accommodated.toString());
			}
			return roomOrder;
		}
		return null;
	}

	
	public String getSaleNum() {
		String saleId = RandomID();
		RoomOrder roomOrder = saleDao.find(saleId);
		while(roomOrder != null) {
			System.out.println("The produced saleId id is " + saleId);
			saleId = RandomID();
			roomOrder = saleDao.find(saleId);
		}
		return saleId;
	}
	
	public String RandomID(){
		StringBuffer letterbuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer numbuffer = new StringBuffer("0123456789");
		StringBuffer sBuffer = new StringBuffer();
		Random random = new Random();
		int range1 = letterbuffer.length();
		int range2 = numbuffer.length();
		//开头为M，表示Member
		sBuffer.append("S");
		for(int i = 0; i < 2; i++) {
			sBuffer.append(letterbuffer.charAt(random.nextInt(range1)));
		}
		for(int i = 0; i < 4; i++) {
			sBuffer.append(numbuffer.charAt(random.nextInt(range2)));
		}
		return sBuffer.toString();
	}


	@Override
	public List<PlanRoom> salesStatistic() {
		return null;
	}

	@Override
	public boolean cancelShopping(String saleId) {
		// 找到这条销售记录
		RoomOrder roomOrder = saleDao.find(saleId);
		// 更新库存
		boolean result = recordDao.update(roomOrder.getHotelId(), roomOrder.getRoomId(), roomOrder.getCreateDate(),
				RoomState.accommodating.toString());
		// 删除该条销售记录
		if (result) {
			return saleDao.delete(saleId);
		}
		return false;
	}

	@Override
	public RoomOrder getRoomOrder(String saleId) {
		return saleDao.find(saleId);
	}
	
	@Override
	public RoomOrder getRoomOrder(String hotelId, String roomId, String date) {
		return saleDao.find(hotelId, roomId, date);
	}

	@Override
	public List<RoomOrder> getUncalculatedOrder() {
		return saleDao.find(1, 0);
	}

	@Override
	public boolean calculate(List<RoomOrder> orderList) {
		for (int i = 0; i < orderList.size(); i++) {
			RoomOrder roomOrder = orderList.get(i);
			boolean res = saleDao.update(roomOrder.getSaleId(), 1);
			if (!res) {
				return false;
			}
			// 增加客栈账户余额
			// 1找到客栈
			HotelDao hotelDao = new HotelDaoImpl();
			Hotel hotel = hotelDao.Find(roomOrder.getHotelId()).get(0);

			System.out.println("结算前Hotel " + hotel.getName() + "的余额为 " + hotel.getBalance());
			// 2修改客栈余额
			double newBalance = hotel.getBalance() + roomOrder.getPrice()*0.8;//抽取20%作为提成
			hotel.setBalance(newBalance);
			System.out.println("结算后Hotel " + hotel.getName() + "的余额为 " + hotel.getBalance());
			UserManager um = new UserManagerImpl();
			User manager = um.find("manager").get(0);
			manager.setBalance(manager.getBalance() - roomOrder.getPrice()*0.8);//减少总经理的余额
			um.updateUser(manager);
			// 3更新客栈
			if (hotelDao.Update(hotel)) {
				System.out.println("成功更新");
			} else {
				System.out.println("客栈更新失败！");
				return false;
			}

		}
		return true;
	}

	@Override
	public boolean updateTenantName(RoomOrder roomOrder) {
		return saleDao.updateTenantName(roomOrder);
	}

	@Override
	public boolean updateSaleState(RoomOrder roomOrder) {
		return saleDao.updateSaleState(roomOrder);
	}

	@Override
	public List<RoomOrder> getSaleStatistic(String hotelId) {
		return saleDao.findSaleStatistic(hotelId);
	}

}
