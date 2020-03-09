package dao;


import java.util.ArrayList;
import java.util.HashMap;

import data.Database;
import vo.UserVO;



public class UserDao {
	
private static UserDao instance;
	
	private UserDao(){} // 접근제어자를 다른 곳에서 쓰지 못하도록하였다.
	
/*	static { // 이렇게 짜야 원래 의도임
		instance = new UserDao();
	}*/
	
	public static UserDao getInstance(){
		if(instance == null){ // 이렇게 하면 두번 호출될 수도 있다
			instance = new UserDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	// 중복 체크
	
	public void insertUser(UserVO user){ // 데이터베이스에 접속해서 파라미터로 받은 것을 저장
		
/*		
		for (int i = 0; i < database.tb_user.size(); i++) {
			param = database.tb_user.get(i);
			boolean flag = true;
			for(String key : param.keySet()){
				String value = param.get(key);
				if(key.equals("ID")){
					if(user.getId().equals(value)) flag = false;
				}
			}
			if(flag) System.out.println("중복된 아이디 값이 있습니다."); return;// 중복된 아이디 값이 있으면  false가 되지 않기 때문에
		}*/
		database.tb_user.add(user); // 데이터베이스에 저장 // 이거까지만 했었음 //이제 중복체크를 만들어보자
	}

	public UserVO selectUser(HashMap<String, String> param) {
		UserVO rtnUser = null;
		for (int i = 0; i < database.tb_user.size(); i++) {
			UserVO user = database.tb_user.get(i);
			boolean flag = true;
			for(String key : param.keySet()){
				String value = param.get(key);
				if(key.equals("ID")){
					if(!user.getId().equals(value)) flag = false;
				}else if (key.equals("PASSWORD")){
					if(!user.getPassword().equals(value)) flag = false;
				}else  if (key.equals("NAME")){
					if(!user.getName().equals(value)) flag = false;
				}else {
					flag = false; // 존재하지 않는 키가 들어오면 false로 해줌
				}
			}
			if(flag) rtnUser = user;
		}
		return rtnUser;
	}

	public ArrayList<UserVO> selectUserList() {
		return database.tb_user;//테이블 전체를 넘겨줌
	}
}
