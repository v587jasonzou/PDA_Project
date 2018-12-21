package sbgl3.yunda.entry;

import java.io.Serializable;

public class AcOperatorSessionBean implements Serializable {
    /**
     * authmode : local
     * ipaddress : 192.168.10.140
     * lastlogin : 1541558510000
     * menutype : default
     * operatorid : 6
     * operatorname : 王治明
     * password : ZwsUcorZkCrsujLiL6T2vQ==
     * status : running
     * userid : 2051
     */

    private String authmode;
    private String ipaddress;
    private Long lastlogin;
    private String menutype;
    private Long operatorid;
    private String operatorname;
    private String password;
    private String status;
    private String userid;

    public String getAuthmode() {
        return authmode;
    }

    public void setAuthmode(String authmode) {
        this.authmode = authmode;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public Long getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Long lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getMenutype() {
        return menutype;
    }

    public void setMenutype(String menutype) {
        this.menutype = menutype;
    }

    public Long getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(Long operatorid) {
        this.operatorid = operatorid;
    }

    public String getOperatorname() {
        return operatorname;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
