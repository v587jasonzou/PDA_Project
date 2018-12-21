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

import sbgl3.yunda.module.assembly.mvp.contract.AssemblyListContract;
import sbgl3.yunda.module.disassembly.entry.DisassemblyBean;


@ActivityScope
public class AssemblyListPresenter extends BasePresenter<AssemblyListContract.Model, AssemblyListContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public AssemblyListPresenter(AssemblyListContract.Model model, AssemblyListContract.View rootView) {
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
    public void getAssembly(String idx,String code,String status){
        mModel.getAssemblyList(idx,code,status).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponsBean<List<DisassemblyBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponsBean<List<DisassemblyBean>> listBaseResponsBean) {
                        mRootView.hideLoading();
                        if(listBaseResponsBean!=null){
                            if(listBaseResponsBean.getSuccess()&&listBaseResponsBean.getData()!=null){
                                mRootView.OnLoadAssemblySuccess(listBaseResponsBean.getData());
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
    public void ScanCode(String code){
        mModel.findPartsStatusByIdentiCode(code).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponsBean<DisassemblyBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponsBean<DisassemblyBean> disassemblyBeanBaseResponsBean) {
                        if(disassemblyBeanBaseResponsBean!=null&&disassemblyBeanBaseResponsBean.getSuccess()){
                            mRootView.OnLoadScanSuccess(disassemblyBeanBaseResponsBean.getData());
                        }else {
                            mRootView.OnLoadFaild("未获取相关二维码配件");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.OnLoadFaild("未获取相关二维码配件"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
