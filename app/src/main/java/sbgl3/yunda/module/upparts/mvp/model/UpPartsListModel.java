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
import sbgl3.yunda.module.getparts.GetPartsApi;
import sbgl3.yunda.module.getparts.entry.GetPartsBean;
import sbgl3.yunda.module.upparts.mvp.contract.UpPartsListContract;


@ActivityScope
public class UpPartsListModel extends BaseModel implements UpPartsListContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public UpPartsListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponsBean<List<GetPartsBean>>> getPartsList(String code, String status) {
        return mRepositoryManager.obtainRetrofitService(GetPartsApi.class).getPartsList(code,status)
                .subscribeOn(Schedulers.io());
    }
}