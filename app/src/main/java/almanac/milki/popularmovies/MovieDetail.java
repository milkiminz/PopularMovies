package almanac.milki.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetail extends AppCompatActivity {
int pos;
    String url;
    String first="https://api.themoviedb.org/3/movie/";
    String end="?api_key=",full;
    RequestQueue requestQueue;
    TextView tt,reld,agr,ovw;
    ImageView backd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tt=(TextView) findViewById(R.id.titleid);
        reld=(TextView) findViewById(R.id.reldateid);
        agr=(TextView) findViewById(R.id.avgratingid);
        ovw=(TextView) findViewById(R.id.overviewid);
        backd = (ImageView) findViewById(R.id.bd);
        Bundle extra =getIntent().getExtras();
        if(extra!=null){
            pos=Integer.parseInt(extra.getString("pos"));
            url=extra.getString("url");
            full=first+url+end;
        }
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, full,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        try
                        {
                            JSONArray results = response.getJSONArray("results");

                            JSONObject poster_image = results.getJSONObject(pos);
                            ovw.setText(poster_image.getString("overview"));
                           reld.setText(poster_image.getString("release_date"));
                            agr.setText(poster_image.getString("vote_average"));
                            tt.setText(poster_image.getString("title"));
                            Picasso.with(getApplicationContext()).load("http://image.tmdb.org/t/p/w500/"+poster_image.getString("backdrop_path")).into(backd);
                            toolbar.setTitle(poster_image.getString("title"));
                        }catch(JSONException e)
                        {

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);


    }

}
