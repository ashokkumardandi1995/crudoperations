package com.crud.dao;

import java.util.List;

import com.crud.model.User;

public interface UserDao {

	void saveUser(User user);

	User getUserById(long id);

	List<User> getAllUsers();

	void updateUser(User user);

	void deleteUserById(long id);

}