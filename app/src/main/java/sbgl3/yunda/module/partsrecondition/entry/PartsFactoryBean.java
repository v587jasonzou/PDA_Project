package sbgl3.yunda.module.partsrecondition.entry;

import java.io.Serializable;

public class PartsFactoryBean implements Serializable {

    /**
     * tableName : null
     * idx : 0020
     * creater : 614
     * createrName : null
     * createTime : 2016-03-16 14:39:10
     * updateTime : 2016-03-16 14:39:10
     * updator : 614
     * updatorName : null
     * madeFactoryName : 西安西电避雷器有限责任公司
     * madeFactoryShortname : 西安西电
     * recordStatus : 0
     * _uid : null
     * _state : null
     */

    private String tableName;
    private String idx;
    private String creater;
    private String createrName;
    private String createTime;
    private String updateTime;
    private String updator;
    private String updatorName;
    private String madeFactoryName;
    private String madeFactoryShortname;
    private Integer recordStatus;
    private Object _uid;
    private Object _state;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getUpdatorName() {
        return updatorName;
    }

    public void setUpdatorName(String updatorName) {
        this.updatorName = updatorName;
    }

    public String getMadeFactoryName() {
        return madeFactoryName;
    }

    public void setMadeFactoryName(String madeFactoryName) {
        this.madeFactoryName = madeFactoryName;
    }

    public String getMadeFactoryShortname() {
        return madeFactoryShortname;
    }

    public void setMadeFactoryShortname(String madeFactoryShortname) {
        this.madeFactoryShortname = madeFactoryShortname;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Object get_uid() {
        return _uid;
    }

    public void set_uid(Object _uid) {
        this._uid = _uid;
    }

    public Object get_state() {
        return _state;
    }

    public void set_state(Object _state) {
        this._state = _state;
    }
}
