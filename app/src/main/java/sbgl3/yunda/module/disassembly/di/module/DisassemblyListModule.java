package sbgl3.yunda.module.disassembly.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.disassembly.mvp.contract.DisassemblyListContract;
import sbgl3.yunda.module.disassembly.mvp.model.DisassemblyListModel;


@Module
public class DisassemblyListModule {
    private DisassemblyListContract.View view;

    /**
     * 构建DisassemblyListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public DisassemblyListModule(DisassemblyListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    DisassemblyListContract.View provideDisassemblyListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    DisassemblyListContract.Model provideDisassemblyListModel(DisassemblyListModel model) {
        return model;
    }
}