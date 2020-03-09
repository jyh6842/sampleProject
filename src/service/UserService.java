package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dao.UserDao;
import data.Session;
import vo.UserVO;

public class UserService {
	
	private static UserService instance;
	
	private UserService(){}
	
	public static UserService getInstance(){
		if(instance == null){
			instance = new UserService();
		}
		return instance;
	}
	
	UserDao userDao = UserDao.getInstance();
	//회원가입
	public void join(){
		Scanner s = new Scanner(System.in);
		
		String id = null;
		
		UserVO idCheck = null;
		// 중복된 아이디가 들어가지 않도록 하자
		do {
			System.out.print("아이디 : ");
			id = s.nextLine();
			
			HashMap<String, String> param = new HashMap<>();
			param.put("ID", id);
			idCheck = userDao.selectUser(param);
			if(idCheck != null){
				System.out.println("사용할 수 없는 아이디 입니다.");
			}
		} while (idCheck != null);
		
		System.out.print("비밀번호 : ");
		String password = s.nextLine();
		System.out.print("이름 : ");
		String name = s.nextLine();
		
		UserVO user = new UserVO();
		// 조장 이수아로 하자
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		
		
		userDao.insertUser(user);
		System.out.println("가입해주셔서 감사합니다.");
		
		
	}
	
	//로그인
	public void login(){
		Scanner s = new Scanner(System.in);
		System.out.print("아이디 : ");
		String id = s.nextLine();
		System.out.print("비밀번호 : ");
		String password = s.nextLine();
		
		//데이터베이스에 접속해서 아이디와 비밀번호 둘다 일치하는 사람 찾기
		HashMap<String, String> param = new HashMap<>();
		param.put("ID", id);
		param.put("PASSWORD", password);
		
		UserVO user = userDao.selectUser(param);
		
		if(user == null){
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
		}else{
			System.out.println("로그인 성공!");
			System.out.println(user.getName() + "님 환영합니다.");
			
			//세션에 저장, 세션이란 브라우져가 켜져 있는 동안에만 유지되는 공간
			Session.loginUser = user;
		}
		
	}
	
	//회원목록
	public void userList(){
		ArrayList<UserVO> userList = userDao.selectUserList(); // 데이터베이스를 넘겨준다.
		
		System.out.println("-----------------------------");
		System.out.println("번호\t아이디\t이름");
		System.out.println("-----------------------------");
		for (int i = userList.size()-1 ; 0 <= i ; i--) {
			UserVO user = userList.get(i);
			System.out.println(i+1 +"\t" + user.getId() + "\t" + user.getName());
		}
		System.out.println("-----------------------------");
	}
}
