package com.pengsoo.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pengsoo.home.dto.MemberDto;
import com.pengsoo.home.entity.Member;
import com.pengsoo.home.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	@RequestMapping("join")
	public String join(Model model) {
		
		MemberDto memberDto = new MemberDto();
		model.addAttribute("memberDto", memberDto);
		
		return "joinForm";
	}
	
	@RequestMapping("joinOk")
	public String joinOk(MemberDto memberDto) {//joinOk(MemberDto memberDto)오버로딩해준다
		

		Member member =Member.createMember(memberDto, passwordEncoder);
		memberService.saveMember(member);
		return "index";
	}
	

	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping("/loginFail")
	public String loginFail() {
		
		return "loginFail";
	}
	
	@RequestMapping("/loginOk")
	public String loginOk() {
		
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		
		return "index";
	}

	
}















