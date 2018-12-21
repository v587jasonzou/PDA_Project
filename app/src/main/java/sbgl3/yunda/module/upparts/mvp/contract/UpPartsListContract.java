package sbgl3.yunda.module.upparts.mvp.contract;

import com.jess.arms.base.entry.BaseResponsBean;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;
import sbgl3.yunda.module.getparts.entry.GetPartsBean;


public interface UpPartsListContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void OnLoadPartsListSuccess(List<GetPartsBean> list);
        void OnLoadScanPartSuccess(GetPartsBean bean);
        void OnLoadFaild(String msg);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseResponsBean<List<GetPartsBean>>> getPartsList(String code, String status);
    }
}
