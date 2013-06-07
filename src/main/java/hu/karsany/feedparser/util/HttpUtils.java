package hu.karsany.feedparser.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public final class HttpUtils {

    private HttpUtils() {

    }

    public static InputStream fetchPage(String url) throws IOException {
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        method.setFollowRedirects(true);
        client.executeMethod(method);
        InputStream responseBodyAsStream = method.getResponseBodyAsStream();
        return responseBodyAsStream;
    }

    public static Document openXMLDocument(String url) throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = HttpUtils.fetchPage(url);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputStream);
        doc.getDocumentElement().normalize();
        return doc;
    }

}
