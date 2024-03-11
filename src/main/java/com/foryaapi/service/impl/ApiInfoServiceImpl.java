package com.foryaapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foryaapi.common.ErrorCode;
import com.foryaapi.exception.BusinessException;
import com.foryaapi.mapper.ApiInfoMapper;
import com.foryaapi.model.entity.ApiInfo;
import com.foryaapi.service.ApiInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author xyc
* @description 针对表【api_info(api信息表)】的数据库操作Service实现
* @createDate 2024-03-10 17:33:49
*/
@Service
public class ApiInfoServiceImpl extends ServiceImpl<ApiInfoMapper, ApiInfo>
    implements ApiInfoService {

    @Override
    public void validApiInfo(ApiInfo apiInfo, boolean add) {
        Long id = apiInfo.getId();
        String apiName = apiInfo.getApiName();
        String description = apiInfo.getDescription();
        String url = apiInfo.getUrl();
        String requestHeader = apiInfo.getRequestHeader();
        String responseHeader = apiInfo.getResponseHeader();
        Integer status = apiInfo.getStatus();
        String method = apiInfo.getMethod();
        Long userId = apiInfo.getUserId();
        Date update_time = apiInfo.getUpdate_time();
        Date create_time = apiInfo.getCreate_time();
        Integer is_deleted = apiInfo.getIs_deleted();

        if (apiInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }


        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(apiName, url, requestHeader, responseHeader, method) || ObjectUtils.anyNull(id, status, userId)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(apiName) && apiName.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "apiName过长");
        }



    }
}




