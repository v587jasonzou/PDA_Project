package sbgl3.yunda.module.setting.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import sbgl3.yunda.module.setting.di.module.SettingModule;
import sbgl3.yunda.module.setting.mvp.ui.activity.SettingActivity;

@ActivityScope
@Component(modules = SettingModule.class, dependencies = AppComponent.class)
public interface SettingComponent {
    void inject(SettingActivity activity);
}