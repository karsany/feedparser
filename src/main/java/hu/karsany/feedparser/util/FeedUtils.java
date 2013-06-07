package hu.karsany.feedparser.util;

import org.w3c.dom.Document;

public final class FeedUtils {

    public static final String RSS20 = "RSS2.0";
    public static final String ATOM = "ATOM";

    private FeedUtils() {
    }

    public static String getFeedType(Document document) {
        String rootNodeName = document.getDocumentElement().getNodeName();
        if (rootNodeName.equals("rss")) {
            return "RSS" + document.getDocumentElement().getAttribute("version");
        } else if (rootNodeName.equals("feed")) {
            return "ATOM";
        } else {
            return "UNKNOWN";
        }
    }

}
