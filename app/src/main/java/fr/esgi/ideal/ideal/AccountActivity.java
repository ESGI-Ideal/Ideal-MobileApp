package fr.esgi.ideal.ideal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        final TextView tok = (TextView) findViewById(R.id.tokenid);
        tok.setText("Token : "+MainActivity.AccessToken);
        final Button close = (Button) findViewById(R.id.retouraccount);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
