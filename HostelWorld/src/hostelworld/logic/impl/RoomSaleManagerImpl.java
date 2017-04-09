package hostelworld.logic.impl;

import java.util.ArrayList;
import java.util.List;

import hostelworld.dao.HotelDao;
import hostelworld.dao.RoomSaleDao;
import hostelworld.factory.DaoFactory;
import hostelworld.logic.RoomSaleManager;
import hostelworld.model.Hotel;
import hostelworld.model.HotelAccommocdatedStatistic;
import hostelworld.model.RoomOrder;
import hostelworld.model.RoomSaleState;

public class RoomSaleManagerImpl implements RoomSaleManager {

	DaoFactory factory = new DaoFactory();
	RoomSaleDao roomSaleDao = factory.getRoomSaleDao();
	HotelDao hotelDao = factory.getHotelDao();

	@Override
	public int[] getAccommodatedNum() {
		int[] nums = new int[2];

		List<RoomOrder> memberOrder = roomSaleDao.findMemberOrder();
		List<RoomOrder> memberAccommodatedOrder = new ArrayList<RoomOrder>();
		for (int i = 0; i < memberOrder.size(); i++) {
			RoomOrder roomOrder = memberOrder.get(i);
			if (roomOrder.getSaleState().equals(RoomSaleState.accommodated.toString())) {
				memberAccommodatedOrder.add(roomOrder);
			}
		}
		List<RoomOrder> unmemberOrder = roomSaleDao.findAccomodatedNotMemberOrder();
		nums[0] = memberAccommodatedOrder.size();
		nums[1] = unmemberOrder.size();
		return nums;
	}

	@Override
	public List<RoomOrder> getMemebrOrderList() {
		return roomSaleDao.findMemberOrder();
	}

	@Override
	public List<RoomOrder> findAllPayedOrderList() {
		return roomSaleDao.find(1, 0);
	}

	@Override
	public List<HotelAccommocdatedStatistic> getHotelAccommocdatedStatistics() {
		List<Hotel> hotels = hotelDao.Find();
		System.out.println("共找到" + hotels.size() + "家酒店");
		ArrayList<HotelAccommocdatedStatistic> hotelAccommocdatedStatistics = new ArrayList<HotelAccommocdatedStatistic>();
		for (int i = 0; i < hotels.size(); i++) {
			HotelAccommocdatedStatistic temp = new HotelAccommocdatedStatistic();
			Hotel hotel = hotels.get(i);
			List<RoomOrder> hotelOrders = roomSaleDao.findSaleStatistic(hotel.getHotelId());
			int leftNum = 0;
			if (hotelOrders.isEmpty()) {
				temp.setOrderNum(0);
			} else {
				for (int j = 0; j < hotelOrders.size(); j++) {
					if (hotelOrders.get(j).getSaleState().equals("sold")
							|| hotelOrders.get(j).getSaleState().equals("accommodated")) {
						leftNum++;
					}
				}
				temp.setOrderNum(leftNum);
			}
			temp.setHotelId(hotel.getHotelId());
			temp.setHotelName(hotel.getName());
			hotelAccommocdatedStatistics.add(temp);
		}
		return hotelAccommocdatedStatistics;
	}

	public static void main(String[] args) {
		RoomSaleManager manager = new RoomSaleManagerImpl();
		List<HotelAccommocdatedStatistic> list = manager.getHotelAccommocdatedStatistics();
		if (list.isEmpty()) {
			System.out.println("这些酒店还木有已finished的订单诶！");
		} else {
			for (HotelAccommocdatedStatistic hotelStatistic : list) {
				System.out.println(hotelStatistic.getHotelId() + " " + hotelStatistic.getHotelName() + " "
						+ hotelStatistic.getOrderNum());
			}
		}
	}

	@Override
	public List<RoomOrder> findOrder(String hotelId) {
		return roomSaleDao.findSaleStatistic(hotelId);
	}
}
