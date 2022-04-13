package egovframework.myblog.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import egovframework.myblog.core.vo.UserDTO;
import egovframework.myblog.core.vo.UserVo;

@Mapper
public interface UserMapper {
	int insUser(UserVo param);
	
	List<UserVo> selUserList();
	UserVo selUser(UserVo param);
	
	//이웃
	int insNeighbor(UserDTO param);
	
	int selNeighbor(UserDTO param);
	List<UserDTO> selNeighborList(UserDTO param);
	
	int delNeighbor(UserDTO param);
	
}
