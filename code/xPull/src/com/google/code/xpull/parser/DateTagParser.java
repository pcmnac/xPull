package com.google.code.xpull.parser;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.code.xpull.ITagHandler;

@SuppressLint("SimpleDateFormat")
public class DateTagParser extends BaseTagParser<Date>
{

    private DateFormat dateFormat;

    public DateTagParser()
    {
        this("yyyy-MM-dd");
    }

    public DateTagParser(ITagHandler<Date> handler)
    {
        this(handler, "yyyy-MM-dd");
    }

    public DateTagParser(String datePattern)
    {
        this(null, datePattern);
    }

    public DateTagParser(DateFormat dateFormat)
    {
        this(null, dateFormat);
    }

    public DateTagParser(ITagHandler<Date> handler, String datePattern)
    {
        super(handler);
        dateFormat = new SimpleDateFormat(datePattern);
    }

    public DateTagParser(ITagHandler<Date> handler, DateFormat dateFormat)
    {
        super(handler);
        setDateFormat(dateFormat);
    }

    public DateFormat getDateFormat()
    {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat)
    {
        this.dateFormat = dateFormat;
    }

    @Override
    protected Date doParse(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        Date date = null;

        try
        {
            String dateText = parser.nextText();
            Log.d("xPull", "date text: " + dateText);
            date = dateFormat.parse(dateText);
            Log.d("xPull", "parsed date: " + date);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }

        return date;
    }

}
