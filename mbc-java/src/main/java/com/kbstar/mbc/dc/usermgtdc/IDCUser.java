package com.kbstar.mbc.dc.usermgtdc;

import java.util.HashMap;
import java.util.List;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.UserDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.PageDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.TreeDDTO;

/**
 * Stub interface for IDCUser
 */
public interface IDCUser {

    String getUserId();

    void setUserId(String userId);

    String getUserName();

    void setUserName(String userName);

    String getUserEmail();

    void setUserEmail(String userEmail);

    List<HashMap> getUserList(ICommonDTO commonDto) throws NewBusinessException;

    // Add missing methods
    void crudUser(UserDDTO[] userDDTOs) throws NewBusinessException;

    List<User> getListUser(UserDDTO userDDTO) throws NewBusinessException;

    List<Page> getListPage(PageDDTO pageDDTO) throws NewBusinessException;

    List<Tree> getListTree(TreeDDTO treeDDTO) throws NewBusinessException;

    // Add other stub methods as needed
}
