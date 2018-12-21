package sbgl3.yunda.module.getparts.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.getparts.mvp.contract.GetParstListContract;
import sbgl3.yunda.module.getparts.mvp.model.GetParstListModel;


@Module
public class GetParstListModule {
    private GetParstListContract.View view;

    /**
     * 构建GetParstListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GetParstListModule(GetParstListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GetParstListContract.View provideGetParstListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GetParstListContract.Model provideGetParstListModel(GetParstListModel model) {
        return model;
    }
}