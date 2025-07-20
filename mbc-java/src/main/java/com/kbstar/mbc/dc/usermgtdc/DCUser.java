package com.kbstar.mbc.dc.usermgtdc;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kbstar.ksa.das.NewPersistenceException;
import com.kbstar.ksa.das.NewRecordNotFoundException;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.exception.NewFrameworkException;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.util.NewObjectUtil;
import com.kbstar.mbc.dc.usermgtdc.dto.PageDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.TreeDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.UserDDTO;
import com.kbstar.mbc.dc.usermgtdc.repository.UserRepository;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;

/**
 * 사용자 관리 도메인 컴포넌트
 * 
 * 프로그램명: DCUser.java
 * 설명: 사용자 관리 관련 데이터베이스 작업을 수행하는 도메인 컴포넌트
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 사용자 CRUD 작업
 * - 페이징 처리
 * - 트리 구조 데이터 조회
 * - MyBatis/JPA 유연한 전환 지원
 * 
 * @version 1.0
 */
@Repository
public class DCUser implements IDCUser {

	protected NewIKesaLogger logger = NewKesaLogHelper.getBiz();

	@Autowired
	private UserRepository userRepository;

	// IDCUser interface methods
	@Override
	public String getUserId() {
		return null; // Stub implementation
	}

	@Override
	public void setUserId(String userId) {
		// Stub implementation
	}

	@Override
	public String getUserName() {
		return null; // Stub implementation
	}

	@Override
	public void setUserName(String userName) {
		// Stub implementation
	}

	@Override
	public String getUserEmail() {
		return null; // Stub implementation
	}

	@Override
	public void setUserEmail(String userEmail) {
		// Stub implementation
	}

	@Override
	public List<HashMap> getUserList(ICommonDTO commonDto) throws NewBusinessException {
		// Stub implementation
		return new ArrayList<>();
	}

	@Override
	public void crudUser(UserDDTO[] userDDTOs) throws NewBusinessException {
		logger.debug("crudUser method started");

		String crud = null;
		logger.debug("userDDTO count" + userDDTOs.length);
		try {

			for (int i = 0; i < userDDTOs.length; i++) {

				crud = (userDDTOs[i].getCrud()).toUpperCase();
				logger.debug("crud = " + crud);

				if (crud.equals("C")) {
					userRepository.insertUser(userDDTOs[i]);
				} else if (crud.equals("U")) {
					userRepository.updateUser(userDDTOs[i]);
				} else if (crud.equals("D")) {
					userRepository.deleteUser(userDDTOs[i]);
				}
			}
		} catch (Exception e) {
			throw new NewBusinessException("B0000002", "processCode", e);
		}
	}

	@Override
	public List<User> getListUser(UserDDTO userDDTO) throws NewBusinessException {
		List<User> UserList = null;

		try {
			UserList = userRepository.getListUser(userDDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return NewObjectUtil.copyForList(User.class, UserList);
	}

	public List<Page> getListPage(PageDDTO pageDDTO) throws NewBusinessException {

		List<Page> pageList = null;
		String pageCount;
		String outptLineCnt;
		try {
			// Query page data that matches the request conditions
			pageList = userRepository.getListPage(pageDDTO);
			// Get total count
			pageCount = userRepository.getPageCount(pageDDTO);
			// Get output count: may need to be calculated separately
			outptLineCnt = String.valueOf(pageList.size());
			// Set total count and output count in the first item of the List
			if (pageList.size() > 0 && pageList != null) {
				((Page) pageList.get(0)).setTotalLineCnt(Integer.parseInt(pageCount));
				((Page) pageList.get(0)).setOutptLineCnt(Integer.parseInt(outptLineCnt));
			}

		} catch (Exception e) {
			// TODO Add proper catch handling
			e.printStackTrace();
		}
		return pageList;

	}

	public List<Tree> getListTree(TreeDDTO treeDDTO) throws NewBusinessException {

		List<Tree> TreeList = null;

		try {
			TreeList = userRepository.getListTree(treeDDTO);
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return NewObjectUtil.copyForList(Tree.class, TreeList);

	}
}
