package com.pengsoo.home.entity;

import javax.persistence.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.pengsoo.home.Role;
import com.pengsoo.home.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="home_member")//테이블 이름 설정
@SequenceGenerator(name = "homemember_seq_generator",
	sequenceName ="homemember_seq",allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id//기본키
	@GeneratedValue(strategy = GenerationType.AUTO)//회원번호 자동 설정
	private Long mnum;
	
	@Column(unique = true)//unique = true는 mid 중복이 안되게 한다
	private String mid;
	
	private String mname;
	
	private String mpw;
	
	private String memail;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
		
		Member member = new Member();
		member.setMid(memberDto.getMid());
		member.setMname(memberDto.getMname());
		member.setMemail(memberDto.getMemail());
		
		String mpw = passwordEncoder.encode(memberDto.getMpw());//암호화된 비밀번호
		
		member.setMpw(mpw);
		member.setRole(Role.USER);
		
		return member;
	}
}



















