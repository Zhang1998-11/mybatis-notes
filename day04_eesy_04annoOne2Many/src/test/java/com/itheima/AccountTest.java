package com.itheima;

import com.itheima.dao.IAccoutDao;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Account;
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

public class AccountTest {
	private InputStream in;
	private SqlSessionFactory factory;
	private SqlSession session;
	private IAccoutDao accoutDao;

	@Before
	public void init() throws Exception {
		in= Resources.getResourceAsStream("SqlMapConfig.xml");
		factory=new SqlSessionFactoryBuilder().build(in);
		session=factory.openSession();
		accoutDao=session.getMapper(IAccoutDao.class);
	}
	@After
	public void destroy() throws Exception{
		session.commit();
		session.close();
		in.close();
	}
	@Test
	public void testFindAll(){
		List<Account> accounts =accoutDao.findAll();
		for (Account account : accounts) {
			System.out.println(account);
		}
	}
	@Test
	public void testFindOne(){
		User user=accoutDao.findById(51);
		System.out.println(user);
	}
	@Test
	public  void testFindByName(){
		List<User> users =accoutDao.findUserByName("%王%");
		for (User user : users) {
			System.out.println(user);
		}
	}

}
