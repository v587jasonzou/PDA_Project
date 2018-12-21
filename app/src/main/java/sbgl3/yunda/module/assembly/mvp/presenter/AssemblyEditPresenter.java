package sbgl3.yunda.module.assembly.mvp.presenter;

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

import sbgl3.yunda.module.assembly.mvp.contract.AssemblyEditContract;
import sbgl3.yunda.module.disassembly.entry.DisassemblyBean;


@ActivityScope
public class AssemblyEditPresenter extends BasePresenter<AssemblyEditContract.Model, AssemblyEditContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public AssemblyEditPresenter(AssemblyEditContract.Model model, AssemblyEditContract.View rootView) {
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
    public void getAssembly(String idx){
        mModel.getAssemblyList(idx).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponsBean<List<DisassemblyBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponsBean<List<DisassemblyBean>> listBaseResponsBean) {
                        mRootView.hideLoading();
                        if(listBaseResponsBean!=null){
                            if(listBaseResponsBean.getSuccess()&&listBaseResponsBean.getData()!=null){
                                mRootView.OnGetPartsSuccess(listBaseResponsBean.getData());
                            }else {
                                if(listBaseResponsBean.getMessage()!=null){
                                    mRootView.OnLoadFaild(listBaseResponsBean.getMessage());
                                }else {
                                    mRootView.OnLoadFaild("未获取到相关信息，请重试");
                                }
                            }
                        }else {
                            mRootView.OnLoadFaild("未获取到相关信息，请重试");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.hideLoading();
                        mRootView.OnLoadFaild("未获取到相关信息，请重试"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void Confirm(String jsonData){
        mModel.Confirm(jsonData).observeOn(AndroidSchedulers.mainThread())
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
                                mRootView.OnLoadFaild(baseResponsBean.getMessage());
                            }else {
                                mRootView.OnLoadFaild("登记失败，请重试");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.hideLoading();
                        mRootView.OnLoadFaild("登记失败，请重试"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
