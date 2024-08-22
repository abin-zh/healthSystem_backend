package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalCheckupSummary;
import com.cykj.model.pojo.MedicalItemCheckResults;
import com.cykj.model.pojo.MedicalProjectSummary;
import com.cykj.model.vo.OrderDeptVO;
import com.cykj.model.vo.OrderVO;
import com.cykj.model.vo.PageVO;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface OrdersService{

    /**
     * 创建订单(开单)，具体事项看实现
     * @param orderVO 开单信息
     * @return
     */
    ResponseDTO addOneOrder(OrderVO orderVO);


    /**
     * 获取订单的项目小结信息(订单号查单个订单，手机号、身份证号查多个订单)
     * @param vo 必要查询信息
     * @return
     */
    ResponseDTO getOrderDetail(PageVO<OrderDeptVO> vo);

    /**
     * 检查订单项目的细项结果状态是否为已填写
     * @return
     */
    ResponseDTO checkItemResultStatus(int psId);

    /**
     * 获取订单中体检项目的体检细项信息
     * @param psId 项目小结id
     * @return
     */
    ResponseDTO getProjectItems(Integer psId);

    /**
     * 编辑/添加细项结果
     * @return
     */
    ResponseDTO editItemResult(MedicalItemCheckResults results);

    /**
     * 编辑/添加项目小结
     * @param summary
     * @return
     */
    ResponseDTO editProjectSummary(MedicalProjectSummary summary);

    /**
     * 获取所有未总结的体检总结
     * @return
     */
    ResponseDTO getAllUndoCheckups(PageVO<MedicalCheckupSummary> vo);


    /**
     * 获取订单关联的所有体检项目(依据体检总结id)
     * @param summary
     * @return
     */
    ResponseDTO getAllOrderProjects(MedicalCheckupSummary summary);

    /**
     * 编辑/添加体检总结
     * @param summary
     * @return
     */
    ResponseDTO editCheckResultSummary(MedicalCheckupSummary summary);
}
