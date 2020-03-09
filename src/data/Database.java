package data;

import java.util.ArrayList;

import vo.UserVO;
import dao.UserDao;

public class Database {
private static Database instance;
	
	private Database(){}
	
	public static Database getInstance(){
		if(instance == null){
			instance = new Database();
		}
		return instance;
	}
	
	// 테이블 형태로 데이터가 저장되어 있다. UserVO가 하나의 회원정보임 UserVO가 여러개 있으면 테이블임
	
	public ArrayList<UserVO> tb_user = new ArrayList<UserVO>(); // 유저 테이블 //실제 데이터베이스가 아님 // 데이터베이스가 초기화 됨
	
	{
		UserVO user = new UserVO();
		user.setId("admin");
		user.setPassword("admin");
		user.setName("관리자");
		tb_user.add(user);
		
	}
}
