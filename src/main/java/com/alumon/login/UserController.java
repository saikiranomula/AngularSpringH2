package com.alumon.login;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alumon.error.UserNotFoundException;
import com.alumon.error.UserUnSupportedFieldPatchException;

@RestController
public class UserController {

    @Autowired
    private UserRepo repository;
    
    @Autowired
    private UserServiceImpl userServImpl;

 // Find
    @PostMapping("/users/authenticate")
    com.alumon.login.User authenticateUser(@RequestBody User newUser) {
		 com.alumon.login.User user = userServImpl.getUserByUsername(newUser.getUsername());
		 //match the pass
		 
		 if(user == null) {
			 return null;
		 }
		 if(user.getPassword().equals(newUser.getPassword())) {
			 return user;
		 }
		 
		 return null;
    }
    // Find
    @GetMapping("/users")
    List<com.alumon.login.User> findAll() {
        return repository.findAll();
    }
    
    // Save
    @PostMapping("/users/register")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    com.alumon.login.User registerUser(@RequestBody com.alumon.login.User newUser) {
        return repository.save(newUser);
    }

    // Save
    @PostMapping("/newuser")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    com.alumon.login.User newUser(@RequestBody com.alumon.login.User newUser) {
        return repository.save(newUser);
    }

    // Find
    @GetMapping("/users/{id}")
    com.alumon.login.User findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // Save or update
    @PutMapping("/users/{id}")
    com.alumon.login.User saveOrUpdate(@RequestBody com.alumon.login.User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setUsername(newUser.getUsername());
                    x.setPassword(newUser.getPassword());
                    x.setPhone(newUser.getPhone());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    // update author only //sn updating pass?
    @PatchMapping("/users/{id}")
    com.alumon.login.User patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String password = update.get("password");
                    if (!StringUtils.isEmpty(password)) {
                        x.setPassword(password);

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new UserUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new UserNotFoundException(id);
                });

    }
    
    // Delete a user working as expected
    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userServImpl.deleteUser(id);
    }
    
}
