package sbgl3.yunda.module.upparts.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.module.partsrecondition.entry.PartsTrainBean;
import sbgl3.yunda.module.upparts.entry.UpPartsTrainBean;


public class UpPartsTrainHolder extends BaseHolder<UpPartsTrainBean> {
    @BindView(R.id.tvTrainAllName)
    TextView tvTrainAllName;
    public UpPartsTrainHolder(View itemView) {
        super(itemView);
    }
    @Override
    public void setData(UpPartsTrainBean data, int position) {
        if (data!=null){
            String str = "";
            if(data.getTrainTypeShortname()!=null){
                str = data.getTrainTypeShortname()+" ";
            }
            if (data.getTrainNo()!=null){
                str = str+data.getTrainNo();
            }
//            if(data.getRepairClassName()!=null){
//                str = str+" "+data.getRepairClassName();
//            }
            tvTrainAllName.setText(str);
        }
    }

}
