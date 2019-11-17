package com.dream.city.controller.wallet;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.DepositCallbackLog;
import com.dream.city.base.model.vo.*;
import com.dream.city.base.utils.IPUtils;
import com.dream.city.base.utils.JSONHelper;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RsaEncryptUtil;
import com.dream.city.service.handler.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/consumer/wallet/api")
public class WalletController {
    private final String assetId = "0xdac17f958d2ee523a2206206994597c13d831ec7";
    private final String CHAIN_ID = "60";

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
        log.info("getWithDrawList");
        try {
            String data = RsaEncryptUtil.decryptByPublicKey(encrypt.getData());
            if (Objects.isNull(encrypt)){
                log.info("getWithDrawList:失败,接收数据空");
                return ResultVM.error("失败");
            }
            WithdrawParamVo dataVo = JSONHelper.fromJson(data, WithdrawParamVo.class);
            /*WithdrawParamVo dataVo = new WithdrawParamVo();
            dataVo.setRequestFrom("ETH_Wallet");
            dataVo.setRequestTime(System.currentTimeMillis());
            dataVo.setChainId("60");
            dataVo.setId(0l);
            dataVo.setStatus("2");
            dataVo.setSize(10);*/

            List<WithdrawTaskRecord> records = walletService.getWithdrawList(dataVo);
            if (records.size()>0) {

                String recordJson = JsonUtil.parseObjToJson(records);
                log.info("getWithDrawList:成功");
                log.info("records");
                log.info(recordJson);

                return ResultVM.ok("成功", RsaEncryptUtil.encryptByPrivateKey(recordJson));
            }else{
                log.info("getWithDrawList:成功");
                log.info("records，当前没有数据");
                return ResultVM.error("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("getWithDrawList:失败");
        return ResultVM.error("失败");
    }

    /**
     * 提供提现数据回传接口
     *
     * @param encrypt
     * @return
     */
    @RequestMapping("/withDrawResultCallback")
    public ResultVM withDrawResultCallback(@RequestBody RequestParamVo encrypt) {
        log.info("withDrawResultCallback");
        try {
            String data = RsaEncryptUtil.decryptByPublicKey(encrypt.getData());
            WithdrawFeedbackParamVo dataVo = JSONHelper.fromJson(data, WithdrawFeedbackParamVo.class);

            /*WithdrawFeedbackParamVo dataVo = new WithdrawFeedbackParamVo();
            dataVo.setBlock_height("65424");
            dataVo.setChecksum("");
            dataVo.setConfirm("1");
            dataVo.setConfirm_time(new Date().toString());
            dataVo.setStatus("2");
            dataVo.setTx_id("0xhlasdfl;wefkaoiwefq");
            dataVo.setTask_id("18");*/


            Result result = walletService.withDrawResultCallback(dataVo);
            if (result.getSuccess()) {
                log.info("withDrawResultCallback:成功");
                return ResultVM.ok("成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("withDrawResultCallback:失败");
        return ResultVM.error("失败");
    }


    /**
     * 提供充值数据回传接口
     *
     * @param encrypt
     * @return
     */
    @RequestMapping("/depositCallback")
    public ResultVM depositCallback(HttpServletRequest request, @RequestBody RequestParamVo encrypt) {
        log.info("depositCallback");

        try {
            String data = RsaEncryptUtil.decryptByPublicKey(encrypt.getData());
            DepositParamVo dataVo = JSONHelper.fromJson(data, DepositParamVo.class);

            DepositCallbackLog dlog = walletService.getDepositCallbackLog(dataVo.getTxId());
            if (Objects.nonNull(dlog)){
                dlog.setDepDesc(dataVo.getMemo()+"错误充值：重复记录");
                dlog.setCreateTime(new Date());
                walletService.addDepositCallbackLog(dlog);

                return ResultVM.error("失败");
            }

            dlog  = new DepositCallbackLog();
            dlog.setId(0);
            dlog.setDepositId(dataVo.getDepositId());
            dlog.setRequestFrom(dataVo.getRequestFrom());
            dlog.setChainId(dataVo.getChainId());
            dlog.setSymbol(dataVo.getSymbol());
            dlog.setAssetId(dataVo.getAssetId());
            dlog.setTxId(dataVo.getTxId());
            dlog.setFromAddress(dataVo.getFromWalletAddress());
            dlog.setToAddress(dataVo.getToWalletAddress());
            dlog.setAmount(dataVo.getAmount());
            dlog.setConfirm(dataVo.getConfirm());
            dlog.setStatus(dataVo.getStatus());
            dlog.setBlockHeight(dataVo.getBlockHeight());
            dlog.setChecksum(dataVo.getChecksum());
            dlog.setRequestTime(dataVo.getRequestTime());
            dlog.setConfirmTime(dataVo.getConfirmTime());

            String ip = IPUtils.getIpAddr(request);
            if(!ip.contains("47.244.78.40")&&!ip.contains("172.31.86.248")){
                log.warn("警报~~~~~~~错误充值：记录IP ：~~~~~{},txid:{}",ip,dataVo.getTxId());

                dlog.setDepDesc(dataVo.getMemo()+"错误充值：记录IP");
                dlog.setCreateTime(new Date());
                walletService.addDepositCallbackLog(dlog);

                return ResultVM.error("失败");
            }
            if (!dataVo.getChainId().equals(CHAIN_ID) || !dataVo.getAssetId().equals(assetId)) {
                dlog.setDepDesc(dataVo.getMemo()+"错误充值：记录合约和主链ID");
                dlog.setCreateTime(new Date());
                walletService.addDepositCallbackLog(dlog);
                return ResultVM.error("失败");
            }
            Result result = walletService.depositCallback(dataVo,dlog);
            log.info("Result：" + result.getMsg());
            if (result.getSuccess()) {
                /*log.info("withDrawResultCallback:成功");
                dlog.setDepDesc(dataVo.getMemo()+"正确充值：充值成功记录");
                dlog.setCreateTime(new Date());
                walletService.addDepositCallbackLog(dlog);*/
                return ResultVM.ok("成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("getWithDrawList:失败");
        DepositCallbackLog dlog  = new DepositCallbackLog();
        dlog.setId(0);
        dlog.setDepDesc("充值回调：充值失败记录-"+encrypt);
        dlog.setCreateTime(new Date());
        walletService.addDepositCallbackLog(dlog);
        return ResultVM.error("失败");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            // String password =
            // "{\"password\":\"12345678\",'password':'12345678','password':'12345678','password':'12345678','password':'12345678','password':'12345678','password':'12345678'}";
            //String password = "e1Dwp+EQ8HTPNGItyZ8ssDnMjIv7PvTmIbkNXuyuYiCnQyVTrB1dUY2KEPRC+YhkAJhaUnr3c8edEKowjD8QXRO2SQkXjqebYfXivIr/HQSj0Nkf5y87uNP6QM4KwA1gGJXpFNNaNkadkqW9H3Uxgx99uhz5oPZpML/giQAAZcBYjsdwaT2q/Pv4yzjSePSAi9fMM7SUYTp6lZRbjaFUrX39BiKBWEcjo07fG9CB/1rTd8zN92J92hykxaG98pd1LAlVuO+f5zh1zubmJqrMpMq4MMGdjwF95r+lM8kOrxzQmraJ1gSRXdRLHOnAzgpRObkbC811jqxPXXcfm9Cw4A==";
            //String password = "hmNSrSGRsdbhju4u3tP71wDNm+ZrpCJp9HpZuuhh3iMzrWDQPaFJWT9cjfclUlAivXcHRG/AqEOo9W+PoCMjvaA5zCEWZBfUjbqGIcqboXOsiad1VU22RQhhuQlsSr3uF1Moj1QTBmmwqU26yOKhPXuknD67swJdZx76zVv33TqBiyfoeqX6oek21DZqrE2Hs6M+NdU/KxhYzanVEjcIeLQyF4OkiGfvc96Vi3lbRjC/Wv/WoL02PD4f5B8f2XSwIuDNDqoK0vlEuhrKt1lUmMY1UVec3weG5wig1KkG/E0Kv8NaLtGto/dyps1WNUGWUsMhC+M1gKzZ78DIiDa3gwz+GDK5q3nblhpJIwG0UZiGKiNcVMdxIxKbkQ6gUFgBeUg339Fcusfzh+rsToR6O/LmKVG9dFYShVM3DCqEpj3y3bh0rTqqfvLwuv3mk2JT4BeJAkfDb16tLsbQ6Fsm0cAmSW90Ntmxm1jGWgzqe1xEl9fQVqmelHOF1zR0QTeRL5WiFKgSFa0/dh30fsNCSoeeyYtjjL0dYpAGfyHB2FTZomZDLhNiKNmhhqvCeJN55+qFPhA0tWlkmmh27fUqlQixAsL6IbyqjuCvsFn+c8QU+ADpGfFKgfbub8Zz8qqFYecVEN1aUn5ictIz6EJjQ9HQ9UzSvjw98T12Ba3T1BQJCkSUxfBozQqD/GNBjHVsqXQ9FsbTIfciQTWdHG21Is4UBsWLgxllZM04dY4CmqdA6ugWp3wgOD7+GB39hzxT7D1/JKBgMT84IVs4STU9dM37VixqZMadnYF4X2a2Ef/QKzMTundenT1Hb8M55HKZbHNnwhLu7MNG7uJ0d2+sEHg5BkTaY4LiRZUFPI93tzeBQBV2bb7ayRZG8G2pC8jjxYe7DBUEI1BbdFkxcZ6mw/BofJgH7FQsVCirP7CHMcjV+MnZf9chK1Vrv4UEVANZXCT2dARjcBIuKIceGuQMyQSMs/YqS3I/yIMHo+6+/Y+TKfZJjg0Go4duGLcCfBAJjVOXnkT9vp+o6omy9QoEpidwPKk4TcAgYkw7i+8brMmg3SC0+t10bNXlwtNzADuo7u6nelqIa+vYfHuDgltsmbAWFHg9DAm0odPhsA/aR6y0/GOtdDBO5pCz1+Rj4GYATKxjaJMz6LKhj4vYu5u0u0lcl5+ibT75zpVAswGXP60dQn71AbHfupltEobvnaWVh1TqkXQTaUdpRtWRmrWs/QPB4ict2oYko1ntw000Up6odflff42yu2y4Fa86jUHwFyAzeCGxx2SsLwXQbtOil/1S6ojRgkDyqgtsWkbO9gMll8CUrqFfPojOsguFU6hxxunAkALNlu4b7QaMWIomMkLjb01/2ETxBw5ci39ShMm6yAK6M+ANqps+N/VyVhUjigITiZfkJr6QGt3/UzLToN9JRNjx1mcGZJidY703mAR788WBih/KhQCRaRxtim3zD41Z96w/vcwINPccMCQp4XcdF2jwjjwcI3xQAokGUK56BqGd1JgCVfirbh37MWDhlmLLlpjN6rXsWfGBacNT7UIRU9dw/BR81aLN7aaPaqj8KZ3hkfMqp928wau7lDVSGjwj+4qpY+HTzNaFG5dIYwM7Zy6qFbZDMj0k/C5Jk9HMNFZ2uI4KXd05yoYAH5tDiaHrjIVm/O0xjAk4tS2+PXOu7muwOC1FmIa7PA5klgQ=";
            String password = "{\"requestFrom\":\"ETH_Wallet\",\"requestTime\":1573906200004,\"chainId\":\"60\",\"symbol\":\"USDT\",\"assetId\":\"0xdac17f958d2ee523a2206206994597c13d831ec7\",\"depositId\":\"0x82a7464611fba17a4c188132e1542fc0de9dbdb549248409d6c997f89544ed6f-0x775e0f92cf0a5157b436b507339277208e520d1d\",\"txId\":\"0x82a7464611fba17a4c188132e1542fc0de9dbdb549248409d6c997f89544ed6f-0x775e0f92cf0a5157b436b507339277208e520d1d\",\"fromWalletAddress\":\"0x4a490c11a2cf3b2556cd40bfcdc0b7c2af5da393\",\"toWalletAddress\":\"0x775e0f92cf0a5157b436b507339277208e520d1d\",\"amount\":1.00000000,\"memo\":\"0x775e0f92cf0a5157b436b507339277208e520d1d\",\"confirm\":\"1\",\"status\":\"2\",\"blockHeight\":\"8944281\",\"confirmTime\":1573906200004,\"checksum\":\"6\"}";
            try {

                System.out.println("len:"+password.length());
                System.out.println("pass:"+password);
                String aa = RsaEncryptUtil.encryptByPrivateKey(password);
                String ed = RsaEncryptUtil.decryptByPublicKey(password);
                System.out.println("ed:"+ed);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
