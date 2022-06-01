package sat.recruitment.api.service;

import java.util.List;
import java.util.stream.Collectors;

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
		return users.stream().filter( user -> 
		user.getEmail().equals(newUser.getEmail())
		|| user.getPhone().equals(newUser.getPhone())
		|| ( user.getName().equals(newUser.getName()) && (user.getAddress().equals(newUser.getAddress())) )
		).limit(1).count() > 0;
	}
}
