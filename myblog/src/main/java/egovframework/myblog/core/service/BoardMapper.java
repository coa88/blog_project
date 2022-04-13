package egovframework.myblog.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import egovframework.myblog.core.vo.BoardDTO;
import egovframework.myblog.core.vo.BoardVo;

@Mapper
public interface BoardMapper {
	int insPost(BoardDTO param);
	int insCategory(BoardDTO param);
	
	BoardDTO selBoard(BoardDTO param);
	List<BoardDTO> selBoardList(BoardVo param);
	List<BoardDTO> selCategoryList(BoardVo param);
	int getBoardListCnt(BoardDTO param);
	
	int updBoardDetail(BoardVo param);
	int updCategory(BoardDTO param);
	
	int delBoardDetail(BoardVo param);
	int delCategory(BoardDTO param);
	

}
