package dao;

import static org.junit.Assert.*;

import java.awt.List;

import org.junit.Test;

import vo.BusVo;

public class BusDaoTest {

	// 실행될 메소드보다 test메소드를 먼저 만드는 방법도 있다.
	// TDD : Test Driven Development 
	// 운영코드보다 test 코드를 이용하여 개발한다는 의미가 있다.
	
	
	
	@Test
	public void test() {
		//given
		BusDao busDao = new BusDao();
		//when
		java.util.List<BusVo> busList = busDao.busList();
		//then
//		assertEquals(busList);
		
	}

}
