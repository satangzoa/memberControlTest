package com.pengsoo.home.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pengsoo.home.entity.Member;

public interface MemberRepository  extends JpaRepository<Member, Long>{//<Member, Long> Member는 엔티티이름 


	public Member findByMid(String mid);
	
}
