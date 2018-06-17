package com.example.dsdatsme.newsapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dsdatsme.newsapp.R;
import com.example.dsdatsme.newsapp.Utils.NewsAdapter;
import com.example.dsdatsme.newsapp.Utils.NewsStructure;
import com.example.dsdatsme.newsapp.Utils.QueryUtils;

import java.util.ArrayList;

public class News extends AppCompatActivity {

    //for log messages
    public static final String LOG_TAG = News.class.getSimpleName();

    //URL for news
    private static final String GURDIAN_REQUEST_URL_1 = "http://content.guardianapis.com/search?q=debates&api-key=test";
    private static final String GURDIAN_REQUEST_URL_2 = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a fake list of earthquakes.
        ArrayList<NewsStructure> news = QueryUtils.extractNews();
       /* ArrayList<NewsStructure> news = new ArrayList<>();
        news.add(new NewsStructure("San Francisco","asdasc","asd","hdahcaochapcnapoc"));
        news.add(new NewsStructure("San Francisco","asdasc","asd","hdahcaochapcnapoc"));
        news.add(new NewsStructure("San Francisco","asdasc","asd","hdahcaochapcnapoc"));
        news.add(new NewsStructure("San Francisco","asdasc","asd","hdahcaochapcnapoc"));
        news.add(new NewsStructure("San Francisco","asdasc","asd","hdahcaochapcnapoc"));
        news.add(new NewsStructure("San Francisco","asdasc","asd","hdahcaochapcnapoc"));
*/
        ListView newsList = findViewById(R.id.list);
        NewsAdapter adapter = new NewsAdapter(this,news);
        newsList.setAdapter(adapter);




















        //NewsAsyncTask task = new NewsAsyncTask();
        //task.execute();
    }// end of onCreate()

   /* private void updateUi(NewsStructure mNews){
        TextView mType, mSection, mDate, mTitle;

        mType = (TextView)findViewById(R.id.news_type);
        mType.setText(mNews.newsType);

        mSection = findViewById(R.id.news_section);
        mSection.setText(mNews.newsSection);

        mDate = findViewById(R.id.news_date);
        mDate.setText(mNews.newsDate);

        mTitle = findViewById(R.id.news_title);
        mTitle.setText(mNews.newsTitle);
    }



    private class NewsAsyncTask extends AsyncTask<URL, Void, UsageEvents.Event> {


        @Override
        protected UsageEvents.Event doInBackground(URL... urls) {
            URL url = createUrl(GURDIAN_REQUEST_URL_1);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }


            NewsStructure mNews = extractFeatureFromJson(jsonResponse);
            return mNews;
        }

        @Override
        protected void onPostExecute(UsageEvents.Event event) {
            if(event == null)
                return;
            updateUi(event);
        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 );
                urlConnection.setConnectTimeout(15000 );
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }
    }*/
}
