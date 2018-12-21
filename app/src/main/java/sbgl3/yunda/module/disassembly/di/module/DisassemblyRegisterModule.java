package sbgl3.yunda.module.disassembly.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.disassembly.mvp.contract.DisassemblyRegisterContract;
import sbgl3.yunda.module.disassembly.mvp.model.DisassemblyRegisterModel;


@Module
public class DisassemblyRegisterModule {
    private DisassemblyRegisterContract.View view;

    /**
     * 构建DisassemblyRegisterModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public DisassemblyRegisterModule(DisassemblyRegisterContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    DisassemblyRegisterContract.View provideDisassemblyRegisterView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    DisassemblyRegisterContract.Model provideDisassemblyRegisterModel(DisassemblyRegisterModel model) {
        return model;
    }
}