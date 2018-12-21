package sbgl3.yunda.module.disassembly.mvp.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.module.disassembly.entry.BigPartsBean;
import sbgl3.yunda.module.disassembly.mvp.ui.holder.BigPartsHolder;
import sbgl3.yunda.module.partsrecondition.mvp.ui.holder.DownPartsHolder;

public class BigPartsAdapter extends DefaultAdapter<BigPartsBean> {


    public BigPartsAdapter(List<BigPartsBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<BigPartsBean> getHolder(View v, int viewType) {
        return new BigPartsHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_big_parts_list;
    }
}
