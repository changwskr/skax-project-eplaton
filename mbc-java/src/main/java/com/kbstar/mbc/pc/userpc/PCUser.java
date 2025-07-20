package com.kbstar.mbc.pc.userpc;

import com.kbstar.mbc.dc.usermgtdc.IDCUser;
import com.kbstar.mbc.dc.usermgtdc.User;
import com.kbstar.mbc.dc.usermgtdc.DCUser;

/**
 * PCUser class
 */
public class PCUser {

    private IDCUser dcUser;

    public PCUser() {
        dcUser = new DCUser();
    }

    public User selectUser(String userId) throws Exception {
        return dcUser.selectUser(userId);
    }
}