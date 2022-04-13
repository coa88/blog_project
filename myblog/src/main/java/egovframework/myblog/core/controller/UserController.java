package egovframework.myblog.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.myblog.core.service.BoardService;
import egovframework.myblog.core.service.UserService;
import egovframework.myblog.core.vo.BoardDTO;
import egovframework.myblog.core.vo.UserDTO;
import egovframework.myblog.core.vo.UserVo;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	final private UserService userService;
	final private BoardService boardService;
	final private HttpSession hs;
	
	
	@GetMapping("/login.do")
	public String login() {
		return "user/login";
	}
	
	@ResponseBody
	@PostMapping("/login.do")
	public int login(@RequestBody UserVo param) {
		System.out.println("접속성공 : " + param.toString());
		return userService.selUser(param, hs);
	}
	
	@GetMapping("/logout.do")
	public String logout() { // 로그아웃시키기
		hs.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/join.do")
	public String join() {
		return "user/join";
	}
	
	@ResponseBody
	@PostMapping("/join.do")
	public Map<String, Object> join(@RequestBody UserVo param) {
		Map<String, Object> resultValue = new HashMap<>();
		
		resultValue.put("data", userService.insUser(param));
		return resultValue;
	}
	
	@GetMapping("/{id}/setting.do")
	public String setting(@PathVariable String id, BoardDTO param, Model model) {
		
		param.setUserId(id);
		
		model.addAttribute("cgList", boardService.selCategoryList(param));
		return "user/setting";
	}
	
	@ResponseBody
	@GetMapping("/setNeighbor.do") //이웃관리
	public Map<String, Object> checkNeighbor(@RequestParam String userId, @RequestParam String neighborId) {
		Map<String, Object> resultValue = new HashMap<>();
		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		dto.setNeighborId(neighborId);
		resultValue.put("data", userService.chkNeighbor(dto));
		return resultValue;
	}
	
	@ResponseBody
	@PostMapping("/checkId.do") //아이디 중복체크
	public Map<String, Object> checkId(@RequestBody UserVo param) {
		Map<String, Object> resultValue = new HashMap<>();
		
		int result = 0;
		String chkId = param.getUserId();
		if (userService.selUser(chkId) == null) {
			result = 0;
		} else {
			result = 1;
		}
		resultValue.put("data", result);
		return resultValue;
	}
}
