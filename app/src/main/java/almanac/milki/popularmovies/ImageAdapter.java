package almanac.milki.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

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

/**
 * Created by 1415019 on 29-04-2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    RequestQueue requestQueue;
String s1="https://api.themoviedb.org/3/movie/",add1;
    String s2="?api_key=a9921873d439557b59941c8130548062",fullurl;
    String[] add2=new String[40];
     public void json(String s){
         fullurl=s1+s+s2;
         JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, fullurl,
                 new Response.Listener<JSONObject>() {

                     @Override
                     public void onResponse(JSONObject response) {


                         try
                         {
                             JSONArray results = response.getJSONArray("results");

                             for(int i=0;i<results.length();i++) {
                                 JSONObject poster_image = results.getJSONObject(i);

                                 add1 = poster_image.getString("poster_path");
                                 add2[i]="http://image.tmdb.org/t/p/w185"+add1;


                             }
                         }catch(JSONException e)
                         {
                             e.printStackTrace();
                         }

                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {

             }
         });
         requestQueue.add(jsonObjectRequest);
     }
    public ImageAdapter(Context c,String d) {

        mContext = c;
        requestQueue = Volley.newRequestQueue(mContext);
        json(d);
    }

    public int getCount() {
        return 20;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(1440, 1100));


        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext).load(add2[position]).into(imageView);
        return imageView;
    }

    // references to our images

}