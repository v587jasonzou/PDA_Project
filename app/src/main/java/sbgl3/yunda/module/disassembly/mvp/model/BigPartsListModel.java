package sbgl3.yunda.module.disassembly.mvp.model;

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
import sbgl3.yunda.module.disassembly.DisassemblyApi;
import sbgl3.yunda.module.disassembly.entry.BigPartsBean;
import sbgl3.yunda.module.disassembly.mvp.contract.BigPartsListContract;


@ActivityScope
public class BigPartsListModel extends BaseModel implements BigPartsListContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public BigPartsListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponsBean<List<BigPartsBean>>> getBigPartsList(String queryData, String identificationCode) {
        return mRepositoryManager.obtainRetrofitService(DisassemblyApi.class).getBigPartsList(queryData,identificationCode)
                .subscribeOn(Schedulers.io());
    }
}