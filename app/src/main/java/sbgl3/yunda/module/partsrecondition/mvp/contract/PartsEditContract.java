package sbgl3.yunda.module.partsrecondition.mvp.contract;

import com.jess.arms.base.entry.BaseResponsBean;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;
import sbgl3.yunda.module.partsrecondition.entry.PartsFactoryBean;
import sbgl3.yunda.module.partsrecondition.entry.PartsModelBean;


public interface PartsEditContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void onLoadModelSuccess(List<PartsModelBean> list);
        void onLoadFactorySuccess(List<PartsFactoryBean> bean);
        void onLoadFaild(String msg);
        void ConfirmSuccess();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseResponsBean<List<PartsModelBean>>> getModels(String idx);
        Observable<BaseResponsBean<List<PartsFactoryBean>>> getFactory();
        Observable<BaseResponsBean>Confirm(String updateData);
    }
}
