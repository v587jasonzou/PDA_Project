package sbgl3.yunda.module.partsrecondition.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.partsrecondition.di.module.PartsListModule;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.partsrecondition.mvp.ui.activity.PartsListActivity;

@ActivityScope
@Component(modules = PartsListModule.class, dependencies = AppComponent.class)
public interface PartsListComponent {
    void inject(PartsListActivity activity);
}