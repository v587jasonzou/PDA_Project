package sbgl3.yunda.entry;

import java.io.Serializable;

public class OrgBean implements Serializable {
    /**
     * createtime : 1539248957000
     * isleaf : y
     * lastupdate : 1539248957000
     * orgcode : 0101
     * orgdegree : plant
     * orgid : 2
     * orglevel : 2
     * orgname : 生产调度中心
     * orgseq : .1.2.
     * orgtype : 车间
     * parentorgid : 1
     * port : 0
     * sortno : 0
     * status : running
     * subcount : 0
     */

    private long createtime;
    private String isleaf;
    private String lastupdate;
    private String orgcode;
    private String orgdegree;
    private Long orgid;
    private Long orglevel;
    private String orgname;
    private String orgseq;
    private String orgtype;
    private Long parentorgid;
    private Long port;
    private Long sortno;
    private String status;
    private Long subcount;

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(String isleaf) {
        this.isleaf = isleaf;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgdegree() {
        return orgdegree;
    }

    public void setOrgdegree(String orgdegree) {
        this.orgdegree = orgdegree;
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public Long getOrglevel() {
        return orglevel;
    }

    public void setOrglevel(Long orglevel) {
        this.orglevel = orglevel;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgseq() {
        return orgseq;
    }

    public void setOrgseq(String orgseq) {
        this.orgseq = orgseq;
    }

    public String getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(String orgtype) {
        this.orgtype = orgtype;
    }

    public Long getParentorgid() {
        return parentorgid;
    }

    public void setParentorgid(Long parentorgid) {
        this.parentorgid = parentorgid;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public Long getSortno() {
        return sortno;
    }

    public void setSortno(Long sortno) {
        this.sortno = sortno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSubcount() {
        return subcount;
    }

    public void setSubcount(Long subcount) {
        this.subcount = subcount;
    }
}
