package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {
    private static String createdAt;
    public String body;
    //public String createdAt;
    public User user;
    public long id;

    public Tweet() {}//empty constructor for parcel

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet ();
        tweet.body = jsonObject.getString("text"); //text as shown under GET in twitter api
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user")); //user has its own field, not a string
        tweet.id = jsonObject.getLong("id");
        return tweet;
    }

    //pass Jason array and return list of tweets
    public static List <Tweet> fromJsonArray (JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

    public static String getFormattedTimeStamp () {
       return TimeFormatter.getTimeDifference(createdAt);
    }

}
