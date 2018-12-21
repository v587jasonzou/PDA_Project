package sbgl3.yunda.module.partsrecondition.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.module.partsrecondition.entry.DownPartsBean;
import sbgl3.yunda.module.partsrecondition.entry.PartsTrainBean;


public class DownPartsHolder extends BaseHolder<DownPartsBean> {
    @BindView(R.id.tvPartsName)
    TextView tvPartsName;
    @BindView(R.id.tvPartsStatus)
    TextView tvPartsStatus;
    @BindView(R.id.tvPartsLocation)
    TextView tvPartsLocation;
    @BindView(R.id.tvPartsMaker)
    TextView tvPartsMaker;
    public DownPartsHolder(View itemView) {
        super(itemView);
    }
    @Override
    public void setData(DownPartsBean data, int position) {
        if (data!=null){
            String str = "";
            if(data.getPartsName()!=null){
                if(data.getUnloadPlace()!=null){
                    tvPartsName.setText(data.getPartsName()+"  "+data.getUnloadPlace());
                }else {
                    tvPartsName.setText(data.getPartsName());
                }
            }else {
                tvPartsName.setText("");
            }
            if("1".equals(data.getStatus())){
                tvPartsStatus.setText("未登记");
                tvPartsStatus.setBackgroundResource(R.drawable.btn_full_red_bg);
            }else {
                tvPartsStatus.setText("已登记");
                tvPartsStatus.setBackgroundResource(R.drawable.btn_full_green_bg);
            }
            if(data.getLocation()!=null){
                tvPartsLocation.setText(data.getLocation());
            }else {
                tvPartsLocation.setText("");
            }
            if(data.getMadeFactoryName()!=null){
                tvPartsMaker.setText(data.getMadeFactoryName());
            }else {
                tvPartsMaker.setText("");
            }
        }
    }

}
