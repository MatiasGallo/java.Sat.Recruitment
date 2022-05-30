package sat.recruitment.api.repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import sat.recruitment.api.controller.User;

@Repository
public class SatRecruitmentRepository {
	
		public List<User> getUsersList() {
			List<User> users = new ArrayList<User>();
			InputStream fstream;
			try {
				fstream = getClass().getResourceAsStream("/users.txt");

				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

				String strLine;

				while ((strLine = br.readLine()) != null) {
					String[] line = strLine.split(",");
					User user = new User();
					user.setName(line[0]);
					user.setEmail(line[1]);
					user.setPhone(line[2]);
					user.setAddress(line[3]);
					user.setUserType(line[4]);
					user.setMoney(Double.valueOf(line[5]));
					users.add(user);

				}
				fstream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return users;
		}
}
