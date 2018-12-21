package sbgl3.yunda.module.assembly.mvp.model;

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
import sbgl3.yunda.module.assembly.mvp.contract.AssemblyBigPartsListContract;
import sbgl3.yunda.module.disassembly.DisassemblyApi;
import sbgl3.yunda.module.disassembly.entry.BigPartsBean;


@ActivityScope
public class AssemblyBigPartsListModel extends BaseModel implements AssemblyBigPartsListContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public AssemblyBigPartsListModel(IRepositoryManager repositoryManager) {
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