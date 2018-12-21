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
import sbgl3.yunda.module.assembly.mvp.contract.AssemblyListContract;
import sbgl3.yunda.module.disassembly.DisassemblyApi;
import sbgl3.yunda.module.disassembly.entry.DisassemblyBean;


@ActivityScope
public class AssemblyListModel extends BaseModel implements AssemblyListContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public AssemblyListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponsBean<List<DisassemblyBean>>> getAssemblyList(String parentActivityIdx, String identificationCode, String statu) {
        return mRepositoryManager.obtainRetrofitService(DisassemblyApi.class).getAssemblyList(parentActivityIdx,identificationCode,statu)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponsBean<DisassemblyBean>> findPartsStatusByIdentiCode(String code) {
        return mRepositoryManager.obtainRetrofitService(DisassemblyApi.class).findPartsStatusByIdentiCode(code)
                .subscribeOn(Schedulers.io());
    }
}