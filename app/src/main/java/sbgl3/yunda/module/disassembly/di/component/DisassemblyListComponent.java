package sbgl3.yunda.module.disassembly.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.disassembly.di.module.DisassemblyListModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.disassembly.mvp.ui.activity.DisassemblyListActivity;

@ActivityScope
@Component(modules = DisassemblyListModule.class, dependencies = AppComponent.class)
public interface DisassemblyListComponent {
    void inject(DisassemblyListActivity activity);
}