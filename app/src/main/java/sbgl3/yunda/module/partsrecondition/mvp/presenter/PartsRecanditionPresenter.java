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

import sbgl3.yunda.module.partsrecondition.entry.PartsTrainBean;
import sbgl3.yunda.module.partsrecondition.mvp.contract.PartsRecanditionContract;


@ActivityScope
public class PartsRecanditionPresenter extends BasePresenter<PartsRecanditionContract.Model, PartsRecanditionContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public PartsRecanditionPresenter(PartsRecanditionContract.Model model, PartsRecanditionContract.View rootView) {
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
                .subscribe(new Observer<BaseResponsBean<List<PartsTrainBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponsBean<List<PartsTrainBean>> baseResponsBean) {
                        mRootView.hideLoading();
                        if(baseResponsBean!=null&&baseResponsBean.getSuccess()){
                            if(baseResponsBean.getData()!=null&&baseResponsBean.getData().size()>0){
                                mRootView.OnLoadTrainSuccess(baseResponsBean.getData());
                            }else {
                                mRootView.OnLoadFaild("无相关在修机车信息，请重试");
                            }
                        }else {
                            if(baseResponsBean!=null&&baseResponsBean.getMessage()!=null){
                                mRootView.OnLoadFaild("获取在修机车列表失败，请重试"+baseResponsBean.getMessage());
                            }else {
                                mRootView.OnLoadFaild("获取在修机车列表失败，请重试");
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.hideLoading();
                        mRootView.OnLoadFaild("获取在修机车列表失败，请重试"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
