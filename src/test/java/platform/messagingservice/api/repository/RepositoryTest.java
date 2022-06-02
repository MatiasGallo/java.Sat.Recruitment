package platform.messagingservice.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sat.recruitment.api.SatRecruitmentApplication;
import sat.recruitment.api.repository.SatRecruitmentRepository;
import sat.recruitment.model.user.User;

@SpringBootTest(classes = SatRecruitmentApplication.class)
public class RepositoryTest {
	
	@Autowired
    private SatRecruitmentRepository repository = new SatRecruitmentRepository();
    
    @Test
    void testCreateDuplicateUserMail() {
    	List<User> users = repository.getUsersList();
    	assertThat(users).isNotEqualTo(null);
    }
}
