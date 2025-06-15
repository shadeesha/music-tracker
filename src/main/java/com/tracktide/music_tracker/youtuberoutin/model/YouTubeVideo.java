package com.tracktide.music_tracker.youtuberoutin.model;

import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class YouTubeVideo {

    private String title;
    private String channel;
    private String views;
    private String thumbnail;
    private OffsetDateTime publishedAt;
    private LocalDate collectAt;

    public static YouTubeVideo fromApiItem(YouTubeApiItem item, LocalDate collectAt) {
        return new YouTubeVideo(
                item.getSnippet().getTitle(),
                item.getSnippet().getChannelTitle(),
                item.getStatistics().getViewCount(),
                item.getSnippet().getThumbnails().getHigh().getUrl(),
                item.getSnippet().getPublishedAt(),
                collectAt
        );
    }
}
