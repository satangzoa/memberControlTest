package com.pengsoo.home;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.pengsoo.home.dto.MemberDto;
import com.pengsoo.home.entity.Member;
import com.pengsoo.home.service.MemberService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations =  "classpath:application-test.properties")
public class loginTest {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Member createMeber(String mid, String mpw) {
		
		MemberDto memberDto = new MemberDto();
		memberDto.setMid(mid);
		memberDto.setMname("홍길동");
		memberDto.setMpw(mpw);
		memberDto.setMemail("123@.net");
		
		Member member =  Member.createMember(memberDto, passwordEncoder);
		Member savedMember = memberService.saveMember(member);
		
		return savedMember;
		
	}
	
	
	@Test
	@DisplayName("로그인 성공 테스트")
	public void loginSuccessTest() throws Exception {
		String mid = "Saja";
		String mpw = "1234";
		
		createMeber(mid,mpw);// Banana와 12345 계정으로 회원 가입
		
		mockMvc.perform(
				formLogin().userParameter("mid")
				.password(mpw)
				.loginProcessingUrl("/login")
				.user(mid).password(mpw))
				.andExpect(SecurityMockMvcResultMatchers.authenticated());
				
	}
	
//	@Test
//	@DisplayName("로그인 실패 테스트")
//	public void loginFailTest() throws Exception {
//		String mid = "Apple22";
//		String mpw = "1234";
//		
//		createMeber(mid,mpw);
//		
//		mockMvc.perform(
//				formLogin().userParameter("mid")
//				.loginProcessingUrl("/login")
//				
//				.user(mid).password("1234"))
//				.andExpect(SecurityMockMvcResultMatchers.authenticated());
//				
//	}
	}




