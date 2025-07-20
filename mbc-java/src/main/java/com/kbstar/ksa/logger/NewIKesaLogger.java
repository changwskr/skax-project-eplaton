package com.kbstar.ksa.logger;

/**
 * New IKesaLogger interface
 * 
 * This is a stub implementation to replace the missing KSA framework
 * IKesaLogger
 */
public interface NewIKesaLogger {

    void info(String seqNo, String message);

    void debug(String seqNo, String message);

    void error(String seqNo, String message);

    void warn(String seqNo, String message);
}