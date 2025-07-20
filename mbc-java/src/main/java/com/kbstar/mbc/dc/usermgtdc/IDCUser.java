package com.kbstar.mbc.dc.usermgtdc;

import java.util.List;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.mbc.dc.usermgtdc.dto.PageDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.TreeDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.UserDDTO;

public interface IDCUser {
    public List<User> getListUser(UserDDTO userDDTO) throws NewBusinessException;

    public void crudUser(UserDDTO[] userDDTOs) throws NewBusinessException;

    public List<Tree> getListTree(TreeDDTO treeDDTO) throws NewBusinessException;

    public List<Page> getListPage(PageDDTO pageDDTO) throws NewBusinessException;

}
