package com.foryaapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.foryaapi.model.entity.ApiInfo;

/**
* @author xyc
* @description 针对表【api_info(api信息表)】的数据库操作Service
* @createDate 2024-03-10 17:33:49
*/
public interface ApiInfoService extends IService<ApiInfo> {

    void validApiInfo(ApiInfo apiInfo, boolean add);
}
