package com.cykj.mapper;
import org.apache.ibatis.annotations.Param;

import com.cykj.model.pojo.Balanceflow;

/**
 * 交易流水操作
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface BalanceflowMapper {

    int addOneFlow(Balanceflow balanceflow);

    int updateByFlowOrderNumber(@Param("updated")Balanceflow updated,@Param("flowOrderNumber")String flowOrderNumber);

    Balanceflow findOneByFlowOrderNumber(@Param("flowOrderNumber")String flowOrderNumber);

    int addOneBalanceFlow(Balanceflow balanceflow);

}