package com.raven.components.model;

public record ArticleDetails(
        String length,
        String publicationDate,
        Boolean isClapped,
        String clappedBy,
        Integer readTime,
        String timeUnit
) {
}
