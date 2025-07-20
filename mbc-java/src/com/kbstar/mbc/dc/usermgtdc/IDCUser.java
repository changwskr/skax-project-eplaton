package com.kbstar.mbc.dc.usermgtdc;

import java.util.List;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.mbc.dc.usermgtdc.dto.PageDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.TreeDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.UserDDTO;


public interface IDCUser {
	public List<User> getListUser(UserDDTO userDDTO) throws BusinessException;
	public void crudUser(UserDDTO[] userDDTOs) throws BusinessException;
    public List<Tree> getListTree(TreeDDTO treeDDTO) throws BusinessException;
    public List<Page> getListPage(PageDDTO pageDDTO) throws BusinessException;

}
