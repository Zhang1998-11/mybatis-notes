package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
/*
* 在mybatis中针对,CRUD一共有四个注解
* @Select @Insert @Update @Delete
*
* */
public interface IUserDao {

	@Select("select * from user")
	@Results(id="userMap",value = {
			@Result(id=true,column = "id",property = "userId"),
			@Result(column = "username",property = "userName"),
			@Result(column = "address",property = "userAddress"),
			@Result(column = "sex",property = "userSex"),
			@Result(column = "birthday",property = "userBirthday"),

	})
	List<User> findAll();

	//根据id查询用户
	@Select("select * from user where id=#{id}")
	@ResultMap(value = {"userMap"})
	User findById(Integer userId);

	//根据用户名称模糊查询
	@Select("select * from user where username like #{username}")
	List<User> findUserByName(String username);



}
