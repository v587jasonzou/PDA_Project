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

import sbgl3.yunda.module.upparts.entry.UpPartsTrainBean;
import sbgl3.yunda.module.upparts.mvp.contract.UpPartsEditContract;


@ActivityScope
public class UpPartsEditPresenter extends BasePresenter<UpPartsEditContract.Model, UpPartsEditContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public UpPartsEditPresenter(UpPartsEditContract.Model model, UpPartsEditContract.View rootView) {
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
    public void getTrainList(){
        mModel.getTrainList().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponsBean<List<UpPartsTrainBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponsBean<List<UpPartsTrainBean>> baseResponsBean) {
                        mRootView.hideLoading();
                        if(baseResponsBean!=null&&baseResponsBean.getSuccess()){
                            if(baseResponsBean.getData()!=null){
                                mRootView.OnLoadTrainListSuccess(baseResponsBean.getData());
                            }else {
                                mRootView.OnLoadFaild("未获取到相关机车信息，请重试");
                            }
                        }else {
                            if(baseResponsBean!=null&&baseResponsBean.getMessage()!=null){
                                mRootView.OnLoadFaild("未获取到相关机车信息，请重试"+baseResponsBean.getMessage());
                            }else {
                                mRootView.OnLoadFaild("未获取到相关机车信息，请重试");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.hideLoading();
                        mRootView.OnLoadFaild("未获取到相关机车信息，请重试"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void UpParts(String data){
        mModel.UpParts(data,"1").observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponsBean baseResponsBean) {
                        mRootView.hideLoading();
                        if(baseResponsBean!=null&&baseResponsBean.getSuccess()){
                            mRootView.OnConfirmSuccess();
                        }else {
                            if(baseResponsBean!=null&&baseResponsBean.getMessage()!=null){
                                mRootView.OnLoadFaild("上车登记失败"+baseResponsBean.getMessage());
                            }else {
                                mRootView.OnLoadFaild("上车登记失败,请重试");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.hideLoading();
                        mRootView.OnLoadFaild("上车登记失败,请重试"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
