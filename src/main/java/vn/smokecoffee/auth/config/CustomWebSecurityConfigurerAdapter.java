package vn.smokecoffee.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	public static final String USER_ROLE = "USER";

	public static final String ADMIN_ROLE = "ADMIN";

	@Autowired
	private CustomBasicAuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		PasswordEncoder passwordEncoder = passwordEncoder();
		auth.inMemoryAuthentication().withUser("Khan2").password(passwordEncoder.encode("password2"))
			//.roles(USER_ROLE)
			.authorities(USER_ROLE);
		
				//.and().withUser("KhanAdmin2").password(passwordEncoder.encode("KhanAdmin2")).roles(USER_ROLE, ADMIN_ROLE)
				//.authorities(USER_ROLE, ADMIN_ROLE);

		// super.configure(auth);
		//encodedPassword = "$2a$10$m2GEPxgHUfHefLFqAx5h1.SZGy1AxXJoKyZnLvPUfmVxa2doxiufC"
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		super.configure(web);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/securityNone").permitAll().anyRequest().authenticated().and().httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint);

		http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
