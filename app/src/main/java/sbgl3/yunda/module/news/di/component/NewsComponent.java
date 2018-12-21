package sbgl3.yunda.module.news.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import sbgl3.yunda.module.message.di.module.ConversationsModule;
import sbgl3.yunda.module.message.mvp.contract.ConversationsContract;
import sbgl3.yunda.module.news.di.module.NewsModule;
import sbgl3.yunda.module.news.mvp.contract.NewsContract;

import com.jess.arms.di.scope.ActivityScope;

import javax.annotation.Nullable;

import sbgl3.yunda.module.news.mvp.ui.activity.NewsActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/07/2018 00:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = {NewsModule.class,ConversationsModule.class}, dependencies = AppComponent.class)
public interface NewsComponent {
    void inject(NewsActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        NewsComponent.Builder view(NewsContract.View view);

        @BindsInstance
        NewsComponent.Builder conversationView(ConversationsContract.View conversationView);

        NewsComponent.Builder appComponent(AppComponent appComponent);

        NewsComponent build();
    }
}