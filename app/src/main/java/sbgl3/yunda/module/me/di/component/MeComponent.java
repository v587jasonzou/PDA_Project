package sbgl3.yunda.module.me.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.me.di.module.MeModule;
import sbgl3.yunda.module.me.mvp.contract.MeContract;

import com.jess.arms.di.scope.ActivityScope;

import sbgl3.yunda.module.me.mvp.ui.activity.MeActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/06/2018 15:53
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = MeModule.class, dependencies = AppComponent.class)
public interface MeComponent {
    void inject(MeActivity activity);

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        MeComponent.Builder view(MeContract.View view);
//
//        MeComponent.Builder appComponent(AppComponent appComponent);
//
//        MeComponent build();
//    }
}