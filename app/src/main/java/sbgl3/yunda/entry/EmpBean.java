package sbgl3.yunda.entry;

import java.io.Serializable;

public class EmpBean implements Serializable {
    /**
     * empcode : 2051
     * empid : 5
     * empname : 王治明
     * empstatus : on
     * gender : n
     * lastmodytime : 1539585432000
     * mobileno : 1399999999
     * operatorid : 6
     * orgid : 2
     * userid : 2051
     */

    private String empcode;
    private Long empid;
    private String empname;
    private String empstatus;
    private String gender;
    private String lastmodytime;
    private String mobileno;
    private Long operatorid;
    private Long orgid;
    private String userid;

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public Long getEmpid() {
        return empid;
    }

    public void setEmpid(Long empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmpstatus() {
        return empstatus;
    }

    public void setEmpstatus(String empstatus) {
        this.empstatus = empstatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastmodytime() {
        return lastmodytime;
    }

    public void setLastmodytime(String lastmodytime) {
        this.lastmodytime = lastmodytime;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public Long getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(Long operatorid) {
        this.operatorid = operatorid;
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
