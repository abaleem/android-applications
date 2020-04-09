package com.example.mainapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class RestaurantsTitleFragment extends ListFragment {

    private ListSelectionListener mListener = null;

    // Callback interface that allows Fragment to notify when clicks on a List Item
    public interface ListSelectionListener {
        void onListSelection(int index);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        getListView().setItemChecked(pos, true);
        mListener.onListSelection(pos);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ListSelectionListener) context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<>(getActivity(), R.layout.resturants_title_item, RestaurantsActivity.titles));
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
