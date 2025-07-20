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
			// ��û�������� �ش��ϴ� �׸��� ������ ��ȸ
			pageList = SqlMapper.getSqlMapClient().queryForList(
					"user.getListPage", pageDDTO);
			// �Ѷ��μ� ��ȸ
			pageCount = (String) SqlMapper.getSqlMapClient().queryForObject(
					"user.getPageCount");
			// ��¶��μ� ��ȸ : ������ ���� �ʿ俩�� ����
			outptLineCnt = String.valueOf(pageList.size());
			// �Ѷ��μ��� ��¶��μ��� ��� List�� ù��° �׸� ����
			if (pageList.size() > 0 && pageList != null) {
				((Page) pageList.get(0)).setTotalLineCnt(pageCount);
				((Page) pageList.get(0)).setOutptLineCnt(outptLineCnt);
			}

		} catch (PersistenceException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		} catch (FrameworkException e) {
			// TODO �ڵ� ������ catch ���
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

		logger.debug("crudUser �޼ҵ�� ������");

		String crud = null;
		logger.debug("userDDTO ����" + userDDTOs.length);
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
