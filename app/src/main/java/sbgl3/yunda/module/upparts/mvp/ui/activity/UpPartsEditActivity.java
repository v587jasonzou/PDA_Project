package sbgl3.yunda.module.upparts.mvp.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.utilcode.util.StringUtils;
import com.jess.arms.utils.utilcode.util.ToastUtils;
import com.speedata.utils.ProgressDialogUtils;

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
import sbgl3.yunda.SysInfo;
import sbgl3.yunda.globle.adapter.MenuAdapter;
import sbgl3.yunda.module.getparts.entry.GetPartsBean;
import sbgl3.yunda.module.home.mvp.ui.activity.HomeActivity;
import sbgl3.yunda.module.partsrecondition.entry.PartsTrainBean;
import sbgl3.yunda.module.upparts.di.component.DaggerUpPartsEditComponent;
import sbgl3.yunda.module.upparts.di.module.UpPartsEditModule;
import sbgl3.yunda.module.upparts.entry.UpPartsTrainBean;
import sbgl3.yunda.module.upparts.mvp.contract.UpPartsEditContract;
import sbgl3.yunda.module.upparts.mvp.presenter.UpPartsEditPresenter;
import sbgl3.yunda.module.upparts.mvp.ui.adapter.UpPartsTraindapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**配件上车登记模块-配件上车登记
 * @auther 周雪巍
 * @Data 2018/11/28
 */
public class UpPartsEditActivity extends BaseActivity<UpPartsEditPresenter> implements UpPartsEditContract.View {

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
    @BindView(R.id.tvUpTainName)
    TextView tvUpTainName;
    @BindView(R.id.tvUpTainNo)
    TextView tvUpTainNo;
    @BindView(R.id.rlImages)
    RecyclerView rlImages;
    @BindView(R.id.cvImage)
    CardView cvImage;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    GetPartsBean entry;
    AlertDialog dialog;
    UpPartsTraindapter adapter;
    List<UpPartsTrainBean> mList = new ArrayList<>();
    TextView tvEmpty;
    RecyclerView rlTrains;
    EditText etSearch;
    int EquipPosition = 0;
    List<UpPartsTrainBean> tempList = new ArrayList<>();
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerUpPartsEditComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .upPartsEditModule(new UpPartsEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_up_parts_edit; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
                tvUpTainName.setEnabled(false);
                String str = "";
                if(entry.getAboardTraintype()!=null){
                    str = str+entry.getAboardTraintype();
                }
                if(entry.getAboardTrainno()!=null){
                    if(str.equals("")){
                        str = str+entry.getAboardTrainno();
                    }else {
                        str = str+" "+entry.getAboardTrainno();
                    }
                }

                if(entry.getAboardRepairClass()!=null){
                    if(str.equals("")){
                        str = str+entry.getAboardRepairClass();
                    }else {
                        str = str+" "+entry.getAboardRepairClass();
                    }
                }
                tvUpTainName.setText(str);
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
        if(dialog==null){
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_select_train,null);
            rlTrains = (RecyclerView)view.findViewById(R.id.rlTrains);
            tvEmpty = (TextView)view.findViewById(R.id.tvEmpty);
            etSearch = (EditText)view.findViewById(R.id.etSearch);
            ArmsUtils.configRecycleView(rlTrains, new GridLayoutManager(UpPartsEditActivity.this, 2, OrientationHelper.VERTICAL, false));
            adapter = new UpPartsTraindapter(mList);
            rlTrains.setAdapter(adapter);
            adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, int viewType, Object data, int position) {
                    EquipPosition = position;
                    UpPartsTrainBean entry = mList.get(position);
                    if (entry!=null){
                        String str = "";
                        if(entry.getTrainTypeShortname()!=null){
                            str = entry.getTrainTypeShortname()+" ";
                        }
                        if (entry.getTrainNo()!=null){
                            str = str+entry.getTrainNo();
                        }
                        if(entry.getRepairClassName()!=null){
                            str = str+" "+entry.getRepairClassName();
                        }
                        tvUpTainName.setText(str);
                    }
                    dialog.dismiss();
                }
            });
            dialog = new AlertDialog.Builder(this).setView(view).create();
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    etSearch.setText("");
                }
            });
            etSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    mList.clear();
                    String str = s.toString();
                    if(!StringUtils.isTrimEmpty(str)){
                        for(UpPartsTrainBean bean:tempList){
                            if((bean.getTrainNo()!=null&&bean.getTrainNo().contains(str))||(bean.getTrainTypeShortname()!=null&&bean.getTrainTypeShortname().contains(str))){
                                mList.add(bean);
                            }
                        }
                    }else {
                        mList.addAll(tempList);
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
    @OnClick(R.id.tvUpTainName)
    void getTrainList(){
        if(mPresenter!=null){
            showLoading();
            mPresenter.getTrainList();
        }
    }
    @OnClick(R.id.tvConfirm)
    void Confirm(){
        if(tvUpTainName.getText()==null||tvUpTainName.getText().toString().equals("")){
            ToastUtils.showShort("还未选择上车车型车号！");
            return;
        }
        UpPartsTrainBean bean = mList.get(EquipPosition);
        if(bean!=null){
            if(bean.getTrainTypeShortname()!=null){
                entry.setAboardTraintype(bean.getTrainTypeShortname());
            }
            if(bean.getTrainTypeIdx()!=null){
                entry.setAboardTraintypeIdx(bean.getTrainTypeIdx());
            }
            if(bean.getTrainNo()!=null){
                entry.setAboardTrainno(bean.getTrainNo());
            }
            if(bean.getRepairClassName()!=null){
                entry.setAboardRepairClass(bean.getRepairClassName());
            }
            if(bean.getRepairClassIdx()!=null){
                entry.setAboardRepairClassIdx(bean.getRepairClassIdx());
            }
            if(bean.getRepairTimeName()!=null){
                entry.setAboardRepairTime(bean.getRepairTimeName());
            }
            if(bean.getRepairTimeIdx()!=null){
                entry.setAboardRepairTimeIdx(bean.getRepairTimeIdx());
            }
            entry.setPartsStatus("0201");
            entry.setPartsStatusName("已上车");
        }else {
            ToastUtils.showShort("上车车型车号有问题，不能登记");
            return;
        }
        if(mPresenter!=null){
            showLoading();
            mPresenter.UpParts(JSON.toJSONString(entry));
        }

    }
    @Override
    public void showLoading() {
        ProgressDialogUtils.showProgressDialog(this,"加载中...");
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
    public void OnLoadTrainListSuccess(List<UpPartsTrainBean> list) {
        mList.clear();
        tempList.clear();
        if(list!=null&&list.size()>0){
            mList.addAll(list);
            tempList.addAll(list);
        }
        dialog.show();
        adapter.notifyDataSetChanged();
        if(mList.size()>0){
            tvEmpty.setVisibility(View.GONE);
        }else {
            tvEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void OnLoadFaild(String msg) {
        ToastUtils.showShort(msg);
    }
}
