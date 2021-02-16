package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.Factory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class SecondLevelCacheTest {
	private InputStream in;
	SqlSessionFactory factory;
	//执行初始化的操作
	@Before//只有加before注解,这个方法才会在测试类方法执行前执行
	public void init() throws Exception {
		//1.读取配置文件,生成字节输入流
		in = Resources.getResourceAsStream("SqlMapConfig.xml");
		//2.获取SqlSessionFactory
		factory =new SqlSessionFactoryBuilder().build(in);

	}
	//做释放资源的操作
	@After//after,这个方法才会在测试类方法执行后执行
	public void destroy() throws Exception {

		in.close();
	}
	/*
	* 测试一级缓存
	* */
	@Test
	public void testFirstLevelCache(){
		SqlSession sqlSession1= factory.openSession();
		IUserDao dao1=sqlSession1.getMapper(IUserDao.class);
		User user1=dao1.findById(41);//第一次是查询,
		System.out.println(user1);
		sqlSession1.close();//一级缓存消失

		SqlSession sqlSession2= factory.openSession();
		IUserDao dao2=sqlSession2.getMapper(IUserDao.class);
		User user2=dao2.findById(41);//第一次是查询,
		System.out.println(user2);
		sqlSession2.close();
		System.out.println(user2==user1);
	}


}
