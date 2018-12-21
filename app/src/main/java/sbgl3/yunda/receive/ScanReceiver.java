package sbgl3.yunda.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import sbgl3.yunda.entry.MessageBean;
/**二维码扫描广播（常驻广播）
 * @auther 周雪巍
 * @Data 2018/11/28
 */
public class ScanReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String code = intent.getStringExtra("barcode_string");
        if(EventBus.getDefault()!=null){
            MessageBean messageBean = new MessageBean();
            messageBean.setMsgType("scan");
            messageBean.setMsgInfo(code);
            EventBus.getDefault().post(messageBean);
        }
        Log.e("二维码：",code);
    }
}