package com.disorder.networking.responses;


public class NewsPost {

    private final long idPost;
    private final String description;
    private final String photoUrl;
    private final String c10;
    private final String creationDate;
    private final String username;
    private final int nLikes;
    private final boolean liked;

    public NewsPost(long idPost, String description, String photoUrl, String c10, String creationDate, String username, int nLikes, boolean liked) {
        this.idPost = idPost;
        this.description = description;
        this.photoUrl = photoUrl;
        this.c10 = c10;
        this.creationDate = creationDate;
        this.username = username;
        this.nLikes = nLikes;
        this.liked = liked;
    }

    public long getIdPost() {
        return idPost;
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

    public String getCreationDate() {
        return creationDate;
    }

    public String getUsername() {
        return username;
    }

    public int getnLikes() {
        return nLikes;
    }

    public boolean isLiked() {
        return liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsPost newsPost = (NewsPost) o;

        if (idPost != newsPost.idPost) return false;
        if (nLikes != newsPost.nLikes) return false;
        if (liked != newsPost.liked) return false;
        if (!description.equals(newsPost.description)) return false;
        if (!photoUrl.equals(newsPost.photoUrl)) return false;
        if (!c10.equals(newsPost.c10)) return false;
        //noinspection SimplifiableIfStatement
        if (!creationDate.equals(newsPost.creationDate)) return false;
        return username.equals(newsPost.username);

    }

    @Override
    public int hashCode() {
        int result = (int) (idPost ^ (idPost >>> 32));
        result = 31 * result + description.hashCode();
        result = 31 * result + photoUrl.hashCode();
        result = 31 * result + c10.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + nLikes;
        result = 31 * result + (liked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NewsPost{" +
                "idPost=" + idPost +
                ", description='" + description + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", c10='" + c10 + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", username='" + username + '\'' +
                ", nLikes=" + nLikes +
                ", liked=" + liked +
                '}';
    }
}

