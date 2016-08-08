package org.cex.service;

import org.cex.domain.User;

public interface UserService {

	public User getUserByEmail(String email);

	public void createUser(User user);

}
