package com.foryaapi.mapper;

import com.foryaapi.model.entity.UserApiInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author xyc
* @description 针对表【user_api_info(用户接口调用次数表)】的数据库操作Mapper
* @createDate 2024-03-12 17:36:55
* @Entity com.foryaapi.model.entity.UserApiInfo
*/


public interface UserApiInfoMapper extends BaseMapper<UserApiInfo> {

    Long selectByApiIdAndUserId(Long apiId, Long userId);
}




