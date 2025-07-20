
package com.kbstar.mbc.dc.usermgtdc;

import com.kbstar.ksa.infra.po.NewAbstractDTO;

/**
 * Tree class
 */
public class Tree extends NewAbstractDTO {

    private String treeId;
    private String treeName;
    private String parentId;

    public Tree() {
        // Default constructor
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
