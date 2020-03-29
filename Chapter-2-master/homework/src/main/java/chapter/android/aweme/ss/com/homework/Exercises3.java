package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;
import chapter.android.aweme.ss.com.homework.util.MyAdapter;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity implements MyAdapter.ListItemClickListener {

    private static final String TAG = "debug";
    private static final String DATA_PATH = "data.xml";

    private int num_list_items;

    private MyAdapter myAdapter;
    private RecyclerView messageListView;

    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Exercises3 onCreate");
        setContentView(R.layout.activity_message);
        messageListView = findViewById(R.id.rv_list);

        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        messageListView.setLayoutManager(layoutManager);


        try {
            //读取data.xml文件
            List<Message> messages = PullParser.pull2xml(getResources().getAssets().open(DATA_PATH));
            Log.d(TAG, messages.toString());
            num_list_items = messages.size();
            myAdapter = new MyAdapter(num_list_items, messages, this);
            messageListView.setAdapter(myAdapter);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(String title) {
        Log.d(TAG, "onListItemClick: " + title);
        //携带数据，跳转到chatActivity
        Intent intent = new Intent(this, ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        intent.putExtra("user", bundle);
        startActivity(intent);
    }
}
