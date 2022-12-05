package user.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import user.security.config.SecurityUser;
import user.security.domain.Users;
import user.security.service.UserService;

@Controller
public class SecurityController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/")
	public String index() {
		System.out.println("index 요청입니다.");
		return "index";
	}
	@GetMapping("/member") // 뷰 정보가 없음. = 뷰 정보를 자동으로 만들어줌. 요청 유알아이에서 첫번째 슬레쉬 빼고 나머지 가지고 뷰 네임으로 삼음. 즉,member.jsp가 실행될 예정. 
	public void forMember() {
		System.out.println("Member 요청입니다.");
	}
	@GetMapping("/manager")
	public void forManager() {
		System.out.println("Manager 요청입니다.");
	}
	@GetMapping("/admin")
	public void forAdmin(@AuthenticationPrincipal SecurityUser user ) {
		System.out.println("user.getUsername()" + user.getUsername());
		System.out.println("Admin 요청입니다.");
	}
	
	@GetMapping("/login")
	public void login() {
	}

	@GetMapping("/loginSuccess")
	public void loginSuccess() {
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}
	
	@GetMapping("/insert")
	public void insert() {
	}
	
	@PostMapping("/insert")
		public String insert(Users users) {
		service.insertUser(users);
		return "redirect:/";
	}
	
}
