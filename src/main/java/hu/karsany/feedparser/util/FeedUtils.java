package hu.karsany.feedparser.util;

import org.w3c.dom.Document;

public final class FeedUtils {

    public static final String RSS20 = "RSS2.0";
    public static final String RSS_UNKNOWN = "RSS-UNKNOWN";
    public static final String ATOM = "ATOM";

    private FeedUtils() {
    }

    public static String getFeedType(Document document) {
        String rootNodeName = document.getDocumentElement().getNodeName();
        if (rootNodeName.equals("rss")) {
            String rssV = "RSS" + document.getDocumentElement().getAttribute("version");
            if (!rssV.equals(RSS20)) {
                rssV = RSS_UNKNOWN;
            }
            return rssV;
        } else if (rootNodeName.equals("feed")) {
            return "ATOM";
        } else {
            return "UNKNOWN";
        }
    }

}
