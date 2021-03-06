package sbgl3.yunda.module.disassembly.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.disassembly.di.module.BigPartsListModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.disassembly.mvp.ui.activity.BigPartsListActivity;

@ActivityScope
@Component(modules = BigPartsListModule.class, dependencies = AppComponent.class)
public interface BigPartsListComponent {
    void inject(BigPartsListActivity activity);
}