package com.google.code.xpull.util;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ParsingUtils
{
    public static boolean findNextStartTag(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        return findNextStartTag(parser, parser.getDepth());
    }

    public static boolean findNextStartTag(XmlPullParser parser, int endDepth) throws XmlPullParserException,
            IOException
    {
        boolean found = false;

        if (parser.getEventType() != XmlPullParser.END_DOCUMENT)
        {
            do
            {
                parser.next();
                if (parser.getEventType() == XmlPullParser.TEXT && parser.isWhitespace())
                {
                    parser.next();
                }
                found = parser.getEventType() == XmlPullParser.START_TAG;
            }
            while ((!found) && (parser.getDepth() > endDepth));
        }

        return found;
    }

}
