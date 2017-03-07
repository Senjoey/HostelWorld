package nju.edu.tanqiong.hostel.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import nju.edu.tanqiong.hostel.model.User;

public class UserDaoImpl implements UserDao{
private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 登录时，检查用户是否存在于数据库中
		@Override
		public boolean checkUser(User user) {
			// TODO Auto-generated method stub
			// 开启 session
			Session session = sessionFactory.openSession();
			
			// 开启事务
			session.beginTransaction();
			
			// 查询语句
			Query query = session.createQuery(" from User u where u.id=:id and u.name=:name");
			
			// 设定查询语句中变量的值
			query.setParameter("id", user.getId());
			query.setParameter("name", user.getName());
			
			// 查询结果
			User u = (User)query.uniqueResult();
			
			// 事务提交并关闭 session
			session.getTransaction().commit();
			session.close();
			
			// 查询结果不为 null，说明存在则返回 true
			if (null != u ) {
				return true;
			}
			
			return false;
		}
		
		// 增加用户
		@Override
		public boolean addUser(User user) {
			// TODO Auto-generated method stub
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			session.close();
			return true;
		}

	    // 删除用户
		@Override
		public boolean deleteUser(int id) {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			// 通过 id 找到该 user
			User u = (User)session.get(User.class, id);
			session.delete(u);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		
		// 更新用户信息
		@Override
		public boolean updateUser(User user) {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
			session.close();
			return true;
		}

		// 用户查询，用于在网页上根据不同条件的查询
	    //（这里可以根据 name 或 company 查询，在后面的 JSP 页面中会体现）
		@Override
		public List<User> queryUser(User user) {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			// 使用 StringBuffer 便于后面根据不同条件，连接查询语句
			StringBuffer hq = new StringBuffer(" from User u where 1=1");
			
			// 参数的值的集合
			ArrayList<String> params = new ArrayList<String>();
	        
	        // name 不为空，则在查询语句中连接 name 查询条件
	        // 并添加 name 的值，便于后面查询
			if (user.getName() != null && !"".equals(user.getName())) {
				hq.append(" and u.name=?");
				params.add(user.getName());
			}
			
			// 同上
			if (user.getCompany() != null && !"".equals(user.getCompany())) {
				hq.append(" and u.company=?");
				params.add(user.getCompany());
			}
			
			// 根据参数的数量，设定查询条件的个数
			Query query = session.createQuery(hq.toString());
			for (int i = 0; i < params.size(); i++) {
				query.setString(i, params.get(i));
			}
			// 查询结果
			List<User> users = query.list();
			session.getTransaction().commit();
			session.close();
			return users;
		}
		// 通过 id 查找用户
		@Override
		public User queryById(int id) {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			User u = (User)session.get(User.class, id);
			session.getTransaction().commit();
			session.close();
			return u;
		}

	    // 查找所有用户
		@Override
		public List<User> queryAll() {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(" from User u");
			List<User> users = query.list();
			session.getTransaction().commit();
			session.close();
			return users;
		}
}
