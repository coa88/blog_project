package egovframework.myblog.core.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.myblog.core.dao.UserDao;
import egovframework.myblog.core.service.UserMapper;
import egovframework.myblog.core.vo.UserDTO;
import egovframework.myblog.core.vo.UserVo;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insUser(UserVo param) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.insUser(param);
	}
	
	@Override
	public List<UserVo> selUserList() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.selUserList();
	}
	
	@Override
	public UserVo selUser(UserVo param) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.selUser(param);
	}
	
	//이웃
	@Override
	public int insNeighbor(UserDTO param) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.insNeighbor(param);
	}
	
	@Override
	public int selNeighbor(UserDTO param) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.selNeighbor(param);
	}
	
	@Override
	public List<UserDTO> selNeighborList(UserDTO param) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.selNeighborList(param);
	}
	
	@Override
	public int delNeighbor(UserDTO param) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.delNeighbor(param);
	}
}
