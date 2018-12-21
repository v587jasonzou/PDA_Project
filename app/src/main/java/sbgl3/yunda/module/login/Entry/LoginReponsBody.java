package sbgl3.yunda.module.login.Entry;

import java.io.Serializable;

import sbgl3.yunda.entry.AcOperatorBean;
import sbgl3.yunda.entry.AcOperatorSessionBean;
import sbgl3.yunda.entry.EmpBean;
import sbgl3.yunda.entry.OrgBean;

public class LoginReponsBody implements Serializable {


    /**
     * acOperator : {"authmode":"local","ipaddress":"192.168.10.140","lastlogin":1541558510000,"menutype":"default","operatorid":6,"operatorname":"王治明","password":"ZwsUcorZkCrsujLiL6T2vQ==","status":"running","userid":"2051"}
     * acOperatorSession : {"authmode":"local","ipaddress":"192.168.10.140","lastlogin":1541558510000,"menutype":"default","operatorid":6,"operatorname":"王治明","password":"ZwsUcorZkCrsujLiL6T2vQ==","status":"running","userid":"2051"}
     * emp : {"empcode":"2051","empid":5,"empname":"王治明","empstatus":"on","gender":"n","lastmodytime":1539585432000,"mobileno":"1399999999","operatorid":6,"orgid":2,"userid":"2051"}
     * org : {"createtime":1539248957000,"isleaf":"y","lastupdate":1539248957000,"orgcode":"0101","orgdegree":"plant","orgid":2,"orglevel":2,"orgname":"生产调度中心","orgseq":".1.2.","orgtype":"车间","parentorgid":1,"port":0,"sortno":0,"status":"running","subcount":0}
     */

    private AcOperatorBean acOperator;
    private AcOperatorSessionBean acOperatorSession;
    private EmpBean emp;
    private OrgBean org;

    public AcOperatorBean getAcOperator() {
        return acOperator;
    }

    public void setAcOperator(AcOperatorBean acOperator) {
        this.acOperator = acOperator;
    }

    public AcOperatorSessionBean getAcOperatorSession() {
        return acOperatorSession;
    }

    public void setAcOperatorSession(AcOperatorSessionBean acOperatorSession) {
        this.acOperatorSession = acOperatorSession;
    }

    public EmpBean getEmp() {
        return emp;
    }

    public void setEmp(EmpBean emp) {
        this.emp = emp;
    }

    public OrgBean getOrg() {
        return org;
    }

    public void setOrg(OrgBean org) {
        this.org = org;
    }
}
