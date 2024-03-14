package com.foryaapi.model.dto.apiinfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户调用测试接口请求
 *
 * @TableName product
 */
@Data
public class ApiInfoInvokeRequest implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * 用户请求参数
     */
    private String userRequestParams;

    ///**
    // * 0 关闭 1 开启
    // */
    //private Integer status;
    //
    ///**
    // * 请求类型
    // */
    //private String method;

//    /**
//     * 创建人id
//     */
//    private Long userId;

//    /**
//     * 更新时间
//     */
//    private Date update_time;
//
//    /**
//     * 创建时间
//     */
//    private Date create_time;
//
//    /**
//     * 是否删除(0-未删, 1-已删)
//     */
//    @TableLogic
//    private Integer is_deleted;
//    @TableField(exist = false)
//    private static final long serialVersionUID = 1L;

}