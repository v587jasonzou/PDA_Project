package sbgl3.yunda.module.upparts;

import com.jess.arms.base.entry.BaseResponsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import sbgl3.yunda.module.upparts.entry.UpPartsTrainBean;


public interface UpPartsApi {
    @GET("GZJX/trainaccess/trainWorkPlan/findLists.action")
    Observable<BaseResponsBean<List<UpPartsTrainBean>>> getTrainList();


    //上车配件登记
    @FormUrlEncoded
    @POST("GZJX/partsaccount/partsAccount/updateUnAccountInfo.action")
    Observable<BaseResponsBean> PartsUp(@Field("updateData") String updateData,
                                         @Field("flage") String flage);
}
