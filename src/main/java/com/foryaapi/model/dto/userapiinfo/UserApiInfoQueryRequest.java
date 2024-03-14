package com.foryaapi.model.dto.userapiinfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.foryaapi.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author foryaapi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserApiInfoQueryRequest extends PageRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

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

    /**
     * 0 允许调用 1 不允许调用
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}