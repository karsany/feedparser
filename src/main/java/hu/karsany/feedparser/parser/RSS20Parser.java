package hu.karsany.feedparser.parser;

import hu.karsany.feedparser.bean.Feed;
import hu.karsany.feedparser.bean.FeedItem;
import hu.karsany.feedparser.util.FeedDateUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class RSS20Parser implements ParserInterface {

    private Document document;

    public RSS20Parser(Document document) {
        this.document = document;
    }

    private FeedItem parseNodeList(NodeList nodeList) {
        FeedItem feedItem = new FeedItem();
        int length = nodeList.getLength();
        for (int i = 0; i < length; i++) {
            String nodeName = nodeList.item(i).getNodeName();
            String nodeValue = nodeList.item(i).getTextContent();

            if (nodeName.equals("title")) {
                feedItem.setTitle(nodeValue);
            }
            if (nodeName.equals("link")) {
                feedItem.setLink(nodeValue);
            }
            if (nodeName.equals("pubDate")) {
                feedItem.setPubDate(FeedDateUtils.parse(nodeValue));
            }
            if (nodeName.equals("description")) {
                feedItem.setDescription(nodeValue);
            }
            if (feedItem.getDescription() == null && nodeName.equals("content:encoded")) {
                feedItem.setDescription(nodeValue);
            }
            if (nodeName.equals("dc:creator")) {
                feedItem.setAuthor(nodeValue);
            }
        }
        return feedItem;
    }

    @Override
    public Feed parse() {

        Feed feed = new Feed();
        feed.setItems(new ArrayList<FeedItem>());

        NodeList channel = document.getDocumentElement().getElementsByTagName("channel");
        NodeList childNodes = channel.item(0).getChildNodes();

        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            String nodeName = childNodes.item(i).getNodeName();
            String nodeValue = childNodes.item(i).getTextContent();
            NodeList itemChilds = childNodes.item(i).getChildNodes();

            //System.out.println(nodeName + " == " + nodeValue);

            if (nodeName.equals("title")) {
                feed.setTitle(nodeValue);
            }
            if (nodeName.equals("link")) {
                feed.setLink(nodeValue);
            }
            if (nodeName.equals("copyright")) {
                feed.setCopyright(nodeValue);
            }
            if (nodeName.equals("item")) {
                feed.getItems().add(parseNodeList(itemChilds));
            }

        }

        return feed;
    }
}
