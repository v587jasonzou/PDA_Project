package sbgl3.yunda.module.partsrecondition.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.partsrecondition.di.module.PartsEditModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.partsrecondition.mvp.ui.activity.PartsEditActivity;

@ActivityScope
@Component(modules = PartsEditModule.class, dependencies = AppComponent.class)
public interface PartsEditComponent {
    void inject(PartsEditActivity activity);
}