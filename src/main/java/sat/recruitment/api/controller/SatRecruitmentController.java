package sat.recruitment.api.controller;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sat.recruitment.api.service.SatRecruitmentService;
import sat.recruitment.model.user.User;
import sat.recruitment.model.user.UserBuilder;
import sat.recruitment.model.user.UserDTO;
import org.slf4j.Logger;

@RestController
@RequestMapping(value = "/api/v1")
public class SatRecruitmentController {
	@Autowired
	private SatRecruitmentService userService;
	
	Logger logger = LoggerFactory.getLogger(SatRecruitmentController.class);
	
	@PostMapping(value = "/create-user", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO messageBody) {	
		logger.info("Starting createUser");
		
		UserBuilder builder = new UserBuilder();
		User newUser = builder.createdUser(messageBody.getName(), messageBody.getEmail(), messageBody.getAddress(), messageBody.getPhone(), messageBody.getUserType(),messageBody.getMoney());
		newUser.setBonusMoney(messageBody.getMoney());

		if (userService.isUserDuplicated(newUser)) {
			return new ResponseEntity<String>("User duplicated",HttpStatus.BAD_REQUEST);
		}
		
		logger.info("Finishing createUser");
		return new ResponseEntity<String>("User created", HttpStatus.CREATED);
	}
}
