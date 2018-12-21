package sbgl3.yunda.module.partsrecondition.mvp.ui.activity;

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
import android.widget.Button;
import android.widget.EditText;

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
import sbgl3.yunda.module.partsrecondition.di.component.DaggerPartsRecanditionComponent;
import sbgl3.yunda.module.partsrecondition.di.module.PartsRecanditionModule;
import sbgl3.yunda.module.partsrecondition.entry.PartsTrainBean;
import sbgl3.yunda.module.partsrecondition.mvp.contract.PartsRecanditionContract;
import sbgl3.yunda.module.partsrecondition.mvp.presenter.PartsRecanditionPresenter;
import sbgl3.yunda.module.partsrecondition.mvp.ui.adapter.PartsTraindapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**配件下车登记模块-机车列表界面
 * @auther 周雪巍
 * @Data 2018/11/28
 */
public class PartsRecanditionActivity extends BaseActivity<PartsRecanditionPresenter> implements PartsRecanditionContract.View {

    @BindView(R.id.menu_tp)
    Toolbar menuTp;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.rvTrains)
    RecyclerView rvTrains;
    @BindView(R.id.svRefresh)
    SmartRefreshLayout svRefresh;
    @BindView(R.id.btNext)
    Button btNext;
    PartsTraindapter adapter;
    List<PartsTrainBean> mList = new ArrayList<>();
    List<PartsTrainBean> tempList = new ArrayList<>();
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPartsRecanditionComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .partsRecanditionModule(new PartsRecanditionModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_parts_recandition_train_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
                    etSearch.setText("");
                    showLoading();
                    mPresenter.getTrainList();
                }
            }
        });
        ArmsUtils.configRecycleView(rvTrains, new LinearLayoutManager(this));
        adapter = new PartsTraindapter(mList);
        rvTrains.setAdapter(adapter);
        if (mPresenter != null) {
            showLoading();
            mPresenter.getTrainList();
        }
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
                    for(PartsTrainBean bean:tempList){
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
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                if(mList.get(position)!=null){
                    Intent intent = new Intent(PartsRecanditionActivity.this,PartsListActivity.class);
                    intent.putExtra("idx",mList.get(position).getIdx());
                    if(mList.get(position).getTrainNo()!=null){
                        intent.putExtra("trainNo",mList.get(position).getTrainNo());
                    }
                    if(mList.get(position).getTrainTypeShortname()!=null){
                        intent.putExtra("name",mList.get(position).getTrainTypeShortname());
                    }
                    ArmsUtils.startActivity(intent);
                }
            }
        });
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
    public void OnLoadTrainSuccess(List<PartsTrainBean> list) {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
