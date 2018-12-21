package sbgl3.yunda.module.getparts.mvp.ui.activity;

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
import sbgl3.yunda.module.getparts.di.component.DaggerGetParstListComponent;
import sbgl3.yunda.module.getparts.di.module.GetParstListModule;
import sbgl3.yunda.module.getparts.entry.GetPartsBean;
import sbgl3.yunda.module.getparts.mvp.contract.GetParstListContract;
import sbgl3.yunda.module.getparts.mvp.presenter.GetParstListPresenter;
import sbgl3.yunda.module.getparts.mvp.ui.adapter.GetPartsAdapter;
import sbgl3.yunda.module.partsrecondition.entry.PartsTrainBean;
import sbgl3.yunda.module.partsrecondition.mvp.ui.activity.PartsEditActivity;
import sbgl3.yunda.module.partsrecondition.mvp.ui.adapter.DownPartsAdapter;
import sbgl3.yunda.widget.zxing.android.CaptureActivity;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**配件接收登记模块-已登记配件列表
 * @auther 周雪巍
 * @Data 2018/11/28
 */
public class GetParstListActivity extends BaseActivity<GetParstListPresenter> implements GetParstListContract.View {

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
    List<GetPartsBean> list = new ArrayList<>();
    List<GetPartsBean> tempList = new ArrayList<>();
    GetPartsAdapter adapter;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerGetParstListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .getParstListModule(new GetParstListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_get_parst_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
                    showLoading();
                    mPresenter.getPartsList("","010102",false);
                }
            }
        });
        ArmsUtils.configRecycleView(rvParts, new LinearLayoutManager(this));
        adapter = new GetPartsAdapter(list);
        rvParts.setAdapter(adapter);
        if(mPresenter!=null){
            showLoading();
            mPresenter.getPartsList("","010102",false);
        }
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                GetPartsBean bean = list.get(position);
                if(bean!=null){
                    Intent intent = new Intent(GetParstListActivity.this,GetPartsEditActivity.class);
                    intent.putExtra("GetPart",bean);
                    intent.putExtra("type","1");
                    ArmsUtils.startActivity(intent);
                }
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
                list.clear();
                String str = s.toString();
                if(!StringUtils.isTrimEmpty(str)){
                    for(GetPartsBean bean:tempList){
                        if((bean.getPartsNo()!=null&&bean.getPartsNo().contains(str))||(bean.getSpecificationModel()!=null&&bean.getSpecificationModel().contains(str))){
                            list.add(bean);
                        }
                    }
                }else {
                    list.addAll(tempList);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
    String scancode;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ScanSuccess(MessageBean messageBean) {
        if (messageBean != null && "scan".equals(messageBean.getMsgType())) {
            if (messageBean.getMsgInfo() != null) {
                if (mPresenter != null) {
                    scancode = messageBean.getMsgInfo();
                    showLoading();
                    mPresenter.getPartsList(messageBean.getMsgInfo(),"010101",true);
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
    public void OnLoadPartsListSuccess(List<GetPartsBean> list) {
        svRefresh.finishRefresh();
        this.list.clear();
        tempList.clear();
        if(list!=null&&list.size()>0){
            this.list.addAll(list);
            tempList.addAll(list);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnLoadScanPartSuccess(GetPartsBean bean) {
        if(bean!=null){
            ToastUtils.showShort("扫码成功");
            Intent intent = new Intent(GetParstListActivity.this,GetPartsEditActivity.class);
            intent.putExtra("GetPart",bean);
            intent.putExtra("type","0");
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
            mPresenter.getPartsList("","010102",false);
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
                    mPresenter.getPartsList(content,"010101",true);
                }
            }
        }
    }
    @OnClick(R.id.btAdd)
    void Scan(){
        Intent intent = new Intent(GetParstListActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }
    @Override
    public void OnLoadFaild(String msg) {
        svRefresh.finishRefresh();
        if(msg.equals("2")){
            ToastUtils.showShort("未查询到二维码为："+scancode+"的配件信息");
        }else {
            ToastUtils.showShort(msg);
        }
    }
}
