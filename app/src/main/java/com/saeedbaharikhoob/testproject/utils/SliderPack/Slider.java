package com.saeedbaharikhoob.testproject.utils.SliderPack;


import com.saeedbaharikhoob.testproject.R;

public class Slider implements SliderView.SliderFragmentItem {


    private SliderFragment fragment;

    private String title;
    private String filter;
    private String url;
    private String image;
    private String packageName;

    public Slider( String image, String url, String packageName, String filter, String title) {
        this.title = title;
        this.url = url;
        this.image = image;
        this.filter = filter;
        this.packageName = packageName;
    }



    @Override
    public SliderView.SliderFragment getFragment() {

        return fragment;
    }

    @Override
    public SliderView.SliderFragment getInstance(int pos) {

        fragment = new SliderFragment(this);
        return fragment;

    }

    @Override
    public int getViewToParallaxResourceId() {
        return R.id.image;
    }

    public String getTitle() {
        return title;
    }

    public String getFilter() {
        return filter;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }




    public String getPackageName() {
        return packageName;
    }

    public void setPakageName(String pakageName) {
        this.packageName = pakageName;
    }
}
