package sbgl3.yunda.module.assembly.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.assembly.mvp.contract.AssemblyListContract;
import sbgl3.yunda.module.assembly.mvp.model.AssemblyListModel;


@Module
public class AssemblyListModule {
    private AssemblyListContract.View view;

    /**
     * 构建AssemblyListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AssemblyListModule(AssemblyListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AssemblyListContract.View provideAssemblyListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AssemblyListContract.Model provideAssemblyListModel(AssemblyListModel model) {
        return model;
    }
}