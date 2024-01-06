package icu.chzqz.foj;

import com.alibaba.druid.pool.DruidDataSource;
import icu.chzqz.foj.entity.User;
import icu.chzqz.foj.mapper.UserMapper;
import icu.chzqz.foj.properties.DefaultProperty;
import icu.chzqz.foj.properties.ServerProperty;
import icu.chzqz.foj.properties.LanguageProperty;
import icu.chzqz.foj.properties.MessageProperty;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.util.FormatUtil;
import icu.chzqz.foj.util.JWTUtil;
import icu.chzqz.foj.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest

@Slf4j
class FojWebApplicationTests {


    @Test
    void JWTTest(){
        log.info("\njwt测试");
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");

        String token = JWTUtil.createToken(claims);
        System.out.println(token);
        Map map = JWTUtil.parseToken(token);
        System.out.println(map);


    }


    //DI注入数据源
    @Autowired
    DataSource dataSource;


    @Test
    void druidTest() throws SQLException {
        log.info("\nDruid数据库连接池测试");
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
        //关闭连接
        connection.close();
    }

    @Autowired
    LanguageProperty languagePropertie;

    // 测试语言列表
    @Test
    void languageList(){
        List<String> languageList = languagePropertie.getLanguage();
        System.out.println(languageList);
    }

    @Autowired
    DefaultProperty defaultProperty;

    // 测试默认属性
    @Test
    void defaultPropertyTest(){
        System.out.println(defaultProperty);
    }

    @Autowired
    ServerProperty judgeServerProperty;
    @Test
    void judgeServerPropertyTest(){
        System.out.println(judgeServerProperty.getJudgeServer());
    }
    @Test
    void resultTest(){
        Result<User> success = Result.success(new User());
        System.out.println(success);
        Result error = Result.error(500,"我是错误");
        System.out.println(error);
    }

    @Test
    void MD5Test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(MD5Util.getMD5ByString("123456"));
        System.out.println(MD5Util.getMD5ByString("123456").length());
    }

    @Autowired
    MessageProperty mp;

    @Test
    void MessageTest(){
        System.out.println(mp.loginExpired);
    }

    @Test
    void checkEmailTest(){
        System.out.println(FormatUtil.checkEmail("110@"));
        System.out.println(FormatUtil.checkEmail("110"));
        System.out.println(FormatUtil.checkEmail("110@123"));
        System.out.println(FormatUtil.checkEmail("110@123.com"));
    }

    @Autowired
    UserMapper userMapper;
    @Test
    void userMapperTest(){
        User user = new User();
        user.setLastLoginTime(LocalDateTime.now());
        user.setId(1);
        userMapper.update(user);
    }

}
