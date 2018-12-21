package sbgl3.yunda.module.partsrecondition.mvp.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.utils.utilcode.util.StringUtils;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.entry.MenuSimpleBean;
import sbgl3.yunda.module.partsrecondition.entry.PartsTrainBean;


public class PartsTrainHolder extends BaseHolder<PartsTrainBean> {
    @BindView(R.id.tvTrainNo)
    TextView tvTrainNo;
    public PartsTrainHolder(View itemView) {
        super(itemView);
    }
    @Override
    public void setData(PartsTrainBean data, int position) {
        if (data!=null){
            String str = "";
            if(data.getTrainTypeShortname()!=null){
                str = data.getTrainTypeShortname()+" ";
            }
            if (data.getTrainNo()!=null){
                str = str+data.getTrainNo();
            }
            tvTrainNo.setText(str);
        }
    }

}
