package com.insthub.cat.android.respository;

import android.content.Context;

import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.insthub.cat.android.App;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.BankListData;
import com.insthub.cat.android.module.BidPriceDetialData;
import com.insthub.cat.android.module.BidPriceListData;
import com.insthub.cat.android.module.ChangePublisData;
import com.insthub.cat.android.module.CollectListData;
import com.insthub.cat.android.module.CompeteTenderData;
import com.insthub.cat.android.module.CouponListData;
import com.insthub.cat.android.module.CreateJingjiaData;
import com.insthub.cat.android.module.DeletePublishData;
import com.insthub.cat.android.module.InnovateListData;
import com.insthub.cat.android.module.JingjiaDraftData;
import com.insthub.cat.android.module.LabelListData;
import com.insthub.cat.android.module.OfferListData;
import com.insthub.cat.android.module.TenderDetialData;
import com.insthub.cat.android.module.TenderListData;
import com.insthub.cat.android.module.ZhaobiaoDraftData;
import com.insthub.cat.android.module.ZhengjiDetialData;
import com.insthub.cat.android.module.ZhengjiDraftData;
import com.insthub.cat.android.module2.ActionListData;
import com.insthub.cat.android.module2.ApplyYanshouData;
import com.insthub.cat.android.module2.BindPhoneData;
import com.insthub.cat.android.module2.ChoujiangDetialData;
import com.insthub.cat.android.module2.CollectServiceData;
import com.insthub.cat.android.module2.CreateActivityData;
import com.insthub.cat.android.module2.CreateKanjiaData;
import com.insthub.cat.android.module2.CreatePintuanData;
import com.insthub.cat.android.module2.CreateServiceOrderData;
import com.insthub.cat.android.module2.CutdownDetialData;
import com.insthub.cat.android.module2.DeleteMyShopData;
import com.insthub.cat.android.module2.FriendInfoData;
import com.insthub.cat.android.module2.AddBankcardData;
import com.insthub.cat.android.module2.DeleteBankcardData;
import com.insthub.cat.android.module2.DeleteUserOrderData;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.module2.HistoryRecordListData;
import com.insthub.cat.android.module2.JoinActivityData;
import com.insthub.cat.android.module2.JoinPintuanData;
import com.insthub.cat.android.module2.KanjiaInfoData;
import com.insthub.cat.android.module2.MiaoshaDetialData;
import com.insthub.cat.android.module2.MyStoreData;
import com.insthub.cat.android.module2.NearbyData;
import com.insthub.cat.android.module2.OrderCommentResultData;
import com.insthub.cat.android.module2.PaytoStoreData;
import com.insthub.cat.android.module2.PintuanDetialData;
import com.insthub.cat.android.module2.PintuanListData;
import com.insthub.cat.android.module2.SameLevelLabelData;
import com.insthub.cat.android.module2.ServiceYanshouData;
import com.insthub.cat.android.module2.ShopBondData;
import com.insthub.cat.android.module2.ShopCommentList;
import com.insthub.cat.android.module2.ShopDetialData;
import com.insthub.cat.android.module2.ShopIncomData;
import com.insthub.cat.android.module2.SortData;
import com.insthub.cat.android.module2.StoreData;
import com.insthub.cat.android.module2.StoreDetialData;
import com.insthub.cat.android.module2.UpdateStoreData;
import com.insthub.cat.android.module2.UseOrderListData;
import com.insthub.cat.android.module2.UserCommentListData;
import com.insthub.cat.android.module.ScoreListData;
import com.insthub.cat.android.module2.CollectStoreData;
import com.insthub.cat.android.module2.CreateOrderData;
import com.insthub.cat.android.module2.DeleteCollectData;
import com.insthub.cat.android.module2.DeleteCommentData;
import com.insthub.cat.android.module2.FinishOrderListData;
import com.insthub.cat.android.module2.OauthStroeData;
import com.insthub.cat.android.module2.OrderDetialData;
import com.insthub.cat.android.module2.OrderWithdrawData;
import com.insthub.cat.android.module2.PaymentToStore;
import com.insthub.cat.android.module2.ShopOrderListData;
import com.insthub.cat.android.module2.TickListData;
import com.insthub.cat.android.module.UpdateUserInfoData;
import com.insthub.cat.android.module.WxBindPhoneData;
import com.insthub.cat.android.module2.AddInvoiceTitleData;
import com.insthub.cat.android.module2.BannerListData;
import com.insthub.cat.android.module2.BindPushData;
import com.insthub.cat.android.module2.DeleteInvoiceData;
import com.insthub.cat.android.module2.EmailCodeData;
import com.insthub.cat.android.module2.ExtensionData;
import com.insthub.cat.android.module2.ExtensionPageListData;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.module.LogoutData;
import com.insthub.cat.android.module.SumbitFeedbackData;
import com.insthub.cat.android.module.ModifyPwdData;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.module2.InvoiceApplyData;
import com.insthub.cat.android.module2.InvoiceTitleListData;
import com.insthub.cat.android.module2.LoginTokenData;
import com.insthub.cat.android.module2.NewsListData;
import com.insthub.cat.android.module2.OpenRedPackageData;
import com.insthub.cat.android.module2.PubuListData;
import com.insthub.cat.android.module2.RedPackageListData;
import com.insthub.cat.android.module2.SetDefaultTitleData;
import com.insthub.cat.android.module2.UnFinishOrderListData;
import com.insthub.cat.android.module2.UpdateInvoiceTitleData;
import com.insthub.cat.android.module2.UserDeductionData;
import com.insthub.cat.android.module2.VersionData;
import com.insthub.cat.android.module2.WXPrepayData;
import com.insthub.cat.android.module2.WithdrawApplyData;
import com.insthub.cat.android.respository.interfaces.Repository;
import com.insthub.cat.android.serviceapi.JldApi;
import com.insthub.cat.android.serviceapi.JldApi2;
import com.insthub.cat.android.serviceapi.JldApi3;

import retrofit2.http.Field;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * 数据操纵
 */
public class RepositoryImpl implements Repository {

    private Context mContext;

    private static RepositoryImpl _instance;

    public static  RepositoryImpl getInstance(Context context)
    {
        if(_instance==null)
        {
            _instance = new RepositoryImpl(context);
        }

        return _instance;
    }

    public RepositoryImpl(Context context) {
        mContext = context;
    }


    @Override
    public Observable<UserInfoData> register(String phone, String loginPassword, String email, String referee) {
        return JldApi.getInstance().getConnection().register(phone, loginPassword, email, referee);
    }

    @Override
    public Observable<LoginTokenData> login(String mobile, String loginPassword,double lat, double lng) {
        return JldApi.getInstance().getConnection().login(mobile, loginPassword,lat,lng);
    }

    @Override
    public Observable<ModifyPwdData> modifyPassword(String userId, String token, String new_pwd, String old_pwd) {
        return JldApi.getInstance().getConnection().modifyPassword(userId, token, new_pwd, old_pwd);
    }

    @Override
    public Observable<EmailCodeData> getEmailCode(String mobile) {
        return JldApi.getInstance().getConnection().getEmailCode(mobile);
    }

    @Override
    public Observable<ModifyPwdData> forgetModifyPassword(String mobile, String code, String new_pwd) {
        return JldApi.getInstance().getConnection().forgetModifyPassword(mobile, code, new_pwd);
    }

    @Override
    public Observable<LoginTokenData> wxlogin(String openid, String unionID,String head_image, String nickname,double lat, double lng) {
        return JldApi.getInstance().getConnection().wxlogin(openid, unionID,head_image, nickname,lat,lng);
    }

    @Override
    public Observable<WxBindPhoneData> wxloginBindPhone(String phone, String recom_id, String user_id, String token) {
        return JldApi.getInstance().getConnection().wxloginBindPhone(phone, recom_id, user_id, token);
    }

    @Override
    public Observable<BindPushData> bindJpush(String user_id, String token, String jpush_token) {
        return JldApi.getInstance().getConnection().bindJpush(user_id, token, jpush_token);
    }

    @Override
    public Observable<LogoutData> logout(String userId,String token) {
        return JldApi3.getInstance().getConnection().logout(userId,token);
    }

    @Override
    public Observable<SumbitFeedbackData> submitFeedback(String userId, String token, String content) {
        return JldApi.getInstance().getConnection().submitFeedback(userId, token, content);
    }

    @Override
    public Observable<UserInfoData> getUserInfo(String userId, String token) {
        return JldApi.getInstance().getConnection().getUserInfo(userId, token);
    }

    @Override
    public Observable<UpdateUserInfoData> modifyUserInfo(String user_id, String token, String head_image, String phone, String name, String sex, String position, String department,String trade) {
        return JldApi.getInstance().getConnection().modifyUserInfo(user_id, token, head_image, phone, name, sex, position, department,trade);
    }

    @Override
    public Observable<HomeData> getHomeData( double lat, double lng) {


        if(CacheManager.getInstance().getToken()!=null)
        {
            return JldApi3.getInstance().getConnection().getHomeData(  lat, lng,CacheManager.getInstance().getToken().getData().getUser_id());
        }

        return JldApi3.getInstance().getConnection().getHomeData(  lat, lng,"");

    }

    @Override
    public Observable<InvoiceTitleListData> getMyInvoiceTitle(String user_id, String token) {
        return JldApi.getInstance().getConnection().getMyInvoiceTitle(user_id, token);
    }

    @Override
    public Observable<AddInvoiceTitleData> addInvoiceTitle(String user_id, String token, String type, String title_name, String tax_no, String phone, String address, String bank, String bank_account, String user_name) {
        return JldApi.getInstance().getConnection().addInvoiceTitle(user_id, token, type, title_name, tax_no, phone, address, bank, bank_account, user_name);
    }

    @Override
    public Observable<SetDefaultTitleData> setDefaultTitle(String user_id, String token, String title_id) {
        return JldApi.getInstance().getConnection().setDefaultTitle(user_id, token, title_id);
    }

    @Override
    public Observable<UpdateInvoiceTitleData> updateInvoiceTitle(String user_id, String token, String type, String title_id, String title_name, String tax_no, String phone, String address, String bank, String bank_account, String user_name) {
        return JldApi.getInstance().getConnection().updateInvoiceTitle(user_id, token, type, title_id, title_name, tax_no, phone, address, bank, bank_account, user_name);
    }

    @Override
    public Observable<DeleteInvoiceData> deleteRaiseByRaiseId(String userId, String token, String title_id) {
        return JldApi.getInstance().getConnection().deleteRaiseByRaiseId(userId, token, title_id);
    }

    @Override
    public Observable<NewsListData> getNewsList(int page, int show_count, String type) {
        return JldApi.getInstance().getConnection().getNewsList(page, show_count, type);
    }

    @Override
    public Observable<InvoiceApplyData> sumbitInvoiceApply(String title_id, String user_id, String token, String store_id,float money) {
        return JldApi.getInstance().getConnection().sumbitInvoiceApply(title_id, user_id, token, store_id,money);
    }

    @Override
    public Observable<RedPackageListData> getRedPackageListData(String user_id, String token, String store_id) {
        return JldApi.getInstance().getConnection().getRedPackageListData(user_id, token, store_id);
    }

    @Override
    public Observable<PubuListData> getPubuListData(int page, int show_count) {
        return JldApi.getInstance().getConnection().getPubuListData(page, show_count);
    }

    @Override
    public Observable<OpenRedPackageData> OpenRedPackageData(String user_id, String token, String rp_id) {
        return JldApi.getInstance().getConnection().OpenRedPackageData(user_id, token, rp_id);
    }

    @Override
    public Observable<BannerListData> getBannerList(String user_id) {
        return JldApi.getInstance().getConnection().getBannerList(user_id);
    }


    @Override
    public Observable<ExtensionPageListData> getExtensionPage() {
        return JldApi.getInstance().getConnection().getExtensionPage("");
    }

    @Override
    public Observable<ExtensionData> sumbitExtension(String phone) {
        return JldApi.getInstance().getConnection().sumbitExtension(phone);
    }


    @Override
    public Observable<ScoreListData> getScoreIntegralLog(String user_id, String token, int page, int show_count) {
        return JldApi.getInstance().getConnection().getScoreIntegralLog(user_id, token, page, show_count);
    }


    @Override
    public Observable<TickListData> getInvoiceLog(String user_id, String token, int page, int show_count, int type) {
        return JldApi.getInstance().getConnection().getInvoiceLog(user_id, token, page, show_count, type);
    }

    @Override
    public Observable<ShopOrderListData> getStoreOrderList(String user_id, String token, int page, int show_count,String store_id) {
        return JldApi.getInstance().getConnection().getStoreOrderList(user_id, token, page, show_count,store_id);
    }

    @Override
    public Observable<PaymentToStore> paymentToStore(String user_id, String token, String paymentToStore, String pay_pwd) {
        return JldApi.getInstance().getConnection().paymentToStore(user_id, token, paymentToStore, pay_pwd);
    }

    @Override
    public Observable<OrderDetialData> getOrderDetial(String user_id, String token, String order_id) {
        return JldApi.getInstance().getConnection().getOrderDetial(user_id, token, order_id);
    }

    @Override
    public Observable<OrderWithdrawData> sumbitStoreWithdraw(String user_id, String token, String order_id, String store_id) {
        return JldApi.getInstance().getConnection().sumbitStoreWithdraw(user_id, token, order_id);
    }

    @Override
    public Observable<FinishOrderListData> getFinishedOrderList(String user_id, String token, int page, int show_count, int type, String store_id) {
        return JldApi.getInstance().getConnection().getFinishedOrderList(user_id, token, page, show_count, type, store_id);
    }

    @Override
    public Observable<UnFinishOrderListData> getUnfinishedOrderList(String user_id, String token, int page, int show_count, String store_id) {
        return JldApi.getInstance().getConnection().getUnfinishedOrderList(user_id, token, page, show_count, store_id);
    }

    @Override
    public Observable<CreateOrderData> createOrder(String user_id, String token, String service_content, float service_price, String service_time, String remarks, String store_id) {
        return JldApi.getInstance().getConnection().createOrder(user_id, token, service_content, service_price, service_time, remarks, store_id);
    }

    @Override
    public Observable<UserCommentListData> getOrderCommentList(String order_id) {
        return JldApi.getInstance().getConnection().getOrderCommentList(order_id);
    }

    @Override
    public Observable<CouponListData> getDiscountList(String user_id, String token) {
        return JldApi.getInstance().getConnection().getDiscountList(user_id, token);
    }


    @Override
    public Observable<OauthStroeData> sumbitOauthData(String user_id, String token, String business_license, String idcard_front, String idcard_back) {
        return JldApi.getInstance().getConnection().sumbitOauthData(user_id, token, business_license, idcard_front, idcard_back);
    }

    @Override
    public Observable<CollectListData> getUserCollect(String user_id, String token,double lng,double lat) {
        return JldApi.getInstance().getConnection().getUserCollect(user_id, token,lng,lat);
    }

    @Override
    public Observable<DeleteCollectData> deleteUserCollect(String user_id, String token, String collect_id) {
        return JldApi.getInstance().getConnection().deleteUserCollect(user_id, token, collect_id);
    }

    @Override
    public Observable<CollectStoreData> collecStore(String user_id, String token, String store_id) {
        return JldApi.getInstance().getConnection().collecStore(user_id, token, store_id);
    }


    @Override
    public Observable<UserCommentListData> getUserommentList(String user_id, String token, int page, int show_count) {
        return JldApi.getInstance().getConnection().getUserommentList(user_id, token, page, show_count);
    }

    @Override
    public Observable<DeleteCommentData> deleteUserComment(String user_id, String token, String store_id) {
        return JldApi.getInstance().getConnection().deleteUserComment(user_id, token, store_id);
    }


    @Override
    public Observable<UseOrderListData> getUserOrderList(String user_id, String token, int page, int show_count) {
        return JldApi.getInstance().getConnection().getUserOrderList(user_id, token, page, show_count);
    }


    @Override
    public Observable<OrderCommentResultData> evaluateOrder(String user_id, String token, String store_id, int score, String evaluate_content, String order_id) {
        return JldApi.getInstance().getConnection().evaluateOrder(user_id, token, score, evaluate_content, order_id);
    }

    @Override
    public Observable<DeleteUserOrderData> deleteUserOrder(String user_id, String token, String order_id) {
        return JldApi.getInstance().getConnection().deleteUserOrder(user_id, token, order_id);
    }

    @Override
    public Observable<DiscoverLabelData> getDiscoverLabel() {
        return JldApi3.getInstance().getConnection().getDiscoverLabel("");
    }

    @Override
    public Observable<DiscoverStoreData> getStoreByLabel(String lavel_lv1, String lavel_lv2, String page, String show_count, double log, double lat) {
        return JldApi.getInstance().getConnection().getStoreByLabel(lavel_lv1, lavel_lv2, page, show_count, log, lat);
    }


    @Override
    public Observable<DiscoverStoreData> getStoreByWords(String page, String getStoreByWords, double log, double lat) {
        return JldApi3.getInstance().getConnection().getStoreByWords(page, getStoreByWords, log, lat,20);
    }

    @Override
    public Observable<StoreData> getStore(String user_id, String token, String lavel_lv1, String lavel_lv2, int type, String keywords,String deatails,String image, double log, double lat,double distance) {

        String dis = "";
        if(distance!=-1)
        {
            dis = String.valueOf(distance);
        }
        return JldApi.getInstance().getConnection().getStore(user_id, token, lavel_lv1, lavel_lv2, type, keywords,deatails,image, log, lat,dis);
    }

    @Override
    public Observable<StoreDetialData> getStoreDetail(String store_id) {

        String user_id = "";

        if(CacheManager.getInstance().getUserInfo()!=null)
        {
            user_id = CacheManager.getInstance().getUserInfo().getData().getId();
        }
        return JldApi3.getInstance().getConnection().getStoreDetail(store_id,user_id);
    }


    @Override
    public Observable<ShopCommentList> getStoreEvaluation(int page, int show_count, String store_id) {
        return JldApi.getInstance().getConnection().getStoreEvaluation(page,show_count,store_id);
    }

    @Override
    public Observable<WXPrepayData> getWxPrePayData(String store_id, String deduction_id, String openid, String appip) {
        return JldApi.getInstance().getConnection().getWxPrePayData(store_id, deduction_id, openid, appip);
    }

    @Override
    public Observable<BankListData> getBankcardList(String user_id, String token) {
        return JldApi.getInstance().getConnection().getBankcardList(user_id, token);
    }


    @Override
    public Observable<AddBankcardData> addBankCard(String user_id, String token, String bank, String bank_account, String hold_name) {
        return JldApi.getInstance().getConnection().addBankCard(user_id, token, bank, bank_account, hold_name);
    }


    @Override
    public Observable<DeleteBankcardData> delBankCard(String user_id, String token, String card_id) {
        return JldApi.getInstance().getConnection().delBankCard(user_id, token, card_id);
    }

    @Override
    public Observable<WithdrawApplyData> withdrawApply(String user_id, String token, String card_id, float money) {
        return JldApi.getInstance().getConnection().withdrawApply(user_id, token, card_id, money);
    }


    @Override
    public Observable<FriendInfoData> getImInfo(String user_id, String token, String card_id) {
        return JldApi.getInstance().getConnection().getImInfo(card_id);
    }

    @Override
    public Observable<MyStoreData> getMyStoreDetail(String user_id, String token) {
        return JldApi.getInstance().getConnection().getMyStoreDetail(user_id, token);
    }

    @Override
    public Observable<DeleteMyShopData> delStore(String user_id, String token, String store_id) {
        return JldApi.getInstance().getConnection().delStore(user_id, token, store_id);
    }

    @Override
    public Observable<UpdateStoreData> updateStore(String user_id, String token, String store_id, String store_name, String banner_list, String service_price, String service_content, String address, String detail, String image_list, String logo, double lng, double lat, String label_lv1, String label_lv2,  String contacts,
                                                   String phone,
                                                String bank,
                                                   String bank_account) {
        return JldApi.getInstance().getConnection().updateStore(user_id, token, store_id, store_name, banner_list, service_price, service_content, address, detail, image_list, logo, lng, lat, label_lv1, label_lv2,contacts,phone,bank,bank_account);

    }


    @Override
    public Observable<ShopIncomData> getShopIncomPage(String user_id, String token) {
        return JldApi.getInstance().getConnection().getShopIncomPage(user_id, token);
    }

    @Override
    public Observable<UserDeductionData> getUserDeduction(String user_id, String token, int page, int show_count, int type) {
        return JldApi.getInstance().getConnection().getUserDeduction(user_id, token, page, show_count, type);
    }


    @Override
    public Observable<UpdateStoreData> saveStore(String user_id, String token, String logo, String store_name, String service_content, String service_price, String service_advantage, String address, String phone, String contacts, String bank, String bank_account, String banner_list, String image_list, String detail, double lng, double lat, String label_lv1, String label_lv2) {
        return JldApi.getInstance().getConnection().saveStore(user_id, token, logo, store_name, service_content, service_price, service_advantage, address, phone, contacts, bank, bank_account, banner_list, image_list, detail, lng, lat, label_lv1, label_lv2);
    }




    @Override
    public Observable<BindPhoneData> bindPhone(String user_id, String token, String phone, String recom_id) {
        return JldApi.getInstance().getConnection().bindPhone(user_id, token, phone, recom_id);
    }


    @Override
    public Observable<HistoryRecordListData> getMoneyLogs(String user_id, String token, int page, int show_count, int type) {
        return JldApi.getInstance().getConnection().getMoneyLogs(user_id, token, page, show_count, type);
    }

    @Override
    public Observable<VersionData> getVersion() {
        return JldApi.getInstance().getConnection().getVersion("");
    }

    @Override
    public Observable<ActionListData> getActivityList(   double lat,
                                                         double lng,int page, int show_count, int type) {
        return JldApi2.getInstance().getConnection().getActivityList(lat,lng,page, show_count, type);
    }

    @Override
    public Observable<ShopBondData> countShopMoney(String user_id, String token) {
        return JldApi2.getInstance().getConnection().countShopMoney(user_id, token);
    }

    @Override
    public Observable<CreateActivityData> createActivity(String user_id, String token, int type, String activity_name, String old_price, String discount_price, String begin_time, String end_time, String cutdown_price, String initiator_num, String remarks, String discount_num, String num, String money) {
        return JldApi2.getInstance().getConnection().createActivity(user_id, token, type, activity_name, old_price, discount_price, begin_time, end_time, cutdown_price, initiator_num, remarks, discount_num, num, money);
    }

    @Override
    public Observable<CutdownDetialData> getCutdownDetail(String activity_id) {
        return JldApi2.getInstance().getConnection().getCutdownDetail(activity_id);
    }

    @Override
    public Observable<MiaoshaDetialData> getMiaoshaDetail(String activity_id) {
        return JldApi2.getInstance().getConnection().getMiaoshaDetail(activity_id);
    }

    @Override
    public Observable<PintuanDetialData> getPintuanDetail(String activity_id) {
        return JldApi2.getInstance().getConnection().getPintuanDetail(activity_id);
    }

    @Override
    public Observable<ChoujiangDetialData> getChoujiangDetail(String activity_id) {
        return JldApi2.getInstance().getConnection().getChoujiangDetail(activity_id);
    }

    @Override
    public Observable<JoinPintuanData> joinPintuan(String user_id, String token, String activity_id, String pintuan_id, String leader) {
        return JldApi2.getInstance().getConnection().joinPintuan(user_id, token, activity_id, pintuan_id, leader);
    }

    @Override
    public Observable<CreatePintuanData> createPintuan(String user_id, String token, String activity_id, String remarks) {
        return JldApi2.getInstance().getConnection().createPintuan(user_id, token, activity_id, remarks);
    }

    @Override
    public Observable<CreateKanjiaData> createKanjia(String user_id, String token, String activity_id) {
        return JldApi2.getInstance().getConnection().createKanjia(user_id, token, activity_id);
    }

    @Override
    public Observable<PintuanListData> getPintuanList(String activity_id) {
        return JldApi2.getInstance().getConnection().getPintuanList(activity_id);
    }

    @Override
    public Observable<KanjiaInfoData> getPersionCutdownInfo(String user_id, String token, String kanjia_id) {
        return JldApi2.getInstance().getConnection().getPersionCutdownInfo(user_id, token, kanjia_id);
    }


    @Override
    public Observable<BidPriceListData> getJingjialist(int page, int show_count, String keywords, int state,  String user_id) {
        return JldApi3.getInstance().getConnection().getJingjialist(page, show_count, keywords, state,user_id);
    }

    @Override
    public Observable<TenderListData> getTenderList(int page, int show_count, String keywords,  String user_id) {
        return JldApi3.getInstance().getConnection().getTenderList(page,show_count,keywords,user_id);
    }

    @Override
    public Observable<InnovateListData> getInnovateList(int page, int show_count, int state,  String user_id) {
        return JldApi3.getInstance().getConnection().getInnovateList(page, show_count, state,user_id);
    }

    @Override
    public Observable<BidPriceDetialData> getJingjiaDetial(String page) {
        return  JldApi3.getInstance().getConnection().getJingjiaDetial(page);
    }

    @Override
    public Observable<ZhengjiDetialData> getZhengjiDetail(String tender_id) {
        return  JldApi3.getInstance().getConnection().getZhengjiDetail(tender_id);
    }

    @Override
    public Observable<TenderDetialData> getTenderDetail(String tender_id) {
        return  JldApi3.getInstance().getConnection().getTenderDetail(tender_id);
    }

    @Override
    public Observable<SameLevelLabelData> getSameLevelLabel(String label_id) {
        return  JldApi3.getInstance().getConnection().getSameLevelLabel(label_id);
    }

    @Override
    public Observable<NearbyData> getNearyByParam(double lat, double lng) {
        return  JldApi3.getInstance().getConnection().getNearyByParam(lat, lng);
    }

    @Override
    public Observable<SortData> getSortRule() {
        return  JldApi3.getInstance().getConnection().getSortRule("");
    }

    @Override
    public Observable<ShopDetialData> getShopDetial(String store_id) {
        return  JldApi3.getInstance().getConnection().getShopDetial(store_id);
    }

    @Override
    public Observable<OfferListData> getShopOfferList(String user_id, String token, int page, String show_count) {
        return  JldApi3.getInstance().getConnection().getShopOfferList(user_id, token, page, show_count);
    }


    @Override
    public Observable<LabelListData> getLabelList() {
        return  JldApi.getInstance().getConnection().getLabelList("");
    }

    @Override
    public Observable<DiscoverStoreData> searchServerList(String keywords, double lat, double lng, String lavel_lv1, String lavel_lv2, int page, int show_count, String area, String distance, String sort_rule) {
        return  JldApi3.getInstance().getConnection().searchServerList(keywords, lat, lng, lavel_lv1, lavel_lv2, page, show_count, area, distance, sort_rule);
    }

    @Override
    public Observable<CreateJingjiaData> createJingjia(String user_id, String token, String label_lv1, String label_lv2, String title, String detail, String end_time, String address, int include_tax, int include_freight, int trans_type, int pay_type, int invoice_type, int business_license, String annex, String save_type, String province, String city, String area,String tender_id,int service_type,String service_content) {
        return  JldApi3.getInstance().getConnection().createJingjia(user_id, token, label_lv1, label_lv2, title, detail, end_time, address, include_tax, include_freight, trans_type, pay_type, invoice_type, business_license, annex, save_type, province, city, area,tender_id,service_type,service_content);
    }

    @Override
    public Observable<CreateJingjiaData> createZhengji(String user_id, String token, String title, String detail, String end_time, String trait, float money, String annex, String save_type,String tender_id,String label_lv1, String label_lv2,int service_type,String service_content) {
        return JldApi3.getInstance().getConnection().createZhengji(user_id, token, title, detail, end_time, trait, money, annex, save_type,tender_id,label_lv1,label_lv2,service_type,service_content);
    }


    @Override
    public Observable<CreateJingjiaData> createZhaobiao(String user_id, String token, String label_lv1, String label_lv2, String title, String detail, String end_time, String address, int include_tax, int include_freight, int invoice_type, String annex, String save_type, String province, String city, String area, String pub_time, String req_list, int tender_req, String collect, String phone, String survey, String collect_address,String tender_id,int service_type,String service_content) {
        return JldApi3.getInstance().getConnection().createZhaobiao(user_id, token, label_lv1, label_lv2, title, detail, end_time, address, include_tax, include_freight, invoice_type, annex, save_type, province, city, area, pub_time, req_list, tender_req, collect, phone, survey, collect_address,tender_id,service_type,service_content);
    }


    @Override
    public Observable<CompeteTenderData> competeTender(String user_id, String token, String tender_id, String annex, float price, String finish_time) {
        return JldApi3.getInstance().getConnection().competeTender(user_id, token, tender_id, annex, price, finish_time);
    }

    @Override
    public Observable<CollectServiceData> collecService(String user_id, String token, String serviceId) {
        return JldApi3.getInstance().getConnection().collecService(user_id, token, serviceId);
    }

    @Override
    public Observable<JingjiaDraftData> getJingjiaDraft(String user_id, String token, String  tender_id) {
        return JldApi3.getInstance().getConnection().getJingjiaDraft(user_id, token,tender_id);
    }

    @Override
    public Observable<ZhaobiaoDraftData> getZhaobiaoDraft(String user_id, String token, String  tender_id) {
        return JldApi3.getInstance().getConnection().getZhaobiaoDraft(user_id, token,tender_id);
    }

    @Override
    public Observable<ZhengjiDraftData> getZhengjiDraft(String user_id, String token, String  tender_id) {
        return JldApi3.getInstance().getConnection().getZhengjiDraft(user_id, token,tender_id);
    }

    @Override
    public Observable<CreateServiceOrderData> createStoreServiceOrder(String user_id, String token, String service_fee, String save_money, String one_money) {
        return JldApi3.getInstance().getConnection().createStoreServiceOrder(user_id, token, service_fee, save_money, one_money);
    }


    @Override
    public Observable<ApplyYanshouData> createOrderYanshou(String user_id, String token, String order_id) {
        return JldApi3.getInstance().getConnection().createOrderYanshou(user_id, token, order_id);
    }

    @Override
    public Observable<ServiceYanshouData> createOrderServiceYanshou(String user_id, String token, String order_id) {
        return JldApi3.getInstance().getConnection().createOrderServiceYanshou(user_id, token, order_id);
    }


    @Override
    public Observable<PaytoStoreData> createPayToStore(String user_id, String token, String order_id) {
        return JldApi.getInstance().getConnection().createPayToStore(user_id, token, order_id);
    }

    @Override
    public Observable<ChangePublisData> changePublishState(String user_id, String token, String order_id, String state) {
        return JldApi3.getInstance().getConnection().changePublishState(user_id, token, order_id, state);
    }

    @Override
    public Observable<DeletePublishData> deletePublishState(String user_id, String token, String order_id) {
        return JldApi3.getInstance().getConnection().deletePublishState(user_id, token, order_id);
    }

    @Override
    public Observable<JoinActivityData> joinActivity(String user_id, String token, String acty_id) {
        return JldApi3.getInstance().getConnection().joinActivity(user_id, token, acty_id);
    }
}
