package com.tuho.YouTubeSharingApp.repository;

import com.tuho.YouTubeSharingApp.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByTitle(String title);
    Optional<Video> findByIdUserAndUrl(Long idUser, String url);
    List<Video> findAllByIdUser(Long idUser);
}
