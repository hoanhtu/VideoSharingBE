package com.tuho.YouTubeSharingApp.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoRes {
    private Long id;
    private String title;
    private String url;
    private Long idUser;
}
