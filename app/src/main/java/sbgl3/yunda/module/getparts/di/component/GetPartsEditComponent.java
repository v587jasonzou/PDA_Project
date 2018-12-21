package sbgl3.yunda.module.getparts.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.getparts.di.module.GetPartsEditModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.getparts.mvp.ui.activity.GetPartsEditActivity;

@ActivityScope
@Component(modules = GetPartsEditModule.class, dependencies = AppComponent.class)
public interface GetPartsEditComponent {
    void inject(GetPartsEditActivity activity);
}