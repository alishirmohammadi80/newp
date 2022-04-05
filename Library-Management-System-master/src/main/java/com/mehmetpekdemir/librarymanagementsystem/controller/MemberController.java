package com.mehmetpekdemir.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mehmetpekdemir.librarymanagementsystem.entity.Member;
import com.mehmetpekdemir.librarymanagementsystem.service.MemberService;

@Controller
public class MemberController {
@Autowired
MemberService memberService;


	@RequestMapping("/members")
	public String findAllMembers(Model model) {

		 List<Member> members = memberService.findAllMembers();

		model.addAttribute("members", members);
		return "list-members";
	}

	@RequestMapping("/member/{id}")
	public String findMemberById(@PathVariable("id") Long id, Model model) {

		 Member member = memberService.findMemberById(id);

		model.addAttribute("member", member);
		return "list-member";
	}

	@GetMapping("/addmember")
	public String showCreateForm(Member member) {
		return "add-member";
	}

	@RequestMapping("/add-member")
	public String createMember(Member member,  Model model) {


		memberService.createMember(member);
		model.addAttribute("member",memberService.findAllMembers());
		return "redirect:/members";
	}

	@GetMapping("/updatemember/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		 Member member = memberService.findMemberById(id);

		model.addAttribute("member",member);
		return "update-member";
	}

	@RequestMapping("/update-member/{id}")
	public String updateMember(@PathVariable("id") Long id, Member member, BindingResult result, Model model) {
		if (result.hasErrors()) {
			member.setId(id);
			return "update-member";
		}

		memberService.updateMember(member);
		model.addAttribute("member", memberService.findAllMembers());
		return "redirect:/members";
	}

	@RequestMapping("/remove-member/{id}")
	public String deleteMember(@PathVariable("id") Long id, Model model) {
		memberService.deleteMember(id);

		model.addAttribute("member", memberService.findAllMembers());
		return "redirect:/members";
	}

}
