package sbgl3.yunda.module.disassembly.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
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
import sbgl3.yunda.module.disassembly.di.component.DaggerDisassemblyListComponent;
import sbgl3.yunda.module.disassembly.di.module.DisassemblyListModule;
import sbgl3.yunda.module.disassembly.entry.BigPartsBean;
import sbgl3.yunda.module.disassembly.entry.DisassemblyBean;
import sbgl3.yunda.module.disassembly.mvp.contract.DisassemblyListContract;
import sbgl3.yunda.module.disassembly.mvp.presenter.DisassemblyListPresenter;
import sbgl3.yunda.module.disassembly.mvp.ui.adapter.BigPartsAdapter;
import sbgl3.yunda.module.disassembly.mvp.ui.adapter.DisassemblyAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**机车配件拆卸模块-已登记小配件列表
 * @auther 周雪巍
 * @Data 2018/11/28
 */
public class DisassemblyListActivity extends BaseActivity<DisassemblyListPresenter> implements DisassemblyListContract.View {

    @BindView(R.id.menu_tp)
    Toolbar menuTp;
    @BindView(R.id.rvTrains)
    RecyclerView rvTrains;
    @BindView(R.id.svRefresh)
    SmartRefreshLayout svRefresh;
    BigPartsBean bigPartsBean;
    List<DisassemblyBean> mList = new ArrayList<>();
    DisassemblyAdapter adapter;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerDisassemblyListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .disassemblyListModule(new DisassemblyListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_disassembly_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
        ArmsUtils.configRecycleView(rvTrains, new LinearLayoutManager(this));
        adapter = new DisassemblyAdapter(mList);
        rvTrains.setAdapter(adapter);
        loadData();
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                Intent intent1 = new Intent(DisassemblyListActivity.this,DisassemblyRegisterActivity.class);
                intent1.putExtra("disassembly",mList.get(position));
                intent1.putExtra("trainInfo",bigPartsBean.getUnloadTraintype());
                ArmsUtils.startActivity(intent1);
            }
        });
    }

    private void loadData() {
        if(mPresenter!=null&&bigPartsBean!=null){
            showLoading();
            mPresenter.getDisassemblyList(bigPartsBean.getIdx(),"");
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
    public void OnLoadDisassemblySuccess(List<DisassemblyBean> list) {
        mList.clear();
        svRefresh.finishRefresh();
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

}
