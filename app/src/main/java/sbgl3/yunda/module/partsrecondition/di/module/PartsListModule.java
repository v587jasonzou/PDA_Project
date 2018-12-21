package sbgl3.yunda.module.partsrecondition.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.partsrecondition.mvp.contract.PartsListContract;
import sbgl3.yunda.module.partsrecondition.mvp.model.PartsListModel;


@Module
public class PartsListModule {
    private PartsListContract.View view;

    /**
     * 构建PartsListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public PartsListModule(PartsListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PartsListContract.View providePartsListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PartsListContract.Model providePartsListModel(PartsListModel model) {
        return model;
    }
}