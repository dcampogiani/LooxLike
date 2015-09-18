package com.disorder.networking.responses;


public class NewsPost {

    private long id;
    private String photoUrl;
    private String description;
    private String itemId;
    private int likes;
    private String username;
    private boolean liked;

    public NewsPost(long id, String photoUrl, String description, String itemId, int likes, String username, boolean liked) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.description = description;
        this.itemId = itemId;
        this.likes = likes;
        this.username = username;
        this.liked = liked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public String toString() {
        return "NewsPost{" +
                "id=" + id +
                ", photoUrl='" + photoUrl + '\'' +
                ", description='" + description + '\'' +
                ", itemId='" + itemId + '\'' +
                ", likes=" + likes +
                ", username='" + username + '\'' +
                ", liked=" + liked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsPost newsPost = (NewsPost) o;

        if (id != newsPost.id) return false;
        if (likes != newsPost.likes) return false;
        if (liked != newsPost.liked) return false;
        if (!photoUrl.equals(newsPost.photoUrl)) return false;
        if (!description.equals(newsPost.description)) return false;
        if (!itemId.equals(newsPost.itemId)) return false;
        return username.equals(newsPost.username);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + photoUrl.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + itemId.hashCode();
        result = 31 * result + likes;
        result = 31 * result + username.hashCode();
        result = 31 * result + (liked ? 1 : 0);
        return result;
    }
}

