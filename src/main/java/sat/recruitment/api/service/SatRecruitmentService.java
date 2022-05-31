package sat.recruitment.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sat.recruitment.api.repository.SatRecruitmentRepository;
import sat.recruitment.model.user.User;

@Service
public class SatRecruitmentService {

	@Autowired
    private SatRecruitmentRepository userRepository;
	
	public boolean isUserDuplicated(User newUser) {
		List<User> users = userRepository.getUsersList();
		for (User user : users) {
			if (user.getEmail().equals(newUser.getEmail()) || user.getPhone().equals(newUser.getPhone())) {
				return true;
			} else if ((user.getName().equals(newUser.getName()) && (user.getAddress().equals(newUser.getAddress() )))) {
					return true;
				}
			}
		return false;
	}
}
