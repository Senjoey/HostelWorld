package hostelworld.logic;

import java.util.List;

import hostelworld.model.Hotel;

public interface HotelManager {
	/**
	 * 新增客栈
	 * @param hotel
	 * @return
	 */
	public boolean addHotel(Hotel hotel);
	/**
	 * 修改客栈信息
	 * @param hotel
	 * @return
	 */
	public boolean updateHotel(Hotel hotel);
	/**
	 * 生成客栈编号
	 * @return
	 */
	public String getHotelNum();
	/**
	 * 会员登录
	 * @param id
	 * @param password
	 * @return
	 */
	public String login(String id, String password);
	/**
	 * 根据id获得客栈信息
	 * @param id
	 * @return
	 */
	public List<Hotel> find(String id);
	/**
	 * 获得所有审批通过的客栈列表
	 * @return
	 */
	public List<Hotel> find();
	/**
	 * 获得所有审批不通过的客栈列表
	 * @return
	 */
	public List<Hotel> findNoApproved();
	public Hotel getHotel(String id);

}
