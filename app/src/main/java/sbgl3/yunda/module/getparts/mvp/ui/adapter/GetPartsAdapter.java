package sbgl3.yunda.module.getparts.mvp.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.module.getparts.entry.GetPartsBean;
import sbgl3.yunda.module.getparts.mvp.ui.holder.GetPartsHolder;
import sbgl3.yunda.module.partsrecondition.entry.DownPartsBean;
import sbgl3.yunda.module.partsrecondition.mvp.ui.holder.DownPartsHolder;

public class GetPartsAdapter extends DefaultAdapter<GetPartsBean> {

    public GetPartsAdapter(List<GetPartsBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<GetPartsBean> getHolder(View v, int viewType) {
        return new GetPartsHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_get_parts_list;
    }
}
