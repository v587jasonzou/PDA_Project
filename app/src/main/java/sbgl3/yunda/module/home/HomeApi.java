package sbgl3.yunda.module.home;

import com.jess.arms.base.entry.BaseResponsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import sbgl3.yunda.entry.MenuSimpleBean;
import sbgl3.yunda.module.login.Entry.LoginReponsBody;

public interface HomeApi {
    //登录
    @FormUrlEncoded
    @POST("GZJX/terminalMenu/queryMenu.action")
    Observable<BaseResponsBean<List<MenuSimpleBean>>> getMenus(@Field("loginClient") String loginClient);
}
