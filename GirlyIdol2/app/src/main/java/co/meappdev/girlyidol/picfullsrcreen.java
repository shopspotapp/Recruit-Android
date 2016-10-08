package co.meappdev.girlyidol;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class picfullsrcreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picfullscreen);

        int pos = getIntent().getIntExtra("position", 0);

        ImageView model_pic_url = (ImageView)findViewById(R.id.model_pic);
        String url = getIntent().getStringExtra("pic_url");
        Picasso.with(this).load(url).into(model_pic_url);

        TextView model_name = (TextView)findViewById(R.id.model_name);
        String name = getIntent().getStringExtra("name_model");
        model_name.setText(name);

        TextView model_date = (TextView)findViewById(R.id.model_date);
        String date = getIntent().getStringExtra("pic_date");
        model_date.setText(date);

    }
}
