package sbgl3.yunda.module.partsrecondition.mvp.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.module.partsrecondition.entry.DownPartsBean;
import sbgl3.yunda.module.partsrecondition.mvp.ui.holder.DownPartsHolder;
import sbgl3.yunda.module.partsrecondition.mvp.ui.holder.PartsTrainHolder;

public class DownPartsAdapter extends DefaultAdapter<DownPartsBean> {

    public DownPartsAdapter(List<DownPartsBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<DownPartsBean> getHolder(View v, int viewType) {
        return new DownPartsHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_parts_list;
    }
}
