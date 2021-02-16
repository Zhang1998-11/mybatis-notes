package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {
	private SqlSessionFactory factory;
	public UserDaoImpl(SqlSessionFactory factory){
		this.factory=factory;
	}
	@Override
	public List<User> findAll() {
		//1.根据factory获取SqlSession对象
		SqlSession session =factory.openSession();
		//2.调用SqlSession中的方法,实现查询列表
		List<User> users=session.selectList("com.itheima.dao.IUserDao.findAll");//参数就是能获取配置信息的key
		//3.释放资源
		session.close();
		return users;
	}

	@Override
	public void saveUser(User user) {
		//1.根据factory获取SqlSession对象
		SqlSession session =factory.openSession();
		//2.调用方法实现保存
		session.insert("com.itheima.dao.IUserDao.saveUser",user);//insert要传两个参数
		//3.提交事务
		session.commit();
		//4.释放资源
		session.close();
	}

	@Override
	public void updateUser(User user) {
		//1.根据factory获取SqlSession对象
		SqlSession session =factory.openSession();
		//2.调用方法实现保存
		session.update("com.itheima.dao.IUserDao.updateUser",user);//insert要传两个参数
		//3.提交事务
		session.commit();
		//4.释放资源
		session.close();
	}

	@Override
	public void deleteUser(Integer userId) {
		//1.根据factory获取SqlSession对象
		SqlSession session =factory.openSession();
		//2.调用方法实现保存
		session.delete("com.itheima.dao.IUserDao.deleteUser",userId);//insert要传两个参数
		//3.提交事务
		session.commit();
		//4.释放资源
		session.close();
	}

	@Override
	public User findById(Integer userId) {
		//1.根据factory获取SqlSession对象
		SqlSession session =factory.openSession();
		//2.调用SqlSession中的方法,实现查询列表
		User users=session.selectOne("com.itheima.dao.IUserDao.findById",userId);//参数就是能获取配置信息的key
		//3.释放资源
		session.close();
		return users;

	}

	@Override
	public List<User> findByName(String username) {
		//1.根据factory获取SqlSession对象
		SqlSession session =factory.openSession();
		//2.调用SqlSession中的方法,实现查询列表
		List<User> users=session.selectList("com.itheima.dao.IUserDao.findByName",username);//参数就是能获取配置信息的key
		//3.释放资源
		session.close();
		return users;

	}

	@Override
	public int findTotal() {
		//1.根据factory获取SqlSession对象
		SqlSession session =factory.openSession();
		//2.调用SqlSession中的方法,实现查询列表
		Integer count=session.selectOne("com.itheima.dao.IUserDao.findTotal");//参数就是能获取配置信息的key
		//3.释放资源
		session.close();
		return count;

	}
}
