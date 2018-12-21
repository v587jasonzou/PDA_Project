package sbgl3.yunda.module.setting.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import sbgl3.yunda.module.setting.di.module.ChangeServerModule;
import sbgl3.yunda.module.setting.mvp.ui.activity.ChangeServerActivity;

@ActivityScope
@Component(modules = ChangeServerModule.class, dependencies = AppComponent.class)
public interface ChangeServerComponent {
    void inject(ChangeServerActivity activity);
}