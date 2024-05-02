package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View; // Thêm import này
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button off, xoa, phanTram, chia, nhan, cong, tru, bang, thapPhan;
    Button so0, so1, so2, so3, so4, so5, so6, so7, so8, so9;
    TextView nhapLieu, ketQua;
    String expression = ""; // Biểu thức toán học

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        off = findViewById(R.id.off);
        xoa = findViewById(R.id.AC);
        phanTram = findViewById(R.id.Delete);
        chia = findViewById(R.id.phepchia);
        nhan = findViewById(R.id.phepnhan);
        cong = findViewById(R.id.daucong);
        tru = findViewById(R.id.dautru);
        bang = findViewById(R.id.result);
        thapPhan = findViewById(R.id.thapphan);

        so0 = findViewById(R.id.num0);
        so1 = findViewById(R.id.num1);
        so2 = findViewById(R.id.num2);
        so3 = findViewById(R.id.num3);
        so4 = findViewById(R.id.num4);
        so5 = findViewById(R.id.num5);
        so6 = findViewById(R.id.num6);
        so7 = findViewById(R.id.num7);
        so8 = findViewById(R.id.num8);
        so9 = findViewById(R.id.num9);

        nhapLieu = findViewById(R.id.iptemp);
        ketQua = findViewById(R.id.output);

        // Sự kiện khi nhấn các số
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String value = button.getText().toString();

                // Thêm giá trị của nút vào biểu thức
                expression += value;

                // Hiển thị biểu thức trên nhapLieu
                nhapLieu.setText(expression);
            }
        };


        // Sự kiện khi nhấn dấu bằng
        bang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tính toán biểu thức
                try {
                    double result = evaluate(expression);
                    ketQua.setText(String.valueOf(result));
                } catch (ArithmeticException e) {
                    ketQua.setText("Error: Division by zero");
                } catch (IllegalArgumentException e) {
                    ketQua.setText("Error: Invalid expression");
                }
            }
        });
        // Sự kiện khi nhấn nút "C"
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa dữ liệu số trên nhapLieu và cập nhật biến expression
                expression = "";
                nhapLieu.setText("");
                ketQua.setText("");
            }
        });


        // Gán sự kiện cho các nút số
        so0.setOnClickListener(numberClickListener);
        so1.setOnClickListener(numberClickListener);
        so2.setOnClickListener(numberClickListener);
        so3.setOnClickListener(numberClickListener);
        so4.setOnClickListener(numberClickListener);
        so5.setOnClickListener(numberClickListener);
        so6.setOnClickListener(numberClickListener);
        so7.setOnClickListener(numberClickListener);
        so8.setOnClickListener(numberClickListener);
        so9.setOnClickListener(numberClickListener);

        cong.setOnClickListener(numberClickListener);
        tru.setOnClickListener(numberClickListener);
        nhan.setOnClickListener(numberClickListener);
        chia.setOnClickListener(numberClickListener);
    }


    // Hàm tính toán biểu thức
    private double evaluate(String expression) {
        // Tách các toán hạng và toán tử từ biểu thức
        String[] tokens = expression.split("(?=[-+*/])|(?<=[-+*/])");

        // Khởi tạo kết quả bằng toán hạng đầu tiên
        double result = Double.parseDouble(tokens[0]);

        // Lặp qua các phần tử còn lại của mảng tokens
        for (int i = 1; i < tokens.length; i += 2) {
            // Lấy toán tử và toán hạng tiếp theo
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i + 1]);

            // Thực hiện phép tính tương ứng với toán tử
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    if (operand != 0) {
                        result /= operand;
                    } else {
                        // Xử lý chia cho 0
                        throw new ArithmeticException("Error: Division by zero");
                    }
                    break;
                default:
                    // Xử lý toán tử không hợp lệ
                    throw new IllegalArgumentException("Error: Invalid operator");
            }
        }
        return result;
    }

}
