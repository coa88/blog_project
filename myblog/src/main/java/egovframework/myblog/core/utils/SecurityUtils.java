package egovframework.myblog.core.utils;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import egovframework.myblog.core.vo.UserVo;

public class SecurityUtils {

	//패스워드 암호화
	public static String hashPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}
	
	//비밀번호 확인
	public static boolean checkPassword(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}
	
	//로그인 정보 가져오기
	public static UserVo getLoginUser(HttpSession hs) {
		return(UserVo)hs.getAttribute("loginUser");
	}
	
	//로그인유저pk 가져오기
		public static String getLoginUserPk(HttpSession hs) {
			return getLoginUser(hs).getUserId();
		}
	
	//로그인 상태 확인
	public static boolean loginStatus(HttpSession hs) {
		return getLoginUser(hs) != null;
	}
}
