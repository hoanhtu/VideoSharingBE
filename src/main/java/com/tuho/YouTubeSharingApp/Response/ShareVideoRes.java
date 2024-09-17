package com.tuho.YouTubeSharingApp.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShareVideoRes {
    private Long id;
    private String title;
    private Long idUser;
}
