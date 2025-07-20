
package com.kbstar.mbc.dc.usermgtdc;

import com.kbstar.ksa.infra.po.AbstractDTO;
import com.kbstar.ksa.infra.po.annotation.GPath;



@GPath(name="tree")
public class Tree extends AbstractDTO {

 private static final long serialVersionUID = 1L;
    

    private String nodeid;
    private String parentid;
    private String text;
    private String textcolor;
    private String imageid;
    
    public String getImageid() {
    
        return imageid;
    }
    
    public void setImageid(String imageid) {
    
        this.imageid = imageid;
    }
    
   
    
    public String getNodeid() {
    
        return nodeid;
    }

    
    public void setNodeid(String nodeid) {
    
        this.nodeid = nodeid;
    }

    public String getParentid() {
    
        return parentid;
    }
    
    public void setParentid(String parentid) {
    
        this.parentid = parentid;
    }
    
    public String getText() {
    
        return text;
    }
    
    public void setText(String text) {
    
        this.text = text;
    }
    
    public String getTextcolor() {
    
        return textcolor;
    }
    
    public void setTextcolor(String textcolor) {
    
        this.textcolor = textcolor;
    }

}

