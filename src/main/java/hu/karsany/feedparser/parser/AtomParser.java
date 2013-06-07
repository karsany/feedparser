package hu.karsany.feedparser.parser;

import hu.karsany.feedparser.bean.Feed;
import hu.karsany.feedparser.bean.FeedItem;
import hu.karsany.feedparser.parser.ParserInterface;
import hu.karsany.feedparser.util.FeedDateUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: fkarsany
 * Date: 2013.06.07.
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public class AtomParser implements ParserInterface {

    private Document document;

    public AtomParser(Document document) {
        this.document = document;
    }

    @Override
    public Feed parse() {
        Feed feed = new Feed();
        feed.setItems(new ArrayList<FeedItem>());

        NodeList childNodes = document.getDocumentElement().getChildNodes();

        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            String nodeName = childNodes.item(i).getNodeName();
            String nodeValue = childNodes.item(i).getTextContent();
            NodeList itemChilds = childNodes.item(i).getChildNodes();

            if (nodeName.equals("title")) {
                feed.setTitle(nodeValue);
            }
            if (nodeName.equals("link")) {
                feed.setLink(childNodes.item(i).getAttributes().getNamedItem("href").getNodeValue());
            }
            if (nodeName.equals("rights")) {
                feed.setCopyright(nodeValue);
            }
            if (nodeName.equals("entry")) {
                feed.getItems().add(parseNodeList(itemChilds));
            }

        }

        return feed;
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
                feedItem.setLink(nodeList.item(i).getAttributes().getNamedItem("href").getNodeValue());
            }
            if (nodeName.equals("published")) {
                feedItem.setPubDate(FeedDateUtils.parse(nodeValue));
            }
            if (feedItem.getPubDate() == null && nodeName.equals("updated")) {
                feedItem.setPubDate(FeedDateUtils.parse(nodeValue));
            }
            if (nodeName.equals("content")) {
                feedItem.setDescription(nodeValue);
            }
            if (feedItem.getDescription() == null && nodeName.equals("summary")) {
                feedItem.setDescription(nodeValue);
            }
            if (nodeName.equals("author")) {
                feedItem.setAuthor(nodeValue);
            }
        }
        return feedItem;
    }
}