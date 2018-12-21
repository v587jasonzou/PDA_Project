package sbgl3.yunda.module.getparts.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.utilcode.util.ToastUtils;
import com.speedata.utils.ProgressDialogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import sbgl3.yunda.R;
import sbgl3.yunda.module.getparts.di.component.DaggerGetPartsEditComponent;
import sbgl3.yunda.module.getparts.di.module.GetPartsEditModule;
import sbgl3.yunda.module.getparts.entry.GetPartsBean;
import sbgl3.yunda.module.getparts.mvp.contract.GetPartsEditContract;
import sbgl3.yunda.module.getparts.mvp.presenter.GetPartsEditPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**配件接收登记模块-配件接收登记界面
 * @auther 周雪巍
 * @Data 2018/11/28
 */
public class GetPartsEditActivity extends BaseActivity<GetPartsEditPresenter> implements GetPartsEditContract.View {

    @BindView(R.id.menu_tp)
    Toolbar menuTp;
    @BindView(R.id.tvScanCode)
    TextView tvScanCode;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.ivScan)
    ImageView ivScan;
    @BindView(R.id.tvPartsModel)
    TextView tvPartsModel;
    @BindView(R.id.tvPartsNo)
    TextView tvPartsNo;
    @BindView(R.id.tvMaker)
    TextView tvMaker;
    @BindView(R.id.etStuffNo)
    EditText etStuffNo;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.tvOrgName)
    TextView tvOrgName;
    @BindView(R.id.rlImages)
    RecyclerView rlImages;
    @BindView(R.id.cvImage)
    CardView cvImage;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    GetPartsBean entry;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerGetPartsEditComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .getPartsEditModule(new GetPartsEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_get_parts_edit; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
        if(intent!=null){
            entry = (GetPartsBean)intent.getSerializableExtra("GetPart");
            String type = intent.getStringExtra("type");
            if("1".equals(type)){
                tvConfirm.setVisibility(View.GONE);
            }
            if(entry!=null){
                if (entry.getIdentificationCode()!=null){
                    tvScanCode.setText(entry.getIdentificationCode());
                }
                if(entry.getSpecificationModel()!=null){
                    tvPartsModel.setText(entry.getSpecificationModel());
                }
                if(entry.getPartsNo()!=null){
                    tvPartsNo.setText(entry.getPartsNo());
                }
                if(entry.getMadeFactoryName()!=null){
                    tvMaker.setText(entry.getMadeFactoryName());
                }
                String strs = "";
                if(entry.getUnloadTraintype()!=null){
                    strs = strs+entry.getUnloadTraintype();
                }
                if(entry.getUnloadTrainno()!=null){
                    if(strs.equals("")){
                        strs = strs+entry.getUnloadTrainno();
                    }else {
                        strs = strs+" "+entry.getUnloadTrainno();
                    }
                }
                if(entry.getPartsName()!=null){
                    if(entry.getUnloadPlace()!=null){
                        menuTp.setTitle(strs+" "+entry.getPartsName()+" "+entry.getUnloadPlace());
                    }else {
                        menuTp.setTitle(strs+" "+entry.getPartsName());
                    }

                }else {
                    if(entry.getUnloadPlace()!=null){
                        menuTp.setTitle(strs+" "+entry.getUnloadPlace());
                    }else {
                        menuTp.setTitle(strs);
                    }
                }
                if(entry.getUnloadRepairClass()!=null){
                    if(strs.equals("")){
                        strs = strs+entry.getUnloadRepairClass();
                    }else {
                        strs = strs+" "+entry.getUnloadRepairClass();
                    }
                }
                tvLocation.setText(strs);
                if(entry.getManageDept()!=null){
                    tvOrgName.setText(entry.getManageDept());
                }
            }
        }
    }

    @OnClick(R.id.tvConfirm)
    void Confirm(){
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList<>();
        if (entry.getIdentificationCode()!=null){
            map.put("identificationCode",entry.getIdentificationCode());
        }
        if(entry.getSpecificationModel()!=null){
            map.put("specificationModel",entry.getSpecificationModel());
        }
        if(entry.getPartsNo()!=null){
            map.put("partsNo",entry.getPartsNo());
        }
        if(entry.getPartsName()!=null){
            map.put("partsName",entry.getPartsName());
        }
        if(entry.getPartsTypeIdx()!=null){
            map.put("partsTypeIdx",entry.getPartsTypeIdx());
        }
        if(entry.getIdx()!=null){
            map.put("idx",entry.getIdx());
        }
        if(entry.getManageDept()!=null){
            map.put("manageDept",entry.getManageDept());
        }
        if(entry.getManageDeptId()!=null){
            map.put("manageDeptId",entry.getManageDeptId());
        }
        list.add(map);
        if(mPresenter!=null){
            showLoading();
            mPresenter.getParts(JSON.toJSONString(list));
        }
    }
    @Override
    public void showLoading() {
        ProgressDialogUtils.showProgressDialog(this,"正在接收中，请稍后...");
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

    @Override
    public void OnGetSuccess() {
        ToastUtils.showShort("接收成功");
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
}
