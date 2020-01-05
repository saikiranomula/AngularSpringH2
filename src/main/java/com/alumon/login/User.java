package com.alumon.login;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String username;
	    private String password;
	    private String email;
	    private long phone;
	    private Date dob;
	    private String[] userRole;
	    private String firstName;
	    private String lastName;

	    // avoid this "No default constructor for entity"
	    public User() {
	    }
	    
	    public User(Long id, String username, String password, String email, long phone, Date dob, String firstName, String lastName, String... userRole) {
	        this.id = id;
	        this.username = username;
	        this.password = password;
	        this.email = email;
	        this.phone = phone;
	        this.dob = dob;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.userRole = userRole;
	    }
	    public User(String username, String password, String... userRole) {
	        this.username = username;
	        this.password = password;
	        this.userRole = userRole;
	    }
	    
	    public User(Long id,  String password) {
	        this.id = id;
	        this.password = password;
	    }
	    public User(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }
	    
	    @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", username='" + username + '\'' +
	                ", password='" + password + '\'' +
	                ", email='" + email + '\'' +
	                ", phone=" + phone +'\'' +
	                ", dob=" + dob +'\'' +
	                ", firstName=" + firstName +'\'' +
	                ", lastName=" + lastName +'\'' +
	                ", userRole=" + userRole +
	                '}';
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public long getPhone() {
			return phone;
		}

		public void setPhone(long phone) {
			this.phone = phone;
		}

		public Date getDob() {
			return dob;
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}

		public String[] getUserRole() {
			return userRole;
		}

		public void setUserRole(String[] userRole) {
			this.userRole = userRole;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

	  
}
