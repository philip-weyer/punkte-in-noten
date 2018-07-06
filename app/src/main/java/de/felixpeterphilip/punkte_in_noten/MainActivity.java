package de.felixpeterphilip.punkte_in_noten;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import de.petix.example.nc_rechner.R;

public class MainActivity extends AppCompatActivity {

    private double rechenAnzahl = 0;
    private int displayAnzahl = 0;
    private double punktedurchschnitt = 0;
    private double punkteinsgesamt = 0;
    private double notendurchschnitt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button button = (Button) findViewById(R.id.add);
        final EditText textfeld = (EditText) findViewById(R.id.eingabe);
        final CheckBox haken = (CheckBox) findViewById(R.id.checkbox1);
        final TextView textAnzahl = (TextView) findViewById(R.id.textAnzahl);
        final TextView textPunktedurchschnitt = (TextView) findViewById(R.id.textPunktedurchschnitt);
        final TextView textNotendurchschnitt = (TextView) findViewById(R.id.textNotendurchschnitt);

        final Button deleteButton = (Button) findViewById(R.id.delete);

        final TextView anzahlPunkte = (TextView) findViewById(R.id.anzahlPunkte) ;
        final TextView punkteDurchschnitt = (TextView) findViewById(R.id.punkteDurchschnitt);
        final TextView notenDurchschnitt = (TextView) findViewById(R.id.Notendurchschnitt);

        anzahlPunkte.setVisibility(View.GONE);
        punkteDurchschnitt.setVisibility(View.GONE);
        notenDurchschnitt.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textfeld.getText().toString().length() > 0) {

                    int inteingabe = Integer.parseInt(textfeld.getText().toString());


                    if (inteingabe > 0 && inteingabe < 16) {

                        anzahlPunkte.setVisibility(View.VISIBLE);
                        punkteDurchschnitt.setVisibility(View.VISIBLE);
                        notenDurchschnitt.setVisibility(View.VISIBLE);

                        if (haken.isChecked()) {
                            displayAnzahl++;
                            rechenAnzahl = rechenAnzahl + 2;
                            punkteinsgesamt = punkteinsgesamt + (inteingabe * 2);
                            punktedurchschnitt = punkteinsgesamt / rechenAnzahl;
                            notendurchschnitt = (17 - punktedurchschnitt) / 3;

                            textAnzahl.setText(Integer.toString(displayAnzahl));
                            textPunktedurchschnitt.setText(Double.toString(rundeBetrag(punktedurchschnitt)));
                            textNotendurchschnitt.setText(Double.toString(rundeBetrag(notendurchschnitt)));
                        } else {
                            displayAnzahl++;
                            rechenAnzahl++;
                            punkteinsgesamt = punkteinsgesamt + inteingabe;
                            punktedurchschnitt = punkteinsgesamt / rechenAnzahl;
                            notendurchschnitt = (17 - punktedurchschnitt) / 3;

                            textAnzahl.setText(Integer.toString(displayAnzahl));
                            textPunktedurchschnitt.setText(Double.toString(rundeBetrag(punktedurchschnitt)));
                            textNotendurchschnitt.setText(Double.toString(rundeBetrag(notendurchschnitt)));

                        }
                        textfeld.setText("");
                        haken.setChecked(false);
                    }
                }

            }


        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rechenAnzahl=0;
                displayAnzahl=0;
                punktedurchschnitt=0;
                punkteinsgesamt=0;
                notendurchschnitt=0;

                haken.setChecked(false);
                textfeld.setText("");
                textAnzahl.setText("");
                textPunktedurchschnitt.setText("");
                textNotendurchschnitt.setText("");

                anzahlPunkte.setVisibility(View.GONE);
                punkteDurchschnitt.setVisibility(View.GONE);
                notenDurchschnitt.setVisibility(View.GONE);

            }
        });


    }

    public double rundeBetrag(double betrag)
    {
        double round = Math.round(betrag*10000);
        round = round / 10000;
        round = Math.round(round*1000);
        round = round / 1000;
        round = Math.round(round*100);
        return round / 100;
    }

}
