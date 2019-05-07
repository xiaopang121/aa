package com.insthub.cat.android.mvp.presenter;

import com.common.android.flog.KLog;
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
import com.insthub.cat.android.module2.ShopOrderListData;
import com.insthub.cat.android.module2.TickListData;
import com.insthub.cat.android.module.WxBindPhoneData;
import com.insthub.cat.android.module2.AddInvoiceTitleData;
import com.insthub.cat.android.module2.BannerListData;
import com.insthub.cat.android.module2.BindPushData;
import com.insthub.cat.android.module2.DeleteInvoiceData;
import com.insthub.cat.android.module.LogoutData;
import com.insthub.cat.android.module.SumbitFeedbackData;
import com.insthub.cat.android.module.ModifyPwdData;
import com.insthub.cat.android.module.UpdateUserInfoData;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.module2.EmailCodeData;
import com.insthub.cat.android.module2.ExtensionData;
import com.insthub.cat.android.module2.ExtensionPageListData;
import com.insthub.cat.android.module2.HomeData;
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
import com.insthub.cat.android.mvp.contract.MVPContract;
import rx.Observer;

import static com.insthub.cat.android.constant.Constants.NET_ERROR;


public class MVPPresenter extends MVPContract.Presenter {


    @Override
    public void register(String phone, String loginPassword, String email, String referee) {
        mRxManage.add(mModel.register(phone, loginPassword, email,referee).subscribe(new Observer<UserInfoData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(UserInfoData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void login(String mobile, String loginPassword,double lat, double lng) {
        mRxManage.add(mModel.login(mobile, loginPassword,lat,lng).subscribe(new Observer<LoginTokenData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(LoginTokenData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void modifyPassword(String userId, String token, String new_pwd, String old_pwd) {
        mRxManage.add(mModel.modifyPassword(userId, token, new_pwd, old_pwd).subscribe(new Observer<ModifyPwdData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(ModifyPwdData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getEmailCode(String mobile) {
        mRxManage.add(mModel.getEmailCode(mobile).subscribe(new Observer<EmailCodeData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(EmailCodeData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void forgetModifyPassword(String mobile, String code, String new_pwd) {
        mRxManage.add(mModel.forgetModifyPassword(mobile, code, new_pwd).subscribe(new Observer<ModifyPwdData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(ModifyPwdData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void wxlogin(String openid, String unionID,String head_image, String nickname,double lat, double lng) {
        mRxManage.add(mModel.wxlogin(openid,unionID, head_image, nickname,lat,lng).subscribe(new Observer<LoginTokenData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(LoginTokenData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void wxloginBindPhone(String phone, String recom_id, String user_id, String token) {
        mRxManage.add(mModel.wxloginBindPhone(phone, recom_id, user_id, token).subscribe(new Observer<WxBindPhoneData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(WxBindPhoneData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void bindJpush(String user_id, String token, String jpush_token) {
        mRxManage.add(mModel.bindJpush(user_id, token, jpush_token).subscribe(new Observer<BindPushData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(BindPushData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void logout(String userId,String token) {
        mRxManage.add(mModel.logout(userId,token).subscribe(new Observer<LogoutData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(LogoutData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void submitFeedback(String userId, String token, String content) {
        mRxManage.add(mModel.submitFeedback(userId, token, content).subscribe(new Observer<SumbitFeedbackData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(SumbitFeedbackData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getUserInfo(String userId, String token) {
        mRxManage.add(mModel.getUserInfo(userId, token).subscribe(new Observer<UserInfoData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(UserInfoData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void modifyUserInfo(String user_id, String token, String head_image, String email, String name, String sex, String position, String department,String trade) {
        mRxManage.add(mModel.modifyUserInfo(user_id, token, head_image, email, name, sex, position, department,trade).subscribe(new Observer<UpdateUserInfoData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(UpdateUserInfoData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getHomeData( double lat, double lng) {
        mRxManage.add(mModel.getHomeData(lat, lng).subscribe(new Observer<HomeData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(HomeData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getMyInvoiceTitle(String user_id, String token) {
        mRxManage.add(mModel.getMyInvoiceTitle(user_id, token).subscribe(new Observer<InvoiceTitleListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(InvoiceTitleListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void addInvoiceTitle(String user_id, String token, String type, String title_name, String tax_no, String phone, String address, String bank, String bank_account, String user_name) {
        mRxManage.add(mModel.addInvoiceTitle(user_id, token, type, title_name, tax_no, phone, address, bank, bank_account, user_name).subscribe(new Observer<AddInvoiceTitleData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(AddInvoiceTitleData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void setDefaultTitle(String user_id, String token, String title_id) {
        mRxManage.add(mModel.setDefaultTitle(user_id, token, title_id).subscribe(new Observer<SetDefaultTitleData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(SetDefaultTitleData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void updateInvoiceTitle(String user_id, String token, String type, String title_id, String title_name, String tax_no, String phone, String address, String bank, String bank_account, String user_name) {
        mRxManage.add(mModel.updateInvoiceTitle(user_id, token, type, title_id, title_name, tax_no, phone, address, bank, bank_account, user_name).subscribe(new Observer<UpdateInvoiceTitleData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(UpdateInvoiceTitleData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void deleteRaiseByRaiseId(String userId, String token, String title_id) {
        mRxManage.add(mModel.deleteRaiseByRaiseId(userId, token, title_id).subscribe(new Observer<DeleteInvoiceData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(DeleteInvoiceData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getNewsList(int page, int show_count, String type) {
        mRxManage.add(mModel.getNewsList(page, show_count, type).subscribe(new Observer<NewsListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(NewsListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void sumbitInvoiceApply(String title_id, String user_id, String token, String store_id,float money) {
        mRxManage.add(mModel.sumbitInvoiceApply(title_id, user_id, token, store_id,money).subscribe(new Observer<InvoiceApplyData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(InvoiceApplyData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getRedPackageListData(String user_id, String token, String store_id) {
        mRxManage.add(mModel.getRedPackageListData(user_id, token, store_id).subscribe(new Observer<RedPackageListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(RedPackageListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getPubuListData(int page, int show_count) {
        mRxManage.add(mModel.getPubuListData(page, show_count).subscribe(new Observer<PubuListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(PubuListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void OpenRedPackageData(String user_id, String token, String rp_id) {
        mRxManage.add(mModel.OpenRedPackageData(user_id, token, rp_id).subscribe(new Observer<OpenRedPackageData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(OpenRedPackageData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getBannerList(String user_id) {
        mRxManage.add(mModel.getBannerList(user_id).subscribe(new Observer<BannerListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(BannerListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getExtensionPage() {
        mRxManage.add(mModel.getExtensionPage().subscribe(new Observer<ExtensionPageListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(ExtensionPageListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void sumbitExtension(String phone) {
        mRxManage.add(mModel.sumbitExtension(phone).subscribe(new Observer<ExtensionData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(ExtensionData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getScoreIntegralLog(String user_id, String token, int page, int show_count) {
        mRxManage.add(mModel.getScoreIntegralLog(user_id, token, page, show_count).subscribe(new Observer<ScoreListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(ScoreListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getInvoiceLog(String user_id, String token, int page, int show_count, int type) {
        mRxManage.add(mModel.getInvoiceLog(user_id, token, page, show_count,type).subscribe(new Observer<TickListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(TickListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getStoreOrderList(String user_id, String token, int page, int show_count,String store_id) {
        mRxManage.add(mModel.getStoreOrderList(user_id, token, page, show_count,store_id).subscribe(new Observer<ShopOrderListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(ShopOrderListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getOrderDetial(String user_id, String token, String order_id) {
        mRxManage.add(mModel.getOrderDetial(user_id, token, order_id).subscribe(new Observer<OrderDetialData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(OrderDetialData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void sumbitStoreWithdraw(String user_id, String token, String order_id, String store_id) {
        mRxManage.add(mModel.sumbitStoreWithdraw(user_id, token, order_id, store_id).subscribe(new Observer<OrderWithdrawData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(OrderWithdrawData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getFinishedOrderList(String user_id, String token, int page, int show_count, int type, String store_id) {
        mRxManage.add(mModel.getFinishedOrderList(user_id, token, page, show_count, type, store_id).subscribe(new Observer<FinishOrderListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(FinishOrderListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getUnfinishedOrderList(String user_id, String token, int page, int show_count, String store_id) {
        mRxManage.add(mModel.getUnfinishedOrderList(user_id, token, page, show_count, store_id).subscribe(new Observer<UnFinishOrderListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(UnFinishOrderListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void createOrder(String user_id, String token, String service_content, float service_price, String service_time, String remarks, String store_id) {
        mRxManage.add(mModel.createOrder(user_id, token, service_content, service_price, service_time, remarks, store_id).subscribe(new Observer<CreateOrderData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(CreateOrderData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getOrderCommentList(String order_id) {
        mRxManage.add(mModel.getOrderCommentList(order_id).subscribe(new Observer<UserCommentListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(UserCommentListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getDiscountList(String user_id, String token) {
        mRxManage.add(mModel.getDiscountList(user_id, token).subscribe(new Observer<CouponListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR,-1);
            }

            @Override
            public void onNext(CouponListData data) {

                if (data.getErrcode()==10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(),data.getErrcode());
                }

            }
        }));
    }




    @Override
    public void sumbitOauthData(String user_id, String token, String business_license, String idcard_front, String idcard_back) {

        mRxManage.add(mModel.sumbitOauthData(user_id, token, business_license, idcard_front, idcard_back).subscribe(new Observer<OauthStroeData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(OauthStroeData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));

    }


    @Override
    public void getUserCollect(String user_id, String token,double lng,double lat) {

        mRxManage.add(mModel.getUserCollect(user_id, token, lng, lat).subscribe(new Observer<CollectListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CollectListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void deleteUserCollect(String user_id, String token, String collect_id) {
        mRxManage.add(mModel.deleteUserCollect(user_id, token,collect_id).subscribe(new Observer<DeleteCollectData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(DeleteCollectData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void collecStore(String user_id, String token, String store_id) {
        mRxManage.add(mModel.collecStore(user_id, token,store_id).subscribe(new Observer<CollectStoreData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CollectStoreData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getUserommentList(String user_id, String token, int page, int show_count) {
        mRxManage.add(mModel.getUserommentList(user_id, token,page,show_count).subscribe(new Observer<UserCommentListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(UserCommentListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void deleteUserComment(String user_id, String token, String store_id) {
        mRxManage.add(mModel.deleteUserComment(user_id, token,store_id).subscribe(new Observer<DeleteCommentData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(DeleteCommentData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getUserOrderList(String user_id, String token, int page, int show_count) {
        mRxManage.add(mModel.getUserOrderList(user_id, token,page,show_count).subscribe(new Observer<UseOrderListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(UseOrderListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void evaluateOrder(String user_id, String token, String store_id, int score, String evaluate_content, String order_id) {
        mRxManage.add(mModel.evaluateOrder(user_id, token, store_id, score, evaluate_content, order_id).subscribe(new Observer<OrderCommentResultData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(OrderCommentResultData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void deleteUserOrder(String user_id, String token, String order_id) {
        mRxManage.add(mModel.deleteUserOrder(user_id, token, order_id).subscribe(new Observer<DeleteUserOrderData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(DeleteUserOrderData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getDiscoverLabel() {
        mRxManage.add(mModel.getDiscoverLabel().subscribe(new Observer<DiscoverLabelData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(DiscoverLabelData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getStoreByLabel(String getStoreByLabel, String lavel_lv2, String page, String show_count, double log, double lat) {
        mRxManage.add(mModel.getStoreByLabel(getStoreByLabel, lavel_lv2, page, show_count, log, lat).subscribe(new Observer<DiscoverStoreData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(DiscoverStoreData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getStore(String user_id, String token, String lavel_lv1, String lavel_lv2, int type, String keywords,String deatails,String image, double log, double lat,double distance) {
        mRxManage.add(mModel.getStore(user_id, token, lavel_lv1, lavel_lv2, type, keywords,deatails,image, log, lat,distance).subscribe(new Observer<StoreData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(StoreData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getStoreByWords(String page, String getStoreByWords, double log, double lat) {
        mRxManage.add(mModel.getStoreByWords(page, getStoreByWords, log, lat).subscribe(new Observer<DiscoverStoreData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(DiscoverStoreData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getStoreDetail(String store_id) {
        mRxManage.add(mModel.getStoreDetail(store_id).subscribe(new Observer<StoreDetialData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(StoreDetialData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getStoreEvaluation(int page, int show_count, String store_id) {
        mRxManage.add(mModel.getStoreEvaluation(page, show_count, store_id).subscribe(new Observer<ShopCommentList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ShopCommentList data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getWxPrePayData(String store_id, String deduction_id, String openid, String appip) {
        mRxManage.add(mModel.getWxPrePayData(store_id, deduction_id, openid, appip).subscribe(new Observer<WXPrepayData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(WXPrepayData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getBankcardList(String user_id, String token) {
        mRxManage.add(mModel.getBankcardList(user_id, token).subscribe(new Observer<BankListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(BankListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void addBankCard(String user_id, String token, String bank, String bank_account, String hold_name) {
        mRxManage.add(mModel.addBankCard(user_id, token, bank, bank_account, hold_name).subscribe(new Observer<AddBankcardData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(AddBankcardData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void delBankCard(String user_id, String token, String card_id) {
        mRxManage.add(mModel.delBankCard(user_id, token, card_id).subscribe(new Observer<DeleteBankcardData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(DeleteBankcardData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void withdrawApply(String user_id, String token, String card_id, float money) {
        mRxManage.add(mModel.withdrawApply(user_id, token, card_id, money).subscribe(new Observer<WithdrawApplyData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(WithdrawApplyData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getImInfo(String user_id, String token, String card_id) {
        mRxManage.add(mModel.getImInfo(user_id, token, card_id).subscribe(new Observer<FriendInfoData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(FriendInfoData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getMyStoreDetail(String user_id, String token) {
        mRxManage.add(mModel.getMyStoreDetail(user_id, token).subscribe(new Observer<MyStoreData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(MyStoreData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void delStore(String user_id, String token, String store_id) {
        mRxManage.add(mModel.delStore(user_id, token,store_id).subscribe(new Observer<DeleteMyShopData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(DeleteMyShopData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void updateStore(String user_id, String token, String store_id, String store_name, String banner_list, String service_price, String service_content, String address, String detail, String image_list, String logo, double lng, double lat, String label_lv1, String label_lv2,
                            String contacts,
                            String phone,
                            String bank,
                            String bank_account) {
        mRxManage.add(mModel.updateStore(user_id, token, store_id, store_name, banner_list, service_price, service_content, address, detail, image_list, logo, lng, lat, label_lv1, label_lv2,contacts,phone,bank,bank_account).subscribe(new Observer<UpdateStoreData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(UpdateStoreData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getShopIncomPage(String user_id, String token) {
        mRxManage.add(mModel.getShopIncomPage(user_id, token).subscribe(new Observer<ShopIncomData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ShopIncomData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getUserDeduction(String user_id, String token, int page, int show_count, int type) {
        mRxManage.add(mModel.getUserDeduction(user_id, token, page, show_count, type).subscribe(new Observer<UserDeductionData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(UserDeductionData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void saveStore(String user_id, String token, String logo, String store_name, String service_content, String service_price, String service_advantage, String address, String phone, String contacts, String bank, String bank_account, String banner_list, String image_list, String detail, double lng, double lat, String label_lv1, String label_lv2) {
        mRxManage.add(mModel.saveStore(user_id, token, logo, store_name, service_content, service_price, service_advantage, address, phone, contacts, bank, bank_account, banner_list, image_list, detail, lng, lat, label_lv1, label_lv2).subscribe(new Observer<UpdateStoreData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(UpdateStoreData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void bindPhone(String user_id, String token, String phone, String recom_id) {
        mRxManage.add(mModel.bindPhone(user_id, token, phone, recom_id).subscribe(new Observer<BindPhoneData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(BindPhoneData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getMoneyLogs(String user_id, String token, int page, int show_count, int type) {
        mRxManage.add(mModel.getMoneyLogs(user_id, token, page, show_count, type).subscribe(new Observer<HistoryRecordListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(HistoryRecordListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getVersion() {
        mRxManage.add(mModel.getVersion().subscribe(new Observer<VersionData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(VersionData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getActivityList(double lat,double lng,int page, int show_count, int type) {
        mRxManage.add(mModel.getActivityList(lat,lng,page, show_count, type).subscribe(new Observer<ActionListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ActionListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void countShopMoney(String user_id, String token) {
        mRxManage.add(mModel.countShopMoney(user_id, token).subscribe(new Observer<ShopBondData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ShopBondData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void createActivity(String user_id, String token, int type, String activity_name, String old_price, String discount_price, String begin_time, String end_time, String cutdown_price, String initiator_num, String remarks, String discount_num, String num, String money) {
        mRxManage.add(mModel.createActivity(user_id, token, type, activity_name, old_price, discount_price, begin_time, end_time, cutdown_price, initiator_num, remarks, discount_num, num, money).subscribe(new Observer<CreateActivityData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CreateActivityData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getCutdownDetail(String activity_id) {
        mRxManage.add(mModel.getCutdownDetail(activity_id).subscribe(new Observer<CutdownDetialData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CutdownDetialData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getMiaoshaDetail(String activity_id) {
        mRxManage.add(mModel.getMiaoshaDetail(activity_id).subscribe(new Observer<MiaoshaDetialData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(MiaoshaDetialData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getPintuanDetail(String activity_id) {
        mRxManage.add(mModel.getPintuanDetail(activity_id).subscribe(new Observer<PintuanDetialData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(PintuanDetialData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getChoujiangDetail(String activity_id) {
        mRxManage.add(mModel.getChoujiangDetail(activity_id).subscribe(new Observer<ChoujiangDetialData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ChoujiangDetialData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void joinPintuan(String user_id, String token, String activity_id, String pintuan_id, String leader) {
        mRxManage.add(mModel.joinPintuan(user_id, token, activity_id, pintuan_id, leader).subscribe(new Observer<JoinPintuanData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(JoinPintuanData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void createPintuan(String user_id, String token, String activity_id, String remarks) {
        mRxManage.add(mModel.createPintuan(user_id, token, activity_id, remarks).subscribe(new Observer<CreatePintuanData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CreatePintuanData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void createKanjia(String user_id, String token, String activity_id) {
        mRxManage.add(mModel.createKanjia(user_id, token, activity_id).subscribe(new Observer<CreateKanjiaData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CreateKanjiaData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getPintuanList(String activity_id) {
        mRxManage.add(mModel.getPintuanList(activity_id).subscribe(new Observer<PintuanListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(PintuanListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getPersionCutdownInfo(String user_id, String token, String kanjia_id) {
        mRxManage.add(mModel.getPersionCutdownInfo(user_id,token,kanjia_id).subscribe(new Observer<KanjiaInfoData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(KanjiaInfoData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getJingjialist(int page, int show_count, String keywords, int state,String user_id) {
        mRxManage.add(mModel.getJingjialist(page, show_count, keywords, state,user_id).subscribe(new Observer<BidPriceListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(BidPriceListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getTenderList(int page, int show_count, String keywords,String user_id) {
        mRxManage.add(mModel.getTenderList(page, show_count, keywords,user_id).subscribe(new Observer<TenderListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(TenderListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getInnovateList(int page, int show_count, int state,String user_id) {
        mRxManage.add(mModel.getInnovateList(page, show_count, state,user_id).subscribe(new Observer<InnovateListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(InnovateListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getJingjiaDetial(String tender_id) {
        mRxManage.add(mModel.getJingjiaDetial(tender_id).subscribe(new Observer<BidPriceDetialData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(BidPriceDetialData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getZhengjiDetail(String tender_id) {
        mRxManage.add(mModel.getZhengjiDetail(tender_id).subscribe(new Observer<ZhengjiDetialData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ZhengjiDetialData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getTenderDetail(String tender_id) {
        mRxManage.add(mModel.getTenderDetail(tender_id).subscribe(new Observer<TenderDetialData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(TenderDetialData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getSameLevelLabel(String label_id) {
        mRxManage.add(mModel.getSameLevelLabel(label_id).subscribe(new Observer<SameLevelLabelData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(SameLevelLabelData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getNearyByParam(double lat, double lng) {
        mRxManage.add(mModel.getNearyByParam(lat,lng).subscribe(new Observer<NearbyData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(NearbyData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getSortRule() {
        mRxManage.add(mModel.getSortRule().subscribe(new Observer<SortData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(SortData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getShopDetial(String store_id) {
        mRxManage.add(mModel.getShopDetial(store_id).subscribe(new Observer<ShopDetialData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ShopDetialData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getShopOfferList(String user_id, String token, int page, String show_count) {
        mRxManage.add(mModel.getShopOfferList(user_id, token, page, show_count).subscribe(new Observer<OfferListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(OfferListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getLabelList() {
        mRxManage.add(mModel.getLabelList().subscribe(new Observer<LabelListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(LabelListData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void searchServerList(String keywords, double lat, double lng, String lavel_lv1, String lavel_lv2, int page, int show_count, String area, String distance, String sort_rule) {
        mRxManage.add(mModel.searchServerList(keywords, lat, lng, lavel_lv1, lavel_lv2, page, show_count, area, distance, sort_rule).subscribe(new Observer<DiscoverStoreData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(DiscoverStoreData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


//    @Override
//    public void createJingjia(String user_id, String token, String label_lv1, String label_lv2, String title, String detail, String end_time, String address, int include_tax, int include_freight, int trans_type, int pay_type, int invoice_type, int business_license, String annex, String save_type, String province, String city, String area,String tender_id) {
//        mRxManage.add(mModel.createJingjia(user_id, token, label_lv1, label_lv2, title, detail, end_time, address, include_tax, include_freight, trans_type, pay_type, invoice_type, business_license, annex, save_type, province, city, area,tender_id).subscribe(new Observer<CreateJingjiaData>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                KLog.e(throwable.getMessage());
//                mView.showError(NET_ERROR, -1);
//            }
//
//            @Override
//            public void onNext(CreateJingjiaData data) {
//
//                if (data.getErrcode() == 10001) {
//                    mView.showSuccess(data);
//                } else {
//                    mView.showError(data.getMessage(), data.getErrcode());
//                }
//
//            }
//        }));
//    }
//
//
//    @Override
//    public void createZhengji(String user_id, String token, String title, String detail, String end_time, String trait, float money,  String annex, String save_type,String tender_id) {
//        mRxManage.add(mModel.createZhengji(user_id, token, title, detail, end_time, trait, money, annex, save_type,tender_id).subscribe(new Observer<CreateJingjiaData>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                KLog.e(throwable.getMessage());
//                mView.showError(NET_ERROR, -1);
//            }
//
//            @Override
//            public void onNext(CreateJingjiaData data) {
//
//                if (data.getErrcode() == 10001) {
//                    mView.showSuccess(data);
//                } else {
//                    mView.showError(data.getMessage(), data.getErrcode());
//                }
//
//            }
//        }));
//    }
//
//
//    @Override
//    public void createZhaobiao(String user_id, String token, String label_lv1, String label_lv2, String title, String detail, String end_time, String address, int include_tax, int include_freight, int invoice_type, String annex, String save_type, String province, String city, String area, String pub_time, String req_list, int tender_req, String collect, String phone, String survey, String collect_address,String tender_id) {
//        mRxManage.add(mModel.createZhaobiao(user_id, token, label_lv1, label_lv2, title, detail, end_time, address, include_tax, include_freight, invoice_type, annex, save_type, province, city, area, pub_time, req_list, tender_req, collect, phone, survey, collect_address,tender_id).subscribe(new Observer<CreateJingjiaData>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                KLog.e(throwable.getMessage());
//                mView.showError(NET_ERROR, -1);
//            }
//
//            @Override
//            public void onNext(CreateJingjiaData data) {
//
//                if (data.getErrcode() == 10001) {
//                    mView.showSuccess(data);
//                } else {
//                    mView.showError(data.getMessage(), data.getErrcode());
//                }
//
//            }
//        }));
//    }


    @Override
    public void createJingjia(String user_id, String token, String label_lv1, String label_lv2, String title, String detail, String end_time, String address, int include_tax, int include_freight, int trans_type, int pay_type, int invoice_type, int business_license, String annex, String save_type, String province, String city, String area, String tender_id, int service_type, String service_content) {
        mRxManage.add(mModel.createJingjia(user_id, token, label_lv1, label_lv2, title, detail, end_time, address, include_tax, include_freight, trans_type, pay_type, invoice_type, business_license, annex, save_type, province, city, area, tender_id, service_type, service_content).subscribe(new Observer<CreateJingjiaData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CreateJingjiaData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void createZhengji(String user_id, String token, String title, String detail, String end_time, String trait, float money, String annex, String save_type, String tender_id, String label_lv1, String label_lv2, int service_type, String service_content) {
        mRxManage.add(mModel.createZhengji(user_id, token, title, detail, end_time, trait, money, annex, save_type, tender_id, label_lv1, label_lv2, service_type, service_content).subscribe(new Observer<CreateJingjiaData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CreateJingjiaData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));

    }

    @Override
    public void createZhaobiao(String user_id, String token, String label_lv1, String label_lv2, String title, String detail, String end_time, String address, int include_tax, int include_freight, int invoice_type, String annex, String save_type, String province, String city, String area, String pub_time, String req_list, int tender_req, String collect, String phone, String survey, String collect_address, String tender_id, int service_type, String service_content) {
        mRxManage.add(mModel.createZhaobiao(user_id, token, label_lv1, label_lv2, title, detail, end_time, address, include_tax, include_freight, invoice_type, annex, save_type, province, city, area, pub_time, req_list, tender_req, collect, phone, survey, collect_address, tender_id, service_type, service_content).subscribe(new Observer<CreateJingjiaData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CreateJingjiaData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void competeTender(String user_id, String token, String tender_id, String annex, float price, String finish_time) {
        mRxManage.add(mModel.competeTender(user_id, token, tender_id, annex, price, finish_time).subscribe(new Observer<CompeteTenderData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CompeteTenderData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void collecService(String user_id, String token, String serviceId) {
        mRxManage.add(mModel.collecService(user_id, token, serviceId).subscribe(new Observer<CollectServiceData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CollectServiceData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void getJingjiaDraft(String user_id, String token,String tender_id) {
        mRxManage.add(mModel.getJingjiaDraft(user_id, token,tender_id).subscribe(new Observer<JingjiaDraftData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(JingjiaDraftData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getZhaobiaoDraft(String user_id, String token,String tender_id) {
        mRxManage.add(mModel.getZhaobiaoDraft(user_id, token,tender_id).subscribe(new Observer<ZhaobiaoDraftData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ZhaobiaoDraftData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void getZhengjiDraft(String user_id, String token,String tender_id) {
        mRxManage.add(mModel.getZhengjiDraft(user_id, token,tender_id).subscribe(new Observer<ZhengjiDraftData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ZhengjiDraftData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void createStoreServiceOrder(String user_id, String token, String service_fee, String save_money, String one_money) {
        mRxManage.add(mModel.createStoreServiceOrder(user_id, token, service_fee, save_money, one_money).subscribe(new Observer<CreateServiceOrderData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(CreateServiceOrderData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void createOrderYanshou(String user_id, String token, String order_id) {
        mRxManage.add(mModel.createOrderYanshou(user_id, token, order_id).subscribe(new Observer<ApplyYanshouData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ApplyYanshouData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void createOrderServiceYanshou(String user_id, String token, String order_id) {
        mRxManage.add(mModel.createOrderServiceYanshou(user_id, token, order_id).subscribe(new Observer<ServiceYanshouData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ServiceYanshouData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void createPayToStore(String user_id, String token, String order_id) {
        mRxManage.add(mModel.createPayToStore(user_id, token, order_id).subscribe(new Observer<PaytoStoreData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(PaytoStoreData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void changePublishState(String user_id, String token, String order_id, String state) {
        mRxManage.add(mModel.changePublishState(user_id, token, order_id,state).subscribe(new Observer<ChangePublisData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(ChangePublisData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }

    @Override
    public void deletePublishState(String user_id, String token, String order_id) {
        mRxManage.add(mModel.deletePublishState(user_id, token, order_id).subscribe(new Observer<DeletePublishData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(DeletePublishData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }


    @Override
    public void joinActivity(String user_id, String token, String acty_id) {
        mRxManage.add(mModel.joinActivity(user_id, token, acty_id).subscribe(new Observer<JoinActivityData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                KLog.e(throwable.getMessage());
                mView.showError(NET_ERROR, -1);
            }

            @Override
            public void onNext(JoinActivityData data) {

                if (data.getErrcode() == 10001) {
                    mView.showSuccess(data);
                } else {
                    mView.showError(data.getMessage(), data.getErrcode());
                }

            }
        }));
    }
}
