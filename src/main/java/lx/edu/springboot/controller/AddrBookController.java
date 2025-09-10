package lx.edu.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lx.edu.springboot.dao.AddrBookDAO;
import lx.edu.springboot.vo.AddrBookVO;

@Controller
public class AddrBookController {
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	AddrBookDAO dao;		
	
	@RequestMapping("/addrbook_form.do")
	public String form() {
		return "addrbook_form"; //jsp file name
	}

	
	@RequestMapping("/insert.do") // (value="/inser.do")
	public String insert(AddrBookVO vo) throws Exception {
		// 1. Client(웹브라우저)가 전송한 데이터를 request에서 꺼내온다
		System.out.println(vo);
		// 2. 
		dao.insertDB(vo);
		return "redirect:addrbook_list.do";
	}	

	@RequestMapping("/addrbook_list.do")
	public String list(HttpSession session, HttpServletRequest req) throws Exception {
		
		/*
		 * if(session.getAttribute("userId")==null) { return "login"; }
		 */		 
		List<AddrBookVO> list = dao.getDBList();
		req.setAttribute("data", list);
		return "addrbook_list";
	}
	/*
	 * public ModelAndView list() throws Exception { 
	 * ModelAndView result = new ModelAndView(); 
	 * // list를 request에 넣는다. 
	 * // req.setAttribute("data", list);
	 * 	result.addObject("data", list); 
	 * 	result.setViewName("addrbook_list"); }
	 */
	
	@RequestMapping("/edit.do")
	public String edit(HttpServletRequest req, @RequestParam(value="abId")int abId) throws Exception {
				
		AddrBookVO vo = dao.getDB(abId);
		req.setAttribute("ab", vo);
		
		return("addrbook_edit_form");		
		
	}
	
	@RequestMapping("/update.do")
	public String upadateDB(AddrBookVO ab) throws Exception {
		dao.updateDB(ab);
		return "redirect:addrbook_list.do";
	}
	
	
	/*
	 * @RequestMapping("/addrbook_list.do") public String list(HttpServletRequest req) throws Exception { 
	 * List<AddrBookVO> list = dao.getDBList(); 
	 * // list를 request에 넣는다. 
	 * req.setAttribute("data", list);
	 *  return "addrbook_list"; 
	 *  }
	 */
}
