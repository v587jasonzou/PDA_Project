package sbgl3.yunda.module.getparts.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.base.entry.BaseResponsBean;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import sbgl3.yunda.module.getparts.GetPartsApi;
import sbgl3.yunda.module.getparts.mvp.contract.GetPartsEditContract;


@ActivityScope
public class GetPartsEditModel extends BaseModel implements GetPartsEditContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public GetPartsEditModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponsBean> GetParts(String updateData, String flage) {
        return mRepositoryManager.obtainRetrofitService(GetPartsApi.class)
                .PartsGet(updateData,flage).subscribeOn(Schedulers.io());
    }
}