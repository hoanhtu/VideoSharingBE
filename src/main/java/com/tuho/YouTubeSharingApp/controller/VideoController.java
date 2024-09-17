package com.tuho.YouTubeSharingApp.controller;

import com.tuho.YouTubeSharingApp.Request.GetVideoReq;
import com.tuho.YouTubeSharingApp.Request.ShareVideoReq;
import com.tuho.YouTubeSharingApp.Response.ShareVideoRes;
import com.tuho.YouTubeSharingApp.Response.VideoRes;
import com.tuho.YouTubeSharingApp.entity.User;
import com.tuho.YouTubeSharingApp.entity.Video;
import com.tuho.YouTubeSharingApp.repository.VideoRepository;
import com.tuho.YouTubeSharingApp.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/videos")
public class VideoController {
    private final VideoService videoService;

    @PostMapping("/share")
    public ResponseEntity<ShareVideoRes> shareVideo(@RequestBody ShareVideoReq req) {
        ShareVideoRes res = videoService.shareVideo(req);
        // Notify users about the new video (more on this in the next section)
        return ResponseEntity.ok(res);
    }

    @PostMapping("/list")
    public ResponseEntity<List<Video>> getAllVideos(@RequestBody GetVideoReq req) {
        List<Video> videos = videoService.getList(req.getUid());
        return ResponseEntity.ok(videos);
    }
}
