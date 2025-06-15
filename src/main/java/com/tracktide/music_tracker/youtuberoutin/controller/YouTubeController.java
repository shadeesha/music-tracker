package com.tracktide.music_tracker.youtuberoutin.controller;


import com.tracktide.music_tracker.youtuberoutin.model.YouTubeVideo;
import com.tracktide.music_tracker.youtuberoutin.service.YouTubeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/youtube")
@Slf4j
public class YouTubeController {

    @Autowired
    private YouTubeService youTubeService;

    @GetMapping("/trending")
    public ResponseEntity<List<YouTubeVideo>> getTrendingMusic() throws IOException {
        log.info("Youtube reqeust started");
        return ResponseEntity.ok(youTubeService.readTrendingVidoesFromFile());
//        return ResponseEntity.ok(youTubeService.fetchTrendingMusicVideos());
    }

    @GetMapping("/trendingByDate")
    public ResponseEntity<List<YouTubeVideo>> getTrendingVideosByDate(
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws IOException {

        log.info("Getting Trending videos by Date");
        return ResponseEntity.ok(youTubeService.readTrendingVidoesFromFile(date));
    }
}
