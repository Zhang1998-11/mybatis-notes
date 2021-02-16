package com.itheima.mybatis.sqlsession.Default;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.SqlSessionFactory;

/*
* SqlSessionFactory接口的实现类
* */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
	private Configuration cfg;
	public DefaultSqlSessionFactory(Configuration cfg){
		this.cfg=cfg;
	}
	/*
	* 用于创建一个新的操作数据库对象
	* */
	@Override
	public SqlSession openSession() {
		return new DefaultSqlSession(cfg);
	}
}
