package sbgl3.yunda.module.disassembly.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.disassembly.di.module.DisassemblyRegisterModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.disassembly.mvp.ui.activity.DisassemblyRegisterActivity;

@ActivityScope
@Component(modules = DisassemblyRegisterModule.class, dependencies = AppComponent.class)
public interface DisassemblyRegisterComponent {
    void inject(DisassemblyRegisterActivity activity);
}