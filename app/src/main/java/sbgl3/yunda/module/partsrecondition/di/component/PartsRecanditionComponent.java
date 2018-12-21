package sbgl3.yunda.module.partsrecondition.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.partsrecondition.di.module.PartsRecanditionModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.partsrecondition.mvp.ui.activity.PartsRecanditionActivity;

@ActivityScope
@Component(modules = PartsRecanditionModule.class, dependencies = AppComponent.class)
public interface PartsRecanditionComponent {
    void inject(PartsRecanditionActivity activity);
}