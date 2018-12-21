package sbgl3.yunda.module.me.mvp.presenter;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import sbgl3.yunda.module.me.mvp.contract.MeContract;
import timber.log.Timber;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/06/2018 15:53
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class MePresenter extends BasePresenter<MeContract.Model, MeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MePresenter(MeContract.Model model, MeContract.View rootView) {
        super(model, rootView);
    }

    public void updateMsg() {
        Observable.interval(2, TimeUnit.SECONDS).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                Timber.d("RxJava2 取消订阅");
            }
        }).observeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindUntilEvent(mRootView,ActivityEvent.PAUSE)).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Timber.d("RxJava2 更新");
                mModel.updateMsg(aLong);
                String msg = mModel.getMsg();
                mRootView.updateMsg(msg);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
