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
import sbgl3.yunda.module.partsrecondition.entry.PartsTrainBean;
import sbgl3.yunda.module.partsrecondition.mvp.contract.PartsRecanditionContract;


@ActivityScope
public class PartsRecanditionModel extends BaseModel implements PartsRecanditionContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public PartsRecanditionModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponsBean<List<PartsTrainBean>>> getTrainList() {
        return mRepositoryManager.obtainRetrofitService(PartsDownApi.class).getTrainList()
                .subscribeOn(Schedulers.io());
    }
}