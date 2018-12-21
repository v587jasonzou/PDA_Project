package sbgl3.yunda.module.news.di.module;

import dagger.Binds;
import dagger.Module;

import sbgl3.yunda.module.news.mvp.contract.NewsContract;
import sbgl3.yunda.module.news.mvp.model.NewsModel;


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
@Module
public abstract class NewsModule {

    @Binds
    abstract NewsContract.Model bindNewsModel(NewsModel model);
}