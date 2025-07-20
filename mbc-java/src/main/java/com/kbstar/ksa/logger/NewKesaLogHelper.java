package com.kbstar.ksa.logger;

/**
 * New KesaLogHelper class
 * 
 * This is a stub implementation to replace the missing KSA framework
 * KesaLogHelper
 */
public class NewKesaLogHelper {

    public static NewIKesaLogger getBiz() {
        return new NewIKesaLogger() {
            @Override
            public void info(String seqNo, String message) {
                // Stub implementation
            }

            @Override
            public void debug(String seqNo, String message) {
                // Stub implementation
            }

            @Override
            public void error(String seqNo, String message) {
                // Stub implementation
            }

            @Override
            public void warn(String seqNo, String message) {
                // Stub implementation
            }
        };
    }

    public static NewIKesaLogger getServer() {
        return getBiz();
    }

    public static NewIKesaLogger getWaf() {
        return getBiz();
    }
}