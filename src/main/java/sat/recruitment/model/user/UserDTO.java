package sat.recruitment.model.user;

public class UserDTO {
	private String name;
	private String email;
	private String address;
	private String phone;
	private String userType;
	protected Double money;
	
	public UserDTO(String name, String email, String address, String phone, String userType, Double money) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.userType = userType;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	};

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	};
}
