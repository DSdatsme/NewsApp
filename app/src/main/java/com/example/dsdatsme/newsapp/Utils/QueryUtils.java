package com.example.dsdatsme.newsapp.Utils;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /**
     * Sample JSON response for a USGS query
     */
    private static final String SAMPLE_JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":13098,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1310,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"politics/2018/jun/08/theresa-may-enters-northern-ireland-abortion-debate\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-06-08T15:06:45Z\",\"webTitle\":\"Theresa May enters Northern Ireland abortion debate\",\"webUrl\":\"https://www.theguardian.com/politics/2018/jun/08/theresa-may-enters-northern-ireland-abortion-debate\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/jun/08/theresa-may-enters-northern-ireland-abortion-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"news/2018/apr/26/putting-the-antisemitism-debate-in-perspective\",\"type\":\"article\",\"sectionId\":\"news\",\"sectionName\":\"News\",\"webPublicationDate\":\"2018-04-26T16:31:31Z\",\"webTitle\":\"Putting the antisemitism debate in perspective | Letters\",\"webUrl\":\"https://www.theguardian.com/news/2018/apr/26/putting-the-antisemitism-debate-in-perspective\",\"apiUrl\":\"https://content.guardianapis.com/news/2018/apr/26/putting-the-antisemitism-debate-in-perspective\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/mar/13/childcare-voucher-changes-delayed-after-commons-debate\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-03-13T19:53:59Z\",\"webTitle\":\"Childcare voucher changes delayed after Commons debate\",\"webUrl\":\"https://www.theguardian.com/politics/2018/mar/13/childcare-voucher-changes-delayed-after-commons-debate\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/mar/13/childcare-voucher-changes-delayed-after-commons-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"business/2018/may/29/brexit-disaster-economic-data-uk-eu\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2018-05-29T11:57:48Z\",\"webTitle\":\"'Brexit is a disaster' –  experts debate the latest economic data\",\"webUrl\":\"https://www.theguardian.com/business/2018/may/29/brexit-disaster-economic-data-uk-eu\",\"apiUrl\":\"https://content.guardianapis.com/business/2018/may/29/brexit-disaster-economic-data-uk-eu\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/may/22/brexit-weekly-briefing-irish-border-problem-dominates-debate\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-05-22T05:00:47Z\",\"webTitle\":\"Brexit weekly briefing: Irish border problem dominates debate\",\"webUrl\":\"https://www.theguardian.com/politics/2018/may/22/brexit-weekly-briefing-irish-border-problem-dominates-debate\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/may/22/brexit-weekly-briefing-irish-border-problem-dominates-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/apr/19/tessa-jowell-visits-parliament-cancer-debate\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-04-19T16:21:14Z\",\"webTitle\":\"Tessa Jowell hailed as an inspiration during MPs' cancer debate\",\"webUrl\":\"https://www.theguardian.com/politics/2018/apr/19/tessa-jowell-visits-parliament-cancer-debate\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/apr/19/tessa-jowell-visits-parliament-cancer-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2018/apr/13/may-urged-to-clear-mondays-commons-diary-for-syria-debate\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-04-13T14:30:31Z\",\"webTitle\":\"May urged to clear Monday's Commons diary for Syria debate\",\"webUrl\":\"https://www.theguardian.com/world/2018/apr/13/may-urged-to-clear-mondays-commons-diary-for-syria-debate\",\"apiUrl\":\"https://content.guardianapis.com/world/2018/apr/13/may-urged-to-clear-mondays-commons-diary-for-syria-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/mar/23/should-labour-back-a-second-brexit-referendum-our-readers-debate\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-03-23T12:31:54Z\",\"webTitle\":\"Should Labour back a second Brexit referendum? Our readers debate\",\"webUrl\":\"https://www.theguardian.com/politics/2018/mar/23/should-labour-back-a-second-brexit-referendum-our-readers-debate\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/mar/23/should-labour-back-a-second-brexit-referendum-our-readers-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/feb/11/brexit-john-mcdonnell-democratic-engagement-second-eu-referendum\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-02-11T12:22:47Z\",\"webTitle\":\"McDonnell: EU debate could still lead to election or referendum\",\"webUrl\":\"https://www.theguardian.com/politics/2018/feb/11/brexit-john-mcdonnell-democratic-engagement-second-eu-referendum\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/feb/11/brexit-john-mcdonnell-democratic-engagement-second-eu-referendum\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/jun/13/snp-mps-walk-out-of-commons-in-protest-over-brexit-debate\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-06-13T23:00:58Z\",\"webTitle\":\"SNP MPs walk out of Commons in protest over Brexit debate\",\"webUrl\":\"https://www.theguardian.com/politics/2018/jun/13/snp-mps-walk-out-of-commons-in-protest-over-brexit-debate\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/jun/13/snp-mps-walk-out-of-commons-in-protest-over-brexit-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link NewsStructure} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<NewsStructure> extractNews() {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<NewsStructure> newsList = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONObject response = baseJsonResponse.getJSONObject("response");
            JSONArray resultsArray = response.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject currentNews = resultsArray.getJSONObject(i);
                String type = currentNews.getString("type");
                String section = currentNews.getString("sectionName");
                String date = currentNews.getString("webPublicationDate");

                //Date dateObject = new Date(date);
                SimpleDateFormat mdate = new SimpleDateFormat("MMM d,yyyy  hh:mm a z");
                //date = mdate.format(dateObject);
                Date parsedDate = null;
                try {
                    parsedDate = mdate.parse(date);
                    date = parsedDate.toString();
                } catch (ParseException e) {
                    e.printStackTrace();
                }



                String title = currentNews.getString("webTitle");
                NewsStructure news = new NewsStructure(type, section, date, title);
                newsList.add(news);
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return newsList;
    }

}