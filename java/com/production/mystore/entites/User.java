package com.production.mystore.entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.hibernate.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty(message = "should not be empty")
	@Size(min = 3, message = "name should have minimum 3 character!")
	private String name;
	
	@Pattern(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "Email must be in the format: user@domain.com")
	private String email;
	
	@Size(min = 10, max = 10, message = "contact number should be 10 digits")
	private String contactNo;
	
	@Size(min = 6, max = 12, message = "password must be min of 6 char max of 12 char")
	private String password;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", // Junction table name
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), // Foreign key to User
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") // Foreign key to Role
	)
	private List<Role> roles;

	@OneToMany
	private List<Address> addresses;
}
