package com.warkiz.indicatorseekbar.donation;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.warkiz.indicatorseekbar.R;
import com.warkiz.indicatorseekbar.fragment.BaseFragment;

/**
 * the Fragment for donation, no impact libs
 */

public class DonationFragment extends BaseFragment implements View.OnClickListener {

    private View mAlipayQRCode;
    private View mWechatpayQRCode;

    @Override
    protected int getLayoutId() {
        return R.layout.buy_me_a_coffee;
    }

    @Override
    protected void initView(View root) {
        root.findViewById(R.id.alipay_text).setOnClickListener(this);
        root.findViewById(R.id.wechatpay_text).setOnClickListener(this);
        root.findViewById(R.id.paypal_text).setOnClickListener(this);
        mAlipayQRCode = root.findViewById(R.id.alipay_qr_code);
        mWechatpayQRCode = root.findViewById(R.id.wechatpay_qr_code);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alipay_text:
                alipay();
                break;
            case R.id.wechatpay_text:
                wechatPay();
                break;
            case R.id.paypal_text:
                paypal();
                break;
            default:
                break;
        }
    }

    private void alipay() {
        if (AlipayUtil.hasInstalledAlipayClient(getContext())) {
            AlipayUtil.startAlipayClient(getActivity());
        } else {
            mAlipayQRCode.setVisibility(mAlipayQRCode.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        }
    }

    private void wechatPay() {
        mWechatpayQRCode.setVisibility(mWechatpayQRCode.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    private void paypal() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/BuyMeACupOfTeaThx")));
    }

}
