package com.kbstar.mbc.dc.usermgtdc;

import java.util.List;

import com.kbstar.ksa.das.NewPersistenceException;
import com.kbstar.ksa.das.NewRecordNotFoundException;
import com.kbstar.ksa.das.ibatis.NewSqlMapper;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.exception.NewFrameworkException;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.util.NewObjectUtil;
import com.kbstar.mbc.dc.usermgtdc.dto.PageDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.TreeDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.UserDDTO;

public class DCUser implements IDCUser {

	protected NewIKesaLogger logger = NewKesaLogHelper.getBiz();

	public List<Page> getListPage(PageDDTO pageDDTO) throws NewBusinessException {

		List<Page> pageList = null;
		String pageCount;
		String outptLineCnt;
		try {
			// Query page data that matches the request conditions
			pageList = NewSqlMapper.getSqlMapClient().queryForList(
					"user.getListPage", pageDDTO);
			// Get total count
			pageCount = (String) NewSqlMapper.getSqlMapClient().queryForObject(
					"user.getPageCount");
			// Get output count: may need to be calculated separately
			outptLineCnt = String.valueOf(pageList.size());
			// Set total count and output count in the first item of the List
			if (pageList.size() > 0 && pageList != null) {
				((Page) pageList.get(0)).setTotalLineCnt(pageCount);
				((Page) pageList.get(0)).setOutptLineCnt(outptLineCnt);
			}

		} catch (NewPersistenceException e) {
			// TODO Add proper catch handling
			e.printStackTrace();
		} catch (NewFrameworkException e) {
			// TODO Add proper catch handling
			e.printStackTrace();
		}
		return pageList;

	}

	public List<Tree> getListTree(TreeDDTO treeDDTO) throws NewBusinessException {

		List<Tree> TreeList = null;

		try {
			TreeList = NewSqlMapper.getSqlMapClient().queryForList(
					"user.getListTree", treeDDTO);
		} catch (NewPersistenceException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (NewFrameworkException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return NewObjectUtil.copyForList(Tree.class, TreeList);

	}

	public List<User> getListUser(UserDDTO userDDTO) throws NewBusinessException {

		List<User> UserList = null;

		try {
			UserList = NewSqlMapper.getSqlMapClient().queryForList(
					"user.getListUser", userDDTO);

		} catch (NewPersistenceException e) {
			e.printStackTrace();
		} catch (NewFrameworkException e) {
			e.printStackTrace();
		}

		return NewObjectUtil.copyForList(User.class, UserList);

	}

	public void crudUser(UserDDTO[] userDDTOs) throws NewBusinessException {

		logger.debug("crudUser method started");

		String crud = null;
		logger.debug("userDDTO count" + userDDTOs.length);
		try {

			for (int i = 0; i < userDDTOs.length; i++) {

				crud = (userDDTOs[i].getCrud()).toUpperCase();
				logger.debug("crud = " + crud);

				if (crud.equals("C")) {
					NewSqlMapper.getSqlMapClient().insert("user.insertUser",
							userDDTOs[i]);
				} else if (crud.equals("U")) {
					NewSqlMapper.getSqlMapClient().update("user.updateUser",
							userDDTOs[i]);
				} else if (crud.equals("D")) {
					NewSqlMapper.getSqlMapClient().update("user.deleteUser",
							userDDTOs[i]);
				}
			}
		} catch (NewRecordNotFoundException e) {
			throw new NewBusinessException("B0000001", "processCode", e);
		} catch (NewPersistenceException e) {
			throw new NewBusinessException("B0000002", "processCode", e);
		} catch (NewFrameworkException e) {
			throw new NewBusinessException("B0000002", "processCode", e);
		}
	}

}
