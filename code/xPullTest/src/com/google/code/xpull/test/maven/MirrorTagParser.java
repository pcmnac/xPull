package com.google.code.xpull.test.maven;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.google.code.xpull.Tag;
import com.google.code.xpull.XPull;
import com.google.code.xpull.parser.BaseTagParser;
import com.google.code.xpull.tag.StringTag;

public class MirrorTagParser extends BaseTagParser<Mirror>
{

    @Override
    protected Mirror doParse(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        StringTag id = new StringTag("id", true);
        StringTag name = new StringTag("name");
        StringTag url = new StringTag("url", true);
        StringTag mirrorOf = new StringTag("mirrorOf", true);

        XPull.parse(parser, new Tag[] { id, name, url, mirrorOf });

        return new Mirror(id.val(), name.val(), url.val(), mirrorOf.val());
    }

}
