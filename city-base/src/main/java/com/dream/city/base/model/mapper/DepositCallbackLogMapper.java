package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.DepositCallbackLog;
import org.apache.ibatis.annotations.*;

/**
 * @author Wvv
 */
@Mapper
public interface DepositCallbackLogMapper {

    @Results(id = "BaseDepositCallbackLogResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "depositId", column = "deposit_id"),
            @Result(property = "requestFrom", column = "request_from"),
            @Result(property = "chainId", column = "chain_id"),
            @Result(property = "symbol", column = "symbol"),
            @Result(property = "assetId", column = "asset_id"),
            @Result(property = "txId", column = "tx_id"),
            @Result(property = "fromAddress", column = "from_address"),
            @Result(property = "toAddress", column = "to_address"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "confirm", column = "confirm"),
            @Result(property = "status", column = "status"),
            @Result(property = "blockHeight", column = "block_height"),
            @Result(property = "checksum", column = "checksum"),
            @Result(property = "confirmTime", column = "confirm_time"),
            @Result(property = "requestTime", column = "request_time"),
            @Result(property = "desc", column = "desc"),
            @Result(property = "createTime", column = "create_time"),


    })
    @Select("select * from deposit_callback_log where 1=1 and  id = #{id} limit 1 ")
    DepositCallbackLog getById(@Param("id")String id);

    @Insert("insert into `deposit_callback_log`(" +
            "  id ," +
            "  deposit_id ," +
            "  request_from ," +
            "  chain_id , " +
            "  symbol , " +
            "  asset_id , " +
            "  tx_id , " +
            "  from_address , " +
            "  to_address , " +
            "  amount , " +
            "  confirm , " +
            "  status , " +
            "  block_height , " +
            "  checksum , " +
            "  confirm_time , " +
            "  request_time ," +
            "  dep_desc, " +
            "  create_time " +
            ")value(0,#{depositId},#{requestFrom},#{chainId},#{symbol}," +
            "#{assetId},#{txId},#{fromAddress},#{toAddress},"+
            "#{amount},#{confirm},#{status}," +
            "#{blockHeight},#{checksum},#{confirmTime}," +
            "#{requestTime},#{DepDesc},#{createTime} )" )
    Integer insertSelective(DepositCallbackLog record);

    @Select("select * from deposit_callback_log where 1=1 and  tx_id = #{id} limit 1 ")
    @ResultMap("BaseDepositCallbackLogResultMap")
    DepositCallbackLog getDepositCallbackLog(@Param("txId") String txId);
}