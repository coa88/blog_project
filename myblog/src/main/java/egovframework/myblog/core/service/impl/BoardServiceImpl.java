package egovframework.myblog.core.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.myblog.core.dao.BoardDao;
import egovframework.myblog.core.service.BoardService;
import egovframework.myblog.core.utils.MyFileUtils;
import egovframework.myblog.core.utils.SecurityUtils;
import egovframework.myblog.core.vo.BoardDTO;
import egovframework.myblog.core.vo.BoardVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	final BoardDao boardDao;
	final private HttpSession hs;
	final MyFileUtils myFileUtils;
	
	@Override //글쓰기
	public int insPost(BoardDTO param) {
		param.setUserId(SecurityUtils.getLoginUserPk(hs));
		
		return boardDao.insPost(param);
	}
	
	@Override
	public int insCategory(BoardDTO param) {
		return boardDao.insCategory(param);
	}
	
	@Override
	public BoardDTO selBoard(BoardDTO param) {
		return boardDao.selBoard(param);
	}
	
	@Override
	public List<BoardDTO> selBoardList(BoardDTO param) {
		return boardDao.selBoardList(param);
	}
	
	@Override
	public List<BoardDTO> selCategoryList(BoardDTO param) {
		return boardDao.selCategoryList(param);
	}
	
	@Override
	public int getBoardListCnt(BoardDTO param) {
		return boardDao.getBoardListCnt(param);
	}
	
	@Override
	public int updBoardDetail(BoardVo param) {
		return boardDao.updBoardDetail(param);
	}
	
	@Override
	public int updCategory(BoardDTO param) {
		return boardDao.updCategory(param);
	}
	
	@Override
	public int delBoardDetail(BoardVo param) {
		return boardDao.delBoardDetail(param);
	}
	
	@Override
	public int delCategory(BoardDTO param) {
		return boardDao.delCategory(param);
	}
	
	@Override
	public String saveBoardImg(MultipartFile img) {
		String userPk = SecurityUtils.getLoginUserPk(hs);
		String path = "/resources/images/temp/" + userPk;
		try {
			String fileNm = myFileUtils.transferTo(img, path);
			return path + "/" + fileNm;
		} catch(Exception e) {
			return null;
		}
	}
}
