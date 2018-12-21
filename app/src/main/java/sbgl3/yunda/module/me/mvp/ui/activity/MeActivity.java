package sbgl3.yunda.module.me.mvp.ui.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sbgl3.yunda.R;
import sbgl3.yunda.module.login.mvp.contract.LoginActivityContract;
import sbgl3.yunda.module.login.mvp.presenter.LoginActivityPresenter;
import sbgl3.yunda.module.me.di.component.DaggerMeComponent;
import sbgl3.yunda.module.me.di.module.MeModule;
import sbgl3.yunda.module.me.mvp.contract.MeContract;
import sbgl3.yunda.module.me.mvp.presenter.MePresenter;



import static com.jess.arms.utils.Preconditions.checkNotNull;


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
public class MeActivity extends BaseActivity<MePresenter> implements MeContract.View ,LifecycleOwner {

    @BindView(R.id.msg)
    TextView msg;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMeComponent //如找不到该类,请编译一下项目
                .builder().appComponent(appComponent).meModule(new MeModule(this)).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_me; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.updateMsg();
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
        this.msg.setText(msg);
    }
}
