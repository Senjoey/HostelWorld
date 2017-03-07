package nju.edu.tanqiong.hostel.service;

import java.util.List;

import nju.edu.tanqiong.hostel.model.User;

public interface UserService {
	public boolean checkUser(User user);
	public boolean addUser(User user);
	public boolean deleteUser(int id);
	public boolean updateUser(User user);
	public List<User> queryUser(User user);
	public User queryById(int id);
	public List<User> queryAll();
}
