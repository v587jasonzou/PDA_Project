package sbgl3.yunda.module.upparts.mvp.model;

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
import sbgl3.yunda.module.upparts.UpPartsApi;
import sbgl3.yunda.module.upparts.entry.UpPartsTrainBean;
import sbgl3.yunda.module.upparts.mvp.contract.UpPartsEditContract;


@ActivityScope
public class UpPartsEditModel extends BaseModel implements UpPartsEditContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public UpPartsEditModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponsBean<List<UpPartsTrainBean>>> getTrainList() {
        return mRepositoryManager.obtainRetrofitService(UpPartsApi.class).getTrainList()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponsBean> UpParts(String updateData, String flage) {
        return mRepositoryManager.obtainRetrofitService(UpPartsApi.class).PartsUp(updateData,flage)
                .subscribeOn(Schedulers.io());
    }
}