package vn.smokecoffee.auth.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {
	public static final String USER_ROLE = "USER";

	public static final String ADMIN_ROLE = "ADMIN";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("Khan").password(passwordEncoder.encode("password2")).roles("USER").and()
				.withUser("KhanAdmin").password(passwordEncoder.encode("KhanAdmin")).roles(USER_ROLE, ADMIN_ROLE);
		// super.configure(auth);
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		super.configure(web);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		//super.configure(http);
	}

}
