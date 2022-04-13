package egovframework.myblog.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyFileUtils {

	final HttpSession session;
	
//	@Value("E:/eGovFrameDev-3.10.0-64bit/workspace/myblog/src/main/webapp") 
	private String uploadImagePath;
	
	//폴더 만들기
	public void makeFolders(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}
	
	//폴더 삭제
	public void delFolder(String path) {
		String basePath = getRealPath(path);
		File folder = new File(basePath);
		while(folder.exists()) {
			File[] fileList = folder.listFiles();
			if(fileList == null) {
				return;
			}
			for (int j = 0; j < fileList.length; j++) {
				File f = fileList[j];
				
				if(f.isDirectory()) {
					delFolder(f.getPath());
				} else {
					f.delete();
				}
			}
			
			folder.delete();
		}
	}

	//파일 삭제
		public void delFile(String path) {
			String basePath = getRealPath(path);
			File file = new File(basePath);
			if (file.exists()) {
				// 파일 삭제 성공시
				if (file.delete()) {
				  System.out.println("파일을 삭제 성공");
				//파일 삭제 실패시
				} else {
				    System.out.println("파일 삭제 실패");
				}
		    // 지정한 경로에 파일이 존재안하는 경우 
			} else {
			  System.out.println("파일이 없습니다.");
			}	
		}
	
	//파일 삭제
	public void delFile(String path, String fileNm) {
		String basePath = getRealPath(path);
		File file = new File(basePath, fileNm);
		if (file.exists()) {
			// 파일 삭제 성공시
			if (file.delete()) {
			  System.out.println("파일을 삭제 성공");
			//파일 삭제 실패시
			} else {
			    System.out.println("파일 삭제 실패");
			}
	    // 지정한 경로에 파일이 존재안하는 경우 
		} else {
		  System.out.println("파일이 없습니다.");
		}	
	}
	
	public void moveFile(String beforePath, String afterPath) {
		try {
			FileUtils.moveFile(FileUtils.getFile(getRealPath(beforePath)), FileUtils.getFile(getRealPath(afterPath)));
		} catch(Exception e) {}
	}
	
	//스프링이 돌아가고 있는 절대주소값에 path값을 붙여서 가져오기
	public String getRealPath(String path) {
		uploadImagePath = session.getServletContext().getRealPath("/"); 
		return uploadImagePath + path;
	}
	
	public String getRandomFileNm() {
		return UUID.randomUUID().toString();
	}
	
	public String getExt(String fileNm) {
		return fileNm.substring(fileNm.lastIndexOf(".") + 1);
	}
	
	public String getRandomFileNm(String fileNm) {
		return getRandomFileNm() + "." + getExt(fileNm);
	}
	
	public String getRandomFileNm(MultipartFile file) {
		return getRandomFileNm(file.getOriginalFilename());
	}
	
	//파일저장 & 랜덤파일명 구하기
	public String transferTo(MultipartFile mf, String target) throws IllegalStateException, IOException {
		
		String fileNm = null;
		String basePath = getRealPath(target);
		makeFolders(basePath);
		
		fileNm = getRandomFileNm(mf.getOriginalFilename());
		File file = new File(basePath, fileNm); //파일이 저장되어야 할 위치정보!!!
		mf.transferTo(file);
		
		return fileNm;
	}
	
	//배열파일저장 & 랜덤파일명 구하기
	public String transferTo(MultipartFile[] mf, String target) throws IllegalStateException, IOException {
		
		String fileNm = null;
		String basePath = getRealPath(target);
		System.out.println("path : "+basePath);
		makeFolders(basePath);
		
		for(int i=0;i<mf.length;i++) {
			fileNm = getRandomFileNm(mf[i].getOriginalFilename());
			File file = new File(basePath, fileNm); //파일이 저장되어야 할 위치정보!!!
			mf[i].transferTo(file);
		}
		
		return fileNm;
	}
}