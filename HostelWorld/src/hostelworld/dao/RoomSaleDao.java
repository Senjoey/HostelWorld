package hostelworld.dao;

import java.util.List;

import hostelworld.model.RoomOrder;

public interface RoomSaleDao {

	/**
	 * 添加一条销售记录
	 * @param saleId
	 * @param date
	 * @param hotelId
	 * @param roomId
	 * @param price
	 * @param saleState
	 * @param createDate
	 * @param isMember
	 * @param memberId
	 * @param isPay
	 * @param tenantName
	 * @return
	 */
	public boolean add(String saleId, String date, String hotelId, String roomId, int price, String saleState, String createDate, int isMember, String memberId, int isPay, String tenantName);
	
	/**
	 * 通过销售记录编号寻找销售记录
	 * @param saleId
	 * @return
	 */
	public RoomOrder find(String saleId);
	
	/**
	 * 通过酒店号、房间号、时间查找销售订单
	 * @param hotelId
	 * @param roomId
	 * @param date
	 * @return
	 */
	public RoomOrder find(String hotelId, String roomId, String date);
	
	/**
	 * 找到所有未结算的会员订单
	 * @param isMember
	 * @param isPay
	 * @return
	 */
	public List<RoomOrder> find(int isMember, int isPay);
	
	/**
	 * 更新订单的结算状态
	 * @param id
	 * @param isPay
	 * @return
	 */
	public boolean update(String id, int isPay);
	
	public boolean delete(String saleId);
	
	/**
	 * 修改销售记录的销售状态（如会员将订单取消）
	 * @param id
	 * @param saleState
	 * @return
	 */
	public boolean update(String id, String saleState, int isPay);
	
	public boolean updateTenantName(RoomOrder roomOrder);
	
	public boolean updateSaleState(RoomOrder roomOrder);
	
	/**
	 * 通过酒店id查找销售订单
	 * @param hotelId
	 * @return
	 */
	public List<RoomOrder> findSaleStatistic(String hotelId);
	
	/**
	 * 找到所有会员相关的订单
	 * @return
	 */
	public List<RoomOrder> findMemberOrder();
	
	/**
	 * 找到所有非会员入住的订单
	 * @return
	 */
	public List<RoomOrder> findAccomodatedNotMemberOrder();

	public List findByCreateDate(String day);
	
	public List findTodayByHotel(String hotelId, String saleState, String date);
	
	public List findByMember(String memberId);
	
	public List statistic();
}
