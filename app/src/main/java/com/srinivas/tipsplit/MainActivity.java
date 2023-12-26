package com.srinivas.tipsplit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private float tip ;

    private EditText bill;

    private TextView tip_amount;

    private TextView total_amount;

    private EditText no_person;

    private TextView totalP;

    private RadioGroup r;

    private static float result;


    private static final DecimalFormat df = new DecimalFormat("0.00");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bill = findViewById(R.id.billTotal);
        tip_amount = findViewById(R.id.tipAmount);
        total_amount = findViewById(R.id.totalWithTip);
        no_person = findViewById(R.id.noPeople);
        totalP = findViewById(R.id.totalPerPerson);
        r = findViewById(R.id.radioGroup2);

    }


    public void calculate(View v){

        if(!bill.getText().toString().isEmpty()) {

            String n = no_person.getText().toString();

            if (n.isEmpty() || n.equals("0")) {
                return;
            }

            int persons = Integer.parseInt(n);


            float per_person = result / persons;
            df.setRoundingMode(RoundingMode.UP);
            totalP.setText("$" + df.format(per_person));
        }
    }


    public void clear(View v){

        System.out.println("clearing");
        bill.setText("");
        tip_amount.setText("");
        total_amount.setText("");
        no_person.setText("");
        totalP.setText("");
        r.clearCheck();
    }

    public void setTip(View v){

        String b = bill.getText().toString();

        if(b.isEmpty()){
            r.clearCheck();
            return;
        } else if(b.charAt(0)=='$'){
            b = b.substring(1);
        }


        float bVal = Float.parseFloat(b);
        df.setRoundingMode(RoundingMode.UP);


        if(v.getId() == R.id.radio12){
            tip = (float) ((0.12) * bVal);
        } else if(v.getId() == R.id.radio15){
            tip = (float) ((0.15) * bVal);
        } else if(v.getId() == R.id.radio18){
            tip = (float) ((0.18) * bVal);
        } else if(v.getId() == R.id.radio20){
            tip = (float) ((0.20) * bVal);
        } else{
            Log.d("MainActivity","Different radio button clicked..  ");
        }

        tip_amount.setText("$"+df.format(tip));
        result = bVal + tip;
        total_amount.setText("$"+df.format(result));
        bill.setText("$"+bVal);


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putString("Bill_W_Tax",bill.getText().toString());
        outState.putString("Tip_Amount",tip_amount.getText().toString());
        outState.putString("Total_W_Tip",total_amount.getText().toString());
        outState.putString("Nof_Persons",no_person.getText().toString());
        outState.putString("Total_p_Persons", totalP.getText().toString());
        outState.putInt("Radio_Button",((RadioGroup) findViewById(R.id.radioGroup2)).getCheckedRadioButtonId());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(!savedInstanceState.isEmpty()){
            bill.setText(savedInstanceState.getString("Bill_W_Tax"));
            tip_amount.setText(savedInstanceState.getString("Tip_Amount"));
            total_amount.setText(savedInstanceState.getString("Total_W_Tip"));
            no_person.setText(savedInstanceState.getString("Nof_Persons"));
            totalP.setText(savedInstanceState.getString("Total_p_Persons"));

            findViewById(savedInstanceState.getInt("Radio_Button"));

        }
    }
}
