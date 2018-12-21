package sbgl3.yunda.module.upparts.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.upparts.mvp.contract.UpPartsEditContract;
import sbgl3.yunda.module.upparts.mvp.model.UpPartsEditModel;


@Module
public class UpPartsEditModule {
    private UpPartsEditContract.View view;

    /**
     * 构建UpPartsEditModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public UpPartsEditModule(UpPartsEditContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UpPartsEditContract.View provideUpPartsEditView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    UpPartsEditContract.Model provideUpPartsEditModel(UpPartsEditModel model) {
        return model;
    }
}