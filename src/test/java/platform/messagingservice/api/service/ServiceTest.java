package platform.messagingservice.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sat.recruitment.api.SatRecruitmentApplication;
import sat.recruitment.api.service.SatRecruitmentService;
import sat.recruitment.model.user.User;
import sat.recruitment.model.user.UserBuilder;

@SpringBootTest(classes = SatRecruitmentApplication.class)
public class ServiceTest {
	@Autowired
    private SatRecruitmentService service = new SatRecruitmentService();
    
    UserBuilder builder = new UserBuilder();
	
    @Test
    void testCreateDuplicateUserMail() {
    	User newUser = builder.createdUser("Raul", "Franco.Perez@gmail.com", "Colombia 115", "+534555213454", "Premium", 200.0);
    	assertThat(service.isUserDuplicated(newUser)).isEqualTo(true);
    }
    
    @Test
    void testCreateDuplicateUserPhone() {
    	User newUser = builder.createdUser("Raul", "Raul@gmail.com", "Colombia 115", "+5491154762312", "Premium", 200.0);
    	assertThat(service.isUserDuplicated(newUser)).isEqualTo(true);
    }
    
    
    @Test
    void testCreateDuplicateUserNameAdress() {
    	User newUser = builder.createdUser("Agustina", "Raul@gmail.com", "Garay y Otra Calle", "+534645213513", "Premium", 200.0);
    	assertThat(service.isUserDuplicated(newUser)).isEqualTo(true);
    }

    @Test
    void testCreateUniqueUser() {
    	User newUser = builder.createdUser("Agustina", "Raul@gmail.com", "Brasil 111", "+534645213513", "Premium", 200.0);
    	assertThat(service.isUserDuplicated(newUser)).isEqualTo(false);
    }
}
