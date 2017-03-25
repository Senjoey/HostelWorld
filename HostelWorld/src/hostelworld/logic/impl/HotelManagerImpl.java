package hostelworld.logic.impl;

import java.util.List;
import java.util.Random;

import hostelworld.dao.HotelDao;
import hostelworld.factory.DaoFactory;
import hostelworld.logic.HotelManager;
import hostelworld.model.Hotel;

public class HotelManagerImpl implements HotelManager{

	DaoFactory factoty = new DaoFactory();
	HotelDao hotelDao = factoty.getHotelDao();
	
	@Override
	public boolean addHotel(Hotel hotel) {
		return hotelDao.Add(hotel);
	}

	@Override
	public boolean updateHotel(Hotel hotel) {
		return hotelDao.Update(hotel);
	}

	@Override
	public String getHotelNum() {
		String hotelNum = randomID();
		List list = hotelDao.Find(hotelNum);
		while(!list.isEmpty()) {
			hotelNum = randomID();
			list = hotelDao.Find(hotelNum);
		}
		return hotelNum;
	}
	
	public String randomID() {
		StringBuffer letterbuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer numbuffer = new StringBuffer("0123456789");
		StringBuffer sBuffer = new StringBuffer();
		Random random = new Random();
		int range1 = letterbuffer.length();
		int range2 = numbuffer.length();
		//开头为H，表示Hotel
		sBuffer.append("H");
		for(int i = 0; i < 2; i++) {
			sBuffer.append(letterbuffer.charAt(random.nextInt(range1)));
		}
		for(int i = 0; i < 4; i++) {
			sBuffer.append(numbuffer.charAt(random.nextInt(range2)));
		}
		return sBuffer.toString();
	}

	@Override
	public String login(String id, String password) {
		List<Hotel> list = hotelDao.Find(id);
		if (list.isEmpty()) {
			return "nohotel";
		} else {
			Hotel hotelInfo = list.get(0);
			if (!password.equals(hotelInfo.getPassword())) {
				return "wrong";
			} else {//检查客栈的状态（处理中、审核不通过、审核通过）
				Hotel.HotelState hotelState = hotelInfo.getState();
				String state = hotelState.toString();
				return state;
			}
		}
	}

	@Override
	public List<Hotel> find(String id) {
		List<Hotel> list = hotelDao.Find(id);
		return list;
	}
	
	@Override
	public List<Hotel> find() {
		//找到所有审批通过的客栈
		List<Hotel> list = hotelDao.Find();
		return list;
	}
	
	@Override
	public List<Hotel> findNoApproved() {
		//找到所有带审批的客栈
		List<Hotel> list = hotelDao.FindNoApproved();
		System.out.println("找到 " + list.size() + "个待审批的客栈！");
		return list;
	}


	@Override
	public Hotel getHotel(String id) {
		List<Hotel> list = hotelDao.Find(id);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
