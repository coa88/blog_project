package egovframework.myblog.core.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.myblog.core.dao.BoardDao;
import egovframework.myblog.core.service.BoardMapper;
import egovframework.myblog.core.vo.BoardDTO;
import egovframework.myblog.core.vo.BoardVo;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	@Override // 글쓰기
	public int insPost(BoardDTO param) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.insPost(param);
	}
	
	@Override // 카테고리 추가
	public int insCategory(BoardDTO param) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.insCategory(param);
	}
	
	@Override // 게시물 선택
	public BoardDTO selBoard(BoardDTO param) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.selBoard(param);
	}
	
	@Override // 게시물 리스트
	public List<BoardDTO> selBoardList(BoardDTO param) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.selBoardList(param);
	}
	
	@Override // 카테고리 리스트
	public List<BoardDTO> selCategoryList(BoardDTO param) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.selCategoryList(param);
	}
	
	@Override
	public int getBoardListCnt(BoardDTO param) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.getBoardListCnt(param);
	}

	@Override // 글수정
	public int updBoardDetail(BoardVo param) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.updBoardDetail(param);
	}
	
	@Override // 카테고리 수정
	public int updCategory(BoardDTO param) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.updCategory(param);
	}
	
	
	@Override // 글삭제
	public int delBoardDetail(BoardVo param) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.delBoardDetail(param);
	}
	
	@Override // 카테고리 삭제
	public int delCategory(BoardDTO param) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.delCategory(param);
	}
	
}
