package sat.recruitment.api.service;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sat.recruitment.api.repository.SatRecruitmentRepository;
import sat.recruitment.model.user.User;

@Service
public class SatRecruitmentService {

	@Autowired
    private SatRecruitmentRepository userRepository;
	
	/**
	 * Returns Predicate that define if user is duplicated:
	 * 	- Equal Email
	 * 	- Equal Phone
	 *  - Equal Name and Address
	 * @param newUser
	 * @return Predicate
	 */
	private Predicate<User> existPredicate(User newUser) {
		return (user -> 
				user.getEmail().equals(newUser.getEmail())
				|| user.getPhone().equals(newUser.getPhone())
				|| ( user.getName().equals(newUser.getName()) 
						&& (user.getAddress().equals(newUser.getAddress())))
		);
	}
	
	public boolean isUserDuplicated(User newUser) {
		List<User> users = userRepository.getUsersList();
		return users.stream().anyMatch(existPredicate(newUser));
	}
}
