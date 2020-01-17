package com.synectiks.pref.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.synectiks.pref.domain.vo.Config;


/**
 * Application constants.
 */
public interface Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String ANONYMOUS_USER = "anonymoususer";

//    private Constants() {
//    }

    
}
