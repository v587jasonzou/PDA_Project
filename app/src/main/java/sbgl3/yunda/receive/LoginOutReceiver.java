package sbgl3.yunda.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;

import sbgl3.yunda.module.login.mvp.ui.activity.LoginActivityActivity;
/**退出登录广播（常驻广播）
 * @auther 周雪巍
 * @Data 2018/11/28
 */
public class LoginOutReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LoginActivityActivity.isUnlogin = true;
        Intent intent1 = new Intent(context, LoginActivityActivity.class);
        ArmsUtils.startActivity(intent1);
    }
}
