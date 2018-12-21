package sbgl3.yunda.module.login;

import com.jess.arms.base.entry.BaseResponsBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import sbgl3.yunda.module.login.Entry.LoginReponsBody;

public interface LoginApi {
    //登录
    @FormUrlEncoded
    @POST("CoreFrameSSOAuthServer/user/login")
    Observable<BaseResponsBean> LoginFirst(@Field("userId") String userid,
                                           @Field("passWord") String userpwd,
                                           @Field("clientType") String clientType);

    //登录
//    @FormUrlEncoded
//    @POST("CoreFrame/purview!purviewValidate.action")
//    Observable<LoginReponsBody> Login(@Field("userId") String userid,
//                                      @Field("passWord") String userpwd);
    @FormUrlEncoded
    @POST("GZJX/component/login/terminalLogin.action")
    Observable<BaseResponsBean<LoginReponsBody>> Login(@Field("userId") String userid,
                                      @Field("passWord") String userpwd);

}
