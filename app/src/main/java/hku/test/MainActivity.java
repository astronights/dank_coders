package hku.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
                Toast.makeText(getBaseContext(), "Start: " + start + "   Destination: " + destination, Toast.LENGTH_LONG).show();
                String response = "37A,6";
                String[] data = response.split(",");
                TextView bus = (TextView) findViewById(R.id.response_bus);
                TextView price = (TextView) findViewById(R.id.response_price);
                bus.setText(data[0]);
                price.setText("$" + data[1]);
            }
        });
    }
}
