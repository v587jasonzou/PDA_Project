package sbgl3.yunda.module.assembly.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.utilcode.util.ToastUtils;
import com.speedata.utils.ProgressDialogUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import sbgl3.yunda.R;
import sbgl3.yunda.SysInfo;
import sbgl3.yunda.module.assembly.di.component.DaggerAssemblyEditComponent;
import sbgl3.yunda.module.assembly.di.module.AssemblyEditModule;
import sbgl3.yunda.module.assembly.mvp.contract.AssemblyEditContract;
import sbgl3.yunda.module.assembly.mvp.presenter.AssemblyEditPresenter;
import sbgl3.yunda.module.disassembly.entry.DisassemblyBean;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**机车配件组装模块-小配件登记界面
 * @auther 周雪巍
 * @Data 2018/11/28
 */
public class AssemblyEditActivity extends BaseActivity<AssemblyEditPresenter> implements AssemblyEditContract.View {

    @BindView(R.id.menu_tp)
    Toolbar menuTp;
    @BindView(R.id.tvScanCode)
    TextView tvScanCode;
    @BindView(R.id.tvPartsModel)
    TextView tvPartsModel;
    @BindView(R.id.etPartsNo)
    TextView etPartsNo;
    @BindView(R.id.tvParts)
    TextView tvParts;
    @BindView(R.id.etPartsReason)
    TextView etPartsReason;
    @BindView(R.id.rlImages)
    RecyclerView rlImages;
    @BindView(R.id.cvImage)
    CardView cvImage;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    String status;
    DisassemblyBean entry;
    String parentActivityIdx;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAssemblyEditComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .assemblyEditModule(new AssemblyEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_assembly_edit; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTranslucentStatus();
        setSupportActionBar(menuTp);
        menuTp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        entry = (DisassemblyBean) intent.getSerializableExtra("parts");
        String trainInfo = intent.getStringExtra("trainInfo");
        parentActivityIdx = intent.getStringExtra("parentActivityIdx");
        if (entry != null) {
            status = entry.getStatus();
            if ("2".equals(status)) {
                tvPartsModel.setEnabled(false);
                tvScanCode.setEnabled(false);
                tvConfirm.setVisibility(View.GONE);
                etPartsNo.setEnabled(false);
                etPartsReason.setEnabled(false);
                etPartsReason.setHint("");
            }
            if (entry.getPartsName() != null) {
                if (trainInfo != null) {
                    menuTp.setTitle(trainInfo + " " + entry.getPartsName());
                }
                if (entry.getIdentificationCode() != null) {
                    tvScanCode.setText(entry.getIdentificationCode());
                }
                if (entry.getSpecificationModel() != null) {
                    tvPartsModel.setText(entry.getSpecificationModel());
                }
                if (entry.getPartsNo() != null) {
                    etPartsNo.setText(entry.getPartsNo());
                }
                if (entry.getRemark() != null) {
                    etPartsReason.setText(entry.getRemark());
                }
            }
        }
    }

    @Override
    public void showLoading() {
        ProgressDialogUtils.showProgressDialog(this,"加载中");
    }

    @Override
    public void hideLoading() {
        ProgressDialogUtils.dismissProgressDialog();
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

    @OnClick(R.id.tvConfirm)
    void Confirm(){
//        if(tvParts.getTag()!=null){
//            entry.setPartsIdx(tvParts.getTag().toString());
//        }
        if(tvParts.getText()!=null&&!tvParts.getText().toString().equals("")){
            entry.setPartsName(tvParts.getText().toString());
        }
        entry.setParentActivityIdx(parentActivityIdx);
        entry.setWorkUserName(SysInfo.emp.getEmpname());
        showLoading();
        mPresenter.Confirm(JSON.toJSONString(entry));
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void OnConfirmSuccess() {
        ToastUtils.showShort("登记成功");
        Observable.just(1).observeOn(AndroidSchedulers.mainThread()).delay(500, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void OnLoadFaild(String msg) {
        ToastUtils.showShort(msg);
    }
    @OnClick(R.id.tvParts)
    void getparts(){
        showLoading();
        if(entry.getPartsTypeIdx()!=null){
            mPresenter.getAssembly(entry.getPartsTypeIdx());
        }else {
            mPresenter.getAssembly("");
        }

    }
    @Override
    public void OnGetPartsSuccess(List<DisassemblyBean> list) {
        if (list != null && list.size() > 0) {
            final PopupMenu popupMenu = new PopupMenu(this, tvParts);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(tvParts.getWindowToken(), 0);
                    }
                    tvParts.setText(item.getTitle().toString());
                    tvParts.setTag(list.get(item.getItemId()).getIdx());
                    popupMenu.dismiss();
                    return false;
                }
            });
            Menu menu_more = popupMenu.getMenu();
            menu_more.clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                menu_more.add(Menu.NONE, i, i, list.get(i).getPartsName());
            }
            popupMenu.show();
        }
    }
}
