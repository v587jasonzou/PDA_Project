package sbgl3.yunda.module.getparts.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.getparts.di.module.GetParstListModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.getparts.mvp.ui.activity.GetParstListActivity;

@ActivityScope
@Component(modules = GetParstListModule.class, dependencies = AppComponent.class)
public interface GetParstListComponent {
    void inject(GetParstListActivity activity);
}