package com.insthub.cat.android.ui.viewholder;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.common.android.futils.TimeUtils;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.widget.CountTimeDownHelper;
import com.insthub.cat.android.ui.widget.RatingBar;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class PintuanUserItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.iv_pintuan_head)
  public  RoundedImageView ivPintuanHead;
    @Bind(R.id.tv_pintuan_name)
    public TextView tvPintuanName;
    @Bind(R.id.tv_pintuan_do)
    public  TextView tvPintuanDo;
    @Bind(R.id.tv_pingtuan_last_people)
    public  TextView tvPingtuanLastPeople;
    @Bind(R.id.tv_pingtuan_last_time)
    public  TextView tvPingtuanLastTime;
    @Bind(R.id.rll_pintuan_content)
    public  RelativeLayout rllPintuanContent;

    private CountTimeDownHelper mHelper;


    public PintuanUserItemHolder(View itemView) {
        super(itemView);

    }



    public void setDownCount(long duration)
    {

      if(mHelper!=null)
      {
        mHelper.onDestroy();
        mHelper=null;
      }
      mHelper = new CountTimeDownHelper(new CountDownTimer(duration*1000,1 * 1000 - 10) {
        @Override
        public void onTick(long millisUntilFinished) {
          String counteTime = TimeUtils.formateTime(millisUntilFinished,TimeUtils.FROMATE_HMS);
          tvPingtuanLastTime.setText("剩余"+counteTime);
        }

        @Override
        public void onFinish() {
          tvPingtuanLastTime.setText("剩余00:00:00");
        }
      });
      mHelper.start();

    }

}
