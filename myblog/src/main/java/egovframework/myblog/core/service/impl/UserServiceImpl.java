package egovframework.myblog.core.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import egovframework.myblog.core.dao.BoardDao;
import egovframework.myblog.core.dao.UserDao;
import egovframework.myblog.core.service.UserService;
import egovframework.myblog.core.utils.SecurityUtils;
import egovframework.myblog.core.vo.BoardDTO;
import egovframework.myblog.core.vo.UserDTO;
import egovframework.myblog.core.vo.UserVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	final private UserDao dao;
	final private BoardDao boardDao;
	
	
	//회원가입처리
	@Override
	public int insUser(UserVo param) {
		param.setUserPw(SecurityUtils.hashPassword(param.getUserPw()));
		if(dao.insUser(param) == 1) {
			BoardDTO dto = new BoardDTO();
			dto.setCategoryName("일기장");
			dto.setUserId(param.getUserId());
			boardDao.insCategory(dto);
			return 1;
		};
		return 0;
	}
	
	//회원목록
	@Override
	public List<UserVo> selUserList() {
		return dao.selUserList();
	}
	
	@Override
	public UserVo selUser(String param) {
		UserVo vo = new UserVo();
		vo.setUserId(param);
		
		UserVo data = dao.selUser(vo);
		
		if(data != null) {
			data.setUserPw(null);
		}
		
		return data;
	}
	
	@Override
	public int selUser(UserVo param, HttpSession hs) { //로그인 조회
		UserVo data = dao.selUser(param);
		
		//아이디 없음
		if(data == null) {
			return 0;
		}
		 
		//비밀번호 틀림
		if(!SecurityUtils.checkPassword(param.getUserPw(), data.getUserPw())) {
			return 2;
		}
		
		data.setUserPw(null);
		hs.setAttribute("loginUser", data);
		return 1;
	}
	
	@Override
	public int chkNeighbor(UserDTO param) {
		int state = dao.selNeighbor(param);
		if(state == 1) {
			return dao.delNeighbor(param);
		}
		return dao.insNeighbor(param);
	}
	
	@Override
	public int selNeighbor(UserDTO param) {
		return dao.selNeighbor(param);
	}
	
	@Override
	public List<UserDTO> selNeighborList(UserDTO param) {
		return dao.selNeighborList(param);
	}
	

}
