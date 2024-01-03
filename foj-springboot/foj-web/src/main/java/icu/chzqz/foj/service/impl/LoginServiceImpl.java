package icu.chzqz.foj.service.impl;

import icu.chzqz.foj.dto.LoginDTO;
import icu.chzqz.foj.entity.User;
import icu.chzqz.foj.exception.RequestFailException;
import icu.chzqz.foj.mapper.LoginMapper;
import icu.chzqz.foj.properties.MessageProperty;
import icu.chzqz.foj.service.LoginService;
import icu.chzqz.foj.util.JWTUtil;
import icu.chzqz.foj.util.MD5Util;
import icu.chzqz.foj.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    MessageProperty messageProperty;

    /**
     * 用户登录
     * @param loginDTO 用户登录的数据传输对象
     * @return 用户登录的视图对象
     */
    @Override
    public LoginVO login(LoginDTO loginDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException, RequestFailException {
        String passwd = MD5Util.getMD5ByString(loginDTO.getPassword());
        User user = loginMapper.login(loginDTO.getUsername());
        if(user==null||!user.getPassword().equals(passwd)){
            throw new RequestFailException(messageProperty.loginFailed);
        }
        Map claims = new HashMap();
        claims.put("name",user.getName());
        claims.put("id",user.getId());
        claims.put("authority",user.getAuthority());
        String token = JWTUtil.createToken(claims);
        return new LoginVO(token,user.getId(),user.getName(),user.getAuthority());
    }
}
