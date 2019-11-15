package com.dream.city.controller.wallet;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.vo.*;
import com.dream.city.base.utils.JSONHelper;
import com.dream.city.base.utils.RsaEncryptUtil;
import com.dream.city.service.handler.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wallet/api")
public class WalletController {

    @Autowired
    WalletService walletService;

    /**
     * 提供提现数据集合
     *
     * @param encrypt
     * @return
     */
    @RequestMapping("/getWithDrawList")
    public ResultVM getWithdrawList(@RequestBody RequestParamVo encrypt) {
        try {
            String data = RsaEncryptUtil.decryptByPublicKey(encrypt.getData());
            WithdrawParamVo dataVo = JSONHelper.fromJson(data, WithdrawParamVo.class);

            List<WithdrawTaskRecord> records = walletService.getWithdrawList(dataVo);

            return ResultVM.ok("成功", records);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultVM.ok("成功", null);
    }

    /**
     * 提供提现数据回传接口
     *
     * @param encrypt
     * @return
     */
    @RequestMapping("/withDrawResultCallback")
    public ResultVM withDrawResultCallback(@RequestBody RequestParamVo encrypt) {

        try {
            String data = RsaEncryptUtil.decryptByPublicKey(encrypt.getData());
            WithdrawFeedbackParamVo dataVo = JSONHelper.fromJson(data, WithdrawFeedbackParamVo.class);
            Result result = walletService.withDrawResultCallback(dataVo);
            if (result.getSuccess()){
                return ResultVM.ok("成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultVM.ok("失败");
    }


    /**
     * 提供充值数据回传接口
     *
     * @param encrypt
     * @return
     */
    @RequestMapping("/depositCallback")
    public ResultVM depositCallback(@RequestBody RequestParamVo encrypt) {
        try {
            String data = RsaEncryptUtil.decryptByPublicKey(encrypt.getData());
            DepositParamVo dataVo = JSONHelper.fromJson(data, DepositParamVo.class);
            Result result = walletService.depositCallback(dataVo);

            if (result.getSuccess()){
                return ResultVM.ok("成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultVM.ok("失败");
    }

}
