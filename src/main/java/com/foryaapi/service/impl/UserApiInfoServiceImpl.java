package com.foryaapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foryaapi.common.ErrorCode;
import com.foryaapi.exception.BusinessException;
import com.foryaapi.model.entity.UserApiInfo;
import com.foryaapi.service.UserApiInfoService;
import com.foryaapi.mapper.UserApiInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author xyc
* @description 针对表【user_api_info(用户接口调用次数表)】的数据库操作Service实现
* @createDate 2024-03-12 17:36:55
*/

@Service
public class UserApiInfoServiceImpl extends ServiceImpl<UserApiInfoMapper, UserApiInfo>
    implements UserApiInfoService{

    @Autowired
    private UserApiInfoMapper userApiInfoMapper;

    @Override
    public void validUserApiInfo(UserApiInfo userApiInfo, boolean add) {
         Long userId = userApiInfo.getUserId();
         Long apiId = userApiInfo.getApiId();
         Integer totalNum = userApiInfo.getTotalNum();
         Integer leftNum = userApiInfo.getLeftNum();

        if (userApiInfo == null || userId == null || apiId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }


        // 创建时，所有参数必须非空
        if (add) {
            if(userId <= 0 || userId <= 0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        if (leftNum < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于0");
        }
    }

    @Override
    public boolean invokeCount(Long apiId, Long userId) {
        //校验
        if(apiId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户或接口不存在");
        }
        //查询对应的ID
        Long id = userApiInfoMapper.selectByApiIdAndUserId(apiId, userId);

        UserApiInfo userApiInfo = this.getById(id);

        Integer totalNum = userApiInfo.getTotalNum();
        Integer leftNum = userApiInfo.getLeftNum();
        Integer status = userApiInfo.getStatus();
        if( leftNum <= 0 || status == 0) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "次数用完或无法使用此接口");
        }
        //这里可以加锁
        UpdateWrapper<UserApiInfo> userApiInfoUpdateWrapper = new UpdateWrapper<>();
        userApiInfoUpdateWrapper.set("leftNum", leftNum - 1);
        userApiInfoUpdateWrapper.set("totalNum", totalNum + 1);
        userApiInfoUpdateWrapper.eq("id", id);

        return this.update(userApiInfoUpdateWrapper);
    }

}




