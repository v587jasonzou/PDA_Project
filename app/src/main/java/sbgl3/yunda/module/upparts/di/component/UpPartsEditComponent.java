package sbgl3.yunda.module.upparts.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.upparts.di.module.UpPartsEditModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.upparts.mvp.ui.activity.UpPartsEditActivity;

@ActivityScope
@Component(modules = UpPartsEditModule.class, dependencies = AppComponent.class)
public interface UpPartsEditComponent {
    void inject(UpPartsEditActivity activity);
}