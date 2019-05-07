package com.common.android.fapi;


import com.common.android.fui.model.DataItemModel;
import com.common.android.fui.model.DataListModel;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Url;


/**
 * 请求接口
 * 
 * @author macbook
 * 
 */
public interface StApiInterface {


//	/**
//	 * 获取停车区域
//	 * @param token
//	 * @param carNun
//	 * @param Ma
//     * @return
//     */
//	@FormUrlEncoded
//	@POST("/CheckParkingCarAreaMessageInfo ")
//	Observable<DataListModel<ParkAreaEntity>> searchParkAreaList(@Field("token") String token,
//																 @Field("carNun") String carNun,
//																 @Field("Ma") String Ma);
//
//
//	/**
//	 * 登录操作
//	 * @param workerAccount
//	 * @param workerPassword
//	 * @param parkId
//	 * @param Ma
//	 * @param LoginTime
//     * @return
//     */
//	@FormUrlEncoded
//	@POST("/DownLodeParkingSystem ")
//	Observable<DataItemModel<LoginEntity>> login(@Field("workerAccount") String workerAccount, @Field("workerPassword") String workerPassword, @Field("parkId") String parkId, @Field("Ma") String Ma, @Field("LoginTime") String LoginTime);
//
//
//    /**
//	 * 获取开台内容
//	 * @param token
//	 * @param Ma
//	 * @return
//     */
//
//	@FormUrlEncoded
//	@POST("/GetStartPageInfo")
//	Observable<DataListModel<GoodEntity>> GetStartPageInfo(@Field("token") String token, @Field("Ma") String Ma);
//
//
//	/**
//	 * 修改开台内容（管理员操作）
//	 * @param token  登录点
//	 * @param goods
//	 * @param ma
//	 * @param updateTime
//	 * @param updatePeople
//     * @return
//     */
//	@FormUrlEncoded
//	@POST("/UpDateGetStartPageInfo")
//	Observable<DataItemModel<EmptyEntity>> UpDateGetStartPageInfo(@Field("token") String token, @Field("goods") String goods, @Field("Ma") String ma, @Field("UpDateTime") String updateTime, @Field("UpDatePeople") String updatePeople);
//
//
//	/**
//	 * 修改接车信息
//	 * @param token
//	 * @param carId
//	 * @param carNum
//	 * @param carColor
//	 * @param carBrand
//	 * @param carType
//	 * @param parkTime
//	 * @param workerName
//	 * @param userPhone
//	 * @param memberCard
//	 * @param parkCard
//	 * @param front
//	 * @param left
//	 * @param right
//     * @param back
//     * @param signUrl
//     * @param Ma
//     * @return
//     */
//	@FormUrlEncoded
//	@POST("/UpDateReceiveCarMessageInfo")
//	Observable<DataItemModel<EmptyEntity>> UpDateReceiveCarMessageInfo(@Field("token") String token,
//																	   @Field("carId") String carId,
//																	   @Field("carNum") String carNum,
//																	   @Field("carColor") String carColor,
//																	   @Field("carBrand") String carBrand,
//																	   @Field("carType") String carType,
//																	   @Field("parkTime") String parkTime,
//																	   @Field("workerName") String workerName,
//																	   @Field("userPhone") String userPhone,
//																	   @Field("memberCard") String memberCard,
//																	   @Field("parkCard") String parkCard,
//																	   @Field("front") String front,
//																	   @Field("left") String left,
//																	   @Field("right") String right,
//																	   @Field("back") String back,
//																	   @Field("signUrl") String signUrl,
//																	   @Field("Ma") String Ma
//	);
//
//
//	/**
//	 * 接车
//	 * @param token
//	 * @param carNum
//	 * @param carColor
//	 * @param carBrand
//	 * @param carType
//	 * @param parkTime
//	 * @param workerName
//	 * @param userPhone
//	 * @param memberCard
//	 * @param parkCard
//	 * @param front
//	 * @param left
//	 * @param right
//	 * @param back
//	 * @param signUrl
//	 * @param Ma
//	 * @return
//	 */
//	@FormUrlEncoded
//	@POST("/ReceiveCarMessageInfo")
//	Observable<DataItemModel<EmptyEntity>> ReceiveCarMessageInfo(@Field("token") String token,
//																 @Field("carNum") String carNum,
//																 @Field("carColor") String carColor,
//																 @Field("carBrand") String carBrand,
//																 @Field("carType") String carType,
//																 @Field("parkTime") String parkTime,
//																 @Field("workerName") String workerName,
//																 @Field("userPhone") String userPhone,
//																 @Field("memberCard") String memberCard,
//																 @Field("parkCard") String parkCard,
//																 @Field("front") String front,
//																 @Field("left") String left,
//																 @Field("right") String right,
//																 @Field("back") String back,
//																 @Field("signUrl") String signUrl,
//																 @Field("Ma") String Ma);
//
//
//	/**
//	 * 泊车
//	 * @param token
//	 * @param carId
//	 * @param carColor
//	 * @param ParkingPeople
//	 * @param Ma
//     * @return
//     */
//	@FormUrlEncoded
//	@POST("/ParkingCarMessageInfo")
//	Observable<DataItemModel<EmptyEntity>> ParkingCarMessageInfo(@Field("token") String token,
//																 @Field("carId") String carId,
//																 @Field("parkId") String carColor,
//																 @Field("ParkingPeople") String ParkingPeople,
//																 @Field("Ma") String Ma);
//
//
//
//	/**
//	 *挪车
//	 * @param token
//	 * @param carId
//	 * @param carColor
//	 * @param ParkingPeople
//	 * @param Ma
//	 * @return
//	 */
//	@FormUrlEncoded
//	@POST("/UpDateParkingCarAreaInfo")
//	Observable<DataItemModel<EmptyEntity>> UpDateParkingCarAreaInfo(@Field("token") String token,
//																	@Field("carNum") String carId,
//																	@Field("parkId") String carColor,
//																	@Field("targetParkId") String ParkingPeople,
//																	@Field("Ma") String Ma,
//																	@Field("UpDatePeople") String UpDatePeople);
//
//
//
//	/**
//	 *搜索
//	 * @param token
//	 * @param carId
//	 * @param Ma
//	 * @return
//	 */
//	@FormUrlEncoded
//	@POST("/CheckParkingCarAreaMessageInfo")
//	Observable<DataItemModel<ParkEntity>> CheckParkingCarAreaMessageInfo(@Field("token") String token,
//																		 @Field("carNum") String carId,
//																		 @Field("Ma") String Ma
//	);


//
//	/**
//	 *搜索
//	 * @param token
//	 * @param carId
//	 * @param Ma
//	 * @return
//	 */
//	@FormUrlEncoded
//	@POST("/CheckParkingCarAreaMessageInfo")
//	Observable<DataItemModel<ParkEntity>> CheckParkingCarAreaMessageInfo(@Field("token") String token,
//																		 @Field("carNum") String carId,
//																		 @Field("Ma") String Ma
//	);


//	/**
//	 * 登录操作
//	 * @param workerAccount
//	 * @param workerPassword
//	 * @param parkId
//	 * @param Ma
//	 * @param LoginTime
//	 * @return
//	 */
//	@FormUrlEncoded
//	@POST("/DownLodeParkingSystem ")
//	Observable<DataItemModel<LoginEntity>> login(@Field("workerAccount") String workerAccount,@Field("workerPassword") String workerPassword,@Field("parkId") String parkId,@Field("Ma") String Ma,@Field("LoginTime") String LoginTime);
//



//	 /* 科目图片上传 单张
//	 * @param token
//	 */
//	@Multipart
//	@POST("/teacher/subject/imgupload")
//	void subjectPicturesUpload(@Part("token") TypedString token,
//							   @Part("photo") TypedFile photo,
//							   Callback<SubjectPicItemModel> callback);
//

	
}


