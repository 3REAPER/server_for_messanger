package com.pervukhin.rest.dto;

import com.pervukhin.LibraryApp;

import java.util.ArrayList;

public class LinkBuilder {
    public static final String LINK_DOWNLOAD = "message/download";
    public static final String LINK_HOME = "192.168.0.199:8080";

    public static String createLink(String path){
        String[] dirs = path.split("\\\\");
        return LINK_HOME + "/" + LINK_DOWNLOAD + "?fileName=" + dirs[dirs.length - 1];
    }
}
