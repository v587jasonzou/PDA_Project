package sbgl3.yunda.module.assembly.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.assembly.di.module.AssemblyEditModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.assembly.mvp.ui.activity.AssemblyEditActivity;

@ActivityScope
@Component(modules = AssemblyEditModule.class, dependencies = AppComponent.class)
public interface AssemblyEditComponent {
    void inject(AssemblyEditActivity activity);
}