package user.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private BoardUserDetailsService boardUserDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
	
		security.authorizeRequests().antMatchers("/").permitAll();
		security.authorizeRequests().antMatchers("/member/**").authenticated();
		security.authorizeRequests().antMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN");
		security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

		security.csrf().disable(); //크로스 사이트 리퀘스트 f.. 레스트풀을 사용하기 위해서는 이 기능을 비활성화 시켜야함. 사이트 위조에 대한 설정. 
		
		security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true); //true = 항상 사용하겠다. 
		
		security.exceptionHandling().accessDeniedPage("/accessDenied"); //403 페이지 "접근권한 없음 페이지로 처리" 
				//예외처리 핸들링. 
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login"); //로그아웃 처리
		
		security.userDetailsService(boardUserDetailsService);
		
		return security.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return 	PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	
	/* @Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("manager")
		.password("{noop}manager123") // noop = 비밀번호를 암호화하지 않겠다. 라는 의미. 
		.roles("MANAGER"); //권한 주는거 지정함. 
		
		auth.inMemoryAuthentication() //권한 줄 인원 추가. 
		.withUser("admin")
		.password("{noop}admin123")
		.roles("ADMIN");
	} */

}