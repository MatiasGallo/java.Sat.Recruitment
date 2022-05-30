package sat.recruitment.api.repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import sat.recruitment.model.user.User;
import sat.recruitment.model.user.UserBuilder;

@Repository
public class SatRecruitmentRepository {
	
		public List<User> getUsersList() {
			List<User> users = new ArrayList<User>();
			InputStream fstream;
			try {
				fstream = getClass().getResourceAsStream("/users.txt");

				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

				String strLine;
				
				UserBuilder builder = new UserBuilder();
				while ((strLine = br.readLine()) != null) {
					String[] line = strLine.split(",");
					User user = builder.createdUser(line[0], line[1], line[2], line[3], line[4],Double.valueOf(line[5]));
					users.add(user);
				}
				fstream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return users;
		}
}
