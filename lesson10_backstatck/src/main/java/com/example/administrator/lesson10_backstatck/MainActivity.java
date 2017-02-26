package com.example.administrator.lesson10_backstatck;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    List<Article> mList = new ArrayList<>();

    ContentFragment framgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        //找到xml中静态创建的Fragment
        framgent = (ContentFragment) getSupportFragmentManager().findFragmentByTag("fragment");
        //获取到引用
        framgent.showToast("这是activity调用fragment中的方法");
        initData();
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article a = mList.get(position);
                ContentFragment fragment = new ContentFragment();
                //activity向新建的Fragment传参
                Bundle bundle = new Bundle();
                bundle.putSerializable("article", a);
                fragment.setArguments(bundle);
                //去显示
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fl_content, fragment, "f1");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }


    private void initData() {
        mList.add(new Article("习近平会见菲总统：把分歧管控好 把合作谈起来", "习近平指出，中菲是隔海相望的近邻，两国人民是血缘相亲的兄弟。中菲同为发展中国家，团结、互助、合作、发展是我们的共同目标。虽然我们之间经历风 雨，但睦邻友好的情感基础和合作意愿没有变。中方高度重视中菲关系，愿同菲方一道努力，不断增进政治互信、深化互利合作、妥善处理分歧，做感情上相近相 通、合作中互帮互助、发展中携手前行的睦邻友好伙伴。"));
        mList.add(new Article("外交部官员", "。中菲同为发展中国家，团结、互助、合作、发展是我们的共同目标。虽然我们之间经历风 雨，但睦邻友好的情感基础和合作意愿没有变。中方高度重视中菲关系，愿同菲方一道努力，不断增进政治互信、深化互利合作、妥善处理分歧，做感情上相近相 通、合作中互帮互助、发展中携手前行的睦邻友好伙伴。"));
        mList.add(new Article("洪秀柱邀国民党4巨头谈党产议题 马英九将出席：把分歧管控好 把合作谈起来", "邻，两国人民是血缘相亲的兄弟。中菲同为发展中国家，团结、互助、合作、发展是我们的共同目标。虽然我们之间经历风 雨，但睦邻友好的情感基础和合作意愿没有变。中方高度重视中菲关系，愿同菲方一道努力，不断增进政治互信、深化互利合作、妥善处理分歧，做感情上相近相 通、合作中互帮互助、发展中携手前行的睦邻友好伙伴。"));
    }


    class Article implements Serializable {
        String title;
        String content;

        public Article(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = new TextView(MainActivity.this);
            TextView tv = (TextView) convertView;
            tv.setText(mList.get(position).title);
            return tv;
        }
    };
}
