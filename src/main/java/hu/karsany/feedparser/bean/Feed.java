package hu.karsany.feedparser.bean;

import java.util.List;

/**
 * @author fkarsany
 */
public class Feed {

    private String title = null;
    private String link = null;
    private String copyright = null;
    private List<FeedItem> items = null;

    public Feed() {
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<FeedItem> getItems() {
        return items;
    }

    public void setItems(List<FeedItem> items) {
        this.items = items;
    }
}
