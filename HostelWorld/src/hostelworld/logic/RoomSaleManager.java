package hostelworld.logic;

import java.util.List;

import hostelworld.model.Hotel;
import hostelworld.model.HotelAccommocdatedStatistic;
import hostelworld.model.RoomOrder;

public interface RoomSaleManager {

	/**
	 * 获得会员和非会员的入住（已离店的）总数量
	 * @return
	 */
	public int[] getAccommodatedNum();
	
	/**
	 * 获得会员所有订单情况
	 * @return
	 */
	public List<RoomOrder> getMemebrOrderList();
	
	/**
	 * 找到所有已结算的会员订单
	 * @return
	 */
	public List<RoomOrder> findAllPayedOrderList();
	
	/**
	 * 获取所有酒店的finished的订单数据（已离店的）
	 * @param
	 * @return
	 */
	public List<HotelAccommocdatedStatistic> getHotelAccommocdatedStatistics();
	
}
