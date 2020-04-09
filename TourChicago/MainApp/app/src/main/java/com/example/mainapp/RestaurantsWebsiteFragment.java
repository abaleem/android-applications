package com.example.mainapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

public class RestaurantsWebsiteFragment extends Fragment {

    private WebView browser;
    private int mCurrIdx = -1;

    int getShownIndex() {
        return mCurrIdx;
    }

    void showWebPageAtIndex(int newIndex) {
        mCurrIdx = newIndex;
        browser.loadUrl(RestaurantsActivity.websites[mCurrIdx]);
    }

    void refreshWebPage(){
        if(mCurrIdx > 0){
            browser.loadUrl(RestaurantsActivity.websites[mCurrIdx]);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        browser = getActivity().findViewById(R.id.browserResturants);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.resturants_webview, container, false);    // Inflates attractions_webview.xml_webview.xml
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);    // retains the state of fragment when orientation changed.
    }
}
