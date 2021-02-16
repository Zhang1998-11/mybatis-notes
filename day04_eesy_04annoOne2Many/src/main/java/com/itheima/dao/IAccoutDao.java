package com.itheima.dao;

import com.itheima.domain.Account;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IAccoutDao {
	/*
	* 查询所有账户,并且获取每个账户所属的用户信息
	* */
	@Select("select * from account")
	List<Account> findAll();
}
