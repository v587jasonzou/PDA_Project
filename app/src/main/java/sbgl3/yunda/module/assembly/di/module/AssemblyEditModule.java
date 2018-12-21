package sbgl3.yunda.module.assembly.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.assembly.mvp.contract.AssemblyEditContract;
import sbgl3.yunda.module.assembly.mvp.model.AssemblyEditModel;


@Module
public class AssemblyEditModule {
    private AssemblyEditContract.View view;

    /**
     * 构建AssemblyEditModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AssemblyEditModule(AssemblyEditContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AssemblyEditContract.View provideAssemblyEditView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AssemblyEditContract.Model provideAssemblyEditModel(AssemblyEditModel model) {
        return model;
    }
}