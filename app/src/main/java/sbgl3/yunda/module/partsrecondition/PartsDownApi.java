package sbgl3.yunda.module.partsrecondition;

import com.jess.arms.base.entry.BaseResponsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sbgl3.yunda.module.login.Entry.LoginReponsBody;
import sbgl3.yunda.module.partsrecondition.entry.DownPartsBean;
import sbgl3.yunda.module.partsrecondition.entry.PartsFactoryBean;
import sbgl3.yunda.module.partsrecondition.entry.PartsModelBean;
import sbgl3.yunda.module.partsrecondition.entry.PartsTrainBean;

public interface PartsDownApi {
    //在修机车列表
    @GET("GZJX/trainaccess/trainWorkPlan/findAll2.action")
    Observable<BaseResponsBean<List<PartsTrainBean>>> getTrainList();
    //下配件列表
    @FormUrlEncoded
    @POST("GZJX/wellparts/partsUnloadRegister/findPartsUnloadRegisterByIdx.action")
    Observable<BaseResponsBean<List<DownPartsBean>>>getPartsList(@Field("idx") String idx);
    //规格型号列表
    @GET("GZJX/partstype/partsType/findAll2.action")
    Observable<BaseResponsBean> getModelList();
//    //规格型号列表
//    @FormUrlEncoded
//    @POST("GZJX/partstype/partsType/findAll2.action")
//    Observable<BaseResponsBean>getMakers(@Field("idx") String idx);
    //规格型号列表
    @FormUrlEncoded
    @POST("GZJX/partstype/partsType/findPartsTypeByPartsTypeList.action")
    Observable<BaseResponsBean<List<PartsModelBean>>>getMakers(@Field("idx") String idx);

    //查询所有生产厂家
    @GET("GZJX/madefactory/partsMadeFactory/findAll2.action")
    Observable<BaseResponsBean<List<PartsFactoryBean>>>getAllMakers();
    //生产厂家列表
    @FormUrlEncoded
    @POST("GZJX/madefactory/partsMadeFactory/findMadeFactoryByid.action")
    Observable<BaseResponsBean<PartsFactoryBean>>getFactory(@Field("idx") String idx);
    //下配件登记
    @FormUrlEncoded
    @POST("GZJX/wellparts/partsUnloadRegister/updatePartsUnloadRegister.action")
    Observable<BaseResponsBean>Confirm(@Field("updateData") String updateData,
                                       @Field("flage") String flage);
}
