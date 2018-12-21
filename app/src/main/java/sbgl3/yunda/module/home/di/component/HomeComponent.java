package sbgl3.yunda.module.home.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.home.di.module.HomeModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.home.mvp.ui.activity.HomeActivity;

@ActivityScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeActivity activity);
}