package sbgl3.yunda.module.partsrecondition.mvp.model;

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
import sbgl3.yunda.module.partsrecondition.PartsDownApi;
import sbgl3.yunda.module.partsrecondition.entry.PartsFactoryBean;
import sbgl3.yunda.module.partsrecondition.entry.PartsModelBean;
import sbgl3.yunda.module.partsrecondition.mvp.contract.PartsEditContract;


@ActivityScope
public class PartsEditModel extends BaseModel implements PartsEditContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public PartsEditModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponsBean<List<PartsModelBean>>> getModels(String idx) {
        return mRepositoryManager.obtainRetrofitService(PartsDownApi.class)
                .getMakers(idx).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponsBean<List<PartsFactoryBean>>> getFactory() {
        return mRepositoryManager.obtainRetrofitService(PartsDownApi.class)
                .getAllMakers().subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponsBean> Confirm(String updateData) {
        return mRepositoryManager.obtainRetrofitService(PartsDownApi.class)
                .Confirm(updateData,"1").subscribeOn(Schedulers.io());
    }
}