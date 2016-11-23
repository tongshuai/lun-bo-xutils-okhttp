package lunbo.feicui.com.lunbotu;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */

public class ViewPagerAdapter extends PagerAdapter{

    private List<View> urls =new ArrayList<View>();
    private Context context;
    public ViewPagerAdapter(Context context,List<View> urls) {
        this.context=context;
        this.urls=urls;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(urls.get(position%urls.size()));
        return urls.get(position%urls.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(urls.get(position%urls.size()));
    }
}
