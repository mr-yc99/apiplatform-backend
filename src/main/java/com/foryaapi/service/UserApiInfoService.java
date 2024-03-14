package com.foryaapi.service;

import com.foryaapi.model.entity.UserApiInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
* @author xyc
* @description 针对表【user_api_info(用户接口调用次数表)】的数据库操作Service
* @createDate 2024-03-12 17:36:55
*/

public interface UserApiInfoService extends IService<UserApiInfo> {
    void validUserApiInfo(UserApiInfo userApiInfo, boolean add);

    /**
     * 统计调用接口次数
     *
     * @param apiId
     * @param userId
     * @return
     */
    boolean invokeCount(Long apiId, Long userId);
}
