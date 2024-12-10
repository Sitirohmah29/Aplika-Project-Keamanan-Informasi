package project.uas;

public class Users {
	private Integer id;
	private String username;
	private String name;
	private String password;
	
	public Users() {}

    // Constructor dengan parameter
    public Users(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
