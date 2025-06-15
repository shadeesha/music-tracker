package com.tracktide.music_tracker.youtuberoutin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracktide.music_tracker.youtuberoutin.model.YouTubeApiResponse;
import com.tracktide.music_tracker.youtuberoutin.model.YouTubeVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class YouTubeService {

    @Value("${youtube.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    //remove this for production. This is only used to read from jsonfiles
    private final ObjectMapper objectMapper;

    @Autowired
    public YouTubeService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    //remove this for production. This is only used to read from jsonfiles
    public List<YouTubeVideo> readTrendingVidoesFromFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("/jsonFiles/trendingVidoes/trending_videos_250614.json");
        if(resource.exists()) {
            return objectMapper.readValue(
                    resource.getInputStream(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, YouTubeVideo.class)
            );
        }
        return List.of();
    }

    public List<YouTubeVideo> readTrendingVidoesFromFile(LocalDate date) throws IOException {
        ClassPathResource resource = new ClassPathResource("/jsonFiles/trendingVidoes/trending_videos_250614.json");
        if(resource.exists()) {
            return objectMapper.readValue(
                    resource.getInputStream(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, YouTubeVideo.class)
            );
        }
        return List.of();
    }

    public List<YouTubeVideo> fetchTrendingMusicVideos() {
        String url = "https://www.googleapis.com/youtube/v3/videos"
                + "?part=snippet,statistics"
                + "&chart=mostPopular"
                + "&videoCategoryId=10"
                + "&maxResults=50"
                + "&key=" + apiKey;

        ResponseEntity<YouTubeApiResponse> response =
                restTemplate.getForEntity(url, YouTubeApiResponse.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getItems().stream()
                    .map(youTubeApiItem -> YouTubeVideo.fromApiItem(youTubeApiItem, LocalDate.now()))
                    .collect(Collectors.toList());
        }
        return List.of();
    }
}
