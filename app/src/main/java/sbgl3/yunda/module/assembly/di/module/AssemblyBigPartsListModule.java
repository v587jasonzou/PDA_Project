package sbgl3.yunda.module.assembly.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.assembly.mvp.contract.AssemblyBigPartsListContract;
import sbgl3.yunda.module.assembly.mvp.model.AssemblyBigPartsListModel;


@Module
public class AssemblyBigPartsListModule {
    private AssemblyBigPartsListContract.View view;

    /**
     * 构建AssemblyBigPartsListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AssemblyBigPartsListModule(AssemblyBigPartsListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AssemblyBigPartsListContract.View provideAssemblyBigPartsListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AssemblyBigPartsListContract.Model provideAssemblyBigPartsListModel(AssemblyBigPartsListModel model) {
        return model;
    }
}