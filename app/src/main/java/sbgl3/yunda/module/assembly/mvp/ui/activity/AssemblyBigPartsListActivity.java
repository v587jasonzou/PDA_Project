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
import android.widget.ImageView;

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
import sbgl3.yunda.module.assembly.di.component.DaggerAssemblyBigPartsListComponent;
import sbgl3.yunda.module.assembly.di.module.AssemblyBigPartsListModule;
import sbgl3.yunda.module.assembly.mvp.contract.AssemblyBigPartsListContract;
import sbgl3.yunda.module.assembly.mvp.presenter.AssemblyBigPartsListPresenter;
import sbgl3.yunda.module.disassembly.entry.BigPartsBean;
import sbgl3.yunda.module.disassembly.mvp.ui.activity.BigPartsListActivity;
import sbgl3.yunda.module.disassembly.mvp.ui.activity.DisassemblyListActivity;
import sbgl3.yunda.module.disassembly.mvp.ui.adapter.BigPartsAdapter;
import sbgl3.yunda.module.getparts.mvp.ui.activity.GetPartsEditActivity;
import sbgl3.yunda.widget.zxing.android.CaptureActivity;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**机车配件组装模块-大配件清单列表*
 * @auther 周雪巍
 * @Data 2018/11/28
 */


public class AssemblyBigPartsListActivity extends BaseActivity<AssemblyBigPartsListPresenter> implements AssemblyBigPartsListContract.View {

    @BindView(R.id.menu_tp)
    Toolbar menuTp;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.btAdd)
    ImageView btAdd;
    @BindView(R.id.rvParts)
    RecyclerView rvParts;
    @BindView(R.id.svRefresh)
    SmartRefreshLayout svRefresh;
    boolean isScan = false;
    String scancode;
    List<BigPartsBean> mList = new ArrayList<>();
    List<BigPartsBean> tempList = new ArrayList<>();
    BigPartsAdapter adapter;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAssemblyBigPartsListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .assemblyBigPartsListModule(new AssemblyBigPartsListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_assembly_big_parts_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
                if (mPresenter != null) {
                    isScan = false;
                    loadData();
                }
            }
        });
        ArmsUtils.configRecycleView(rvParts, new LinearLayoutManager(this));
        adapter = new BigPartsAdapter(mList);
        rvParts.setAdapter(adapter);
        isScan = false;
        loadData();
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                Intent intent = new Intent(AssemblyBigPartsListActivity.this,AssemblyListActivity.class);
                intent.putExtra("parts",mList.get(position));
                ArmsUtils.startActivity(intent);
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
                    for(BigPartsBean bean:tempList){
                        if((bean.getUnloadTraintype()!=null&&bean.getUnloadTraintype().contains(str))||(bean.getUnloadRepairClass()!=null&&bean.getUnloadRepairClass().contains(str))
                                ||(bean.getPartsName()!=null&&bean.getPartsName().contains(str))){
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
        if(mPresenter!=null){
            showLoading();
            mPresenter.getBigPartsList("","",isScan);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ScanSuccess(MessageBean messageBean) {
        if (messageBean != null && "scan".equals(messageBean.getMsgType())) {
            if (messageBean.getMsgInfo() != null) {
                if (mPresenter != null) {
                    scancode = messageBean.getMsgInfo();
                    showLoading();
                    mPresenter.getBigPartsList("",scancode,true);
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
    public void OnLoadListSuccess(List<BigPartsBean> list) {
        svRefresh.finishRefresh();
        mList.clear();
        tempList.clear();
        if(list!=null&&list.size()>0){
            mList.addAll(list);
            tempList.addAll(list);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnLoadFaild(String msg) {
        svRefresh.finishRefresh();
        ToastUtils.showShort(msg);
    }

    @Override
    public void isScanSuccess(BigPartsBean bean) {
        if(bean!=null){
            ToastUtils.showShort("扫码成功");
            Intent intent = new Intent(AssemblyBigPartsListActivity.this,AssemblyListActivity.class);
            intent.putExtra("parts",bean);
            ArmsUtils.startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        if (mPresenter != null) {
            showLoading();
            mPresenter.getBigPartsList("","",false);
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
                if (mPresenter != null) {
                    scancode = content;
                    showLoading();
                    mPresenter.getBigPartsList("",content,true);
                }
            }
        }
    }
    @OnClick(R.id.btAdd)
    void Scan(){
        Intent intent = new Intent(AssemblyBigPartsListActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }
}
