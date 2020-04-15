package vn.smokecoffee.auth.console;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptTest {

	public static void main(String[] args) {

		int i = 0;
		String pass = "password2";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		while (i < 10) {
			String passwordEncoded =passwordEncoder.encode(pass); 
			System.out.println(passwordEncoded);
		
			System.out.println(passwordEncoder.matches("password2", passwordEncoded));
			
			
			i++;
		}
	}

}
