package sat.recruitment.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sat.recruitment.api.service.SatRecruitmentService;
import sat.recruitment.model.user.User;
import sat.recruitment.model.user.UserBuilder;
import sat.recruitment.model.user.UserDTO;

@RestController
@RequestMapping(value = "/api/v1")
public class SatRecruitmentController {
	@Autowired
	private SatRecruitmentService userService;

	@PostMapping(value = "/create-user", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseStatusException createUser(@RequestBody UserDTO messageBody) {
		if (messageBody == null) {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body is null");
		}
		
		String errors = validateErrors(messageBody.getName(), messageBody.getEmail(), messageBody.getAddress(), messageBody.getPhone(), messageBody.getMoney());
		if (errors != null && errors != "") {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
		}
		
		UserBuilder builder = new UserBuilder();
		User newUser = builder.createdUser(messageBody.getName(), messageBody.getEmail(), messageBody.getAddress(), messageBody.getPhone(), messageBody.getUserType(),messageBody.getMoney());
		newUser.setBonusMoney(messageBody.getMoney());

		if (userService.isUserDuplicated(newUser)) {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is duplicated");
		}
		
		return new ResponseStatusException(HttpStatus.OK, "User created");
	}

	public String validateErrors(String name, String email, String address, String phone, Double money) {
		String errors = "";
		if (name == null)
			// Validate if Name is null
			errors = "The name is required";
		if (email == null)
			// Validate if Email is null
			errors = errors + " The email is required";
		if (address == null)
			// Validate if Address is null
			errors = errors + " The address is required";
		if (phone == null)
			// Validate if Phone is null
			errors = errors + " The phone is required";
		if (money == null || money < 0)
			// Validate if Money is valid
			errors = errors + " The money amount is required";
		return errors;
	}

}
