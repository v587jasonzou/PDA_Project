package sbgl3.yunda.module.upparts.mvp.presenter;

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

import sbgl3.yunda.module.getparts.entry.GetPartsBean;
import sbgl3.yunda.module.upparts.mvp.contract.UpPartsListContract;


@ActivityScope
public class UpPartsListPresenter extends BasePresenter<UpPartsListContract.Model, UpPartsListContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public UpPartsListPresenter(UpPartsListContract.Model model, UpPartsListContract.View rootView) {
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
    public void getPartsList(String code,String status,boolean isScan){
        mModel.getPartsList(code,status).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponsBean<List<GetPartsBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponsBean<List<GetPartsBean>> baseResponsBean) {
                        mRootView.hideLoading();
                        if(isScan){
                            if(baseResponsBean!=null&&baseResponsBean.getData()!=null){
                                if(baseResponsBean.getData().size()>0){
                                    mRootView.OnLoadScanPartSuccess(baseResponsBean.getData().get(0));
                                }else {
                                    mRootView.OnLoadFaild("2");
                                }
                            }else {
                                if(baseResponsBean!=null&&baseResponsBean.getMessage()!=null){
                                    mRootView.OnLoadFaild("扫码获取良好未上车配件信息失败"+baseResponsBean.getMessage());
                                }else {
                                    mRootView.OnLoadFaild("扫码获取良好未上车配件信息失败,请重试");
                                }
                            }
                        }else {
                            if(baseResponsBean!=null&&baseResponsBean.getData()!=null){
                                if(baseResponsBean.getData().size()>0){
                                    mRootView.OnLoadPartsListSuccess(baseResponsBean.getData());
                                }else {
                                    mRootView.OnLoadFaild("无相关已上车配件信息");
                                }
                            }else {
                                if(baseResponsBean!=null&&baseResponsBean.getMessage()!=null){
                                    mRootView.OnLoadFaild("获取已上车配件信息失败"+baseResponsBean.getMessage());
                                }else {
                                    mRootView.OnLoadFaild("获取已上车配件信息失败,请重试");
                                }
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.hideLoading();
                        if(isScan){
                            mRootView.OnLoadFaild("扫码获取良好未上车配件信息失败,请重试");
                        }else{
                            mRootView.OnLoadFaild("获取已上车配件信息失败,请重试"+e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
