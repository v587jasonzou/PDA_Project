package sbgl3.yunda.module.login.mvp.presenter;

import android.app.Application;

import com.jess.arms.base.BaseApplication;
import com.jess.arms.base.entry.BaseResponsBean;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.LogUtils;
import com.jess.arms.utils.utilcode.util.CacheDiskUtils;
import com.jess.arms.utils.utilcode.util.ObjectUtils;
import com.jess.arms.utils.utilcode.util.SPUtils;
import com.jess.arms.utils.utilcode.util.StringUtils;
import com.jess.arms.utils.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import sbgl3.yunda.SysInfo;
import sbgl3.yunda.module.login.Entry.LoginReponsBody;
import sbgl3.yunda.module.login.mvp.contract.LoginActivityContract;


@ActivityScope
public class LoginActivityPresenter extends BasePresenter<LoginActivityContract.Model, LoginActivityContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    //    ArrayList<LoginReponsBody.TodoJobBean> todos = new ArrayList<>();
    @Inject
    public LoginActivityPresenter(LoginActivityContract.Model model, LoginActivityContract.View rootView) {
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

    //    public ArrayList<LoginReponsBody.TodoJobBean> getTodoJobs(){
//        return todos;
//    }
    public void Login(String username,String password,boolean isSave){
        mModel.Login(username,password).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<BaseResponsBean<LoginReponsBody>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponsBean<LoginReponsBody> responseBody) {
                mRootView.hideLoading();
                if(!ObjectUtils.isEmpty(responseBody)){
                    if(responseBody.getSuccess()){
                        if(responseBody.getData()!=null){
                            SysInfo.emp = responseBody.getData().getEmp();
                            SysInfo.acOperator = responseBody.getData().getAcOperator();
//                            SysInfo.acOperatorSession = responseBody.getData().getAcOperatorSession();
                            SysInfo.org = responseBody.getData().getOrg();
                        }

                        ToastUtils.showShort("登录成功！");
                        if(isSave){
                            ArrayList<String> list = (ArrayList<String>)CacheDiskUtils.getInstance().getSerializable("users");
                            if(list==null){
                                list = new ArrayList<>();
                            }
                            if(!list.contains(username)){
                                list.add(username);
                                CacheDiskUtils.getInstance().put("users",list);
                            }
                        }
                        mRootView.toMainActivity();
                    }else {
                        BaseApplication.cookieStore.clear();
                        if(!StringUtils.isTrimEmpty(responseBody.getMessage())){
                            ToastUtils.showShort(responseBody.getMessage());
                        }else {
                            ToastUtils.showShort("登录失败请重试！");
                        }
                    }
                }else {
                    BaseApplication.cookieStore.clear();
                    ToastUtils.showShort("连接服务器失败，请重试");
                }
            }

            @Override
            public void onError(Throwable e) {
                BaseApplication.cookieStore.clear();
                mRootView.hideLoading();
                ToastUtils.showShort("登录失败请重试！"+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
//    public void Login(String username, String password, boolean isSave) {
//        mModel.LoginFirst(username, password).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<BaseResponsBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(BaseResponsBean responseBody) {
//                        mRootView.hideLoading();
////                    if(!ObjectUtils.isEmpty(responseBody)){
////                        if(responseBody.isSuccess()){
////                            SysInfo.emp = responseBody.getEmp();
////                            SysInfo.acOperator = responseBody.getAcOperator();
////                            SysInfo.acOperatorSession = responseBody.getAcOperatorSession();
////                            SysInfo.org = responseBody.getOrg();
////                            ToastUtils.showShort("登录成功！");
////                            if(isSave){
////                                ArrayList<String> list = (ArrayList<String>)CacheDiskUtils.getInstance().getSerializable("users");
////                                if(list==null){
////                                    list = new ArrayList<>();
////                                }
////                                if(!list.contains(username)){
////                                    list.add(username);
////                                    CacheDiskUtils.getInstance().put("users",list);
////                                }
////                            }
////                            mRootView.toMainActivity();
////                        }else {
////                            if(!StringUtils.isTrimEmpty(responseBody.getErrMsg())){
////                                ToastUtils.showShort(responseBody.getErrMsg());
////                            }else {
////                                ToastUtils.showShort("登录失败请重试！");
////                            }
////                        }
////                    }else {
////                        ToastUtils.showShort("连接服务器失败，请重试");
////                    }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mRootView.hideLoading();
//                        ToastUtils.showShort("登录失败请重试！" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
}
