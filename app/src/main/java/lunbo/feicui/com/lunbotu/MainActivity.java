package lunbo.feicui.com.lunbotu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<String> urls=new ArrayList<String>();
    private ViewPagerAdapter viewPagerAdapter;
    private ImageView[] points=new ImageView[5];
    private ListView listView;
    private List<View> views=new ArrayList<View>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageOptions imageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swip);
        listView= (ListView) findViewById(R.id.list);
        viewPager= (ViewPager) findViewById(R.id.vp);
        addPoints();
        addUrls();
        initData();
        imageOptions=new ImageOptions.Builder()
                .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setFailureDrawableId(R.drawable.imgbg)
                .setLoadingDrawableId(R.drawable.imgbg)
                .build();

        viewPagerAdapter=new ViewPagerAdapter(this,views);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(listener);
        viewPagerAdapter.notifyDataSetChanged();
        setBitmipFromUrl();
        handler.sendEmptyMessageDelayed(0,3000);


    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            //循环调用handler，设置延时
            handler.sendEmptyMessageDelayed(0, 3000);

        }
    };

    private ViewPager.OnPageChangeListener listener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setPoint(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void setPoint(int index){
        for(int i=0;i<points.length;i++){
            if (i==index){
                points[i].setAlpha(225);
            }else{
                points[i].setAlpha(100);
            }
        }

    }

    //实例化视图填充布局
    private void initData(){
        View view=null;
        view=getLayoutInflater().inflate(R.layout.vp_item,null);
        views.add(view);
        view=getLayoutInflater().inflate(R.layout.vp_item,null);
        views.add(view);
        view=getLayoutInflater().inflate(R.layout.vp_item,null);
        views.add(view);
        view=getLayoutInflater().inflate(R.layout.vp_item,null);
        views.add(view);
        view=getLayoutInflater().inflate(R.layout.vp_item,null);
        views.add(view);
    }


    //圆点
    private void addPoints(){
        points[0]= (ImageView) findViewById(R.id.point1);
        points[1]= (ImageView) findViewById(R.id.point2);
        points[2]= (ImageView) findViewById(R.id.point3);
        points[3]= (ImageView) findViewById(R.id.point4);
        points[4]= (ImageView) findViewById(R.id.point5);
        setPoint(0);
    }
    //图片链接
    private void addUrls(){
        urls.add("http://pic.4j4j.cn/upload/pic/20121031/261e39e216.jpg");
        urls.add("http://pic.4j4j.cn/upload/pic/20121031/13248c986d.jpg");
        urls.add("http://pic.4j4j.cn/upload/pic/20121031/ee3ce2abb2.jpg");
        urls.add("http://pic.4j4j.cn/upload/pic/20121031/9398aec558.jpg");
        urls.add("http://img.ishuo.cn/1610/1475545098.jpg");
    }
    //获取当前所要填充的布局，设置图片
    private void setBitmipFromUrl(){
        for (int i=0;i<views.size();i++){
            ImageView ic_vp= (ImageView) views.get(i).findViewById(R.id.img_vpitem);
            ic_vp.setTag(urls.get(i));
            x.image().bind(ic_vp,urls.get(i),imageOptions);
            viewPagerAdapter.notifyDataSetChanged();

        }

    }

}
