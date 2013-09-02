package hu.karsany.feedparser.util;

import org.apache.commons.httpclient.HostConfiguration;
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

    private String url;
    private String proxyHost;
    private int proxyPort;

    public HttpUtils(String url, String proxyHost, int proxyPort) {
        this.url = url;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    public HttpUtils(String url) {
        this.url = url;
        this.proxyHost = null;
        this.proxyPort = -1;
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

    public String getUrl() {
        return url;

    }

    public void setUrl(String url) {
        this.url = url;
    }

    private InputStream fetchPage() throws IOException {
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        HostConfiguration config = client.getHostConfiguration();
        if (proxyHost != null && proxyPort != -1) {
            config.setProxy("127.0.0.1", 3128); // @TODO
        }
        client.executeMethod(method);
        InputStream responseBodyAsStream = method.getResponseBodyAsStream();
        return responseBodyAsStream;
    }

    public Document openXMLDocument() throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = fetchPage();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputStream);
        doc.getDocumentElement().normalize();
        return doc;
    }

}
