package com.swu.swugiftshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);



        TextView goodsName = (TextView)findViewById(R.id.textView1);
        TextView goodsPrice = (TextView)findViewById(R.id.textView2);
        ImageView iv = (ImageView)findViewById(R.id.imageView1);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        goodsName.setText(intent.getStringExtra("name"));
        goodsPrice.setText(intent.getStringExtra("Price"));
        iv.setImageResource(intent.getIntExtra("img", 0));


//        Button imageButton = (Button) findViewById(R.id.paybutton);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), DetailpageActivity1.class);
//                startActivity(intent);
//            }
//        });


    }
}