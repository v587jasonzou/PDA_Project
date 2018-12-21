package sbgl3.yunda.entry;

import java.io.Serializable;

public class ItemClickMessage implements Serializable {
    private String type;
    private int position;
    private String EquipCode;
    private String messageInfo;

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public String getEquipCode() {
        return EquipCode;
    }

    public void setEquipCode(String equipCode) {
        EquipCode = equipCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
