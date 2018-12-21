package sbgl3.yunda.module.disassembly.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.module.disassembly.entry.BigPartsBean;
import sbgl3.yunda.module.partsrecondition.entry.DownPartsBean;


public class BigPartsHolder extends BaseHolder<BigPartsBean> {
    @BindView(R.id.tvPartsName)
    TextView tvPartsName;
    @BindView(R.id.tvPartsLocation)
    TextView tvPartsLocation;
    @BindView(R.id.tvPartsMaker)
    TextView tvPartsMaker;
    public BigPartsHolder(View itemView) {
        super(itemView);
    }
    @Override
    public void setData(BigPartsBean data, int position) {
        if (data!=null){
            String str = "";
            if(data.getPartsName()!=null){
                if(data.getUnloadTraintype()!=null){
                    if(data.getUnloadRepairClass()!=null){
                        tvPartsName.setText(data.getUnloadTraintype()+"  "+data.getPartsName()+data.getUnloadRepairClass());
                    }else {
                        tvPartsName.setText(data.getUnloadTraintype()+"  "+data.getPartsName());
                    }

                }else {
                    tvPartsName.setText(data.getPartsName());
                }
            }else {
                tvPartsName.setText("");
            }

            if(data.getTeamName()!=null){
                tvPartsLocation.setText(data.getTeamName());
            }else {
                tvPartsLocation.setText("");
            }
            if(data.getUnloadPlace()!=null){
                tvPartsMaker.setText(data.getUnloadPlace());
            }else {
                tvPartsMaker.setText("");
            }
        }
    }

}
