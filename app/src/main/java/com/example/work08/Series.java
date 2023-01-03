package com.example.work08;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;




public class Series extends AppCompatActivity implements View.OnLongClickListener, AdapterView.OnItemLongClickListener {
    ListView lv;
    Button btn;
    TextView tv1;
    String[] numbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        numbers = getArray();
        btn = (Button) findViewById(R.id.button2);
        tv1 = (TextView) findViewById(R.id.textViewX1);
        lv = (ListView) findViewById(R.id.ListView);
        System.out.println(numbers);
        lv.setOnItemLongClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numbers);

        lv.setAdapter(adp);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
        lv.setOnCreateContextMenuListener(this);

    }

    /*
    public void onItemLongClick(AdapterView<?> parent, View view, int position, long l) {

        Double sum = 0., curr = 0.;
        curr = Double.parseDouble(numbers[position]);
        Double one = 0., two = 0., three = 0., four = 0.;
        one = Double.parseDouble(numbers[0]);
        two = Double.parseDouble(numbers[1]);
        three = Double.parseDouble(numbers[2]);
        four = Double.parseDouble(numbers[3]);

        if ((four - three) == (two - one))
        {
            tv1.setText(numbers[0]);
            sum = ((position + 1) * (Double.valueOf(curr) + Double.parseDouble(numbers[0]))) / 2;
        }
        else
        {
            tv1.setText(numbers[0]);
            double mul = two/one;
            sum = ((one * (Math.pow(mul, position+1) - 1)) / (mul-1));
        }

    }
    */

    public String[] getArray() {
        NumberFormat numFormat = new DecimalFormat();
        numFormat = new DecimalFormat("0.###E0");
        String arr[] = new String[20];
        double num = 0, first = 0;
        String v;
        Intent gi = getIntent();
        String firstNum = gi.getStringExtra("firstNum");
        String mul = gi.getStringExtra("mul");
        int mOrG = gi.getIntExtra("Series", -1000);
        arr[0] = firstNum;
        first = Double.parseDouble(firstNum);
        if (mOrG == 1) {
            for (int i = 1; i < 20; i++) {
                num = first + (i * Double.valueOf(mul));
                if (num <= 0.000009)
                    arr[i] = numFormat.format(num);
                else
                    arr[i] = String.format("%.4f", num);
            }
            return arr;
        } else {
            for (int i = 1; i < 20; i++) {
                num = first * (Math.pow(Double.valueOf(mul), i));
                if (num <= 0.0009 && num > 0 || num >= -0.0009 && num < 0)
                    arr[i] = numFormat.format(num);
                else
                    arr[i] = String.format("%.4f", num);
            }
            return arr;
        }
    }

    public void Return(View view) {
        Intent si = new Intent(this, MainActivity.class);
        startActivity(si);
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Series Operations");
        menu.add("number position");
        menu.add("sum till this number");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        String oper = item.getTitle().toString();
        if (oper.equals("number position")) {
            tv1.setText(Integer.toString(index));
        }
        if(oper.equals("sum till this number"))
        {
            Double sum = 0., curr = 0.;
            curr = Double.parseDouble(numbers[index]);
            Double one = 0., two = 0., three = 0., four = 0.;
            one = Double.parseDouble(numbers[0]);
            two = Double.parseDouble(numbers[1]);
            three = Double.parseDouble(numbers[2]);
            four = Double.parseDouble(numbers[3]);

            if ((four - three) == (two - one))
            {
                sum = ((index + 1) * (Double.valueOf(curr) + Double.parseDouble(numbers[0]))) / 2;
                tv1.setText(Double.toString(sum));

            }
            else
            {
                double mul = two/one;
                sum = ((one * (Math.pow(mul, index+1) - 1)) / (mul-1));
                tv1.setText(Double.toString(sum));

            }
        }
        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        String st = item.getTitle().toString();
        if(st.equals("Credit Screen"))
        {
            Intent si = new Intent(this,CreditScreen.class);
            startActivity(si);
        }
        return true;
    }
}


