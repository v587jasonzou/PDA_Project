package sbgl3.yunda.module.partsrecondition.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.partsrecondition.mvp.contract.PartsRecanditionContract;
import sbgl3.yunda.module.partsrecondition.mvp.model.PartsRecanditionModel;


@Module
public class PartsRecanditionModule {
    private PartsRecanditionContract.View view;

    /**
     * 构建PartsRecanditionModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public PartsRecanditionModule(PartsRecanditionContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PartsRecanditionContract.View providePartsRecanditionView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PartsRecanditionContract.Model providePartsRecanditionModel(PartsRecanditionModel model) {
        return model;
    }
}