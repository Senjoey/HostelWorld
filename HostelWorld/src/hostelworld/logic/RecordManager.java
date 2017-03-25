package hostelworld.logic;

import java.util.List;

import hostelworld.model.PlanRoom;
import hostelworld.model.Room;
import hostelworld.model.RoomCart;
import hostelworld.model.RoomOrder;
import hostelworld.model.PlanRoom.RoomState;

public interface RecordManager {
	
	/**
	 * 获得合战某日某房间的状态
	 * @param hotelId
	 * @param roomId
	 * @param date
	 * @return
	 */
	public RoomState getRoomState(String hotelId, String roomId, String date);
	
	/**
	 * 获得客栈某日某房间的销售信息
	 * @param hotelId
	 * @param roomId
	 * @param date
	 * @return
	 */
	public Room getRoom(String hotelId, String roomId, String date);
	
	/**
	 * 修改客栈某房间的状态
	 * @param hotelId
	 * @param roomId
	 * @param state
	 * @return
	 */
	public PlanRoom modifyRoom(String hotelId, String roomId, String state, String date);
	
	/**
	 * 获得客栈某日某类型房间信息
	 * @param hotelId
	 * @param date
	 * @param type
	 * @return
	 */
	public List<Room> getList(String hotelId, String date, String type);
	
	/**
	 * 获得客栈某日所有房间信息
	 * @param hotelId
	 * @param date
	 * @return
	 */
	public List<Room> getList(String hotelId, String date);
	
	/**
	 * 添加一条销售记录 更新库存
	 * @param hotelId
	 * @param cart
	 * @param sale_state
	 * @param isMember
	 * @param memebrId
	 * @param isPay
	 * @param date
	 * @return
	 */
	public RoomOrder shopping(String hotelId, RoomCart cart, String saleState, int isMember, String memberId, int isPay, String date,String tenantName);
	
	/**
	 * 取消预定 删除销售记录
	 * @param saleId
	 * @return
	 */
	public boolean cancelShopping(String saleId);
	
	public RoomOrder getRoomOrder(String saleId);
	
	/**
	 * 通过酒店号、房间号以及入住时间查询订单
	 * @param hotelId
	 * @param roomId
	 * @param date
	 * @return
	 */
	public RoomOrder getRoomOrder(String hotelId, String roomId, String date);
	
	
	public boolean updateTenantName(RoomOrder roomOrder);
	
	public boolean updateSaleState(RoomOrder roomOrder);
	
	/**
	 * 房间预定入住情况统计
	 * @return
	 */
	public List<PlanRoom> salesStatistic();
	
	public List<RoomOrder> getSaleStatistic(String hotelId);
	
	/**
	 * 获取所有未结算的会员订单
	 * @return
	 */
	public List<RoomOrder> getUncalculatedOrder();
	
	/**
	 * 结算
	 * @param orderList
	 * @return
	 */
	public boolean calculate(List<RoomOrder> orderList);
}
