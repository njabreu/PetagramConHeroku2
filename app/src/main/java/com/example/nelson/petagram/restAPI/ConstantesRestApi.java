package com.example.nelson.petagram.restAPI;

/**
 * Created by Nelson Abreu on 6/26/2016.
 */
public final class ConstantesRestApi {
    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "3461966779.0fac6d8.9012e5ec09b64326996b0778bddb5fab";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER_SELF = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER_SELF = KEY_GET_RECENT_MEDIA_USER_SELF + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_URL_GET_USERID ="users/search?q=";
    public static final String KEY_URL_GET_USERS = "users/" ;
    public static final String KEY_URL_GET_MEDIA_RECENT = "/media/recent";
}
