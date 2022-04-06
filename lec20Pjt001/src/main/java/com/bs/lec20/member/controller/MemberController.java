package com.bs.lec20.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bs.lec20.member.Member;
import com.bs.lec20.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@ModelAttribute("serverTime")
	public String getServerTime(Locale locale) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		return dateFormat.format(date);
	}
	
	// Join
	@RequestMapping("/joinForm")
	public String joinForm(Member member) {
		return "/member/joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinReg(Member member) {
		
		service.memberRegister(member);
		
		return "/member/joinOk";
	}
	
	// Login
	@RequestMapping("/loginForm")
	public String loginForm(Member member) {
		return "/member/loginForm";
	}
	
	/*
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memLogin(Member member, HttpServletRequest request) {
		
		
		Member mem = service.memberSearch(member);
		
		// HttpServletRequest 객체로부터 session get
		HttpSession session = request.getSession();
		
		// session에 추가하고 싶은 정보 
		// 속성 이름 member, 속성값 mem (회원 정보)
		session.setAttribute("member", mem);
		
		return "/member/loginOk";
	}
	*/
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memLogin(Member member, HttpSession session) {
		
		Member mem = service.memberSearch(member);
		
		// getSession() -> session.setAttribute 없이 바로  
		session.setAttribute("member", mem);
		
		return "/member/loginOk";
	}
	
	// Logout
	/*
	@RequestMapping("/logout")
	public String memLogout(Member member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "/member/logoutOk";
	}
	*/
	
	@RequestMapping("/logout")
	public String memLogout(Member member, HttpSession session) {
		
		session.invalidate();
		
		return "/member/logoutOk";
	}
	
	// Modify
	// session에 set해 둔 정보 가져와서 사용 (로그인할 때)
	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	public ModelAndView modifyForm(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("member", service.memberSearch(member));
		
		mav.setViewName("/member/modifyForm");
		
		return mav;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ModelAndView modify(Member member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Member mem = service.memberModify(member);
		session.setAttribute("member", mem);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memAft", mem);
		mav.setViewName("/member/modifyOk");
		
		return mav;
	}
	
	// Remove
	@RequestMapping("/removeForm")
	public ModelAndView removeForm(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session =  request.getSession();
		Member member = (Member) session.getAttribute("member");
		mav.addObject("member", member);
		mav.setViewName("/member/removeForm");
		
		return mav;
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String memRemove(Member member, HttpServletRequest request) {
		
		// 삭제
		service.memberRemove(member);
		
		// 세션에서 삭제
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "/member/removeOk";
	}
	
}
