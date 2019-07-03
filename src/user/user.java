package user;

import java.io.Serializable;

public class user implements Serializable{
	public String name;
	public String password;
	
	public user() {
		super();
	}
	
	public user(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
