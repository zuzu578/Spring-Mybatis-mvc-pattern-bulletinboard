package com.javalec.java;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javalec.java.DAO.DAO;
import com.javalec.java.DTO.DTO;


@Controller
public class HomeController {

	@Autowired 
	private SqlSession sqlSession;

	@RequestMapping("/list")
	public String list(Model model) {
		DAO dao = sqlSession.getMapper(DAO.class);
		ArrayList<DTO>dtos = dao.list();
		model.addAttribute("dtos", dtos);
		return "list";
		
	}
	@RequestMapping("/signup")
	public String signup() {
		return "usersignup";
	}
	
	@ResponseBody
	@RequestMapping(value="/dosignup",method=RequestMethod.POST) 
	public String dosignup(HttpServletRequest req, Model model) {
		String userid  = req.getParameter("userid");
		String useremail  = req.getParameter("useremail");
		String passcode = req.getParameter("passcode");
		DAO dao = sqlSession.getMapper(DAO.class);
		
		//아이디가 중복되는경우
		System.out.println("debug1");
		HttpSession session = req.getSession();
		int cnt = dao.signupCheck(userid);
		System.out.println("debug2");
		System.out.println(cnt);
		//model.addAttribute("cnt", cnt);
		if(cnt==1) {
			System.out.println("중복되는 아이디입니다.");
			return "redirect:signup";
		}else {
			//아이디가 중복되지않은경우
			System.out.println("debug4");
			dao.signup(userid,passcode,useremail);
			System.out.println("debug5");
			return "redirect:login";
		}
	
	}
	
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest req, Model model) {
		int bId = Integer.parseInt(req.getParameter("bId"));
		DAO dao = sqlSession.getMapper(DAO.class);
		DTO dto = dao.contentView(bId);
		dao.upHit(bId);
		model.addAttribute("content_view",dto);
		return "content_view";
	}
	@RequestMapping("/write_view")
	public String writeview(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		if(session.getAttribute("uid")==null || session.getAttribute("uid").equals("")) {
			return "redirect:login";
		}
		return "write_view";
	}
	@RequestMapping(value="/write",method=RequestMethod.POST) 
	public String write(HttpServletRequest req, Model model) {
		//bTitle bName bContent
		String bTitle = req.getParameter("bTitle");
		String bName = req.getParameter("bName");
		String bContent = req.getParameter("bContent");
		DAO dao = sqlSession.getMapper(DAO.class);
		dao.write(bTitle,bName,bContent);
		return "redirect:list";
	}
	@RequestMapping("/modify_view")
	public String modify_view(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession();
		if(session.getAttribute("uid") == null ||session.getAttribute("uid").equals("")) {
			return "redirect:login";
		}
		int bId = Integer.parseInt(req.getParameter("bId"));
		DAO dao = sqlSession.getMapper(DAO.class);
		DTO dto = dao.contentView(bId);
		model.addAttribute("modify_view",dto);
		return "modify_view";
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(HttpServletRequest req, Model model) {
		String bId = req.getParameter("bId");
		String bTitle =req.getParameter("bTitle");
		String bName = req.getParameter("bName");
		String bContent = req.getParameter("bContent");
		DAO dao = sqlSession.getMapper(DAO.class);
		dao.modify(Integer.parseInt(bId),bTitle,bName,bContent);
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete")
	
	public String delete(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		if(session.getAttribute("uid")==null || session.getAttribute("uid").equals("")) {
			return "redirect:login";
		}
		String bId= req.getParameter("bId");
		DAO dao = sqlSession.getMapper(DAO.class);
		dao.delete(Integer.parseInt(bId));
		return "redirect:list";
		
	}
	
	@RequestMapping("/login")
	public String doLogin() {
		return "login";
	}
	@RequestMapping(value="/trylogin",method=RequestMethod.POST) 
	public String trylogin(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		String userid = req.getParameter("userid");
		String passcode = req.getParameter("passcode");
		DAO dao = sqlSession.getMapper(DAO.class);
		int cnt = dao.checkuser(userid,passcode);
		if(cnt==1) {
			session.setAttribute("uid",userid);
		}else {
			return "redirect:login";
		}
		return "redirect:list";
	}
	
	@RequestMapping("/logout")
	//로그아웃할때
	public String doLogout(HttpServletRequest req , Model model) {
		HttpSession session = req.getSession();
		//로그아웃시 세션종료를 하고 리스트로 돌아간다
		session.invalidate();
		model.addAttribute("logout","Y");
		return "redirect:/list";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
