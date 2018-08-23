package com.fastbooster.android_teamwith;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.fastbooster.android_teamwith.adapter.PologPortfoliListAdapter;
import com.fastbooster.android_teamwith.dummy.DummyContent;
import com.fastbooster.android_teamwith.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;


public class PortfolioFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PortfolioFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PortfolioFragment newInstance(int columnCount) {
        PortfolioFragment fragment = new PortfolioFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        FrameLayout fl=(FrameLayout)inflater.inflate(R.layout.fragment_portfolio,container,false);
        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);
        List<String> data=new ArrayList<String>();
        for(int i=0;i<100;i++){
            data.add(Integer.toString(i));
        }
     ListView listView=(ListView)view.findViewById(R.id.klvfragment);
        PologPortfoliListAdapter ppl=new PologPortfoliListAdapter(getActivity(),data);
        listView.setAdapter(ppl);
        // Set the adapter

        return view;
    }



}
