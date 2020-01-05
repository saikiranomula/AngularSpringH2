package com.alumon.login;

import java.util.List;

import com.alumon.login.User;

public interface UserService {
	
	public List<User> retrieveUsers();
	  
	 public User getUser(Long UserId);
	  
	 public void saveUser(User User);
	  
	 public void deleteUser(Long UserId);
	  
	 public void updateUser(User User);

	public User getUserByUsername(String UserName);
}
