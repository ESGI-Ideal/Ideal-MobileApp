package fr.esgi.ideal.ideal;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.Locale;

public class AccountActivity extends AppCompatActivity {
    Handler Loadhandler = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // toolbar
        Toolbar toolbar = findViewById(R.id.toolbarviewer);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Si RGPD n'est pas accepté alors on affiche le message
        if(!MainActivity.acceptedRGDP){
            Intent myIntent = new Intent(AccountActivity.this, RGPD.class);
            AccountActivity.this.startActivity(myIntent);
        }

        // Bouton de sauvegarde
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                recreate();
            }
        });

        /*Button close = (Button) findViewById(R.id.retouraccount);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ddd","BACKKKK");
                // La langue a changé et que l'on annule alors elle redevient comme avant
                if(MainActivity.changedlang){
                    MainActivity.changedlang = false;

                    if (getBaseContext().getResources().getConfiguration().locale.getLanguage().toUpperCase().contains("FR")) {
                        // On change la langue en anglais
                        Configuration config = getBaseContext().getResources().getConfiguration();
                        Locale locale = new Locale("en");
                        Locale.setDefault(locale);
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config,
                                getBaseContext().getResources().getDisplayMetrics());
                    } else {
                        // On change la langue en français
                        Configuration config = getBaseContext().getResources().getConfiguration();
                        Locale locale = new Locale("fr");
                        Locale.setDefault(locale);
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config,
                                getBaseContext().getResources().getDisplayMetrics());
                    }

                }
                finish();
            }
        });*/

        // Affichage de la langue actuelle
        TextView lang = (TextView) findViewById(R.id.lang);
        String actuallang = getBaseContext().getResources().getConfiguration().locale.getLanguage().toUpperCase();
        lang.setText(getText(R.string.lang) + " " + actuallang + ")");

        // Bouton de changement de langue
        Button swlang = (Button) findViewById(R.id.swlang);
        if (actuallang.contains("FR")) {
            swlang.setText("  Passer la langue en anglais");
            swlang.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.en), null, null, null);
            swlang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // On change la langue en anglais
                    Configuration config = getBaseContext().getResources().getConfiguration();
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    // La langue a été changé donc on actualise l'activité principal
                    MainActivity.changedlang = true;
                    recreate();
                }
            });
        } else { // EN
            swlang.setText("   Switch language to french");
            swlang.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.fr), null, null, null);
            swlang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // On change la langue en français
                    Configuration config = getBaseContext().getResources().getConfiguration();
                    Locale locale = new Locale("fr");
                    Locale.setDefault(locale);
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    // La langue a été changé donc on actualise l'activité principal
                    MainActivity.changedlang = true;
                    recreate();
                }
            });

            // Debug : affichage du token
            final TextView tok = (TextView) findViewById(R.id.tokenid);
            tok.setText("Token : " + MainActivity.AccessToken);
        }

        //handler.sendEmptyMessageDelayed(1, 100);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
                /*
                i++;


                handler.sendEmptyMessageDelayed(1, 30);
                */
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}