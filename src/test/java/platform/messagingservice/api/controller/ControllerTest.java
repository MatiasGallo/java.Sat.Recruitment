package platform.messagingservice.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import sat.recruitment.api.SatRecruitmentApplication;
import sat.recruitment.api.controller.SatRecruitmentController;
import sat.recruitment.model.user.UserDTO;

@SpringBootTest(classes = SatRecruitmentApplication.class)
class ControllerTest {
    @Autowired
    private SatRecruitmentController controller;
    
    @Test
    void testCreateNullUser() {
    	assertThat(controller.createUser(null).getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    
    @Test
    void testCreateUserWithNull() {
    	UserDTO newUser = new UserDTO("Carlos", "carlos.ramirez@gmail.com", null, "+534645213451", "SuperUser", 150.0);
    	assertThat(controller.createUser(newUser).getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    
    @Test
    void testCreateDuplicateUser() {
    	UserDTO newUser = new UserDTO("Raul", "Franco.Perez@gmail.com", "Colombia 115", "+534555213454", "Premium", 200.0);
    	assertThat(controller.createUser(newUser).getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    
    @Test
    void testCreateUser() {
    	UserDTO newUser = new UserDTO("Raul", "Raul@gmail.com", "Uruguay 115", "+534555252454", "Normal", 80.0);
    	assertThat(controller.createUser(newUser).getStatus()).isEqualTo(HttpStatus.OK);
    }
}
