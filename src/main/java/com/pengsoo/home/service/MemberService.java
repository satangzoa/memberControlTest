package com.pengsoo.home.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pengsoo.home.entity.Member;
import com.pengsoo.home.reporitory.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor//final 생성자를 생성해준다
public class MemberService implements UserDetailsService{

	private final MemberRepository memberRepository;//값을 못바꾸게 final 선언
	
	private void validateDuplicateMember(Member member) {//유효성과 중복검사하는 메소드
	
	 Member resultMember = memberRepository.findByMid(member.getMid());
	 
	 if(resultMember != null) {
		 throw new IllegalStateException("이미 가입된 회원입니다!");
	 }
	}
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);//테스트해본다
		Member returnMember= memberRepository.save(member);
		
		return returnMember;
		
	}

	
	@Override
	public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {

		Member member = memberRepository.findByMid(mid);
		
		if(member == null) {
			throw new UsernameNotFoundException(mid);
		}
		
		return User.builder()
				.username(member.getMid())
				.password(member.getMpw())
				.roles(member.getRole().toString())
				.build()
				;
	
	}
	
	
}





