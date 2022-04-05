package com.mehmetpekdemir.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mehmetpekdemir.librarymanagementsystem.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
