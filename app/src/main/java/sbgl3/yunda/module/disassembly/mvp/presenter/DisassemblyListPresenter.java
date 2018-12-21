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

import sbgl3.yunda.module.disassembly.entry.DisassemblyBean;
import sbgl3.yunda.module.disassembly.mvp.contract.DisassemblyListContract;


@ActivityScope
public class DisassemblyListPresenter extends BasePresenter<DisassemblyListContract.Model, DisassemblyListContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public DisassemblyListPresenter(DisassemblyListContract.Model model, DisassemblyListContract.View rootView) {
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
    public void getDisassemblyList(String idx,String status){
        mModel.getDisassemblyList(idx,status).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponsBean<List<DisassemblyBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponsBean<List<DisassemblyBean>> baseResponsBean) {
                        mRootView.hideLoading();
                        if(baseResponsBean!=null&&baseResponsBean.getSuccess()){
                            if(baseResponsBean.getData()!=null){
                                mRootView.OnLoadDisassemblySuccess(baseResponsBean.getData());
                            }else {
                                mRootView.OnLoadFaild("未获取到相关数据，请重试");
                            }
                        }else {
                            mRootView.OnLoadFaild("未获取到相关数据，请重试");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.hideLoading();
                        mRootView.OnLoadFaild("未获取到相关数据，请重试"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
