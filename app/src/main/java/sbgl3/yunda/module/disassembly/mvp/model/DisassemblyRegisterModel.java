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
import sbgl3.yunda.module.disassembly.mvp.contract.DisassemblyRegisterContract;
import sbgl3.yunda.module.partsrecondition.PartsDownApi;
import sbgl3.yunda.module.partsrecondition.entry.PartsModelBean;


@ActivityScope
public class DisassemblyRegisterModel extends BaseModel implements DisassemblyRegisterContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public DisassemblyRegisterModel(IRepositoryManager repositoryManager) {
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
         return mRepositoryManager.obtainRetrofitService(DisassemblyApi.class)
                .getMakers(idx).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponsBean> Confirm(String updateData) {
        return mRepositoryManager.obtainRetrofitService(DisassemblyApi.class).confirm(updateData)
                .subscribeOn(Schedulers.io());
    }
}