package com.af.upworktasks.ui.fragments.YoutubePlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubePlayerViewModel extends ViewModel {

    private MutableLiveData<String> mutableLiveData = new MutableLiveData<>();


    public LiveData<String> getStringVideo(String url){
       return getYouTubeId(url);
    }


    private MutableLiveData<String> getYouTubeId (String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
             mutableLiveData.postValue(matcher.group());
        } else {
             mutableLiveData.postValue("error");
        }

        return mutableLiveData;

    }



}
