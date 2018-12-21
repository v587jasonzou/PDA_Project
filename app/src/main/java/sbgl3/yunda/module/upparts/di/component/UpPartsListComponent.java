package sbgl3.yunda.module.upparts.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.upparts.di.module.UpPartsListModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.upparts.mvp.ui.activity.UpPartsListActivity;

@ActivityScope
@Component(modules = UpPartsListModule.class, dependencies = AppComponent.class)
public interface UpPartsListComponent {
    void inject(UpPartsListActivity activity);
}