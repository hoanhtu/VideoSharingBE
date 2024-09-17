package com.tuho.YouTubeSharingApp.Request;

import lombok.Data;

@Data
public class ShareVideoReq {
    private String title;
    private String url;
    private Long uid;
}
