package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

public interface IAccountDao {
	/*
	* 查询所有账户,同时还要获取到当前用户的所属用户信息
	* */
	List<Account> findAll();
	/*
	* 根据用户id查询账户信息
	* */
	List<Account> findAccountByUid(Integer uid);

}
