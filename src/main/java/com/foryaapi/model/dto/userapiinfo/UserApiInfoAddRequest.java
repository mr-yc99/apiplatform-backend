package com.foryaapi.model.dto.userapiinfo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 *
 * @TableName product
 */
@Data
public class UserApiInfoAddRequest implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 接口id
     */
    private Long apiId;

    /**
     * 总共调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}