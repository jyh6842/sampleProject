package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import data.Database;
import vo.UserVO;

public class UserDaoTest {

	// 테스트 메소드 설정 방법
	// 메소드에 @Test 어노테이션을 붙인다. ==> 테스트 메소드로 인식
	// @Test 어노테이션이 붙은 테스트 메소드는
	// 접근제어자 : public 
	// 리턴타입 : void
	// public void를 써야 에러가 나지 않음 꼭 public void
	
	// junit 프레임워크의 실행 순서
	// @Before --> @Test --> @After
	
	UserDao userDao;
	
	@Before
	public void setup(){
		userDao = UserDao.getInstance();
		
	}
	
	// 테스트 메소드 이름 : 운영메소드 명 + Test
	// insertUser 메소드 테스트
	
	@Test
	public void insertUserTest() {
		// insertUser 메소드를 실행하기 위해 필요한 것
		// 1. userDao의 인스턴스가 필요
		// 2. insertUser메소드 인자 ==> UserVO
		
		// given : 나한테 주어진 상황
		
		UserVO userVo = new UserVO();
		
		// 사용자로부터 입력 받았다고 가정한 값을 userVO에 넣어준다.
		userVo.setId("brown");
		userVo.setPassword("brown_pass");
		userVo.setName("브라운");
		
		// when : 행동(메소드 실행)
		userDao.insertUser(userVo);// insertUser() 리턴값이 vo 인데 어떻게 정상인지 확인할수 있을까? 정상적으로 실행되었다면 
		
		// then : 그 결과 (한건의 사용자를 추가했으므로 tb_user 데이터는 2건이 된다.
		assertEquals(2, Database.getInstance().tb_user.size());

	}
	
	
	//	art + shift + j  : test 할 메소드 주석
	/**
	 * selectUserList (사용자 전체 리스트 테스트)
	 */
	@Test
	public void selectUserListTest(){
		//given (userDao 인스턴스)
		
		//when
		ArrayList<UserVO> userList = userDao.selectUserList();
		
		//then
		assertNotNull(userList); // 널 값이 아니면 ok
		assertTrue(userList.size() >= 1); // true면 ok
	}
	
	//selectUserTest
	@Test
	public void selectUserSuccessTest(){
		//given (userDao 인스턴스 --> @Before에서 처리했음, 인자가 필요
		HashMap<String, String> paramMap = new HashMap<String, String>();
		
		//Database가 초기화 될 때 admin이라는 사용자가 tb_user 리스트에 들어가 있다.
		paramMap.put("ID", "admin");
		
		//when
		UserVO userVO = userDao.selectUser(paramMap);

		//then
		UserVO expectedUserVO = new UserVO();
		expectedUserVO.setId("admin");
		expectedUserVO.setPassword("admin");
		expectedUserVO.setName("관리자");
		
		assertNotNull(userVO);
		assertEquals("admin", userVO.getPassword());
		assertEquals("관리자", userVO.getName());// 이것보다
		
		// 객체간 비교는 equals를 사용해야 한다.
		// assertEquals 쓰는 이유는 여러개를 비교할 경우에 이게 더 효율적
		assertEquals(expectedUserVO, userVO);// 여기서 페일러 에러가 아님
		
		//객체의 값을 비교하기 위해서는 equals 메소드를 override 해야한다.
		//객체 동일(동일이면 동치), 동치(동치면 동일? ㄴㄴ)
		//UserVO userVO = new UserVO();
		//UserVO userVO2 = UserVO();
		//userVO 와 userVO2 는 동일
		
		//UserVO userVO = new UserVO();
		//userVO.setId("admin");
		//userVO.setPassword("admin");
		//userVO.setName("관리자");
		
		//UserVO userVO2 = new UserVO();
		//userVO2.setId("admin");
		//userVO2.setPassword("admin");
		//userVO2.setName("관리자");
		
		//userVO와 userVO2는 같은값을 갖는다. 동치
		//if(userVO == userVO2) ==> 동일에 대한 비교
		//if(userVO.equals(userVO2)) ==> 동치에 대한 비교
	}
	
	//selectUser의 실패 케이스
	@Test
	public void selectUserFailTest(){
		//given : userDao 인스턴스(이미 @before에서 만듬), HashMap
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("ID", "NOT_EXISTS_ID");
		
		//when
		UserVO userVO = userDao.selectUser(paramMap);

		//then
		assertNull(userVO);
	}
	
	@Test
	public void selectUserNotExistsKEYTest(){
		//given : userDao 인스턴스(이미 @before에서 만듬), HashMap
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("NONE", "NOT_EXISTS_ID");
				
		//when
		UserVO userVO = userDao.selectUser(paramMap);

		//then
		assertNull(userVO);
	}
	
	

	

}
