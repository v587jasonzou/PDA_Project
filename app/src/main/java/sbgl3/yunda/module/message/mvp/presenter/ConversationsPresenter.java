package sbgl3.yunda.module.message.mvp.presenter;

import android.app.Application;
import android.widget.Toast;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.RxLifecycleUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import sbgl3.yunda.module.message.mvp.contract.ConversationsContract;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/06/2018 23:44
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class ConversationsPresenter extends BasePresenter<ConversationsContract.Model, ConversationsContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ConversationsPresenter(ConversationsContract.Model model, ConversationsContract.View rootView) {
        super(model, rootView);
    }

    public void receiveNewMsg() {
        mModel.getNewMsg().doOnDispose(new Action() {
            @Override
            public void run() throws Exception {

            }
        }).observeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindUntilEvent(mRootView,ActivityEvent.STOP)).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                mRootView.showMessage("新消息" + aLong.toString());
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
