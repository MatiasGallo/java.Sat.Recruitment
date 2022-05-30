package sat.recruitment.model.user;

public class NormalUser extends User {
	private final double thresholdNormalLowBonus = 10;
	
	private final double percentageHighNormalUser = 0.12;
	
	private final double percentageLowNormalUser = 0.8;
	
	public NormalUser() {
	}
	
	public NormalUser(String name, String email, String address, String phone, Double money) {
		super(name, email, address, phone, money);
	}

	@Override
	public void setBonusMoney(Double money) {
		Double percentageGif = 0.0;
		if (money > this.thresholdBonus) {
			percentageGif=percentageHighNormalUser;
		} else if (money > thresholdNormalLowBonus) {
			percentageGif=percentageLowNormalUser;
		}
		this.addGift(percentageGif, money);
	}
}
