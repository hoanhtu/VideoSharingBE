package com.tuho.YouTubeSharingApp.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRes {
    String token;
    long uid;
    String username;
}
