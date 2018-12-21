package sbgl3.yunda.module.partsrecondition.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sbgl3.yunda.R;
import sbgl3.yunda.module.partsrecondition.di.component.DaggerPartsListComponent;
import sbgl3.yunda.module.partsrecondition.di.module.PartsListModule;
import sbgl3.yunda.module.partsrecondition.entry.DownPartsBean;
import sbgl3.yunda.module.partsrecondition.mvp.contract.PartsListContract;
import sbgl3.yunda.module.partsrecondition.mvp.presenter.PartsListPresenter;
import sbgl3.yunda.module.partsrecondition.mvp.ui.adapter.DownPartsAdapter;
import sbgl3.yunda.module.partsrecondition.mvp.ui.adapter.PartsTraindapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**配件下车登记模块-已登记配件列表界面
 * @auther 周雪巍
 * @Data 2018/11/28
 */
public class PartsListActivity extends BaseActivity<PartsListPresenter> implements PartsListContract.View {


    @BindView(R.id.menu_tp)
    Toolbar menuTp;
    @BindView(R.id.rvTrains)
    RecyclerView rvTrains;
    @BindView(R.id.svRefresh)
    SmartRefreshLayout svRefresh;
    @BindView(R.id.btNext)
    Button btNext;
    String idx;
    DownPartsAdapter adapter;
    List<DownPartsBean> mList = new ArrayList<>();
    String trainInfo;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPartsListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .partsListModule(new PartsListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_parts_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
                    mPresenter.getPartsList(idx);
                }
            }
        });
        ArmsUtils.configRecycleView(rvTrains, new LinearLayoutManager(this));
        adapter = new DownPartsAdapter(mList);
        rvTrains.setAdapter(adapter);
        Intent intent = getIntent();
        if(intent!=null){
            idx = intent.getStringExtra("idx");
            if(idx!=null){
                if (mPresenter != null) {
                    showLoading();
                    mPresenter.getPartsList(idx);
                }
            }
            String trainNo = intent.getStringExtra("trainNo");
            String name = intent.getStringExtra("name");
            if(name!=null){
                if(trainNo!=null){
                    trainInfo = name+" "+trainNo;
                    menuTp.setTitle(name+" "+trainNo+" 下车配件清单");
                }else {
                    trainInfo = name;
                    menuTp.setTitle(name+" 下车配件清单");
                }
            }else {
                if(trainNo!=null){
                    trainInfo = trainNo;
                    menuTp.setTitle(trainNo+" 下车配件清单");
                }else {
                    menuTp.setTitle("下车配件清单");
                }
            }
        }
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                DownPartsBean bean = mList.get(position);
                if(bean!=null){
                    Intent intent = new Intent(PartsListActivity.this,PartsEditActivity.class);
                    intent.putExtra("parts",bean);
                    if(!StringUtils.isTrimEmpty(trainInfo)){
                        intent.putExtra("trainInfo",trainInfo);
                    }
                    ArmsUtils.startActivity(intent);
                }
            }
        });
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
    public void OnLoadPartsSuccess(List<DownPartsBean> list) {
        svRefresh.finishRefresh();
        mList.clear();
        if(list!=null&&list.size()>0){
            mList.addAll(list);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnLoadFaild(String msg) {
        svRefresh.finishRefresh();
        ToastUtils.showShort(msg);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!StringUtils.isTrimEmpty(idx)){
            if (mPresenter != null) {
                showLoading();
                mPresenter.getPartsList(idx);
            }
        }
    }
}
