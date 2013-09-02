package hu.karsany.feedparser.util;


import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

import java.util.Date;

public final class FeedDateUtils {

    private FeedDateUtils() {
    }

    public static Date parse(String date) {
        boolean success = false;
        Date parseDate = null;
        try {
            parseDate = DateUtils.parseDate(date, new String[]{
                    DateUtils.PATTERN_RFC1123,
                    DateUtils.PATTERN_RFC1036,
                    DateUtils.PATTERN_ASCTIME,
                    "EEE, dd-MMM-yyyy HH:mm:ss z",
                    "EEE, dd-MMM-yyyy HH-mm-ss z",
                    "EEE, dd MMM yy HH:mm:ss z",
                    "EEE dd-MMM-yyyy HH:mm:ss z",
                    "EEE dd MMM yyyy HH:mm:ss z",
                    "EEE dd-MMM-yyyy HH-mm-ss z",
                    "EEE dd-MMM-yy HH:mm:ss z",
                    "EEE dd MMM yy HH:mm:ss z",
                    "EEE, dd MMM yy HH:mm:ss z",
                    "EEE,dd-MMM-yy HH:mm:ss z",
                    "EEE,dd-MMM-yyyy HH:mm:ss z",
                    "EEE, dd-MM-yyyy HH:mm:ss z",
                    "dd MMM yyyy HH:mm:ss zzz",
                    "MMM dd, yyyy KK:mm:ss aa zzz",
                    "yyyy-MM-dd'T'HH:mm:ssz",
                    "yyyy-MM-dd'T'HH:mm:ss.SSSz",
                    "yyyy-MM-dd'T'HH:mm:ss.SSz"
            });
            success = true;
        } catch (DateParseException e) {
        }
        if (!success) {
            try {
                String transfmdDate;
                if (date.charAt(19) == '.') {
                    transfmdDate = date.substring(0, 22) + "GMT" + date.substring(23);
                } else {
                    transfmdDate = date.substring(0, 19) + "GMT" + date.substring(20);
                }

                parseDate = DateUtils.parseDate(transfmdDate, new String[]{"yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd'T'HH:mm:ss.SSz"});
            } catch (DateParseException e) {
                throw new RuntimeException("FeedDateUtils: Error converting date: " + date, e);
            }
        }
        return parseDate;
    }
}
