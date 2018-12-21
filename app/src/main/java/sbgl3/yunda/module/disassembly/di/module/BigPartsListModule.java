package sbgl3.yunda.module.disassembly.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.disassembly.mvp.contract.BigPartsListContract;
import sbgl3.yunda.module.disassembly.mvp.model.BigPartsListModel;


@Module
public class BigPartsListModule {
    private BigPartsListContract.View view;

    /**
     * 构建BigPartsListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public BigPartsListModule(BigPartsListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    BigPartsListContract.View provideBigPartsListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    BigPartsListContract.Model provideBigPartsListModel(BigPartsListModel model) {
        return model;
    }
}