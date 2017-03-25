package hostelworld.logic.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import hostelworld.dao.ChargeDao;
import hostelworld.dao.PointDao;
import hostelworld.dao.RecordDao;
import hostelworld.dao.UserDao;
import hostelworld.factory.DaoFactory;
import hostelworld.logic.UserManager;
import hostelworld.model.Charge;
import hostelworld.model.Point;
import hostelworld.model.RoomOrder;
import hostelworld.model.User;

public class UserManagerImpl implements UserManager{
	
	DaoFactory factory = new DaoFactory();
	UserDao userDao = factory.getUserDao();
	ChargeDao chargeDao = factory.getChargeDao();
	PointDao pointDao = factory.getPointDao();
	RecordDao recordDao = factory.getRecordDao();
	
	public boolean addUser(User user) {
		return userDao.Add(user);
	}
	
	public boolean updateUser(User user){
		return userDao.Update(user);
	}
	
	public String login(String id,String password, boolean isMemeber){
		List<User> list = userDao.Find(id);
		if(list.isEmpty())//用户不存在
			return "nouser";
		else{//用户存在
			User userinfo = list.get(0);
			if(!password.equals(userinfo.getPassword()))//密码错误
				return "wrong";
			else{//密码正确
				if (!isMemeber) {
					return "true";
				}
				String state;
				try {//获取账户状态 并提醒用户
					state = checkState(userinfo).toString();
					return state;
				} catch (ParseException e) {
					e.printStackTrace();
					return "wrong";
				}
			}
		}
	}
	
	public User.UserState checkState(User user) throws ParseException{
		User.UserState state = user.getState();
		//账户为激活状态，比较当前日期和有效期，若已过有效期
		//查看余额，余额小于10，设置状态为暂停状态，设置暂停日期（将原有效期加上一年）；余额大于等于10，设置有效期（将原有效期增加一年）
		if(state==User.UserState.active) {
			//当前日期
			Date todayDate = new Date();
			//原来的有效期
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date validDate = dateFormat.parse(user.getDate());
			//有效期加上一年
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(validDate);
			calendar.add(Calendar.YEAR, 1);
			Date newValidDay = calendar.getTime();
			if(todayDate.getTime() >= validDate.getTime()) {
				if(user.getBalance()<10.0) {
					user.setState("paused");
				}
				user.setDate(dateFormat.format(newValidDay));
				//反应到数据库
				userDao.Update(user);
			} 	
		}
		//账户为暂停状态，比较当前日期和有效期，若已过有效期，设置账户为停止状态
		if(state==User.UserState.paused) {
			//当前日期
			Date todayDate = new Date();
			//原来的有效期
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date validDate = dateFormat.parse(user.getDate());
			if(todayDate.getTime() >= validDate.getTime()) {
				user.setState("stopped");
				//反应到数据库
				userDao.Update(user);
			} 
		}
		
		return user.getState();
	}
	

	public String getUserNum() {
		String userNum = RandomID();
		List list = userDao.Find(userNum);
		while(!list.isEmpty()){
			userNum = RandomID();
			list = userDao.Find(userNum);
		}
		return userNum;
	}
	
	public String RandomID(){
		StringBuffer letterbuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer numbuffer = new StringBuffer("0123456789");
		StringBuffer sBuffer = new StringBuffer();
		Random random = new Random();
		int range1 = letterbuffer.length();
		int range2 = numbuffer.length();
		//开头为M，表示Member
		sBuffer.append("M");
		for(int i = 0; i < 2; i++) {
			sBuffer.append(letterbuffer.charAt(random.nextInt(range1)));
		}
		for(int i = 0; i < 4; i++) {
			sBuffer.append(numbuffer.charAt(random.nextInt(range2)));
		}
		return sBuffer.toString();
	}

	public List<User> find(String id) {
		List list = userDao.Find(id);
		return list;
	}

	public List<Charge> getChargeRecord(String id) {
		List list = chargeDao.find(id);
		return list;
	}
	
	@Override
	public List<Point> getPointRecord(String id) {
		List list = pointDao.find(id);
		return list;
	}

	public boolean charge(User user, double money) {//充值增加积分，充多少钱得多少积分
//		DateFormat df1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String date = df.format(now);
		double balance = user.getBalance()+money;
		int charge = (int) (user.getCharge()+money);
		boolean result=chargeDao.add(user.getId(), date, money, balance);
		if(result){
			boolean inactiveToActive = user.getState()==User.UserState.inactive && balance >= 1000;
			boolean pauseToActive = user.getState()==User.UserState.paused && balance >= 10;
			if(inactiveToActive || pauseToActive){
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(now);
				calendar.add(Calendar.YEAR, 1);
				Date validday = calendar.getTime();
				user.setState("active");
				user.setDate(df.format(validday));
				
			}
			user.setBalance(balance);
			System.out.println("Before charge the user's charge is" + user.getCharge());
			user.setCharge(charge);
			user.setLevel();
			System.out.println("After charge the user's level is " + user.getLevel() );
			
			System.out.println("After charge the uset's charge is " + user.getCharge());
			double currentPoint = user.getPoint();
			double afterChargePoint = currentPoint + money;
			user.setPoint(afterChargePoint);
			System.out.println("before charge the point is: " + currentPoint + " and after charge the point is " + afterChargePoint);
			result = updateUser(user);
		}
		return result;
	}
	
	
	
	@Override
	public List<RoomOrder> getRoomOrder(String userId) {
		List<RoomOrder> list = recordDao.findByMember(userId);
		return list;
	}

	@Override
	public List<User> find() {
		List list = userDao.Find();
		return list;
	}

	@Override
	public boolean point(User user, double point) {
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String date = df.format(now);
		String userId = user.getId();
		double currentPoint = user.getPoint();
		double balance = user.getBalance();//增加余额，不是charge
		double pointMoney = 0;
		int userLevel = user.getLevel();
		//根据会员等级计算折算后的钱
		if (userLevel == 1) {
			pointMoney = 0.1 * point;
		} else if (userLevel == 2) {
			 pointMoney = 0.15 * point;
		} else {
			 pointMoney = 0.2 * point;
		}
		//增加会员卡的余额
		double afterPointBalance = balance + pointMoney;
		//减少原来的积分
		double afterPoint = currentPoint - point;
		user.setBalance(afterPointBalance);
		user.setPoint(afterPoint);
		boolean result = pointDao.add(userId, date, point, pointMoney, afterPointBalance);
		//同步数据库
		if (result) {
			return updateUser(user);
			
		}
		return false;
	}
}
