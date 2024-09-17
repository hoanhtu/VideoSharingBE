package com.tuho.YouTubeSharingApp.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuho.YouTubeSharingApp.Request.ShareVideoReq;
import com.tuho.YouTubeSharingApp.Response.ShareVideoRes;
import com.tuho.YouTubeSharingApp.entity.Notification;
import com.tuho.YouTubeSharingApp.entity.Video;
import com.tuho.YouTubeSharingApp.repository.VideoRepository;
import com.tuho.YouTubeSharingApp.service.NotificationService;
import com.tuho.YouTubeSharingApp.service.VideoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private VideoService videoService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testShareVideo_Success() throws JsonProcessingException {
        ShareVideoReq req = new ShareVideoReq();
        req.setTitle("Test Video");
        req.setUrl("http://test.com/video");
        req.setUid(1L);

        Video video = new Video();
        video.setId(1L);
        video.setTitle("Test Video");
        video.setUrl("http://test.com/video");
        video.setIdUser(1L);

        when(videoRepository.findByTitle(req.getTitle())).thenReturn(Optional.empty());
        when(videoRepository.findByIdUserAndUrl(req.getUid(), req.getUrl())).thenReturn(Optional.empty());
        when(videoRepository.save(any(Video.class))).thenReturn(video);

        ShareVideoRes res = videoService.shareVideo(req);

        assertNotNull(res);
        assertEquals("Test Video", res.getTitle());
        assertEquals(1L, res.getIdUser());

        String expectedMessage = objectMapper.writeValueAsString(new Notification(req.getTitle(), req.getUid()));
        verify(notificationService, times(1)).notifyUsers(expectedMessage, req.getUid());
    }

    @Test
    public void testShareVideo_TitleAlreadyExists() {
        ShareVideoReq req = new ShareVideoReq();
        req.setTitle("Test Video");
        req.setUrl("http://test.com/video");
        req.setUid(1L);

        when(videoRepository.findByTitle(req.getTitle())).thenReturn(Optional.of(new Video()));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            videoService.shareVideo(req);
        });

        assertEquals("Title already exists", exception.getMessage());
    }

    @Test
    public void testShareVideo_VideoAlreadyExists() {
        ShareVideoReq req = new ShareVideoReq();
        req.setTitle("Test Video");
        req.setUrl("http://test.com/video");
        req.setUid(1L);

        when(videoRepository.findByTitle(req.getTitle())).thenReturn(Optional.empty());
        when(videoRepository.findByIdUserAndUrl(req.getUid(), req.getUrl())).thenReturn(Optional.of(new Video()));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            videoService.shareVideo(req);
        });

        assertEquals("Video already exists", exception.getMessage());
    }
}