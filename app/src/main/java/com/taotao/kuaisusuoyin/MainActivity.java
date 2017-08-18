package com.taotao.kuaisusuoyin;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IndexBar.OnLetterChangeListener,AbsListView.OnScrollListener {

    private IndexBar letters;
    private ListView listView;
    private TextView letterText;

    private List<Person> list;

    private Handler handler;

    private PersonAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getData();
        adapter=new PersonAdapter(this,getData());
        listView.setAdapter(adapter);
    }

    private void init() {
        letters= (IndexBar) findViewById(R.id.letters);
        listView= (ListView) findViewById(R.id.list);
        letterText= (TextView) findViewById(R.id.tv);
        letters.setOnLetterChangeListener(this);

        handler=new Handler();
    }

    /**
     * 按下手指字母改变监听回调
     * @param letter
     */
    @Override
    public void letterChange(String letter) {

        letterText.setText(letter);
        letterText.setVisibility(View.VISIBLE);
        //清空之前所有的消息
        handler.removeCallbacksAndMessages(null);
        //500ms后让letterText隐藏
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                letterText.setVisibility(View.INVISIBLE);
            }
        },500);
        updateListView(letter);
    }


    private List<Person> getData(){
        list=new ArrayList<>();
        list.add(new Person("李逵"));
        list.add(new Person("李啊"));
        list.add(new Person("李逵飞"));
        list.add(new Person("李飞飞"));
        list.add(new Person("王飞"));
        list.add(new Person("松逵"));
        list.add(new Person("黑啊"));
        list.add(new Person("飞逵飞"));
        list.add(new Person("皮飞飞"));
        list.add(new Person("黑飞"));
        list.add(new Person("压逵"));
        list.add(new Person("图啊"));
        list.add(new Person("逵飞"));
        list.add(new Person("无飞飞"));
        list.add(new Person("起飞"));
        list.add(new Person("李逵"));
        list.add(new Person("李啊"));
        list.add(new Person("李逵飞"));
        list.add(new Person("李飞飞"));
        list.add(new Person("王飞"));
        list.add(new Person("松逵"));
        list.add(new Person("黑啊"));
        list.add(new Person("飞逵飞"));
        list.add(new Person("皮飞飞"));
        list.add(new Person("黑飞"));
        list.add(new Person("压逵"));
        list.add(new Person("图啊"));
        list.add(new Person("逵飞"));
        list.add(new Person("无飞飞"));
        list.add(new Person("起飞"));
        //对集合进行排序
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person l, Person h) {
                //根据拼音进行排序
                return l.getPinyin().compareTo(h.getPinyin());
            }
        });
        return list;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //当滑动listView时更新右边的显示
        letters.setTouchIndex(list.get(firstVisibleItem).getHeaderWord());
    }


    /*首字母*/
    private void updateListView(String word){
        for (int i=0;i<list.size();i++){
            String headerWord=list.get(i).getHeaderWord();
            //将手指按下的字母与列表中相同字母开头的项找出
            if (word.equals(headerWord)){
                listView.setSelection(i);
                return;
            }

        }
    }
}
