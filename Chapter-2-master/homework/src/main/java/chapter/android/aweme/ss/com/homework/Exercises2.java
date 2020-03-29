package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_view);
        Button btn1 = findViewById(R.id.count_view);
        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {//在点击时调用统计view个数函数
                TextView tv = findViewById(R.id.tv_center);
                View root_view = getWindow().getDecorView().findViewById(android.R.id.content);
                int view_num = count_view(root_view);
                tv.setText(String.valueOf(view_num));
            }
        });
    }

    public int count_view(View view){
        int count = 0;
        if (view instanceof ViewGroup) {
            ViewGroup view_group = (ViewGroup) view;
            for (int i = 0; i < view_group.getChildCount(); i++) {
                View childView = view_group.getChildAt(i);
                count += count_view(childView);
            }
        } else {
            count = 1;
        }
        return count;
    }
}
