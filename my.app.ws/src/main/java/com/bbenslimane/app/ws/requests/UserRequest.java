package com.bbenslimane.app.ws.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	
	
	@NotNull(message="Firstname can't be null!")
	@Size(min=3, message="Firstname must conatains atleast 3 characters!")
	private String firstName;
	
	@NotNull(message="Second can't be null!")
	@Size(min=3, message="Second must conatains atleast 3 characters!")
	private String secondName;
	
	@NotNull(message="Email can't be null!")
	@Email(message="Email must have the Email format ")
	private String email;
	
	@NotNull(message="Password can't be null!") 
	@Size(min=8, message="Password must have atleast 8 characters!")
	@Size(max=12, message="Password can't caontains more than 12 characters!")
	@Pattern(regexp="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"
			, message="password must contains alphanumeric characters")
	private String password;
	
	private List<AddressRequest> addresses;
	
	private ContactRequest contact;
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AddressRequest> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressRequest> addresses) {
		this.addresses = addresses;
	}
	public ContactRequest getContact() {
		return contact;
	}
	public void setContact(ContactRequest contact) {
		this.contact = contact;
	}

	

}

