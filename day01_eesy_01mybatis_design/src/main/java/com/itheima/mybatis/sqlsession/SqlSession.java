package com.itheima.mybatis.sqlsession;
/*
* 自定义mybatis中的和数据库的核心类
* 它里面可以创建dao接口的代理对象
* */
public interface SqlSession {
	/*
	* 创建参数创建一个代理对象
	* */
	<T> T getMapper(Class<T> daoInterfaceClass);
	/*
	* 释放资源
	* */
	void close();
}
