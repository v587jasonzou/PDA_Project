package sbgl3.yunda.module.disassembly.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import sbgl3.yunda.R;
import sbgl3.yunda.module.disassembly.entry.DisassemblyBean;
import sbgl3.yunda.module.disassembly.mvp.ui.holder.DisassemblyHolder;
import sbgl3.yunda.module.partsrecondition.entry.DownPartsBean;
import sbgl3.yunda.module.partsrecondition.mvp.ui.holder.DownPartsHolder;

public class DisassemblyAdapter extends DefaultAdapter<DisassemblyBean> {

    public DisassemblyAdapter(List<DisassemblyBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<DisassemblyBean> getHolder(View v, int viewType) {
        return new DisassemblyHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_parts_list;
    }
}
