package sbgl3.yunda.module.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.base.entry.BaseResponsBean;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import sbgl3.yunda.entry.MenuSimpleBean;
import sbgl3.yunda.module.home.HomeApi;
import sbgl3.yunda.module.home.mvp.contract.HomeContract;
import sbgl3.yunda.module.login.LoginApi;


@ActivityScope
public class HomeModel extends BaseModel implements HomeContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponsBean<List<MenuSimpleBean>>> getMenus(String loginClient) {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getMenus(loginClient)
                .subscribeOn(Schedulers.io());
    }
}