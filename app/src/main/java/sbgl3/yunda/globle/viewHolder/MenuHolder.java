package sbgl3.yunda.globle.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.utils.utilcode.util.SizeUtils;
import com.jess.arms.utils.utilcode.util.StringUtils;

import butterknife.BindView;
import sbgl3.yunda.R;
import sbgl3.yunda.entry.ImagesBean;
import sbgl3.yunda.entry.MenuSimpleBean;


public class MenuHolder extends BaseHolder<MenuSimpleBean> {
    @BindView(R.id.ivMenu)
    ImageView ivMenu;
    @BindView(R.id.tvMenuName)
    TextView tvMenuName;
    public MenuHolder(View itemView) {
        super(itemView);
    }
    @Override
    public void setData(MenuSimpleBean data, int position) {
        if (data != null) {
            if(!StringUtils.isTrimEmpty(data.getMenu())){
                tvMenuName.setText(data.getMenu());
                if(data.getMenu().equals("配件下车登记")){
                    ivMenu.setImageResource(R.mipmap.part_down_menu_icon);
                }else if(data.getMenu().equals("配件接收登记")){
                    ivMenu.setImageResource(R.mipmap.get_part_menu_icon);
                }else if(data.getMenu().equals("配件上车登记")){
                    ivMenu.setImageResource(R.mipmap.up_part_menu_icon);
                }else if(data.getMenu().equals("大部件拆解清单登记")){
                    ivMenu.setImageResource(R.mipmap.disassembly_parts_menu_icon);
                }else if(data.getMenu().equals("大部件组装清单登记")){
                    ivMenu.setImageResource(R.mipmap.assembly_parts_menu_icon);
                }
            }
        }
    }

}
