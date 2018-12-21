package sbgl3.yunda.module.disassembly.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.module.disassembly.entry.DisassemblyBean;
import sbgl3.yunda.module.partsrecondition.entry.DownPartsBean;


public class DisassemblyHolder extends BaseHolder<DisassemblyBean> {
    @BindView(R.id.tvPartsName)
    TextView tvPartsName;
    @BindView(R.id.tvPartsStatus)
    TextView tvPartsStatus;
    @BindView(R.id.tvPartsLocation)
    TextView tvPartsLocation;
    @BindView(R.id.tvPartsMaker)
    TextView tvPartsMaker;
    public DisassemblyHolder(View itemView) {
        super(itemView);
    }
    @Override
    public void setData(DisassemblyBean data, int position) {
        if (data!=null){
            String str = "";
            if(data.getPartsName()!=null){
                if(data.getPartsNo()!=null){
                    tvPartsName.setText(data.getPartsName()+"  "+data.getPartsNo());
                }else {
                    tvPartsName.setText(data.getPartsName());
                }
            }else {
                tvPartsName.setText("");
            }
            if("2".equals(data.getStatus())){
                tvPartsStatus.setText("已登记");
                tvPartsStatus.setBackgroundResource(R.drawable.btn_full_green_bg);
            }else {
                tvPartsStatus.setText("未登记");
                tvPartsStatus.setBackgroundResource(R.drawable.btn_full_red_bg);
            }
            if(data.getSpecificationModel()!=null){
                tvPartsLocation.setText(data.getSpecificationModel());
            }else {
                tvPartsLocation.setText("");
            }
            if(data.getCreaterName()!=null){
                tvPartsMaker.setText(data.getCreaterName());
            }else {
                tvPartsMaker.setText("");
            }
        }
    }

}
