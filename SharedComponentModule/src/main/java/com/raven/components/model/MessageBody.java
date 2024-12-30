package com.raven.components.model;

public class MessageBody {
    private UserDetails userDetails;
    private ArticleDetails articleDetails;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public MessageBody setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
        return this;
    }

    public ArticleDetails getArticleDetails() {
        return articleDetails;
    }

    public MessageBody setArticleDetails(ArticleDetails articleDetails) {
        this.articleDetails = articleDetails;
        return this;
    }
}
