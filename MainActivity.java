package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etInput;
    private TextView tvResult;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private Button btnCong, btnTru, btnNhan, btnChia, btnBang, btnCham, btnC, btnAC;
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        setEventClickViews();
    }
    
    public void initWidget() {                      //Ánh xạ các View
        etInput = findViewById(R.id.etInput);
        tvResult = findViewById(R.id.tvResult);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);
        btnBang = findViewById(R.id.btnBang);
        btnCham = findViewById(R.id.btnCham);
        btnC = findViewById(R.id.btnC);
        btnAC = findViewById(R.id.btnAC);
    }
   
    public void setEventClickViews() {                  //set sự kiện cho các button            
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);
        btnBang.setOnClickListener(this);
        btnCham.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnAC.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {                   //implement hàm onClick
        switch (v.getId()) {
            case R.id.btn1:
                etInput.append("1");                //thêm vào cuối dãy đang nhập trong editText

                break;
            case R.id.btn2:
                etInput.append("2");

                break;
            case R.id.btn3:
                etInput.append("3");

                break;
            case R.id.btn4:
                etInput.append("4");

                break;
            case R.id.btn5:
                etInput.append("5");

                break;
            case R.id.btn6:
                etInput.append("6");

                break;
            case R.id.btn7:
                etInput.append("7");

                break;
            case R.id.btn8:
                etInput.append("8");

                break;
            case R.id.btn9:
                etInput.append("9");

                break;
            case R.id.btn0:
                etInput.append("0");

                break;
            case R.id.btnCong:
                etInput.append("+");

                break;
            case R.id.btnTru:
                etInput.append("-");

                break;
            case R.id.btnNhan:
                etInput.append("*");

                break;
            case R.id.btnChia:
                etInput.append("/");

                break;
            case R.id.btnBang:
                addOperator(etInput.getText().toString().trim());
                addNumber(etInput.getText().toString().trim());
                double result = 0;
                DecimalFormat df = new DecimalFormat("###.######");                     //định dạng cho kết quả hiển thị

                if (arrOperator.size() >= arrNumber.size() || arrOperator.size()<1) {       //nếu số operator >= số number thì hiển thị
                    tvResult.setText("Syntax Error!");
                } else {
                    for (int i = 0; i < (arrNumber.size() - 1); i++) {            //thực hiện các phép tính theo thứ tự nhập vào
                        switch (arrOperator.get(i)) {
                            case "+":
                                if (i == 0)
                                    result = arrNumber.get(i) + arrNumber.get(i + 1);
                                else
                                    result = result + arrNumber.get(i + 1);
                                break;
                            case "-":
                                if (i == 0)
                                    result = arrNumber.get(i) - arrNumber.get(i + 1);
                                else
                                    result = result - arrNumber.get(i + 1);
                                break;
                            case "*":
                                if (i == 0)
                                    result = arrNumber.get(i) * arrNumber.get(i + 1);
                                else
                                    result = result * arrNumber.get(i + 1);
                                break;
                            case "/":
                                if (i == 0)
                                    result = arrNumber.get(i) / arrNumber.get(i + 1);
                                else
                                    result = result / arrNumber.get(i + 1);
                                break;
                            default:
                                break;
                        }
                    }
                    tvResult.setText(df.format(result)+"");
                }

                break;
            case R.id.btnCham:
                etInput.append(".");

                break;
            case R.id.btnC:
                BaseInputConnection textFieldInputConnection = new BaseInputConnection(etInput,true);
                textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));    //xóa kí tự bên trái con trỏ

                break;
            case R.id.btnAC:
                etInput.setText("");                  //xóa toàn bộ màn hình
                tvResult.setText("");

                break;
        }

    }

    public ArrayList<String> arrOperator;         //mảng chứa các operator
    public ArrayList<Double> arrNumber;           //mảng chứa các số hạng
    
    public void addOperator(String input) {        //thêm operator vào mảng
        arrOperator = new ArrayList<>();
        char[] arr = input.toCharArray();

        for(int i=0; i<arr.length; i++) {
            switch (arr[i]) {
                case '+':
                    arrOperator.add(arr[i]+"");
                    break;
                case '-':
                    arrOperator.add(arr[i]+"");
                    break;
                case '*':
                    arrOperator.add(arr[i]+"");
                    break;
                case '/':
                    arrOperator.add(arr[i]+"");
                    break;
                default:
                    break;

            }
        }
    }

    public void addNumber(String input) {             //thêm số hạng vào mảng
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(input);
        while(matcher.find()) {
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }
}
