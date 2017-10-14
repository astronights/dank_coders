package hku.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    String start="", destination="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner startSpinner = (Spinner) findViewById(R.id.start_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> startAdapter = ArrayAdapter.createFromResource(this,
                R.array.start_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        startSpinner.setAdapter(startAdapter);

        Spinner desSpinner = (Spinner) findViewById(R.id.des_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> desAdapter = ArrayAdapter.createFromResource(this,
                R.array.des_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        desAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        desSpinner.setAdapter(desAdapter);

        startSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                start = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        desSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                destination = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        Button submit = (Button) findViewById(R.id.btnsubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BufferedReader reader;
                String[] record;
                TextView bus = (TextView) findViewById(R.id.response_bus);
                TextView price = (TextView) findViewById(R.id.response_price);
                try {
                    final InputStream file = getAssets().open("data.txt");
                    reader = new BufferedReader(new InputStreamReader(file));
                    String line = reader.readLine();
                    while(line != null) {
                        record = line.split(",");
                        if(record[0].equals(start) && record[1].equals(destination)){
                            bus.setText(record[2]);
                            price.setText("$" + record[3]);
                            break;
                        }
                        line = reader.readLine();
                    }
                } catch(IOException ioe) {
                    System.out.println("e");
                }
                System.out.println("Start:" + start + "   Destination:" + destination);
            }
        });
    }
}
