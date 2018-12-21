package sbgl3.yunda.module.partsrecondition.mvp.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.entry.MenuSimpleBean;
import sbgl3.yunda.globle.viewHolder.MenuHolder;
import sbgl3.yunda.module.partsrecondition.entry.PartsTrainBean;
import sbgl3.yunda.module.partsrecondition.mvp.ui.holder.PartsTrainHolder;

public class PartsTraindapter extends DefaultAdapter<PartsTrainBean> {



    public PartsTraindapter(List<PartsTrainBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<PartsTrainBean> getHolder(View v, int viewType) {
        return new PartsTrainHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_parts_train;
    }
}
