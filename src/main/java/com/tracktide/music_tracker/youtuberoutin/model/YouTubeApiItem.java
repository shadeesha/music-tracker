package com.tracktide.music_tracker.youtuberoutin.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class YouTubeApiItem {

    private String id;
    private Snippet snippet;
    private Statistics statistics;

    @Data
    public static class Snippet {
        private String title;
        private String channelTitle;
        private Thumbnails thumbnails;
        private OffsetDateTime publishedAt;

        @Data
        public static class Thumbnails {
            private Thumbnail high;

            @Data
            public static class Thumbnail {
                private String url;
            }
        }
    }

    @Data
    public static class Statistics {
        private String viewCount;
        private String likeCount;
    }

}
