package platform.messagingservice.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import sat.recruitment.api.SatRecruitmentApplication;
import sat.recruitment.api.controller.SatRecruitmentController;
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
    	User user = builder.createdUser("Carlos", "carlos.ramirez@gmail.com", null, "+534645213451", "SuperUser", 150.0);
    	assertThat(controller.createUser(user).getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    
    @Test
    void testCreateDuplicateUser() {
    	User newUser = builder.createdUser("Raul", "Franco.Perez@gmail.com", "Colombia 115", "+534555213454", "Premium", 200.0);

    	assertThat(controller.createUser(newUser).getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    
    @Test
    void testCreateUser() {
    	User newUser = builder.createdUser("Raul", "Raul@gmail.com", "Uruguay 115", "+534555252454", "Normal", 80.0);
    	assertThat(controller.createUser(newUser).getStatus()).isEqualTo(HttpStatus.OK);
    }
}
