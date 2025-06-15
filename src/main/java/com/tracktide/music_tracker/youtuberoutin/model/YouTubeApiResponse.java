package com.tracktide.music_tracker.youtuberoutin.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class YouTubeApiResponse {

    private List<YouTubeApiItem> items;

}
