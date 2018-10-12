package com.example.porbook.circlegraph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showGraph(View view) {
        Intent intent = new Intent(this, ShowGraphActivity.class);

        String graphName = ((EditText) findViewById(R.id.graphNameEt)).getText().toString();
        String partAmt = ((EditText) findViewById(R.id.partAmtEt)).getText().toString();

        intent.putExtra("graphName", graphName);
        intent.putExtra("partAmt", partAmt);

        startActivity(intent);
    }
}
