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
import sbgl3.yunda.module.disassembly.entry.DisassemblyBean;
import sbgl3.yunda.module.disassembly.mvp.contract.DisassemblyListContract;


@ActivityScope
public class DisassemblyListModel extends BaseModel implements DisassemblyListContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public DisassemblyListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponsBean<List<DisassemblyBean>>> getDisassemblyList(String parentActivityIdx, String statu) {
        return mRepositoryManager.obtainRetrofitService(DisassemblyApi.class).getDisassemblyList(parentActivityIdx)
                .subscribeOn(Schedulers.io());
    }
}