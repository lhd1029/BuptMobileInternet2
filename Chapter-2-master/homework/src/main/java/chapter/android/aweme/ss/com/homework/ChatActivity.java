package chapter.android.aweme.ss.com.homework;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("Registered")
public class ChatActivity extends AppCompatActivity {
    private final static String TAG = "debug";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ChatActivity onCreate");
        setContentView(R.layout.activity_chatroom);

        TextView item_no = findViewById(R.id.textView);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle("user");
            if (bundle != null) {
                item_no.setText("我是第" + bundle.getString("title") + "个item");
            }
        }

    }
}
