package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.PortfolioContentVO;
import com.fastbooster.android_teamwith.task.ImageTask;
import com.fastbooster.android_teamwith.viewholder.PortfolioContentViewHolder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class PortfolioContentAdapter extends BaseAdapter {
    private static final String dURL="http://192.168.30.64:8089";
    private Context context;
    private List<PortfolioContentVO> data;
    private LayoutInflater inflater;
    public PortfolioContentAdapter(Context context, List<PortfolioContentVO> data){
        this.context=context;
        this.data=data;
        inflater= LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        PortfolioContentViewHolder viewHolder;
        View v=inflater.inflate(R.layout.portfolio_content_layout,null);
        LinearLayout ll=(LinearLayout)v.findViewById(R.id.k_ll);
        if(data.get(i).getPortfolioContentName().equals("image")){
            ImageView iv=new ImageView(context);
            iv.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, 750));
            iv.setForegroundGravity(2);

            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setMaxWidth(1000);
            iv.setTag(data.get(i).getPortfolioContentValue());
            ImageTask imageViewTask= new ImageTask(context);
            imageViewTask.execute(iv);
            ll.addView(iv);

            TextView tv=new TextView(context);
            tv.setPadding(0,40,0,200);
            tv.setText(data.get(i).getPortfolioContentIntro());
            tv.setTextSize(20);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ll.addView(tv);
        }else  if(data.get(i).getPortfolioContentName().equals("ppt")){
            final String path=data.get(i).getPortfolioContentValue();
            Button btn=new Button(context);
            btn.setText("PDF 보기");
            btn.setLayoutParams(new ViewGroup.LayoutParams(500, 100));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(android.content.Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setDataAndType(Uri.parse("http://192.168.30.64:8089"+path), "application/pdf");
                    context.startActivity(intent);
                }
            });
            ll.addView(btn);
            TextView tv = new TextView(context);
            tv.setPadding(0, 40, 0, 200);
            if(!data.get(i).getPortfolioContentIntro().equals("null")) {
                tv.setText(data.get(i).getPortfolioContentIntro());
            }
            else{
                tv.setText("");
            }
            tv.setTextSize(20);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ll.addView(tv);
        }else if(data.get(i).getPortfolioContentName().equals("media")){
            WebView web=new WebView(context);
            web.getSettings().setJavaScriptEnabled(true);
            web.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, 750));
            web.loadUrl(data.get(i).getPortfolioContentValue());
            ll.addView(web);

            TextView tv = new TextView(context);
            tv.setPadding(0, 40, 0, 200);
            if(!data.get(i).getPortfolioContentIntro().equals("null")) {
                tv.setText(data.get(i).getPortfolioContentIntro());
            }
            else{
                tv.setText(" ");
            }
            tv.setTextSize(20);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ll.addView(tv);



        }
        return v;
    }
}
