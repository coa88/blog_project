package egovframework.myblog.core.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import egovframework.myblog.core.service.BoardService;
import egovframework.myblog.core.service.UserService;
import egovframework.myblog.core.utils.SecurityUtils;
import egovframework.myblog.core.vo.BoardDTO;
import egovframework.myblog.core.vo.UserDTO;
import egovframework.myblog.core.vo.UserVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MainController {
	
	final private BoardService boardService;
	final private UserService userService;
	final private HttpSession hs;
	
	@GetMapping("/main.do")
	public String main(BoardDTO param, Model model) {
		UserVo loginUser = SecurityUtils.getLoginUser(hs);
		if(loginUser != null) { // 로그인상태면 이웃리스트 화면으로
			UserDTO userParam = new UserDTO();
			userParam.setUserId(loginUser.getUserId());
			model.addAttribute("neighborList", userService.selNeighborList(userParam));
		} else {
			model.addAttribute("userList", userService.selUserList());
		}
		
		int pageNo = param.getPageNo();
		if(param.getPageNo() == 0) {
			pageNo = 1;
		} 
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageNo); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(5); //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		
		param.setFirstRecordIndex(paginationInfo.getFirstRecordIndex());
		param.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int postCnt = boardService.getBoardListCnt(param);
		paginationInfo.setTotalRecordCount(postCnt); //전체 게시물 건 수
		
		model.addAttribute("data", boardService.selBoardList(param));
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("pageNo", pageNo);
		
		return "main";
	}
	
}
	