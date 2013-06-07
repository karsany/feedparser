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

    private FeedParser() {
    }

    public static Feed parse(URL url) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, UnknownFeedTypeException {
        Document document = HttpUtils.openXMLDocument(url.toString());
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
}
