package sbgl3.yunda.module.message.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import sbgl3.yunda.module.message.mvp.contract.ConversationsContract;
import sbgl3.yunda.module.message.mvp.model.ConversationsModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/06/2018 23:44
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class ConversationsModule {

   /* public ConversationsModule(ConversationsContract.View view) {
        this.view = view;
    }

    private ConversationsContract.View view;



    @Provides
    ConversationsContract.View provideConversationsView(){
        return this.view;
    }*/

    @Binds
    abstract ConversationsContract.Model bindConversationsModel(ConversationsModel model);
}