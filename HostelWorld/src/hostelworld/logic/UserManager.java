package hostelworld.logic;

import java.util.List;

import hostelworld.model.Charge;
import hostelworld.model.Point;
import hostelworld.model.RoomOrder;
import hostelworld.model.User;

public interface UserManager {
	/**
	 * 新增会员
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);
	
	/**
	 * 修改会员信息
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);
	
	/**
	 * 生成会员编号
	 * @return
	 */
	public String getUserNum();
	
	/**
	 * 会员登录
	 * @param id
	 * @param password
	 * @return
	 */
	public String login(String id,String password, boolean isMemeber);
	
	/**
	 * 根据id获得会员信息
	 * @param id
	 * @return
	 */
	public List<User> find(String id);
	
	/**
	 * 获得所有会员列表
	 * @return
	 */
	public List<User> find();
	
	/**
	 * 根据会员id获得该会员所有充值记录
	 */
	public List<Charge> getChargeRecord(String id);
	
	/**
	 * 根据会员id获得该会员的所有积分兑换记录
	 * @param id
	 * @return
	 */
	public List<Point> getPointRecord(String id);
	
	/**
	 * 
	 * @param user
	 * @param money
	 * @return
	 */
	public boolean charge(User user, double money);
	
	/**
	 * 兑换积分
	 * @param user
	 * @param point 需要兑换的积分数
	 * @return
	 */
	public boolean point(User user, double point);
	
	
	/**
	 * 根据会员id获得该会员所有的房间订单记录
	 * @param userId
	 * @return
	 */
	public List<RoomOrder> getRoomOrder(String userId);
}
