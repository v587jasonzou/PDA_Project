package sbgl3.yunda.module.message.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import javax.inject.Inject;

import sbgl3.yunda.R;
import sbgl3.yunda.module.me.di.module.MeModule;
import sbgl3.yunda.module.me.mvp.contract.MeContract;
import sbgl3.yunda.module.me.mvp.presenter.MePresenter;
import sbgl3.yunda.module.message.di.component.DaggerConversationsComponent;
import sbgl3.yunda.module.message.mvp.contract.ConversationsContract;
import sbgl3.yunda.module.message.mvp.presenter.ConversationsPresenter;


import static com.jess.arms.utils.Preconditions.checkNotNull;


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
public class ConversationsActivity extends BaseActivity<ConversationsPresenter> implements ConversationsContract.View,MeContract.View {

    @Inject
    MePresenter mePresenter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerConversationsComponent //如找不m到该类,请编译一下项目
                .builder().appComponent(appComponent).view(this).meModule(new MeModule(this)).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_conversations; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mePresenter.updateMsg();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void updateMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
