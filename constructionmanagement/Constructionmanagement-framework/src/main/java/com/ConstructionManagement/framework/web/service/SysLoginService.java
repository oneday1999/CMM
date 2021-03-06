package com.ConstructionManagement.framework.web.service;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.ConstructionManagement.common.constant.Constants;
import com.ConstructionManagement.common.core.domain.entity.SysUser;
import com.ConstructionManagement.common.core.domain.model.LoginUser;
import com.ConstructionManagement.common.core.redis.RedisCache;
import com.ConstructionManagement.common.exception.ServiceException;
import com.ConstructionManagement.common.exception.user.CaptchaException;
import com.ConstructionManagement.common.exception.user.CaptchaExpireException;
import com.ConstructionManagement.common.exception.user.UserPasswordNotMatchException;
import com.ConstructionManagement.common.utils.DateUtils;
import com.ConstructionManagement.common.utils.MessageUtils;
import com.ConstructionManagement.common.utils.ServletUtils;
import com.ConstructionManagement.common.utils.ip.IpUtils;
import com.ConstructionManagement.framework.config.threadpoolconfig.AsyncManager;
import com.ConstructionManagement.framework.config.threadpoolconfig.AsyncFactory;
import com.ConstructionManagement.system.service.ISysConfigService;
import com.ConstructionManagement.system.service.ISysUserService;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService
{
                                                                                                                                                                                                                                                                                                                    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public Map<String,String> login(String username, String password, String code, String uuid)
    {
        boolean captchaOnOff = configService.selectCaptchaOnOff();
        // 验证码开关
        if (captchaOnOff)
        {
            validateCaptcha(username, code, uuid);
        }

        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
             authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        recordLoginInfo(loginUser.getUserId());
        // 生成token
        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("token", tokenService.createToken(loginUser));
        resultMap.put("userId", String.valueOf(loginUser.getUserId()));
        return resultMap;
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    //更新用户的登录ip，登录时间
    public void recordLoginInfo(Long userId)
    {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtils.getNowDate());
        //更新用户的登录ip，登录时间
        userService.updateUserProfile(sysUser);
    }
}
