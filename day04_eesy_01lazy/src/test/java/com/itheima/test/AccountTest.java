package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
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
	private SqlSession sqlSession;
	private IAccountDao accountDao;
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
		accountDao =sqlSession.getMapper(IAccountDao.class);
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
	@Test
	public void testFindAll(){
		List<Account> accounts=accountDao.findAll();
		for (Account account : accounts) {
			System.out.println("---------每个account的信息-------------");
			System.out.println(account);
			System.out.println(account.getUser());
		}
	}

}
