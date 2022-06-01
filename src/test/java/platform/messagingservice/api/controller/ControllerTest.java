package platform.messagingservice.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import sat.recruitment.api.SatRecruitmentApplication;
import sat.recruitment.api.controller.SatRecruitmentController;
import sat.recruitment.model.user.NormalUser;
import sat.recruitment.model.user.User;
import sat.recruitment.model.user.UserBuilder;

@SpringBootTest(classes = SatRecruitmentApplication.class)
class ControllerTest {
    @Autowired
    private SatRecruitmentController controller;
    
    UserBuilder builder = new UserBuilder();

    @Test
    void testCreateNullUser() {
    	assertThat(controller.createUser(null).getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    
    @Test
    void testCreateUserWithNull() {
    	User user = builder.createdUser("Carlos", "carlos.ramirez@gmail.com", "+534645213451", null, "SuperUser", 150.0);
    	assertThat(controller.createUser(user).getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    
    @Test
    void testCreateDuplicateUser() {
    	NormalUser newUser = new NormalUser();
		newUser.setName("Raul");
		newUser.setEmail("Franco.Perez@gmail.com");
		newUser.setAddress("Colombia 115");
		newUser.setPhone("+534555213454");
		newUser.setUserType("Premium");
		newUser.setMoney(200.0);

    	assertThat(controller.createUser(newUser).getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    
    @Test
    void testCreateUser() {
    	NormalUser newUser = new NormalUser();
		newUser.setName("Raul");
		newUser.setEmail("Raul@gmail.com");
		newUser.setAddress("Uruguay 115");
		newUser.setPhone("+534555252454");
		newUser.setUserType("Normal");
		newUser.setMoney(80.0);

    	assertThat(controller.createUser(newUser).getStatus()).isEqualTo(HttpStatus.OK);
    }
}
