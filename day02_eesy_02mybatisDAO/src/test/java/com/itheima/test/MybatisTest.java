package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/*
* 测试mybatis的crud操作* */
public class MybatisTest {
	private InputStream in;
	private IUserDao userDao;
	//执行初始化的操作
	@Before//只有加before注解,这个方法才会在测试类方法执行前执行
	public void init() throws Exception {
		//1.读取配置文件,生成字节输入流
		 in = Resources.getResourceAsStream("SqlMapConfig.xml");
		//2.获取SqlSessionFactory
		 SqlSessionFactory factory =new SqlSessionFactoryBuilder().build(in);
		//3.使用工厂对象，创建dao对象
		 userDao =new UserDaoImpl(factory);
	}
	//做释放资源的操作
	@After//after,这个方法才会在测试类方法执行后执行
	public void destroy() throws Exception {
		//释放资源
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
	@Test
	public void testSave() throws Exception {
		User user =new User();
		user.setUsername("dao impl save");
		user.setAddress("北京");
		user.setSex("男");
		user.setBirthday(new Date());
		System.out.println("保存操作之前:"+user);//保存之前id是null
		//5.执行保存方法
		userDao.saveUser(user);
		System.out.println("保存操作之后:"+user);//保存之后得到id
	}
	/*
	* 测试更新操作
	* */
	@Test
	public void updateUser() throws Exception {
		User user =new User();
		user.setId(53);
		user.setUsername("mybatis updateUser");
		user.setAddress("北京");
		user.setSex("女");
		user.setBirthday(new Date());
		//5.执行保存方法
		userDao.updateUser(user);
	}
	/*
	 * 测试删除操作
	 * */
	@Test
	public void testDelete() throws Exception {
		//5.执行删除方法
		userDao.deleteUser(53);
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
	/*
	 * 测试模糊查询
	 * */
	@Test
	public void testfindByName() throws Exception {
		//5.执行查询一个方法
		List <User> users = userDao.findByName("%王%");
		for (User user : users) {
			System.out.println(user);
		}
	}
	/*
	 * 测试查询总记录条数
	 * */
	@Test
	public void testFindTotal() throws Exception {
		//5.执行删除方法
		int count=userDao.findTotal();
		System.out.println(count);
	}


}
