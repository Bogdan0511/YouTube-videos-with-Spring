package com.googleIntegration.YoutubeAPI.search;

public record SearchResult(String kind, String etag, SearchId id, SearchSnippet snippet) {
}
