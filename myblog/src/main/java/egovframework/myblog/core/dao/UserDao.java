package egovframework.myblog.core.dao;

import java.util.List;

import egovframework.myblog.core.vo.UserDTO;
import egovframework.myblog.core.vo.UserVo;

public interface UserDao {
	public int insUser(UserVo param);
	
	public List<UserVo> selUserList();
	public UserVo selUser(UserVo param);
	
	//이웃
	public int insNeighbor(UserDTO param);
	
	public int selNeighbor(UserDTO param);
	public List<UserDTO> selNeighborList(UserDTO param);
	
	public int delNeighbor(UserDTO param);
}
