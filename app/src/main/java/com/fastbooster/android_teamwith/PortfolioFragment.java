package com.fastbooster.android_teamwith;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.fastbooster.android_teamwith.task.MyPortfolioTask;


public class PortfolioFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private MyPortfolioTask ps;
    private View view;

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
        view=inflater.inflate(R.layout.fragment_portfolio,container,false);
        try {

            return view;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        GridView lv=(GridView)view.findViewById(R.id.k_lv_polport);
//        PologPortfoliListAdapter ppl=new PologPortfoliListAdapter(getActivity(),ps.getPoerfolioSimpleList());
        ps=new MyPortfolioTask(view.getContext(),lv);
        String memberId=getArguments().getString("memberId");
        ps.execute(memberId);//생성자로 아이디받기
//        lv.setAdapter(ppl);
        super.onActivityCreated(savedInstanceState);
    }
}
