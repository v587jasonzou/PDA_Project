package sbgl3.yunda.module.partsrecondition.mvp.ui.activity;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.utilcode.util.StringUtils;
import com.jess.arms.utils.utilcode.util.ToastUtils;
import com.speedata.utils.ProgressDialogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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
import sbgl3.yunda.entry.MessageBean;
import sbgl3.yunda.module.partsrecondition.di.component.DaggerPartsEditComponent;
import sbgl3.yunda.module.partsrecondition.di.module.PartsEditModule;
import sbgl3.yunda.module.partsrecondition.entry.DownPartsBean;
import sbgl3.yunda.module.partsrecondition.entry.PartsFactoryBean;
import sbgl3.yunda.module.partsrecondition.entry.PartsModelBean;
import sbgl3.yunda.module.partsrecondition.mvp.contract.PartsEditContract;
import sbgl3.yunda.module.partsrecondition.mvp.presenter.PartsEditPresenter;
import sbgl3.yunda.widget.zxing.android.CaptureActivity;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**配件下车登记模块-配件登记界面
 * @auther 周雪巍
 * @Data 2018/11/28
 */
public class PartsEditActivity extends BaseActivity<PartsEditPresenter> implements PartsEditContract.View {

    @BindView(R.id.menu_tp)
    Toolbar menuTp;
    @BindView(R.id.tvScanCode)
    TextView tvScanCode;
    @BindView(R.id.ivScan)
    ImageView ivScan;
    @BindView(R.id.tvPartsModel)
    TextView tvPartsModel;
    @BindView(R.id.etPartsNo)
    EditText etPartsNo;
    @BindView(R.id.tvMaker)
    TextView tvMaker;
    @BindView(R.id.etStuffNo)
    EditText etStuffNo;
    @BindView(R.id.etLocation)
    EditText etLocation;
    @BindView(R.id.rlImages)
    RecyclerView rlImages;
    @BindView(R.id.cvImage)
    CardView cvImage;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.etPartsReason)
    EditText etPartsReason;
    DownPartsBean entry;
    String status;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPartsEditComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .partsEditModule(new PartsEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_parts_edit; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
        if (intent != null) {
            entry = (DownPartsBean) intent.getSerializableExtra("parts");
            String trainInfo = intent.getStringExtra("trainInfo");
            if (entry != null) {
                status = entry.getStatus();
                if ("2".equals(status)) {
                    tvMaker.setEnabled(false);
                    tvPartsModel.setEnabled(false);
                    tvScanCode.setEnabled(false);
                    tvConfirm.setVisibility(View.GONE);
                    etLocation.setEnabled(false);
                    etPartsNo.setEnabled(false);
                    etPartsReason.setEnabled(false);
                    etPartsReason.setHint("");
                    ivScan.setVisibility(View.GONE);
                    ivDelete.setVisibility(View.GONE);
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
                        if (entry.getPartsTypeIdx() != null) {
                            tvPartsModel.setTag(entry.getPartsTypeIdx());
                        }
                    }
                    if (entry.getPartsNo() != null) {
                        etPartsNo.setText(entry.getPartsNo());
                    }
                    if (entry.getMadeFactoryName() != null) {
                        tvMaker.setText(entry.getMadeFactoryName());
                    }
                    if (entry.getUnloadPlace() != null) {
                        etLocation.setText(entry.getUnloadPlace());
                    }
                    if (entry.getUnloadReason() != null && !"null".equals(entry.getUnloadReason())) {
                        etPartsReason.setText(entry.getUnloadReason());
                    }
                }
            }
        }

    }

    @Override
    public void showLoading() {
        ProgressDialogUtils.showProgressDialog(this, "加载中...");
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ScanSuccess(MessageBean messageBean) {
        if ("1".equals(status) && messageBean != null && "scan".equals(messageBean.getMsgType())) {
            if (messageBean.getMsgInfo() != null) {
                ToastUtils.showShort("扫码成功");
                tvScanCode.setText(messageBean.getMsgInfo());
                ivDelete.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick(R.id.ivDelete)
    void Delete() {
        tvScanCode.setText("");
        ivDelete.setVisibility(View.GONE);
    }

    @OnClick(R.id.ivScan)
    void Scan() {
        Intent intent = new Intent(PartsEditActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @OnClick(R.id.tvPartsModel)
    void getModel() {
        if (mPresenter != null) {
            showLoading();
            mPresenter.getModels(entry.getPartsIdx());
        }
    }

    @OnClick(R.id.tvMaker)
    void getFactory() {
        if (mPresenter != null) {
            showLoading();
            mPresenter.getFactorys(entry.getMadeFactoryIdx());
        }
    }

    @OnClick(R.id.tvConfirm)
    void Confirm() {
        if (isAllReady()) {
            if (etPartsReason.getText() != null & !etPartsReason.getText().toString().trim().equals("")) {
                entry.setUnloadReason(etPartsReason.getText().toString());
            }
            entry.setIdentificationCode(tvScanCode.getText().toString());
            entry.setSpecificationModel(tvPartsModel.getText().toString());
            entry.setPartsNo(etPartsNo.getText().toString());
            entry.setMadeFactoryName(tvMaker.getText().toString());
            if (etLocation.getText() != null && !etLocation.getText().toString().equals(""))
                entry.setUnloadPlace(etLocation.getText().toString());
            entry.setPartsTypeIdx(tvPartsModel.getTag().toString());
            entry.setStatus("2");
            List<DownPartsBean> list = new ArrayList<>();
            list.add(entry);
            if (mPresenter != null) {
                showLoading();
                mPresenter.Confirm(JSON.toJSONString(list));
            }
        }
    }

    private boolean isAllReady() {
        if (tvScanCode.getText() == null || StringUtils.isTrimEmpty(tvScanCode.getText().toString())) {
            ToastUtils.showShort("还未扫描二维码");
            return false;
        }
        if (tvPartsModel.getText() == null || StringUtils.isTrimEmpty(tvPartsModel.getText().toString())) {
            ToastUtils.showShort("还未选择配件规格");
            return false;
        }
        if (etPartsNo.getText() == null || StringUtils.isTrimEmpty(etPartsNo.getText().toString())) {
            ToastUtils.showShort("还未填写配件编号");
            return false;
        }
        if (tvMaker.getText() == null || StringUtils.isTrimEmpty(tvMaker.getText().toString())) {
            ToastUtils.showShort("还未填写生产厂家");
            return false;
        }
//        if (etLocation.getText() == null || StringUtils.isTrimEmpty(etLocation.getText().toString())) {
//            ToastUtils.showShort("还未填写安装位置");
//            return false;
//        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                tvScanCode.setText(content);
                ivDelete.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onLoadModelSuccess(List<PartsModelBean> list) {
        if (list != null && list.size() > 0) {
            final PopupMenu popupMenu = new PopupMenu(this, tvPartsModel);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(tvPartsModel.getWindowToken(), 0);
                    }
                    tvPartsModel.setText(item.getTitle().toString());
                    tvPartsModel.setTag(list.get(item.getItemId()).getIdx());
                    popupMenu.dismiss();
                    return false;
                }
            });
            Menu menu_more = popupMenu.getMenu();
            menu_more.clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                menu_more.add(Menu.NONE, i, i, list.get(i).getSpecificationModel());
            }
            popupMenu.show();
        }
    }

    @Override
    public void onLoadFactorySuccess(List<PartsFactoryBean> list) {
        if (list != null && list.size() > 0) {
            final PopupMenu popupMenu = new PopupMenu(this, tvMaker);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(tvMaker.getWindowToken(), 0);
                    }
                    tvMaker.setText(item.getTitle().toString());
                    tvMaker.setTag(list.get(item.getItemId()).getIdx());
                    popupMenu.dismiss();
                    return false;
                }
            });
            Menu menu_more = popupMenu.getMenu();
            menu_more.clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                menu_more.add(Menu.NONE, i, i, list.get(i).getMadeFactoryName());
            }
            popupMenu.show();
        }

    }

    @Override
    public void onLoadFaild(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void ConfirmSuccess() {
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
}
