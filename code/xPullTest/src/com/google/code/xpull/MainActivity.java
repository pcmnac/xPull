package com.google.code.xpull;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.util.Xml;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;

import com.google.code.xpull.parser.ListTagParser;
import com.google.code.xpull.parser.StringTagParser;
import com.google.code.xpull.tag.ListTag;
import com.google.code.xpull.test.maven.Mirror;
import com.google.code.xpull.test.maven.MirrorTagParser;
import com.google.code.xpull.test.maven.Server;
import com.google.code.xpull.test.maven.ServerTagParser;
import com.google.code.xpull.test.rss.Image;
import com.google.code.xpull.test.rss.Item;
import com.google.code.xpull.test.rss.ItemTagParser;

public class MainActivity extends Activity
{

    private String fontSize;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Esconde a barra de status/navegação.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        double ratio = width / 640;
        NumberFormat format = new DecimalFormat("#%");
        fontSize = format.format(ratio);

        // testMavenSettings();

        testRss();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private void testRss()
    {
        AsyncTask<String, Integer, Long> at = new AsyncTask<String, Integer, Long>()
        {

            @Override
            protected Long doInBackground(String... params)
            {
                loadRss();
                return 1l;
            }

        };

        at.execute("teste");
    }

    private void loadRss()
    {

        XmlPullParser parser = Xml.newPullParser();
        InputStream is = null;
        try
        {
            URL url = new URL(
                    "http://www.terra.com.br/rss/Controller?channelid=20e07ef2795b2310VgnVCM3000009af154d0RCRD&ctName=atomo-noticia");
            URLConnection ucon = url.openConnection();
            is = ucon.getInputStream();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);
            parser.nextTag();

            List<Item> items = new ListTagParser<Item>("item", new ItemTagParser()).parse(parser);

            final List<Item> itemsWithImage = new ArrayList<Item>();
            for (Item item : items)
            {
                String imageTag = getImageUrl(item.getDescription());
                if (imageTag != null)
                {

                    final Image image = getImage(imageTag);

                    item.setImage(image);
                    itemsWithImage.add(item);
                    System.out.println(image.getUrl());
                }
            }

            runOnUiThread(new Runnable()
            {

                @Override
                public void run()
                {
                    setContentView(R.layout.terra_rss);
                    WebView webView = (WebView) findViewById(R.id.webview);
                    int index = (int) (itemsWithImage.size() * Math.random());
                    Item item = itemsWithImage.get(index);

                    View web = findViewById(R.layout.terra_rss);

                    StringBuilder html = new StringBuilder();

                    try
                    {
                        // BufferedReader reader = new BufferedReader(new
                        // InputStreamReader(new FileInputStream(
                        // Environment.getExternalStorageDirectory() +
                        // "/adplayer/template/terra_template.html"),
                        // "UTF8"));
                        BufferedReader reader = new BufferedReader(new FileReader(
                                Environment.getExternalStorageDirectory() + "/adplayer/template/terra_template.html"));
                        while (reader.ready())
                        {
                            html.append(replaceContents(reader.readLine(), item));
                        }
                    }
                    catch (FileNotFoundException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    webView.loadDataWithBaseURL(null, html.toString(), "text/html", "UTF-8", null);

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            testRss();

                        }
                    }, 10000);

                }
            });

        }
        catch (XmlPullParserException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
            }
        }
    }

    private String replaceContents(String line, Item item)
    {
        return line.replaceAll("#imageUrl#", item.getImage().getUrl()).replaceAll("#text#", item.getTitle())
                .replaceAll("#imageTitle#", item.getImage().getTitle()).replaceAll("#font-size#", fontSize);
    }

    private String getImageUrl(String description)
    {

        Pattern pattern = Pattern.compile("<img .*[\"'\\s]>");
        Matcher matcher = pattern.matcher(description);

        String group = null;

        if (matcher.find())
        {
            group = matcher.group();
        }

        return group;
    }

    private Image getImage(String imageTag)
    {

        // imageTag = "<img src='retr?d&sfd'>";
        if (imageTag.endsWith(">"))
        {
            imageTag = imageTag.replace(">", "/>");
        }
        imageTag = imageTag.replaceAll("'", "\"");
        imageTag = imageTag.replaceAll("&", "&amp;");

        Image image = new Image();

        InputStream in = new ByteArrayInputStream(imageTag.getBytes());
        XmlPullParser parser = Xml.newPullParser();
        try
        {
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();

            for (int i = 0; i < parser.getAttributeCount(); i++)
            {
                if (parser.getAttributeName(i).equals("src"))
                {
                    image.setUrl(parser.getAttributeValue(i));
                }
                else if (parser.getAttributeName(i).equals("title"))
                {
                    image.setTitle(parser.getAttributeValue(i));
                }
            }

        }
        catch (XmlPullParserException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return image;
    }

    private void testMavenSettings()
    {
        InputStream in = getResources().openRawResource(R.raw.settings);
        XmlPullParser parser = Xml.newPullParser();
        try
        {
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();

            Tag<String> offline = new Tag<String>("offline", new StringTagParser(), true);
            ListTag<Server> serverTags = new ListTag<Server>(new Tag<Server>("server", new ServerTagParser()), true);
            ListTag<Mirror> mirroTags = new ListTag<Mirror>(new Tag<Mirror>("mirror", new MirrorTagParser()));

            XPull.parse(parser, new Tag<?>[] { offline, serverTags, mirroTags });

            // Server server = new ServerTagParser().parse(parser);
            // List<Server> servers = new ListTagParser<Server>("server", new
            // ServerTagParser()).parse(parser);
            List<Server> servers = serverTags.val();

            for (Server server : servers)
            {
                Log.d("xPull", "SERVER ID: " + server.getId());
            }

        }
        catch (XmlPullParserException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}
