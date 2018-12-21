package sbgl3.yunda.module.assembly.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.assembly.di.module.AssemblyBigPartsListModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.assembly.mvp.ui.activity.AssemblyBigPartsListActivity;

@ActivityScope
@Component(modules = AssemblyBigPartsListModule.class, dependencies = AppComponent.class)
public interface AssemblyBigPartsListComponent {
    void inject(AssemblyBigPartsListActivity activity);
}