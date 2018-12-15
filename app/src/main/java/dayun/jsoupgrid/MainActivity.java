package dayun.jsoupgrid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    GridView gridView;
    Activity act = this;
    ArrayList<String> imageArray = new ArrayList<String>();
    ArrayList<String> hrefArray = new ArrayList<String>();
    private String htmlPageUrl = "http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx/";


    Button btn1, btn2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridViewResult);
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();

    }


        private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

            Bitmap bitmap;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Document doc = Jsoup.connect(htmlPageUrl).get();
                    Elements links = doc.select("[src]");

                    for (Element src : links) {
                        if (src.tagName().equals("img")) {
                            hrefArray.add(src.attr("abs:src"));
                        }
                    }
                    for (int i = 0; i < hrefArray.size(); i++) {
                        String str = hrefArray.get(i);
                        if(str.startsWith("https://"))
                            str = str.replace("https://","http://");
                        if(str!=null)
                        imageArray.add(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                gridView.setAdapter(new GridAdapter(act,imageArray));
                Toast.makeText(MainActivity.this, "이미지 출력", Toast.LENGTH_LONG).show();


            }
        }

    }

