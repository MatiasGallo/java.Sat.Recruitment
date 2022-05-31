package sat.recruitment.model.user;

public class UserBuilder {
	
	public UserBuilder() {
	}

	
	public User createdUser(String name, String email, String address, String phone, String userType, Double money)  {
		switch(userType) {
			case User.PREMIUM_USER_TYPE:
				PremiumUser premiumUser = new PremiumUser(name, email, address, phone, money);
				return premiumUser;
			case User.SUPER_USER_TYPE:
				SuperUser superUser = new SuperUser(name, email, address, phone, money);
				return superUser;
			default:
				NormalUser normalUser = new NormalUser(name, email, address, phone, money);
				return normalUser;
		}
	}
}
