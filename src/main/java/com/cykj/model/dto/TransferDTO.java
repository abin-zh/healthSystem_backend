package com.cykj.model.dto;

import com.cykj.model.pojo.Menu;
import lombok.Data;

import java.util.List;

/**
 * @author abin
 * @date 2024/7/30 13:14
 */

@Data
public class TransferDTO {

    private List<Menu> fromData;
    private List<Menu> toData;
    private List<Integer> toIds;

    public TransferDTO(List<Menu> fromData, List<Menu> toData, List<Integer> toIds) {
        this.fromData = fromData;
        this.toData = toData;
        this.toIds = toIds;
    }
}
