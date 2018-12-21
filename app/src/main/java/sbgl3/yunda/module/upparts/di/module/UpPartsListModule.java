package sbgl3.yunda.module.upparts.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.upparts.mvp.contract.UpPartsListContract;
import sbgl3.yunda.module.upparts.mvp.model.UpPartsListModel;


@Module
public class UpPartsListModule {
    private UpPartsListContract.View view;

    /**
     * 构建UpPartsListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public UpPartsListModule(UpPartsListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UpPartsListContract.View provideUpPartsListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    UpPartsListContract.Model provideUpPartsListModel(UpPartsListModel model) {
        return model;
    }
}