package com.alumon.login;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	
	//List<User> findByTitleContainingOrContentContaining(String text, String textAgain);

}
