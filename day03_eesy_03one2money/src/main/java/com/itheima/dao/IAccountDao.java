package com.itheima.dao;

import com.itheima.domain.Account;
import com.itheima.domain.AccountUser;

import java.util.List;

public interface IAccountDao {
	/*
	* 查询所有账户,同时还要获取到当前用户的所属用户信息
	* */
	List<Account> findAll();
	List<AccountUser> findAllAccount();
}
