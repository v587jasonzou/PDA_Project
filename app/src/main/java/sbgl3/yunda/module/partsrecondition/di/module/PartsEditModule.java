package sbgl3.yunda.module.partsrecondition.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.partsrecondition.mvp.contract.PartsEditContract;
import sbgl3.yunda.module.partsrecondition.mvp.model.PartsEditModel;


@Module
public class PartsEditModule {
    private PartsEditContract.View view;

    /**
     * 构建PartsEditModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public PartsEditModule(PartsEditContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PartsEditContract.View providePartsEditView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PartsEditContract.Model providePartsEditModel(PartsEditModel model) {
        return model;
    }
}