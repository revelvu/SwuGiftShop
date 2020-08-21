package com.swu.swugiftshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainListViewActivity extends AppCompatActivity {

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private ListView listView1;
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    private TextView textView1;


    ArrayList<Song> al = new ArrayList<Song>();  // Top10 곡명을 담을 리스트


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);


        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        // AutoCompleteTextView 에 아답터를 연결한다.
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list));

        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);
        ListView listView1 = (ListView)findViewById(R.id.listView1);
        TextView textView1 = (TextView)findViewById(R.id.textView1);

//        listView1.setVisibility(View.VISIBLE);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 리스트에 검색될 데이터(단어)를 추가한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);




        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);

                listView1.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.INVISIBLE);

            }
        });
        list.clear();



        // 인기가요 순위 데이터 (다량의 데이터 준비)
        al.add(new Song("유시 무선 노트",R.drawable.usinotecrop,"3000원"));
        al.add(new Song("홀로그램 스티커",R.drawable.sticker,"3000원"));
        al.add(new Song("꽃학잠", R.drawable.flower_jacket,"35000원"));


        MyAdapter adapter = new MyAdapter(
                getApplicationContext(), // 현재화면의 제어권자
                R.layout.detail_row,  // 리스트뷰의 한행의 레이아웃
                al);         // 데이터



        listView1.setAdapter(adapter);


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 상세정보 화면으로 이동하기(인텐트 날리기)
                // 1. 다음화면을 만든다
                // 2. AndroidManifest.xml 에 화면을 등록한다
                // 3. Intent 객체를 생성하여 날린다
                Intent intent = new Intent(
                        getApplicationContext(), // 현재화면의 제어권자
                        ItemDetail.class); // 다음넘어갈 화면


                // intent 객체에 데이터를 실어서 보내기
                // 리스트뷰 클릭시 인텐트 (Intent) 생성하고 position 값을 이용하여 인텐트로 넘길값들을 넘긴다
                intent.putExtra("name", al.get(position).name);
                intent.putExtra("img", al.get(position).img);
                intent.putExtra("price", al.get(position).price);



                startActivity(intent);


            }
        });
    }



    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();



        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
//            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else {
            // 리스트의 모든 데이터를 검색한다.
            for (int i = 0; i < arraylist.size(); i++) {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText)) {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));

                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();



    }



//    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList() {
        list.add("유시 무선 노트");
        list.add("웬디 손거울");
        list.add("유시 L자 파일");
        list.add("슈니즈 L자 파일");
        list.add("홀로그램 스티커");
        list.add("전자파 차단 스티커");
        list.add("2020 S/S 꽃학잠");
    }


}

class MyAdapter extends BaseAdapter { // 리스트 뷰의 아답타
    Context context;
    int layout;
    ArrayList<Song> al;
    LayoutInflater inf;
    public MyAdapter(Context context, int layout, ArrayList<Song> al) {
        this.context = context;
        this.layout = layout;
        this.al = al;
        inf = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);


    }
    @Override
    public int getCount() {
        return al.size();
    }
    @Override
    public Object getItem(int position) {
        return al.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView = inf.inflate(layout, null);
        }
        ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);
        TextView goodsName = (TextView)convertView.findViewById(R.id.textView1);
        TextView goodsPrice = (TextView)convertView.findViewById(R.id.textView2);

        Song m = al.get(position);
        iv.setImageResource(m.img);
        goodsName.setText(m.name);
        goodsPrice.setText(m.price);



        return convertView;
    }
}

class Song { // 자바빈
    String name = ""; // 곡 title
    int img; // 앨범 이미지
    String price = ""; // artist
    public Song(String name, int img, String price) {
        super();
        this.name = name;
        this.img = img;
        this.price = price;


    }
    public Song() {}
}
