package sbgl3.yunda.module.assembly.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.utilcode.util.StringUtils;
import com.jess.arms.utils.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.speedata.utils.ProgressDialogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sbgl3.yunda.R;
import sbgl3.yunda.entry.MessageBean;
import sbgl3.yunda.module.assembly.di.component.DaggerAssemblyListComponent;
import sbgl3.yunda.module.assembly.di.module.AssemblyListModule;
import sbgl3.yunda.module.assembly.entry.AssemblyPartsBean;
import sbgl3.yunda.module.assembly.mvp.contract.AssemblyListContract;
import sbgl3.yunda.module.assembly.mvp.presenter.AssemblyListPresenter;
import sbgl3.yunda.module.disassembly.entry.BigPartsBean;
import sbgl3.yunda.module.disassembly.entry.DisassemblyBean;
import sbgl3.yunda.module.disassembly.mvp.ui.activity.DisassemblyListActivity;
import sbgl3.yunda.module.disassembly.mvp.ui.activity.DisassemblyRegisterActivity;
import sbgl3.yunda.module.disassembly.mvp.ui.adapter.DisassemblyAdapter;
import sbgl3.yunda.widget.zxing.android.CaptureActivity;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**机车配件组装模块-小配件列表界面
 * @auther 周雪巍
 * @Data 2018/11/28
 */
public class AssemblyListActivity extends BaseActivity<AssemblyListPresenter> implements AssemblyListContract.View {

    @BindView(R.id.menu_tp)
    Toolbar menuTp;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.rvParts)
    RecyclerView rvParts;
    @BindView(R.id.svRefresh)
    SmartRefreshLayout svRefresh;
    @BindView(R.id.btAdd)
    TextView btAdd;
    BigPartsBean bigPartsBean;
    List<DisassemblyBean> mList = new ArrayList<>();
    List<DisassemblyBean> tempList = new ArrayList<>();
    DisassemblyAdapter adapter;
    boolean isScan = false;
    String scancode;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAssemblyListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .assemblyListModule(new AssemblyListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_assembly_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
        svRefresh.setRefreshHeader(new ClassicsHeader(this));
        svRefresh.setRefreshFooter(new ClassicsFooter(this));
        svRefresh.setNoMoreData(true);
        svRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadData();
            }
        });
        Intent intent = getIntent();
        if(intent!=null){
            bigPartsBean = (BigPartsBean) intent.getSerializableExtra("parts");
            if(bigPartsBean!=null&&mPresenter!=null){
                String str = "";
                if(bigPartsBean.getUnloadTraintype()!=null){
                    str = bigPartsBean.getUnloadTraintype();
                }
                if(bigPartsBean.getUnloadRepairClass()!=null){
                    str = str+" "+bigPartsBean.getUnloadRepairClass();
                }
                if(bigPartsBean.getPartsName()!=null){
                    str = str+"-"+bigPartsBean.getPartsName();
                }
                if(bigPartsBean.getUnloadPlace()!=null){
                    str = str+" "+bigPartsBean.getUnloadPlace();
                }
                menuTp.setTitle(str);
            }
        }
        ArmsUtils.configRecycleView(rvParts, new LinearLayoutManager(this));
        adapter = new DisassemblyAdapter(mList);
        rvParts.setAdapter(adapter);
        loadData();
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                Intent intent1 = new Intent(AssemblyListActivity.this,AssemblyEditActivity.class);
                intent1.putExtra("disassembly",mList.get(position));
                intent1.putExtra("trainInfo",bigPartsBean.getUnloadTraintype());
                ArmsUtils.startActivity(intent1);
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
                    for(DisassemblyBean bean:tempList){
                        if((bean.getPartsNo()!=null&&bean.getPartsNo().contains(str))
                                ||(bean.getSpecificationModel()!=null&&bean.getSpecificationModel().contains(str))){
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
    private void loadData() {
        if(mPresenter!=null&&bigPartsBean!=null){
            showLoading();
            mPresenter.getAssembly(bigPartsBean.getIdx(),"","2");
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ScanSuccess(MessageBean messageBean) {
        if (messageBean != null && "scan".equals(messageBean.getMsgType())) {
            if (messageBean.getMsgInfo() != null) {
                if (mPresenter != null) {
                    scancode = messageBean.getMsgInfo();
                    showLoading();
                    mPresenter.ScanCode(scancode);
                }
            }
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
    public void OnLoadAssemblySuccess(List<DisassemblyBean> list) {
        mList.clear();
        tempList.clear();
        svRefresh.finishRefresh();
        if(list!=null&&list.size()>0){
            tempList.addAll(list);
            mList.addAll(list);
        }else {
            ToastUtils.showShort("无相关配件信息");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnLoadFaild(String msg) {
        svRefresh.finishRefresh();
        ToastUtils.showShort(msg);
    }

    @Override
    public void OnLoadScanSuccess(DisassemblyBean bean) {
        if(bean!=null){
            if("0103".equals(bean.getPartsStatus())){
                ToastUtils.showShort("扫码成功");
                Intent intent = new Intent(AssemblyListActivity.this,AssemblyEditActivity.class);
                intent.putExtra("parts",bean);
                intent.putExtra("trainInfo",bigPartsBean.getUnloadTraintype());
                intent.putExtra("parentActivityIdx",bigPartsBean.getIdx());
                ArmsUtils.startActivity(intent);
            }else {
                ToastUtils.showShort("当前扫描配件不是良好状态，不能登记");
            }

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        loadData();
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
                if (mPresenter != null) {
                    scancode = content;
                    showLoading();
                    mPresenter.ScanCode(content);
                }
            }
        }
    }
    @OnClick(R.id.btAdd)
    void Scan(){
        Intent intent = new Intent(AssemblyListActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }
}
