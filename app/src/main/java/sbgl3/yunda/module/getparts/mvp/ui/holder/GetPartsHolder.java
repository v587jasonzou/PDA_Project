package sbgl3.yunda.module.getparts.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.module.getparts.entry.GetPartsBean;
import sbgl3.yunda.module.partsrecondition.entry.DownPartsBean;


public class GetPartsHolder extends BaseHolder<GetPartsBean> {
    @BindView(R.id.tvPartsModel)
    TextView tvPartsModel;
    @BindView(R.id.tvPartsNo)
    TextView tvPartsNo;
    @BindView(R.id.tvPartsMaker)
    TextView tvPartsMaker;
    @BindView(R.id.tvTrainInfo)
    TextView tvTrainInfo;
    public GetPartsHolder(View itemView) {
        super(itemView);
    }
    @Override
    public void setData(GetPartsBean data, int position) {
        if (data!=null){
            String str = "";
            if(data.getSpecificationModel()!=null){
                tvPartsModel.setText(data.getSpecificationModel());
            }else {
                tvPartsModel.setText("");
            }
            if(data.getPartsNo()!=null){
                tvPartsNo.setText(data.getPartsNo());
            }else {
                tvPartsNo.setText("");
            }
            if(data.getMadeFactoryName()!=null){
                tvPartsMaker.setText(data.getMadeFactoryName());
            }else {
                tvPartsMaker.setText("");
            }
            String strs = "";
            if(data.getUnloadTraintype()!=null){
                strs = strs+data.getUnloadTraintype();
            }
            if(data.getUnloadTrainno()!=null){
                if(strs.equals("")){
                    strs = strs+data.getUnloadTrainno();
                }else {
                    strs = strs+" "+data.getUnloadTrainno();
                }
            }
            if(data.getUnloadRepairClass()!=null){
                if(strs.equals("")){
                    strs = strs+data.getUnloadRepairClass();
                }else {
                    strs = strs+" "+data.getUnloadRepairClass();
                }
            }
            if(strs.equals("")){
                tvTrainInfo.setVisibility(View.GONE);
            }else {
                tvTrainInfo.setVisibility(View.VISIBLE);
                tvTrainInfo.setText(strs);
            }
        }
    }

}
