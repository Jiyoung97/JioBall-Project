package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.SupportDao;
import dao.impl.SupportDaoImpl;
import dto.Support;
import dto.SupportFile;
import service.face.SupportService;
import util.Paging;

public class SupportServiceImpl implements SupportService{
	
	private SupportDao supportDao = new SupportDaoImpl();



	@Override
	public List<Support> getSupportlist(Paging paging, String search, String type) {
		
		List<Support> list = supportDao.selectSupportAll(JDBCTemplate.getConnection(), paging, search, type);
		
		return list;
	}
	
	@Override
	public Paging getSupportPaging(HttpServletRequest req, String search, String type) {
		//전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
//		System.out.println("getPaging search = "+search);
						
		int curPage = 0;
		if( param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}else {
//			System.out.println("[WARN] BoardService getPagin() - curPage값이 null이거나 비어있음");
		}
						
		//총 게시글 수 조회하기
		int totalCount = 0;
		if(search != null && !"".equals(search)) {
			totalCount = supportDao.selectSupportCntAll(JDBCTemplate.getConnection(), search, type);
		}else {
			totalCount = supportDao.selectSupportCntAll(JDBCTemplate.getConnection(),type);
		}	
				
		Paging paging = new Paging(totalCount, curPage);
						
		return paging;
		
	}
	
	@Override
	public Support getSupportNo(HttpServletRequest req) {
		Support supportNo = new Support();
		
		supportNo.setSupportNo(Integer.parseInt(req.getParameter("supportno")));
		return supportNo;
	}
	
	@Override
	public Support getSupportView(Support supportNo) {
		Support supportView = supportDao.selectSupportView(JDBCTemplate.getConnection(), supportNo);
		return supportView;
	}
	
	@Override
	public void supportWrite(HttpServletRequest req, HttpServletResponse resp) {
		//파일업로드형식 인코딩이 맞는지 검사
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
				
		//multipart/form-data 형식이 아닐 경우 파일업로드 처리 중단
		if( !isMultipart ) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");
			return;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리에서 처리 사이즈 설정
		int maxMem = 1 * 1024 * 1024; // 1MB == 1048576B
		factory.setSizeThreshold(maxMem);
		
		//서블릿컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//임시파일 폴더
		String path = context.getRealPath("tmp");
		File tmpRepository = new File(path);
		tmpRepository.mkdir();
		factory.setRepository(tmpRepository);
		//파일업로드 수행 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
				
		//파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; //10MB
		upload.setFileSizeMax(maxFile);
		
		//파일 업로드된 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//게시글 정보 DTO
		Support support = new Support();
		
		//첨부파일 정보 DTO
		SupportFile supportFile = new SupportFile();
		
		//파일아이템 반복자
		Iterator<FileItem> iter = items.iterator();
				
		while( iter.hasNext() ) {
			FileItem item = iter.next();
				
			//--- 1) 빈 파일에 대한 처리 ---
			if( item.getSize() <= 0 ) {
						
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}
					
					
			//--- 2) 폼 필드에 대한 처리 ---
			if( item.isFormField() ) {
						
				//키 추출하기
				String key = item.getFieldName();
						
				//값 추출하기
				String value = null;
				try {
					 value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
						
				//key에 맞게 value를 DTO에 삽입
				if( "title".equals(key) ) {
					support.setSupportTitle(value);
							
				} else if ( "content".equals(key) ) {
					support.setSupportContent(value);
						
				} else if ("type".equals(key)) {
					support.setSupportType(value);
				}
						
			} //if( item.isFormField() ) end
			
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID생성
				String uid = UUID.randomUUID().toString().split("-")[0]; //8자리 UUID
				
				//파일 업로드 폴더
				File uploadFolder = new File( context.getRealPath("upload") );
				uploadFolder.mkdir();

				//파일명 처리
				String origin = item.getName();
				String stored = uid;
				
				//업로드할 파일 객체 생성하기
				File up = new File(uploadFolder, stored);
				
				try {
					item.write(up); // 임시파일 -> 실제 업로드 파일
					item.delete(); // 임시파일 제거
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보를 DTO객체에 저장하기
				supportFile.setSupportFileOriginName(origin);
				supportFile.setSupportFileStoredName(stored);
				supportFile.setSupportFileSize( (int)item.getSize() );
				supportFile.setSupportFilePath(uploadFolder.getPath());
				
			} //if( !item.isFormField() ) end
			
		} //while( iter.hasNext() ) end
		
		//DB연결 객체
				Connection conn = JDBCTemplate.getConnection();
				
				//게시글 번호 생성
				int supportno = supportDao.selectSupportNo(conn);
				
				
				//게시글 정보 삽입
				support.setSupportNo(supportno);
				if(support.getSupportTitle()==null || "".equals(support.getSupportTitle())) {
					support.setSupportTitle("(제목없음)");
				}else if(support.getSupportContent() == null || "".equals(support.getSupportContent())) {
					support.setSupportContent("(내용없음)");
				}
				
				//작성자
//				support.setSupportWriter(( (String) req.getSession().getAttribute("teamid") ));
				
				if( supportDao.insertSupportWrite(conn, support) > 0 ) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
				
				
				//첨부파일 정보 삽입
				if( supportFile.getSupportFileSize() != 0 ) {
					supportFile.setSupportNo(supportno);
					
					if( supportDao.insertSupportFile(conn, supportFile) > 0 ) {
						JDBCTemplate.commit(conn);
					} else {
						JDBCTemplate.rollback(conn);
					}
		
	
				}
	}
	
	@Override
	public SupportFile getSupportFileView(Support support) {
		
		SupportFile supportFile =  supportDao.selectSupportFileView(JDBCTemplate.getConnection(),support);
		return supportFile;
	}
	
	@Override
	public void answerWrite(HttpServletRequest req) {
		Support support = new Support();
		
		support.setSupportNo(Integer.parseInt(req.getParameter("supportno")));
		support.setSupportAnswer(req.getParameter("answer"));
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = supportDao.updateAnswer(conn,support);
		
		if(res>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
}
