package com.kbstar.mbc.fc.foundation.bzcrudbus.business.as;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.infra.po.NewKBData;

/**
 * Common Application Service Execution class
 */
public class ASComExec {

    public NewKBData execute(NewKBData reqData) throws NewBusinessException {
        // Default implementation - can be overridden by subclasses
        return reqData;
    }
}