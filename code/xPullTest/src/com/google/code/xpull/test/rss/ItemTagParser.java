package com.google.code.xpull.test.rss;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.google.code.xpull.Tag;
import com.google.code.xpull.XPull;
import com.google.code.xpull.parser.BaseTagParser;
import com.google.code.xpull.tag.StringTag;

public class ItemTagParser extends BaseTagParser<Item>
{

    public ItemTagParser()
    {
        super(null);
    }

    @Override
    protected Item doParse(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        StringTag title = new StringTag("title");
        StringTag description = new StringTag("description");

        XPull.parse(parser, new Tag<?>[] { title, description });

        return new Item(title.val(), description.val());
    }

}
