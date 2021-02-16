package com.itheima.mybatis.sqlsession.Default;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.proxy.MapperProxy;

import java.lang.reflect.Proxy;

/*
* SqlSession接口的实现类
* */
public class DefaultSqlSession implements SqlSession {
	private Configuration cfg;
	public DefaultSqlSession(Configuration cfg){
		this.cfg=cfg;
	}
	/*
	* 用于创建代理对象
	* */
	@Override
	public <T> T getMapper(Class<T> daoInterfaceClass) {
		Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
					new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers())
				);
		return null;
	}
	/*
	* 用于释放资源
	* */
	@Override
	public void close() {

	}
}
