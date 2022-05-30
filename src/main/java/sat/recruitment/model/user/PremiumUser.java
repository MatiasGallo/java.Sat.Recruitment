package sat.recruitment.model.user;

public class PremiumUser extends User {
	
	private final double percentagePremiumUser = 2.00;

	public PremiumUser() {
	}
	
	public PremiumUser(String name, String email, String address, String phone, Double money) {
		super(name, email, address, phone, money);
	}

	@Override
	public void setBonusMoney(Double money) {
		Double percentageGif = 0.0;
		if (money > this.thresholdBonus)
			percentageGif=percentagePremiumUser;
		this.addGift(percentageGif, money);
	}

}
