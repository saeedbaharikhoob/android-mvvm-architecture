package com.saeedbaharikhoob.testproject.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.utils.TabPager.TabListener;
import com.saeedbaharikhoob.testproject.utils.TabPager.TextTab;

import java.util.ArrayList;


public class TabHost extends RecyclerView {

    public int stripSize = 3;
    public int textColor;
    public int primaryColor;
    public int accentColor;

    private boolean isTablet;
    private float density;
    private LinearLayout layout;
    private int selected;
    private Point lastTouchedPoint;
    private int width;

    TabListener listener;
    private TabAdapter adapter;

    public TabHost(Context context) {
        super(context);
    }


    public TabHost(Context context, AttributeSet attrs) {
        super(context, attrs);


        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MaterialTabHost, 0, 0);

            try {
                // custom attributes
                primaryColor = a.getColor(R.styleable.MaterialTabHost_primaryColor, Color.parseColor("#009688"));
                accentColor = a.getColor(R.styleable.MaterialTabHost_accentColor, Color.parseColor("#00b0ff"));
                textColor = a.getColor(R.styleable.MaterialTabHost_textColor, Color.WHITE);
                stripSize = a.getDimensionPixelSize(R.styleable.MaterialTabHost_stripSize, stripSize);

            } finally {
                a.recycle();
            }
        }

    }


    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }


    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }


    /***
     * the color when a tab selected
     *
     * @param accentColor
     */
    public void setAccentColor(int accentColor) {
        this.accentColor = accentColor;
    }

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> tabsIcon = new ArrayList<>();
    ArrayList<Integer> tabsEnableIcon = new ArrayList<>();
    ArrayList<Integer> tabsDisableIcon = new ArrayList<>();

    public void addTab(CharSequence title, int enableIcon, int disableIcon) {

        titles.add(title.toString());
        tabsEnableIcon.add(enableIcon);
        tabsDisableIcon.add(disableIcon);
    }

    public void addTab(CharSequence title, String image) {

        titles.add(title.toString());
        tabsIcon.add(image);
    }

    public void addTab(CharSequence title) {

        titles.add(title.toString());
    }

    /****
     * call when you want to show and make it ready
     */
    public void setup() {
        if (tabsEnableIcon.size() > 0) {
            adapter = new TabAdapter(titles, tabsEnableIcon, tabsDisableIcon, new TabSelected() {
                @Override
                public void onTabSelected(int poition) {

                    setSelectedTab(poition, true);
                    adapter.notifyDataSetChanged();
                }
            });
        } else if (tabsIcon.size() > 0) {
            adapter = new TabAdapter(titles, tabsIcon, new TabSelected() {
                @Override
                public void onTabSelected(int poition) {

                    setSelectedTab(poition, true);
                    adapter.notifyDataSetChanged();
                }
            });
        } else if (tabsIcon.size() == 0 && tabsEnableIcon.size() == 0) {
            adapter = new TabAdapter(titles, new TabSelected() {
                @Override
                public void onTabSelected(int poition) {

                    setSelectedTab(poition, true);
                    adapter.notifyDataSetChanged();
                }
            });
        }
        adapter.host = this;

        this.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setReverseLayout(true);

        this.setLayoutManager(llm);

    }


    public void setSelectedTab(int tab, boolean call) {

        if (tab == selected)
            return;

        selected = tab;

        if (listener != null && call)
            listener.onTabSelected(selected);

        adapter.notifyDataSetChanged();

        ((LinearLayoutManager) getLayoutManager()).smoothScrollToPosition(this, null, selected);

    }


    public int getSelectedIndex() {
        return selected;
    }

    public void setTabListener(TabListener listener) {
        this.listener = listener;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (getLayoutManager() == null)
            return;
        ((LinearLayoutManager) getLayoutManager()).smoothScrollToPosition(this, null, selected);
    }

    public static interface TabSelected {

        void onTabSelected(int poition);
    }


    /**
     * the adapter for TabHost
     */
    public static class TabAdapter extends Adapter<TextTab> {


        private ArrayList<String> tabs;
        private ArrayList<Integer> tabsEnableIcon;
        private ArrayList<Integer> tabsDisableIcon;
        ArrayList<String> tabsIcon = new ArrayList<>();
        private TabSelected listener;


        private TabHost host;

        public TabAdapter(ArrayList<String> tabs, TabSelected listener) {

            this.tabs = tabs;
            this.listener = listener;
        }

        public TabAdapter(ArrayList<String> tabs, ArrayList<Integer> tabsEnableIcon, ArrayList<Integer> tabsDisableIcon, TabSelected listener) {

            this.tabs = tabs;
            this.tabsEnableIcon = tabsEnableIcon;
            this.tabsDisableIcon = tabsDisableIcon;
            this.listener = listener;
        }

        public TabAdapter(ArrayList<String> tabs, ArrayList<String> tabsIcon, TabSelected listener) {

            this.tabs = tabs;
            this.tabsIcon = tabsIcon;

            this.listener = listener;
        }

        @Override
        public TextTab onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_tab, null);


            return new TextTab(view);
        }

        @Override
        public void onBindViewHolder(final TextTab holder, final int position) {
            if (tabsIcon != null && tabsIcon.size() > 0 || tabsEnableIcon != null && tabsEnableIcon.size() > 0) {
                holder.getStrip().setVisibility(GONE);
                holder.getView().setVisibility(GONE);
                holder.getImage().setVisibility(VISIBLE);
                LinearLayout.LayoutParams Params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                Params.setMargins(0, ViewUIHelper.dpToPx(-10), 0, 0);
                holder.getTitle().setLayoutParams(Params);


                if (tabsEnableIcon != null && tabsEnableIcon.size() > 0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) (ViewUIHelper.getScreenWidth(G.context) / tabs.size()),
                            LinearLayout.LayoutParams.WRAP_CONTENT);

                    holder.getLayoutTab().setLayoutParams(lp);
                }
                if (tabsIcon.size() == 0) {
                    holder.getTitle().setText(tabs.get(position));
                    holder.getTitle().setVisibility(VISIBLE);
                } else
                    holder.getTitle().setVisibility(GONE);

                if (position == host.selected) {
                    if (tabsEnableIcon != null && tabsEnableIcon.size() > 0) {
                        holder.getImage().setImageResource(tabsEnableIcon.get(position));
                        holder.getTitle().setTextColor(G.context.getResources().getColor(R.color.colorAccent));
                    } else {

                        holder.getImage().getLayoutParams().width = ViewUIHelper.dpToPx(40);
                        holder.getImage().getLayoutParams().height = ViewUIHelper.dpToPx(40);
                        holder.getImage().setPadding(10, 10, 10, 10);
                        try {
                            Glide.with(G.context)
                                    .asBitmap()
                                    .load(tabsIcon.get(position))
                                    .apply(new RequestOptions()
                                            .override(100, 100)
                                            .dontAnimate())
                                    .into(holder.getImage());

                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }

                } else {
                    if (tabsDisableIcon != null && tabsDisableIcon.size() > 0) {
                        holder.getImage().setImageResource(tabsDisableIcon.get(position));
                        holder.getTitle().setTextColor(G.context.getResources().getColor(R.color.colorTabHostFont));
                    } else {
                        holder.getImage().getLayoutParams().width = ViewUIHelper.dpToPx(40);
                        holder.getImage().getLayoutParams().height = ViewUIHelper.dpToPx(40);
                        holder.getImage().setPadding(10, 10, 10, 10);

                        try {
                            Glide.with(G.context)
                                    .asBitmap()
                                    .load(tabsIcon.get(position))
                                    .apply(new RequestOptions().override(100, 100)
                                            .dontAnimate()
                                    )
                                    .into(holder.getImage());

                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                }

            } else {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) (ViewUIHelper.getScreenWidth(G.context) / tabs.size()),
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                holder.getImage().setVisibility(GONE);
                holder.getView().setVisibility(VISIBLE);
                holder.getStrip().setVisibility(VISIBLE);
                holder.getLayoutTab().setLayoutParams(lp);


                holder.getTitle().setText(tabs.get(position));
                holder.getTitle().setTextColor(host.textColor);

                LinearLayout.LayoutParams Params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                Params.setMargins(0, 0, 0, 0);
                holder.getTitle().setLayoutParams(Params);


                holder.getTitle().setTextSize(15);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, host.stripSize);
                holder.getStrip().setLayoutParams(parms);
                if (position == host.selected) {

                    holder.getStrip().setVisibility(VISIBLE);
                    holder.getStrip().setBackgroundColor(host.accentColor);
                    holder.getTitle().setTextColor(host.accentColor);

                } else {

                    holder.getStrip().setVisibility(GONE);
                }
            }

            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onTabSelected(position);

                }
            });

            holder.itemView.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {


                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if (tabsEnableIcon != null)
                                holder.getImage().setImageResource(tabsEnableIcon.get(position));


                            break;

                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:

                            if (position != host.selected) {
                                if (tabsDisableIcon != null)
                                    holder.getImage().setImageResource(tabsDisableIcon.get(position));

                            }

                    }

                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return tabs.size();
        }
    }

}
