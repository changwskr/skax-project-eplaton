package com.kbstar.mbc.as.runtimeas;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.infra.po.GenericDto;
import com.kbstar.ksa.infra.po.KBData;
import com.kbstar.ksa.logger.IKesaLogger;
import com.kbstar.ksa.logger.KesaLoggerFactory;
import com.kbstar.ksa.oltp.biz.IApplicationService;

public class ASMBC75999 implements IApplicationService {
	
	protected IKesaLogger logger = KesaLoggerFactory.getLogger("log.ifrs.mbc");
	Process process = null;
	ProcessInputStream in = null;
	ProcessInputStream error = null;

	public KBData execute(KBData reqData) throws BusinessException {
		
		String cmd = reqData.getInputGenericDto().using(GenericDto.INDATA ).getString("RuntimeCmd").trim();
				
		StringBuffer resultBuf = new StringBuffer();
		long time = System.currentTimeMillis();
		
		try {
			
			resultBuf.append("star time     :" + new java.util.Date() + "\n");
			
			if (cmd.startsWith("/")) {
	    		File file = new File(cmd);
	    		resultBuf.append("command       :" + file.getCanonicalPath() + "\n");
	    	} else {
	    		resultBuf.append("command       :" + cmd + "\n");
	    	}
			resultBuf.append("command output:\n");
			resultBuf.append("--------------------------------------------------------------------------------\n");
			process = Runtime.getRuntime().exec(cmd);                
			in = new ProcessInputStream(process.getInputStream(), resultBuf);
	        in.start();
	        error = new ProcessInputStream(process.getErrorStream(), resultBuf);        
	        error.start();
	        int count = 0;
			while (!isDone(resultBuf) || count <= 10) {			
				//Thread.currentThread().sleep(500);
				count++;
	        }        
	        if (count == 10) {
	        	in.interrupt();
	        	error.interrupt();
	        }
	        process.destroy();
		}
		catch(IOException ioe) {
			resultBuf.append(stackTraceToString(ioe) + "\n");
		}
		
		resultBuf.append("--------------------------------------------------------------------------------\n");
		resultBuf.append("elaspsed time :" + (System.currentTimeMillis() - time) / 1000f + "\n");
		
		reqData.getOutputGenericDto().using(GenericDto.OUTDATA ).setString("Results", resultBuf.toString());
		
		return reqData;
	}
	
	public boolean isDone(StringBuffer resultBuf) {
        try {        	
            process.waitFor();            
        } catch (Exception e) {
			resultBuf.append(stackTraceToString(e) + "\n");
        }
        return in.isDone() && error.isDone();
    }
	
	public String stackTraceToString(Throwable throwable) {
		StringWriter sw = new StringWriter();
		throwable.printStackTrace(new PrintWriter(sw, true));
		return sw.toString();        
	}
		
}

class ProcessInputStream extends Thread {
    InputStream in;    
    private boolean done = true;
    StringBuffer writer;

    public ProcessInputStream(InputStream in, StringBuffer writer) {        
    	this.in = in;
        this.writer = writer;    
        setDaemon(true);    
    }

    public void run() {
        done = false;
        try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;            
            while ((line = reader.readLine()) != null) {            	
                writer.append("\t" + line + "\n");
            }            
        } catch (Exception e) {
        	writer.append(stackTraceToString(e) + "\n");
        }
        done = true;
    }

    public boolean isDone() {
        return done;
    }
    
    public String stackTraceToString(Throwable throwable) {
		StringWriter sw = new StringWriter();
		throwable.printStackTrace(new PrintWriter(sw, true));
		return sw.toString();        
	}
   
}

