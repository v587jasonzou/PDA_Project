package sbgl3.yunda.module.getparts;

import com.jess.arms.base.entry.BaseResponsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import sbgl3.yunda.module.getparts.entry.GetPartsBean;

public interface GetPartsApi {
    @FormUrlEncoded
    @POST("GZJX/partsaccount/partsAccount/findpartsAccount.action")
    Observable<BaseResponsBean<List<GetPartsBean>>> getPartsList(@Field("identificationCode") String idx,
                                                                 @Field("partsStatus") String status);

    @FormUrlEncoded
    @POST("GZJX/partsaccount/partsAccount/uodateAccountAndPartsTake.action")
    Observable<BaseResponsBean> PartsGet(@Field("updateData") String updateData,
                                         @Field("flage") String flage);

}
