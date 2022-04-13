package egovframework.myblog.core.dao;

import java.util.List;

import egovframework.myblog.core.vo.BoardDTO;
import egovframework.myblog.core.vo.BoardVo;

public interface BoardDao {
	public int insPost(BoardDTO param);
	public int insCategory(BoardDTO param);
	
	public BoardDTO selBoard(BoardDTO param);
	public List<BoardDTO> selBoardList(BoardDTO param);
	public List<BoardDTO> selCategoryList(BoardDTO param);
	public int getBoardListCnt(BoardDTO param); //페이징 총 게시글 개수
	
	public int updBoardDetail(BoardVo param);
	public int updCategory(BoardDTO param);
	
	public int delBoardDetail(BoardVo param);
	public int delCategory(BoardDTO param);

}
