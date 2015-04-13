package ar.com.wolox.recycler.testclass;

import ar.com.wolox.recycler.simpleRecyclerLibrary.entities.SimpleRecyclerItemInterface;

public class RowNews implements SimpleRecyclerItemInterface<RowNews> {

    private String title;
    private String content;
    private String imageUrl;
    private boolean like;
    private String date;

    public RowNews(String title, String content, String imageUrl, boolean like, String date) {

        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.like = like;
        this.date = date;
    }

    @Override
    public RowNews create() {
        return new RowNews("default", "default", "default", false, getDate());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
