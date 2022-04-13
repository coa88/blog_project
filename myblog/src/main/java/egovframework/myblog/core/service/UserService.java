package egovframework.myblog.core.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import egovframework.myblog.core.vo.UserDTO;
import egovframework.myblog.core.vo.UserVo;

public interface UserService {
	//회원가입
	public int insUser(UserVo param);
	
	//회원리스트조회
	public List<UserVo> selUserList();
	
	//회원아이디조회
	public UserVo selUser(String param);
	
	public int selUser(UserVo param, HttpSession hs);
	
	//이웃
	
	public int chkNeighbor(UserDTO param);
	public int selNeighbor(UserDTO param);
	public List<UserDTO> selNeighborList(UserDTO param);
	
}
