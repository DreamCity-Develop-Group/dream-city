package com.dream.city.job.thread;

import com.dream.city.base.model.entity.InvestOrder;
import lombok.Data;


@Data
public class IncreaseVo {
    private InvestOrder order;
    private Integer count;

    public IncreaseVo(InvestOrder order, Integer count) {
        this.order = order;
        this.count = count;
    }
}
