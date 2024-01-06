package icu.chzqz.foj.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {

    public static boolean checkEmail(String email){
        if(email==null) return false;
        String emailRegex = "^[a-zA-Z0-9._]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkPasswd(String passwd){
        if(passwd==null) return false;
        //密码是8-16位字母和数字的组合
        String PWD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        Pattern pattern = Pattern.compile(PWD_REGEX);
        Matcher matcher = pattern.matcher(passwd);
        return matcher.matches();
    }
}
