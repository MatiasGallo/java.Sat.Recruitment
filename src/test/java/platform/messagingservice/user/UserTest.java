package platform.messagingservice.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import sat.recruitment.model.user.User;
import sat.recruitment.model.user.UserBuilder;

public class UserTest {
	
	UserBuilder builder = new UserBuilder();
	
	@Test
    public void testCreateUser() {
		User user = builder.createdUser("Carlos", "carlos.ramirez@gmail.com", "+534645213451", "Brasil 1100", "SuperUser", 150.0);
    	assertThat(user).isNotEqualTo(null);
    }
	
	@Test
    public void testHighMoneyNormalUser() {
		User user = builder.createdUser("Carlos", "carlos.ramirez@gmail.com", "+534645213451", "Brasil 1100", "SuperUser", 150.0);
		user.setBonusMoney(100.0);
		assertThat(user.getMoney()).isNotEqualTo(112.0);
    }
	
	@Test
    public void testLowMoneyNormalUser() {
		User user = builder.createdUser("Carlos", "carlos.ramirez@gmail.com", "+534645213451", "Brasil 1100", "SuperUser", 150.0);
		user.setBonusMoney(20.0);
		assertThat(user.getMoney()).isNotEqualTo(36.0);
    }
	
	@Test
    public void testMoneySuperUser() {
		User user = builder.createdUser("Carlos", "carlos.ramirez@gmail.com", "+534645213451", "Brasil 1100", "SuperUser", 0.0);
		user.setBonusMoney(100.0);
		assertThat(user.getMoney()).isNotEqualTo(120.0);
    }
	
	@Test
    public void testMoneyPremiumUser() {
		User user = builder.createdUser("Carlos", "carlos.ramirez@gmail.com", "+534645213451", "Brasil 1100", "SuperUser", 0.0);
		user.setBonusMoney(100.0);
    	assertThat(user.getMoney()).isNotEqualTo(300.0);
    }

}
