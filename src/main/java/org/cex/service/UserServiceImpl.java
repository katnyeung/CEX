package org.cex.service;

import org.cex.domain.User;
import org.cex.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	public User getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}

	public void createUser(User user) {
		userMapper.insertUser(user);
	}

}
