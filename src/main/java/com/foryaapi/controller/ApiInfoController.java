package com.foryaapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.foryaapi.annotation.AuthCheck;
import com.foryaapi.common.*;
import com.foryaapi.constant.CommonConstant;
import com.foryaapi.exception.BusinessException;
import com.foryaapi.model.dto.apiinfo.ApiInfoAddRequest;
import com.foryaapi.model.dto.apiinfo.ApiInfoQueryRequest;
import com.foryaapi.model.dto.apiinfo.ApiInfoUpdateRequest;
import com.foryaapi.model.entity.ApiInfo;
import com.foryaapi.model.entity.User;
import com.foryaapi.model.enums.ApiStatusEnum;
import com.foryaapi.service.ApiInfoService;
import com.foryaapi.service.UserService;
import com.learnjava.apiclientsdk.client.ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 接口管理
 *
 * @author foryaapi
 */
@RestController
@RequestMapping("/apiInfo")
@Slf4j
public class ApiInfoController {

    @Resource
    private ApiInfoService apiInfoService;

    @Resource
    private UserService userService;

    @Resource
    private ApiClient apiClient;

    // region 增删改查

    /**
     * 创建
     *
     * @param apiInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addApiInfo(@RequestBody ApiInfoAddRequest apiInfoAddRequest, HttpServletRequest request) {
        if (apiInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ApiInfo apiInfo = new ApiInfo();
        BeanUtils.copyProperties(apiInfoAddRequest, apiInfo);
        // 校验
        apiInfoService.validApiInfo(apiInfo, true);
        User loginUser = userService.getLoginUser(request);
        apiInfo.setUserId(loginUser.getId());
        boolean result = apiInfoService.save(apiInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newApiInfoId = apiInfo.getId();
        return ResultUtils.success(newApiInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteApiInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        ApiInfo oldApiInfo = apiInfoService.getById(id);
        if (oldApiInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!oldApiInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = apiInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param apiInfoUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateApiInfo(@RequestBody ApiInfoUpdateRequest apiInfoUpdateRequest,
                                            HttpServletRequest request) {
        if (apiInfoUpdateRequest == null || apiInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ApiInfo apiInfo = new ApiInfo();
        BeanUtils.copyProperties(apiInfoUpdateRequest, apiInfo);
        // 参数校验
        apiInfoService.validApiInfo(apiInfo, false);
        User user = userService.getLoginUser(request);
        long id = apiInfoUpdateRequest.getId();
        // 判断是否存在
        ApiInfo oldApiInfo = apiInfoService.getById(id);
        if (oldApiInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!oldApiInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = apiInfoService.updateById(apiInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<ApiInfo> getApiInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ApiInfo apiInfo = apiInfoService.getById(id);
        return ResultUtils.success(apiInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param apiInfoQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<ApiInfo>> listApiInfo(ApiInfoQueryRequest apiInfoQueryRequest) {
        ApiInfo apiInfoQuery = new ApiInfo();
        if (apiInfoQueryRequest != null) {
            BeanUtils.copyProperties(apiInfoQueryRequest, apiInfoQuery);
        }
        QueryWrapper<ApiInfo> queryWrapper = new QueryWrapper<>(apiInfoQuery);
        List<ApiInfo> apiInfoList = apiInfoService.list(queryWrapper);
        return ResultUtils.success(apiInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param apiInfoQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<ApiInfo>> listApiInfoByPage(ApiInfoQueryRequest apiInfoQueryRequest, HttpServletRequest request) {
        if (apiInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ApiInfo apiInfoQuery = new ApiInfo();
        BeanUtils.copyProperties(apiInfoQueryRequest, apiInfoQuery);
        long current = apiInfoQueryRequest.getCurrent();
        long size = apiInfoQueryRequest.getPageSize();
        String sortField = apiInfoQueryRequest.getSortField();
        String sortOrder = apiInfoQueryRequest.getSortOrder();
        String description = apiInfoQuery.getDescription();
        // content 需支持模糊搜索
        apiInfoQuery.setDescription(null);
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<ApiInfo> queryWrapper = new QueryWrapper<>(apiInfoQuery);
        queryWrapper.like(StringUtils.isNotBlank(description), "content", description);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<ApiInfo> apiInfoPage = apiInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(apiInfoPage);
    }

    // endregion

    /**
     * 发布接口
     * @param idRequest
     * @param request
     * @return
     */
    @AuthCheck(mustRole = "admin")// 这里通过aop实现
    @PostMapping("/online")
    public BaseResponse<Boolean> onlineApiInfo(@RequestBody IdRequest idRequest,
                                               HttpServletRequest request) {

        //判断接口是否存在
        if( idRequest == null || idRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //判断id是否存在
        ApiInfo apiInfoServiceById = apiInfoService.getById(idRequest.getId());
        if(apiInfoServiceById == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //判断接口是否可用
        //这里模拟client调用该接口就行了，使用之前开发的apiClientSDK
        com.learnjava.apiclientsdk.model.User userClient = new com.learnjava.apiclientsdk.model.User();
        userClient.setUserName("admin");
        // todo 这里apiClientSDK中的请求地址是固定的，要改一下sdk
        String userNameByPost = apiClient.getUserNameByPost(userClient);
        if(StringUtils.isBlank(userNameByPost)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "接口不可用");
        }

        //启用接口

        apiInfoServiceById.setStatus(ApiStatusEnum.online.getValue());

        return ResultUtils.success(true);
    }


    /**
     * 更新接口
     * @param idRequest
     * @param request
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @PostMapping("/offline")
    public BaseResponse<Boolean> offlineApiInfo(@RequestBody IdRequest idRequest,
                                               HttpServletRequest request) {
        //判断接口是否存在
        if( idRequest == null || idRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //判断id是否存在
        ApiInfo apiInfoServiceById = apiInfoService.getById(idRequest.getId());
        if(apiInfoServiceById == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //关闭接口
        apiInfoServiceById.setStatus(ApiStatusEnum.offline.getValue());

        return ResultUtils.success(true);

    }

}
