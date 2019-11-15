package com.dream.city.controller.wallet;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.vo.*;
import com.dream.city.base.utils.JSONHelper;
import com.dream.city.base.utils.JsonUtil;
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
            String recordJson = JsonUtil.parseObjToJson(records);
            return ResultVM.ok("成功", RsaEncryptUtil.encryptByPrivateKey(recordJson));
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

    /*public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            // String password =
            // "{\"password\":\"12345678\",'password':'12345678','password':'12345678','password':'12345678','password':'12345678','password':'12345678','password':'12345678'}";
            //String password = "e1Dwp+EQ8HTPNGItyZ8ssDnMjIv7PvTmIbkNXuyuYiCnQyVTrB1dUY2KEPRC+YhkAJhaUnr3c8edEKowjD8QXRO2SQkXjqebYfXivIr/HQSj0Nkf5y87uNP6QM4KwA1gGJXpFNNaNkadkqW9H3Uxgx99uhz5oPZpML/giQAAZcBYjsdwaT2q/Pv4yzjSePSAi9fMM7SUYTp6lZRbjaFUrX39BiKBWEcjo07fG9CB/1rTd8zN92J92hykxaG98pd1LAlVuO+f5zh1zubmJqrMpMq4MMGdjwF95r+lM8kOrxzQmraJ1gSRXdRLHOnAzgpRObkbC811jqxPXXcfm9Cw4A==";
            String password = "123456";
            try {

                System.out.println("len:"+password.length());
                System.out.println("pass:"+password);
                String aa = RsaEncryptUtil.encryptByPrivateKey(password);
                String ed = RsaEncryptUtil.decryptByPublicKey(aa);
                System.out.println("ed:"+ed);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

}
