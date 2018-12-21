package sbgl3.yunda.module.disassembly;

import com.jess.arms.base.entry.BaseResponsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import sbgl3.yunda.module.disassembly.entry.BigPartsBean;
import sbgl3.yunda.module.disassembly.entry.DisassemblyBean;
import sbgl3.yunda.module.getparts.entry.GetPartsBean;
import sbgl3.yunda.module.partsrecondition.entry.PartsModelBean;

public interface DisassemblyApi {


    @FormUrlEncoded
    @POST("GZJX/repair/partsRepairActivity/findListToThisTable.action")
    Observable<BaseResponsBean<List<BigPartsBean>>> getBigPartsList(@Field("queryData") String queryData,
                                                                    @Field("identificationCode") String identificationCode);

    @FormUrlEncoded
    @POST("GZJX/repair/partsOffListRel/findOffListByParentActivityIdx.action")
    Observable<BaseResponsBean<List<DisassemblyBean>>> getDisassemblyList(@Field("parentActivityIdx") String parentActivityIdx);
    @FormUrlEncoded
    @POST("GZJX/repair/partsOffListRel/savePartsOffListRel.action")
    Observable<BaseResponsBean> confirm(@Field("partsOffListRelEntity") String partsOffListRelEntity);
    @FormUrlEncoded
    @POST("GZJX/repair/partsOffListRel/findModelByPartsIdx.action")
    Observable<BaseResponsBean<List<PartsModelBean>>>getMakers(@Field("partsIdx") String partsIdx);
    @FormUrlEncoded
    @POST("GZJX/repair/partsOnListRel/findOnListByParentActivityIdx.action")
    Observable<BaseResponsBean<List<DisassemblyBean>>> getAssemblyList(@Field("parentActivityIdx") String parentActivityIdx,
                                                                       @Field("identificationCode") String identificationCode,
                                                                       @Field("status") String status);
    @FormUrlEncoded
    @POST("GZJX/repair/partsOnListRel/findPartsStatusByIdentiCode.action")
    Observable<BaseResponsBean<DisassemblyBean>> findPartsStatusByIdentiCode(@Field("identificationCode") String identificationCode);
    @FormUrlEncoded
    @POST("GZJX/repair/partsOnListRel/savePartsOnListRelToPartsAcount.action")
    Observable<BaseResponsBean> confirmAssembly(@Field("partsOnListRelEntity") String partsOnListRelEntity);

    @FormUrlEncoded
    @POST("GZJX/repair/partsOnListRel/findPartsByPartsTypeIdx.action")
    Observable<BaseResponsBean<List<DisassemblyBean>>> getPartsList(@Field("partsTypeIdx") String partsTypeIdx);
}
