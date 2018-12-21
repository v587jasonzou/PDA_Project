package sbgl3.yunda.globle.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.entry.ImagesBean;
import sbgl3.yunda.globle.viewHolder.ImagesHolder;

public class ImagesAdapter extends DefaultAdapter<ImagesBean> {
    Context context;
    public ImagesAdapter(List<ImagesBean> infos, Context context) {
        super(infos);
        this.context = context;
    }

    @Override
    public BaseHolder<ImagesBean> getHolder(View v, int viewType) {
        return new ImagesHolder(v,context);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.images_item;
    }
}
