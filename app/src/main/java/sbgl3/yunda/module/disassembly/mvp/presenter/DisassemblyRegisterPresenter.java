package sbgl3.yunda.module.disassembly.mvp.presenter;

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

import sbgl3.yunda.module.disassembly.mvp.contract.DisassemblyRegisterContract;
import sbgl3.yunda.module.partsrecondition.entry.PartsModelBean;


@ActivityScope
public class DisassemblyRegisterPresenter extends BasePresenter<DisassemblyRegisterContract.Model, DisassemblyRegisterContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public DisassemblyRegisterPresenter(DisassemblyRegisterContract.Model model, DisassemblyRegisterContract.View rootView) {
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
    public void getModels(String idx){
        mModel.getModels(idx).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponsBean<List<PartsModelBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponsBean<List<PartsModelBean>> baseResponsBean) {
                        mRootView.hideLoading();
                        if(baseResponsBean!=null&&baseResponsBean.getSuccess()){
                            if(baseResponsBean.getData()!=null&&baseResponsBean.getData().size()>0){
                                mRootView.onLoadModelSuccess(baseResponsBean.getData());
                            }else {
                                mRootView.onLoadFaild("无相关配件规格数据选择");
                            }
                        }else {
                            if(baseResponsBean!=null&&baseResponsBean.getMessage()!=null){
                                mRootView.onLoadFaild("获取配件规格数据失败"+baseResponsBean.getMessage());
                            }else {
                                mRootView.onLoadFaild("获取配件规格数据失败,请重试");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.hideLoading();
                        mRootView.onLoadFaild("获取配件规格数据失败,请重试"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void Confirm(String entityJson){
        mModel.Confirm(entityJson).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponsBean baseResponsBean) {
                        mRootView.hideLoading();
                        if(baseResponsBean!=null){
                            if(baseResponsBean.getSuccess()){
                                mRootView.ConfirmSuccess();
                            }else {
                                if(baseResponsBean.getMessage()!=null){
                                    mRootView.onLoadFaild(baseResponsBean.getMessage());
                                }else {
                                    mRootView.onLoadFaild("登记失败，请重试！");
                                }
                            }
                        }else {
                            mRootView.onLoadFaild("登记失败，请重试！");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.hideLoading();
                        mRootView.onLoadFaild("登记失败，请重试！"+ e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
