package egovframework.myblog.core.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import egovframework.myblog.core.service.BoardService;
import egovframework.myblog.core.service.UserService;
import egovframework.myblog.core.utils.SecurityUtils;
import egovframework.myblog.core.vo.BoardDTO;
import egovframework.myblog.core.vo.BoardVo;
import egovframework.myblog.core.vo.UserDTO;
import egovframework.myblog.core.vo.UserVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class BoardController {

	final private UserService userService;
	final private BoardService boardService;
	final private HttpSession hs;
	
	//블로그 게시물 리스트
	@GetMapping("/{id}.do") 
	public String board(@PathVariable String id, BoardDTO param, Model model) {
		UserVo loginUser = SecurityUtils.getLoginUser(hs);
		if(loginUser != null) { // 로그인했을때 이웃인지 확인
			UserDTO userParam = new UserDTO();
			userParam.setUserId(loginUser.getUserId());
			userParam.setNeighborId(id);
			model.addAttribute("neighborChk", userService.selNeighbor(userParam));
		}
		param.setUserId(id);
		
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
		
		model.addAttribute("hostId", id);
		model.addAttribute("hostNm", userService.selUser(id).getNm());
		model.addAttribute("data", boardService.selBoardList(param));
		model.addAttribute("cgList", boardService.selCategoryList(param));
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("pageNo", pageNo);
		
		
		return "board/list";
	}
	
	//블로그 카테고리 게시물 리스트
	@GetMapping("/{id}/{categoryName}.do") 
	public String board(@PathVariable String id, @PathVariable String categoryName, BoardDTO param, Model model) {
		UserVo loginUser = SecurityUtils.getLoginUser(hs);
		if(loginUser != null) { // 로그인했을때 이웃인지 확인
			UserDTO userParam = new UserDTO();
			userParam.setUserId(loginUser.getUserId());
			userParam.setNeighborId(id);
			model.addAttribute("neighborChk", userService.selNeighbor(userParam));
		}
		param.setUserId(id);
		
		int pageNo = param.getPageNo();
		if(param.getPageNo() == 0) {
			pageNo = 1;
		} 
		
		System.out.println("연결됨 ");
		System.out.println("카테고리이름 : " + param.getCategoryName());
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageNo); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(5); //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		
		param.setFirstRecordIndex(paginationInfo.getFirstRecordIndex());
		param.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int postCnt = boardService.getBoardListCnt(param);
		paginationInfo.setTotalRecordCount(postCnt); //전체 게시물 건 수
		
		model.addAttribute("hostId", id);
		model.addAttribute("hostNm", userService.selUser(id).getNm());
		model.addAttribute("data", boardService.selBoardList(param));
		model.addAttribute("cgList", boardService.selCategoryList(param));
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("pageNo", pageNo);
		
		
		return "board/list";
	}

	//글쓰기
	@GetMapping("/{id}/write.do") 
	public String write(@PathVariable String id, BoardDTO param, Model model) {
		param.setUserId(id);		
		
		model.addAttribute("cgData", boardService.selCategoryList(param));
		return "board/write";
	}
	
	@ResponseBody
	@PostMapping("/write.do") 
	public Map<String, Object> write(BoardDTO param) {
		Map<String, Object> result = new HashMap<>();
		
		result.put("result", boardService.insPost(param));
		result.put("userId", SecurityUtils.getLoginUserPk(hs));
		return result;
	}
	
	//이미지 파일 저장 후 주소값 반환
	@ResponseBody
	@PostMapping("/imageUpload.do")
	public Map<String, String> imageUpload(MultipartFile img) {
		System.out.println("fileOri : " + img.getOriginalFilename());
		Map<String, String> result = new HashMap<>();
		result.put("imageUrl", boardService.saveBoardImg(img));
		return result;
	}
	
	//글수정
	@GetMapping("/{id}/modify.do") 
	public String modify(@PathVariable String id, BoardDTO param, Model model) {
		param.setUserId(id);		
		
		model.addAttribute("cgData", boardService.selCategoryList(param));
		model.addAttribute("data", boardService.selBoard(param));
		
		return "board/write";
	}
	
	@ResponseBody
	@PostMapping("/modify.do") 
	public Map<String, Object> modify(BoardDTO param) {
		Map<String, Object> result = new HashMap<>();
		
		result.put("result", boardService.updBoardDetail(param));
		result.put("userId", SecurityUtils.getLoginUserPk(hs));
		return result;
	}
	
	//게시물 삭제
	@ResponseBody
	@DeleteMapping("/delPost/{iBoard}.do")
	public Map<String, Object> delPost(BoardVo param) {
		Map<String, Object> result = new HashMap<>();
		result.put("result",boardService.delBoardDetail(param));
		return result;
	}
	
	
	//카테고리 설정
	@ResponseBody
	@PostMapping("/modCategory.do")
	public Map<String, Object> modCategory(@RequestBody List<BoardDTO> params) {
		System.out.println("카테고리 수정 개수: " + params.size());

		for(int i = 0; i<params.size(); i++) {
			String type = params.get(i).getType(); //
			System.out.println("타입: " + type);
			
			if(type.equals("ins")) { //카테고리 추가
				System.out.println("추가성공");
				boardService.insCategory(params.get(i));
			}
			
			if(type.equals("del")) { //카테고리 삭제
				System.out.println("삭제성공");
				boardService.delCategory(params.get(i));
			}
			
			if(type.equals("upd")) { //카테고리 수정
				System.out.println("수정성공");
				boardService.updCategory(params.get(i));
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("result",1);
		return result;
	}
}

