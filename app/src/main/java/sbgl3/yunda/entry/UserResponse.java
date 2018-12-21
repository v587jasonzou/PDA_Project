package sbgl3.yunda.entry;

import java.io.Serializable;
import java.util.List;

public class UserResponse implements Serializable {
   private Boolean success;
   private List<UserInfo> userList;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<UserInfo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfo> userList) {
        this.userList = userList;
    }
}
