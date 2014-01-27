package hu.karsany.feedparser;

import hu.karsany.feedparser.bean.Feed;
import hu.karsany.feedparser.exception.UnknownFeedTypeException;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author fkarsany
 */
public class FeedParserTest {

    @Test
    public void testRSS20_1() throws IOException, SAXException, ParserConfigurationException, URISyntaxException, UnknownFeedTypeException {
        Feed feed = new FeedParser().parse(new URL("http://upside.blog.hu/rss2"));
        Assert.assertEquals("UPS!DE", feed.getTitle());
        Assert.assertEquals("http://upside.blog.hu", feed.getLink());
        Assert.assertEquals("©2014 blog.hu", feed.getCopyright());
    }

    @Test
    public void testRSS_UNKNOWN_1() throws IOException, SAXException, ParserConfigurationException, URISyntaxException, UnknownFeedTypeException {
        Feed feed = new FeedParser().parse(new URL("http://www.portfolio.hu/rss/cikkek/all.xml"));
        Assert.assertEquals("Portfolio.hu Online Gazdasági Újság", feed.getTitle());
        Assert.assertEquals("http://www.portfolio.hu/", feed.getLink());
        Assert.assertEquals(null, feed.getCopyright());
    }

    @Test
    public void testAtom_1() throws IOException, URISyntaxException, ParserConfigurationException, UnknownFeedTypeException, SAXException {
        Feed feed = new FeedParser().parse(new URL("http://blog.kowalczyk.info/atom.xml"));
        Assert.assertEquals("Krzysztof Kowalczyk blog", feed.getTitle());
        Assert.assertEquals("http://blog.kowalczyk.info/atom.xml", feed.getLink());
    }

    @Test
    public void testAtom_2() throws IOException, URISyntaxException, ParserConfigurationException, UnknownFeedTypeException, SAXException {
        Feed feed = new FeedParser().parse(new URL("http://digitalnatives.blog.hu/atom"));
        Assert.assertEquals("DiNa blog", feed.getTitle());
        Assert.assertEquals("http://digitalnatives.blog.hu", feed.getLink());
    }

}
