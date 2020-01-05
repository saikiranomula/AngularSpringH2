package com.alumon.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	 @Autowired
	 private UserRepo repository;
	@Override
	public List<User> retrieveUsers() {
		List<User> userList = repository.findAll();
		return userList;
	}

	@Override
	public User getUser(Long UserId) {
		List<User> userList = repository.findAll();
		User user = userList.stream().filter(t->t.getId().equals(UserId)).findFirst().get();
		return user;
	}

	@Override
	public User getUserByUsername(String UserName) {
		List<User> userList = repository.findAll();
		User result = userList.stream().filter(x -> UserName.equals(x.getUsername()))       // we want "jack" only
        .findAny()                                      // If 'findAny' then return found
        .orElse(null);
		
		return result;
	}
	@Override
	public void saveUser(User newUser) {

		repository.findById(newUser.getId())
                .map(x -> {
                    x.setUsername(newUser.getUsername());
                    x.setPassword(newUser.getPassword());
                    x.setPhone(newUser.getPhone());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newUser.setId(newUser.getId());
                    return repository.save(newUser);
                });}

	@Override
	public void deleteUser(Long UserId) {
		repository.deleteById(UserId);
		
	}

	@Override
	public void updateUser(User User) {
		// TODO Auto-generated method stub
		
	}


}
