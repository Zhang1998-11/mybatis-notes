package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserText {
	private InputStream in;
	private SqlSession sqlSession;
	private IUserDao userDao;
	SqlSessionFactory factory;
	//执行初始化的操作
	@Before//只有加before注解,这个方法才会在测试类方法执行前执行
	public void init() throws Exception {
		//1.读取配置文件,生成字节输入流
		in = Resources.getResourceAsStream("SqlMapConfig.xml");
		//2.获取SqlSessionFactory
		factory =new SqlSessionFactoryBuilder().build(in);
		//3.获取SqlSession对象
		sqlSession =factory.openSession();
		//4.获取dao的代理对象
		userDao =sqlSession.getMapper(IUserDao.class);
	}
	//做释放资源的操作
	@After//after,这个方法才会在测试类方法执行后执行
	public void destroy() throws Exception {
		//提交事务
//		sqlSession.commit();
		//6.释放资源
		sqlSession.close();
		in.close();
	}
	/*
	* 测试一级缓存
	* */
	@Test
	public void testFirstLevelCache(){
		User user1=userDao.findById(41);//第一次是查询,
		System.out.println(user1);
//		sqlSession.close();
//		//再次获取SqlSession对象
//		sqlSession=factory.openSession();
		sqlSession.clearCache();//此方法可以清空缓存
		userDao=sqlSession.getMapper(IUserDao.class);
		User user2=userDao.findById(41);//第二次是从缓存中取
		System.out.println(user2);
		System.out.println(user2==user1);
	}
	/*
	* 测试缓存的同步
	* */
	public void testClearCache(){
		//1.根据id查询用户
		User user1=userDao.findById(41);//第一次是查询,
		System.out.println(user1);
		//2.更新用户信息
		user1.setUsername("update user clear cache");
		user1.setAddress("北京市");
		userDao.updateUser(user1);
		//3.再次查询id为41的用户
		User user2=userDao.findById(41);//第二次是从缓存中取
		System.out.println(user2);
		System.out.println(user2==user1);
	}

}
