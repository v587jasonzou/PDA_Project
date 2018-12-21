package sbgl3.yunda.entry;

import java.io.Serializable;

public class MessageBean<T> implements Serializable {
    private String msgType;
    private String msgInfo;
    private Boolean isSuccess;
    private T msgContent;
    private Integer position;
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public T getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(T msgContent) {
        this.msgContent = msgContent;
    }
}
