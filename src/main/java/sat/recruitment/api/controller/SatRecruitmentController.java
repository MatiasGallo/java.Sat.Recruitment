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

@RestController
@RequestMapping(value = "/api/v1")
public class SatRecruitmentController {
	@Autowired
	private SatRecruitmentService userService;

	@PostMapping(value = "/create-user", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseStatusException createUser(@RequestBody User messageBody) {
		if (messageBody == null) {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body is null");
		}
		String errors = "";

		validateErrors(messageBody.getName(), messageBody.getEmail(), messageBody.getAddress(), messageBody.getPhone(),
				errors);

		if (errors != null && errors != "") {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
		}

		User newUser = new User();
		newUser.setName(messageBody.getName());
		newUser.setEmail(messageBody.getEmail());
		newUser.setAddress(messageBody.getAddress());
		newUser.setPhone(messageBody.getPhone());
		newUser.setUserType(messageBody.getUserType());
		newUser.setMoney(messageBody.getMoney());

		if (newUser.getUserType().equals("Normal")) {
			if (Double.valueOf(newUser.getMoney()) > 100) {
				Double percentage = Double.valueOf("0.12");
				// If new user is normal and has more than USD100
				var gif = Double.valueOf(newUser.getMoney()) * percentage;
				newUser.setMoney(newUser.getMoney() + gif);
			}
			if (Double.valueOf(newUser.getMoney()) < 100) {
				if (Double.valueOf(newUser.getMoney()) > 10) {
					var percentage = Double.valueOf("0.8");
					var gif = Double.valueOf(newUser.getMoney()) * percentage;
					newUser.setMoney(newUser.getMoney() + gif);
				}
			}
		}
		if (newUser.getUserType().equals("SuperUser")) {
			if (Double.valueOf(newUser.getMoney()) > 100) {
				Double percentage = Double.valueOf("0.20");
				Double gif = Double.valueOf(newUser.getMoney()) * percentage;
				newUser.setMoney(newUser.getMoney() + gif);
			}
		}
		if (newUser.getUserType().equals("Premium")) {
			if (Double.valueOf(newUser.getMoney()) > 100) {
				Double gif = Double.valueOf(newUser.getMoney()) * 2;
				newUser.setMoney(newUser.getMoney() + gif);
			}
		}

		if (userService.isUserDuplicated(newUser)) {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is duplicated");
		}
		
		return new ResponseStatusException(HttpStatus.OK, "User created");
	}

	public void validateErrors(String name, String email, String address, String phone, String errors) {
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
	}

}
