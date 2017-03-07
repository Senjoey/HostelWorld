package nju.edu.tanqiong.hostel.service;

import java.util.List;

import nju.edu.tanqiong.hostel.dao.UserDao;
import nju.edu.tanqiong.hostel.model.User;

public class UserServiceImpl implements UserService{
private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean checkUser(User user) {
		// TODO Auto-generated method stub
		if ( userDao.checkUser(user) ) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(id);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public List<User> queryUser(User user) {
		// TODO Auto-generated method stub
		return userDao.queryUser(user);
	}

	@Override
	public User queryById(int id) {
		// TODO Auto-generated method stub
		return userDao.queryById(id);
	}

	@Override
	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return userDao.queryAll();
	}
}
