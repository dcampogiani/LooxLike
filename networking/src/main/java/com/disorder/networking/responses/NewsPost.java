package com.disorder.networking.responses;


public class NewsPost {

    private final long postId;
    private final String description;
    private final String photoUrl;
    private final String c10;
    private final String creationTime;
    private final String userName;
    private final int numLikes;
    private final boolean liked;

    public NewsPost(long postId, String description, String photoUrl, String c10, String creationTime, String userName, int numLikes, boolean liked) {
        this.postId = postId;
        this.description = description;
        this.photoUrl = photoUrl;
        this.c10 = c10;
        this.creationTime = creationTime;
        this.userName = userName;
        this.numLikes = numLikes;
        this.liked = liked;
    }

    public long getPostId() {
        return postId;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getC10() {
        return c10;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public boolean isLiked() {
        return liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsPost newsPost = (NewsPost) o;

        if (postId != newsPost.postId) return false;
        if (numLikes != newsPost.numLikes) return false;
        if (liked != newsPost.liked) return false;
        if (!description.equals(newsPost.description)) return false;
        if (!photoUrl.equals(newsPost.photoUrl)) return false;
        if (!c10.equals(newsPost.c10)) return false;
        //noinspection SimplifiableIfStatement
        if (!creationTime.equals(newsPost.creationTime)) return false;
        return userName.equals(newsPost.userName);

    }

    @Override
    public int hashCode() {
        int result = (int) (postId ^ (postId >>> 32));
        result = 31 * result + description.hashCode();
        result = 31 * result + photoUrl.hashCode();
        result = 31 * result + c10.hashCode();
        result = 31 * result + creationTime.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + numLikes;
        result = 31 * result + (liked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NewsPost{" +
                "postId=" + postId +
                ", description='" + description + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", c10='" + c10 + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", userName='" + userName + '\'' +
                ", numLikes=" + numLikes +
                ", liked=" + liked +
                '}';
    }
}

