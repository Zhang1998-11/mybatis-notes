package com.itheima.test;

import com.itheima.dao.IRoleDao;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class RoleText {
	private InputStream in;
	private SqlSession sqlSession;
	private IRoleDao roleDao;
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
		roleDao =sqlSession.getMapper(IRoleDao.class);
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
		List<Role> roles =roleDao.findAll();
		for (Role role : roles) {
			System.out.println("----每个角色的信息");
			System.out.println(role);
			System.out.println(role.getUsers());
		}
	}


}
