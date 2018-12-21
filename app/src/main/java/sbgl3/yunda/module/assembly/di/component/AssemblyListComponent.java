package sbgl3.yunda.module.assembly.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.assembly.di.module.AssemblyListModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.assembly.mvp.ui.activity.AssemblyListActivity;

@ActivityScope
@Component(modules = AssemblyListModule.class, dependencies = AppComponent.class)
public interface AssemblyListComponent {
    void inject(AssemblyListActivity activity);
}