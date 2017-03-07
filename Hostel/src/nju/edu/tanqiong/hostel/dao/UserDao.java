package nju.edu.tanqiong.hostel.dao;

import java.util.List;

import nju.edu.tanqiong.hostel.model.User;

public interface UserDao {
	// 用以登录时检查数据库中是否存在该用户
		public boolean checkUser(User user);
		
		// 添加用户
		public boolean addUser(User user);
		
		// 删除用户
		public boolean deleteUser(int id);
		
		// 修改用户信息
		public boolean updateUser(User user);
		
		// 查询用户
		public List<User> queryUser(User user);
		public User queryById(int id);
		
		// 查询所有用户
		public List<User> queryAll();
}
