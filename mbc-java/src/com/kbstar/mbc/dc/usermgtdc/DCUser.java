package com.kbstar.mbc.dc.usermgtdc;

import java.util.List;

import com.kbstar.ksa.das.PersistenceException;
import com.kbstar.ksa.das.RecordNotFoundException;
import com.kbstar.ksa.das.ibatis.SqlMapper;
import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.exception.FrameworkException;
import com.kbstar.ksa.logger.IKesaLogger;
import com.kbstar.ksa.logger.KesaLogHelper;
import com.kbstar.ksa.util.ObjectUtil;
import com.kbstar.mbc.dc.usermgtdc.dto.PageDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.TreeDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.UserDDTO;

public class DCUser implements IDCUser {

	protected IKesaLogger logger = KesaLogHelper.getBiz();

	public List<Page> getListPage(PageDDTO pageDDTO) throws BusinessException {

		List<Page> pageList = null;
		String pageCount;
		String outptLineCnt;
		try {
			// 요청페이지에 해당하는 그리드 데이터 조회
			pageList = SqlMapper.getSqlMapClient().queryForList(
					"user.getListPage", pageDDTO);
			// 총라인수 조회
			pageCount = (String) SqlMapper.getSqlMapClient().queryForObject(
					"user.getPageCount");
			// 출력라인수 조회 : 업무에 따라 필요여부 결정
			outptLineCnt = String.valueOf(pageList.size());
			// 총라인수와 출력라인수를 출력 List의 첫번째 항목에 매핑
			if (pageList.size() > 0 && pageList != null) {
				((Page) pageList.get(0)).setTotalLineCnt(pageCount);
				((Page) pageList.get(0)).setOutptLineCnt(outptLineCnt);
			}

		} catch (PersistenceException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} catch (FrameworkException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		return pageList;

	}

	public List<Tree> getListTree(TreeDDTO treeDDTO) throws BusinessException {

		List<Tree> TreeList = null;

		try {
			TreeList = SqlMapper.getSqlMapClient().queryForList(
					"user.getListTree", treeDDTO);
		} catch (PersistenceException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (FrameworkException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return ObjectUtil.copyForList(Tree.class, TreeList);

	}

	public List<User> getListUser(UserDDTO userDDTO) throws BusinessException {

		List<User> UserList = null;

		try {
			UserList = SqlMapper.getSqlMapClient().queryForList(
					"user.getListUser", userDDTO);

		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (FrameworkException e) {
			e.printStackTrace();
		}

		return ObjectUtil.copyForList(User.class, UserList);

	}

	public void crudUser(UserDDTO[] userDDTOs) throws BusinessException {

		logger.debug("crudUser 메소드로 들어왔음");

		String crud = null;
		logger.debug("userDDTO 길이" + userDDTOs.length);
		try {

			for (int i = 0; i < userDDTOs.length; i++) {

				crud = (userDDTOs[i].getCrud()).toUpperCase();
				logger.debug("crud = " + crud);

				if (crud.equals("C")) {
					SqlMapper.getSqlMapClient().insert("user.insertUser",
							userDDTOs[i]);
				} else if (crud.equals("U")) {
					SqlMapper.getSqlMapClient().update("user.updateUser",
							userDDTOs[i]);
				} else if (crud.equals("D")) {
					SqlMapper.getSqlMapClient().update("user.deleteUser",
							userDDTOs[i]);
				}
			}
		} catch (RecordNotFoundException e) {
			throw new BusinessException("B0000001", "processCode", e);
		} catch (PersistenceException e) {
			throw new BusinessException("B0000002", "processCode", e);
		} catch (FrameworkException e) {
			throw new BusinessException("B0000002", "processCode", e);
		}
	}

}
