package com.mehmetpekdemir.librarymanagementsystem.service;

import java.util.List;

import com.mehmetpekdemir.librarymanagementsystem.entity.Member;

public interface MemberService {

	public List<Member> findAllMembers();

	public Member findMemberById(Long id);

	public void createMember(Member member);

	public void updateMember(Member member);

	public void deleteMember(Long id);

}
