package hu.karsany.feedparser;

import hu.karsany.feedparser.util.FeedDateUtils;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: fkarsany
 * Date: 2013.06.07.
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class FeedDateUtilsTest {

    private void testIt(String tt) {
        FeedDateUtils.parse(tt);
    }

    @Test
    public void test1() {
        testIt("Tue, 29 May 2012 12:48:15 +0200");
    }

}
