package sbgl3.yunda.module.disassembly.entry;

import java.io.Serializable;

public class BigPartsBean implements Serializable {

    /**
     * teamName : 部件组
     * unloadTraintype : HXD1C
     * idx : eddf6dbe35a140e8a562dd1dfedb095d
     * unloadRepairClass : C5
     * unloadPlace : 11
     * partsName : 车顶大盖
     */

    private String teamName;
    private String unloadTraintype;
    private String idx;
    private String unloadRepairClass;
    private String unloadPlace;
    private String partsName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUnloadTraintype() {
        return unloadTraintype;
    }

    public void setUnloadTraintype(String unloadTraintype) {
        this.unloadTraintype = unloadTraintype;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getUnloadRepairClass() {
        return unloadRepairClass;
    }

    public void setUnloadRepairClass(String unloadRepairClass) {
        this.unloadRepairClass = unloadRepairClass;
    }

    public String getUnloadPlace() {
        return unloadPlace;
    }

    public void setUnloadPlace(String unloadPlace) {
        this.unloadPlace = unloadPlace;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }
}
