package hu.karsany.feedparser;

import hu.karsany.feedparser.util.FeedUtils;
import hu.karsany.feedparser.util.HttpUtils;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: fkarsany
 * Date: 2013.06.07.
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public class FeedTypeTest {

    @Test
    public void testRSS20() throws IOException, SAXException, ParserConfigurationException, URISyntaxException {
        String feedType = FeedUtils.getFeedType(new HttpUtils("http://upside.blog.hu/rss2").openXMLDocument());
        Assert.assertEquals("RSS2.0", feedType);
    }

    @Test
    public void testRSS_UNKNOWN() throws IOException, SAXException, ParserConfigurationException, URISyntaxException {
        String feedType = FeedUtils.getFeedType(new HttpUtils("http://www.portfolio.hu/rss/cikkek/all.xml").openXMLDocument());
        Assert.assertEquals("RSS-UNKNOWN", feedType);
    }

    @Test
    public void testAtom() throws IOException, SAXException, ParserConfigurationException {
        String feedType = FeedUtils.getFeedType(new HttpUtils("http://upside.blog.hu/atom").openXMLDocument());
        Assert.assertEquals("ATOM", feedType);
    }

}
