package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.User;

public class UserDaoFake implements UserDao {

	List<User> userRepository;
	
	{
		userRepository = new ArrayList<User>();
		
		userRepository.add(new User("user", "pass", "My User"));
		userRepository.add(new User("bmoney1", "cookie", "Brian M"));
		userRepository.add(new User("cmendez", "burrito", "Carlos M"));
	}
	
	public User getUser(String username) {
		
		User ret = null;
		
		for (User u : userRepository) {
			if (u.getUsername().equals(username)) {
				ret = u;
			}
		}
		
		return ret;
	}

	public void createUser(User user) {

	}

	public List<User> getAllUsers() {
		return null;
	}

	public void updateUser(User user) {

	}

	public void deleteUser(User user) {

	}

}
