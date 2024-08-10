package com.cykj.util;

import javax.servlet.http.Cookie;

/**
 * @author abin
 * @date 2024/7/31 22:44
 */
public class CookieUtils {

    public static Cookie getCookie(String name,Cookie[] cookies){
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(name)){
                return cookie;
            }
        }
        return null;
    }

}
