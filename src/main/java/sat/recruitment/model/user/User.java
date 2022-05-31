package sat.recruitment.model.user;

public abstract class User {
	
	public static final String SUPER_USER_TYPE = "SuperUser";
	
	public static final String PREMIUM_USER_TYPE = "Premium";
	
	public static final String NORMAL_USER_TYPE = "Normal";
	
	private String name;
	private String email;
	private String address;
	private String phone;
	private String userType;
	protected Double money;
	
	protected final double thresholdBonus = 100;
	
	public User() {
		
	}

	public User(String name, String email, String address, String phone, Double money) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
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
	
	public abstract void setBonusMoney(Double money);
	
	protected void addGift(Double percentage,Double money){
		var gif = money * percentage;
		this.setMoney(money + gif);
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	};
}
