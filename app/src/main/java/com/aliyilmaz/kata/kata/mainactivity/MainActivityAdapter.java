package com.aliyilmaz.kata.kata.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.aliyilmaz.kata.kata.R;
import com.aliyilmaz.kata.kata.models.JSONModel;
import com.aliyilmaz.kata.kata.webview.WebViewActivity;

import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {
    private static final String LOG_EMPTY_URL = "EMPTY_URL";
    private static final String LOG_EMPTY_CONTENT_ARRAY = "EMPTY_URL";
    private static final String WEBVIEW_EXTRA = "url";
    private List<JSONModel> jsonModelList;
    private Context context;

    public MainActivityAdapter(Context context, List<JSONModel> data) {
        this.jsonModelList = data;
        this.context = context;
    }


    @Override
    public MainActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MainActivityAdapter.ViewHolder holder, final int position) {
        // Check null or not
        if (jsonModelList.get(position).getTitle() != null) {
            holder.txtTitle.setText(jsonModelList.get(position).getTitle());
        } else {
            holder.txtTitle.setVisibility(View.GONE);
        }

        // Check null or not
        if (jsonModelList.get(position).getTopDescription() != null) {
            holder.txtTopDescription.setText(jsonModelList.get(position).getTopDescription());
        } else {
            holder.txtTopDescription.setVisibility(View.GONE);
        }

        // Check null or not if not fix the link because it s not appropriate and then set it to the textview
        if (jsonModelList.get(position).getBottomDescription() != null) {
            String url = jsonModelList.get(position).getBottomDescription().replaceAll("\\\\", "").trim();
            holder.txtBottomDescription.setText(Html.fromHtml((url)));
            holder.txtBottomDescription.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            holder.txtBottomDescription.setVisibility(View.GONE);
        }

        // Check null or not
        if (jsonModelList.get(position).getPromoMessage() != null) {
            holder.txtPromoMessage.setText(jsonModelList.get(position).getPromoMessage());
        } else {
            holder.txtPromoMessage.setVisibility(View.GONE);
        }

        // Get the link of bacgkroundImage using Glide and then load it into the ImageView
        Glide.with(context).load(jsonModelList.get(position).getBackgroundImage()).into(holder.coverImage);


        // Check content array is null or not
        if (jsonModelList.get(position).getContent() != null) {
            // Check how many elements content array has if its equals 2, then create 2 buttons with their onclick listeners
            if (jsonModelList.get(position).getContent().length == 2) {
                holder.shopBtn1.setText(jsonModelList.get(position).getContent()[0].getTitle());
                holder.shopBtn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToWebView(jsonModelList.get(position).getContent()[0].getTarget());
                    }
                });
                holder.shopBtn2.setText(jsonModelList.get(position).getContent()[1].getTitle());
                holder.shopBtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToWebView(jsonModelList.get(position).getContent()[1].getTarget());
                    }
                });
            } else if (jsonModelList.get(position).getContent().length == 1) {  //if length of content array is 1
                holder.shopBtn2.setVisibility(View.GONE);
                // Check whether its title and target values are empty or not
                if (!jsonModelList.get(position).getContent()[0].getTitle().isEmpty() && !jsonModelList.get(position).getContent()[0].getTarget().isEmpty()) {
                    holder.shopBtn1.setText(jsonModelList.get(position).getContent()[0].getTitle());
                    holder.shopBtn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goToWebView(jsonModelList.get(position).getContent()[0].getTarget());
                        }
                    });
                } else {
                    holder.shopBtn1.setVisibility(View.GONE);
                }
            } else {    // content array has no element
                Log.d(LOG_EMPTY_CONTENT_ARRAY, holder.itemView.getContext().getResources().getString(R.string.empty_array));
            }
        } else {    // content array is null
            holder.shopBtn1.setVisibility(View.GONE);
            holder.shopBtn2.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return jsonModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;

        private TextView txtTitle;
        private ImageView coverImage;
        private TextView txtTopDescription;
        private TextView txtBottomDescription;
        private TextView txtPromoMessage;
        private Button shopBtn1;
        private Button shopBtn2;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtTitle = mView.findViewById(R.id.title);
            coverImage = mView.findViewById(R.id.coverImage);
            txtTopDescription = mView.findViewById(R.id.topDescription);
            txtBottomDescription = mView.findViewById(R.id.bottomDescription);
            txtPromoMessage = mView.findViewById(R.id.promoMessage);
            shopBtn1 = mView.findViewById(R.id.shopBtn1);
            shopBtn2 = mView.findViewById(R.id.shopBtn2);

        }
    }
    // Open Webview Activity and send the url as parameter to the Intent
    private void goToWebView(String url) {
        if (!url.isEmpty()) {
            Intent i = new Intent(context, WebViewActivity.class);
            i.putExtra(WEBVIEW_EXTRA, url);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Log.d(LOG_EMPTY_URL, context.getResources().getString(R.string.gotowebviewerror));
        }
    }
}
