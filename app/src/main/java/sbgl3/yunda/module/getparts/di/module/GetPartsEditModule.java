package sbgl3.yunda.module.getparts.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.getparts.mvp.contract.GetPartsEditContract;
import sbgl3.yunda.module.getparts.mvp.model.GetPartsEditModel;


@Module
public class GetPartsEditModule {
    private GetPartsEditContract.View view;

    /**
     * 构建GetPartsEditModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GetPartsEditModule(GetPartsEditContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GetPartsEditContract.View provideGetPartsEditView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GetPartsEditContract.Model provideGetPartsEditModel(GetPartsEditModel model) {
        return model;
    }
}