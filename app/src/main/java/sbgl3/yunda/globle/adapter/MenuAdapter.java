package sbgl3.yunda.globle.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.entry.MenuSimpleBean;
import sbgl3.yunda.globle.viewHolder.ImagesHolder;
import sbgl3.yunda.globle.viewHolder.MenuHolder;

public class MenuAdapter extends DefaultAdapter<MenuSimpleBean> {

    public MenuAdapter(List<MenuSimpleBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<MenuSimpleBean> getHolder(View v, int viewType) {
        return new MenuHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_menu_new;
    }
}
