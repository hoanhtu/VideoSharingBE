package com.tuho.YouTubeSharingApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuho.YouTubeSharingApp.Request.ShareVideoReq;
import com.tuho.YouTubeSharingApp.Response.ShareVideoRes;
import com.tuho.YouTubeSharingApp.Response.VideoRes;
import com.tuho.YouTubeSharingApp.entity.Notification;
import com.tuho.YouTubeSharingApp.entity.Video;
import com.tuho.YouTubeSharingApp.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ShareVideoRes shareVideo(ShareVideoReq req) {
        if (videoRepository.findByTitle(req.getTitle()).isPresent()) {
            throw new IllegalArgumentException("Title already exists");
        }
        if (videoRepository.findByIdUserAndUrl(req.getUid(), req.getUrl()).isPresent()) {
            throw new IllegalArgumentException("Video already exists");
        }
        Video video = new Video();
        video.setTitle(req.getTitle());
        video.setUrl(req.getUrl());
        video.setIdUser(req.getUid());
        video = videoRepository.save(video);
        // Notify other users
        // Create notification message
        String message = "User " + req.getUid() + " shared new video: " + video.getTitle();
        Notification notificationMessage = new Notification();
        notificationMessage.setMessage(message);
        notificationMessage.setUserId(req.getUid());
        String messageSend;
        try {
            messageSend = objectMapper.writeValueAsString(notificationMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert notification message to JSON", e);
        }
        notificationService.notifyUsers(messageSend, req.getUid());
        return ShareVideoRes.builder().id(video.getId()).title(video.getTitle()).idUser(video.getIdUser()).build();
    }

    public List<Video> getList(Long uid) {
        List<Video> videos = videoRepository.findAllByIdUser(uid);
        return videos;
    }


}
