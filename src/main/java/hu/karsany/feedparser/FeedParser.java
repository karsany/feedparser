package hu.karsany.feedparser;

import hu.karsany.feedparser.bean.Feed;
import hu.karsany.feedparser.exception.UnknownFeedTypeException;
import hu.karsany.feedparser.parser.AtomParser;
import hu.karsany.feedparser.parser.ParserInterface;
import hu.karsany.feedparser.parser.RSS20Parser;
import hu.karsany.feedparser.util.FeedUtils;
import hu.karsany.feedparser.util.HttpUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author fkarsany
 */
public final class FeedParser {

    private String proxyHost;
    private int proxyPort;

    public FeedParser(String proxyHost, int proxyPort) {

        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    public FeedParser() {
        proxyHost = null;
        proxyPort = -1;

    }

    public Feed parse(URL url) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, UnknownFeedTypeException {

        HttpUtils httpu = new HttpUtils(url.toString());

        if (this.proxyPort != -1 && this.proxyHost != null) {
            httpu.setProxyHost(proxyHost);
            httpu.setProxyPort(proxyPort);
        }

        Document document = httpu.openXMLDocument();
        String feedType = FeedUtils.getFeedType(document);

        ParserInterface parser;

        if (feedType.equals(FeedUtils.RSS20)) {
            parser = new RSS20Parser(document);
        } else if (feedType.equals(FeedUtils.ATOM)) {
            parser = new AtomParser(document);
        } else {
            throw new UnknownFeedTypeException();
        }

        return parser.parse();
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }
}
