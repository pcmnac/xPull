package com.google.code.xpull;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.google.code.xpull.util.ParsingUtils;

public class XPull
{

    public static void parse(XmlPullParser parser, Tag<?> tag) throws XmlPullParserException, IOException
    {
        parse(parser, new Tag<?>[] { tag });
    }

    public static void parse(XmlPullParser parser, Tag<?>[] tags) throws XmlPullParserException, IOException
    {

        if (parser.getEventType() != XmlPullParser.START_TAG)
        {
            throw new XmlPullParserException("START TAG expected");
        }

        int startSearchIndex = 0;
        int endSearchIndex = getEndSearchIndex(tags, startSearchIndex);

        int depth = parser.getDepth();
        do
        {

            if (parser.getEventType() == XmlPullParser.START_TAG)
            {
                for (int i = startSearchIndex; i <= endSearchIndex; i++)
                {
                    Tag<?> tag = tags[i];
                    if (parser.getName().equals(tag.getName()))
                    {
                        tag.parse(parser);

                        startSearchIndex = i + 1;
                        endSearchIndex = getEndSearchIndex(tags, startSearchIndex);

                        break;
                    }
                }
            }

            ParsingUtils.findNextStartTag(parser);

        }
        while (parser.getDepth() > depth);

        for (int i = startSearchIndex; i < tags.length; i++)
        {
            if (tags[i].isRequired())
            {
                throw new RuntimeException("Missing required tag: " + tags[i].getName());
            }
        }

    }

    private static int getEndSearchIndex(Tag<?>[] tags, int start)
    {
        int result = tags.length - 1;

        for (int i = start; i < tags.length; i++)
        {
            if (tags[i].isRequired())
            {
                result = i;
                break;
            }
        }

        return result;
    }

}
