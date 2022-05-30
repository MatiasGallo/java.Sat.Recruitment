package sat.recruitment.model.user;

public class SuperUser extends User {
	
	private final double percentageSuperUser = 0.20;
	
	public SuperUser() {
	}
	
	public SuperUser(String name, String email, String address, String phone, Double money) {
		super(name, email, address, phone, money);
	}

	@Override
	public void setBonusMoney(Double money) {
		Double percentageGif = 0.0;
		if (money > this.thresholdBonus)
			percentageGif=percentageSuperUser;
		this.addGift(percentageGif, money);
	}

}
