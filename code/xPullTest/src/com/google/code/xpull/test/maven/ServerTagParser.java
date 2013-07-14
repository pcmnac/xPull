package com.google.code.xpull.test.maven;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.google.code.xpull.Tag;
import com.google.code.xpull.XPull;
import com.google.code.xpull.parser.BaseTagParser;
import com.google.code.xpull.tag.StringTag;

public class ServerTagParser extends BaseTagParser<Server>
{

    public ServerTagParser()
    {
        super(null);
    }

    @Override
    protected Server doParse(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        StringTag id = new StringTag("id");
        StringTag username = new StringTag("username");
        StringTag password = new StringTag("password");

        XPull.parse(parser, new Tag[] { id, username, password });

        return new Server(id.val(), username.val(), password.val());
    }

}
