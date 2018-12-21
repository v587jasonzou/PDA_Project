package sbgl3.yunda.module.upparts.mvp.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.module.partsrecondition.mvp.ui.holder.PartsTrainHolder;
import sbgl3.yunda.module.upparts.entry.UpPartsTrainBean;
import sbgl3.yunda.module.upparts.mvp.ui.holder.UpPartsTrainHolder;

public class UpPartsTraindapter extends DefaultAdapter<UpPartsTrainBean> {


    public UpPartsTraindapter(List<UpPartsTrainBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<UpPartsTrainBean> getHolder(View v, int viewType) {
        return new UpPartsTrainHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_up_parts_train_list;
    }
}
