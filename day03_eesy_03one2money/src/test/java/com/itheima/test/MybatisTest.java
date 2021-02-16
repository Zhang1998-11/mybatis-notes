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
import java.util.ArrayList;
import java.util.List;

/*
* 测试mybatis的crud操作* */
public class MybatisTest {
	private InputStream in;
	private SqlSession sqlSession;
	private IUserDao userDao;
	//执行初始化的操作
	@Before//只有加before注解,这个方法才会在测试类方法执行前执行
	public void init() throws Exception {
		//1.读取配置文件,生成字节输入流
		 in = Resources.getResourceAsStream("SqlMapConfig.xml");
		//2.获取SqlSessionFactory
		 SqlSessionFactory factory =new SqlSessionFactoryBuilder().build(in);
		//3.获取SqlSession对象
		 sqlSession =factory.openSession();
		//4.获取dao的代理对象
		 userDao =sqlSession.getMapper(IUserDao.class);
	}
	//做释放资源的操作
	@After//after,这个方法才会在测试类方法执行后执行
	public void destroy() throws Exception {
		//提交事务
		sqlSession.commit();
		//6.释放资源
		sqlSession.close();
		in.close();
	}

	/*
	* 测试查询所有
	* */
	@Test
	public void testFindAll() {

		//5.执行查询所有方法
		List<User> users=userDao.findAll();
		for (User user : users) {
			System.out.println(user);
		}
	}


	/*
	 * 测试查询一个方法
	 * */
	@Test
	public void testfindById() throws Exception {
		//5.执行查询一个方法
		User user = userDao.findById(48);
		System.out.println(user);
	}


}
