package sbgl3.yunda.module.partsrecondition.mvp.presenter;

import android.app.Application;

import com.jess.arms.base.entry.BaseResponsBean;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import sbgl3.yunda.module.partsrecondition.entry.DownPartsBean;
import sbgl3.yunda.module.partsrecondition.mvp.contract.PartsListContract;


@ActivityScope
public class PartsListPresenter extends BasePresenter<PartsListContract.Model, PartsListContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public PartsListPresenter(PartsListContract.Model model, PartsListContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
    public void getPartsList(String idx){
        mModel.getPartsList(idx).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponsBean<List<DownPartsBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponsBean<List<DownPartsBean>> baseResponsBean) {
                        mRootView.hideLoading();
                        if(baseResponsBean!=null&&baseResponsBean.getSuccess()){
                            if(baseResponsBean.getData()!=null&&baseResponsBean.getData().size()>0){
                                mRootView.OnLoadPartsSuccess(baseResponsBean.getData());
                            }else {
                                mRootView.OnLoadFaild("未获取到相关配件信息");
                            }
                        }else {
                            if(baseResponsBean!=null&&baseResponsBean.getMessage()!=null){
                                mRootView.OnLoadFaild("获取配件信息失败"+baseResponsBean.getMessage());
                            }else {
                                mRootView.OnLoadFaild("获取配件信息失败,请重试");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.hideLoading();
                        mRootView.OnLoadFaild("获取配件信息失败,请重试"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
