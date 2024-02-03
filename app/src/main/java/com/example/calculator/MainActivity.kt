package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var math : EditText
    private lateinit var txtOper : TextView
    private lateinit var txtVsp : TextView
    private lateinit var btn0 : Button
    private lateinit var btn1 : Button
    private lateinit var btn2 : Button
    private lateinit var btn3 : Button
    private lateinit var btn4 : Button
    private lateinit var btn5 : Button
    private lateinit  var btn6 : Button
    private lateinit var btn7 : Button
    private lateinit var btn8 : Button
    private lateinit var btn9 : Button
    private lateinit var btnac : Button
    private lateinit var btnplus : Button
    private lateinit var btnrazn: Button
    private lateinit var btnproiz : Button
    private lateinit var btndel: Button
    private lateinit var btnravn: Button
    private lateinit var btnpoint: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn0 = findViewById(R.id.btn0)//Присвоение значений по индексу
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btnpoint = findViewById(R.id.btnpoint)
        btnac = findViewById(R.id.btnac)
        btnplus = findViewById(R.id.btnplus)
        btnrazn= findViewById(R.id.btnrazn)
        btnproiz = findViewById(R.id.btnproiz)
        btndel = findViewById(R.id.btndel)
        btnravn = findViewById(R.id.btnravn)
        math = findViewById(R.id.math)
        txtOper = findViewById(R.id.txtOper)
        txtVsp = findViewById(R.id.txtVsp)

        btn0.setOnClickListener{ setText("0") }
        btn1.setOnClickListener{ setText("1") }
        btn2.setOnClickListener{ setText("2") }
        btn3.setOnClickListener{ setText("3") }
        btn4.setOnClickListener{ setText("4") }
        btn5.setOnClickListener{ setText("5") }
        btn6.setOnClickListener{ setText("6") }
        btn7.setOnClickListener{ setText("7") }
        btn8.setOnClickListener{ setText("8") }
        btn9.setOnClickListener{ setText("9") }
        btnpoint.setOnClickListener{ setText(".") }
        btnplus.setOnClickListener{ action("+") }
        btnproiz.setOnClickListener{ action("*") }
        btndel.setOnClickListener{ action("/") }
        btnrazn.setOnClickListener{ action("-") }
        btnravn.setOnClickListener{ ravno() }

        btnac.setOnClickListener{
            math.text.clear()
            txtVsp.text = ""
            txtOper.text = ""
        }
    }
    private fun setText(str: String){
        val new = math.text.toString()
        val new2 = txtOper.text.toString()
        if(new == "0" && str != ".")
         {
                Toast.makeText(this@MainActivity, "Число не может начинаться с 0!", Toast.LENGTH_SHORT)
                    .show()//всплывающее сообщение: контекст, текст и продолжительность
                math.text.clear()
                return//?

            }
        else
            math.append(str)
    }

    private fun osn_oper ( op: String){
        var res: Float = 0F
        var oper: String = txtOper.text.toString()
        val num1: Float = (math.getText().toString()).toFloat()
        val num2: Float = (txtVsp.text.toString()).toFloat()
        when (oper) {
            "+" -> res += num2 + num1
            "-" -> res += num2 - num1
            "*" -> res += num2 * num1
            "/" -> {
                if (num1 == 0F) {
                    Toast.makeText(
                        this@MainActivity,
                        "На 0 делить нельзя!",
                        Toast.LENGTH_SHORT
                    )
                        .show()//всплывающее сообщение: контекст, текст и продолжительность
                    math.text.clear()
                    return//?
                } else
                    res += num2 / num1
            }
        }
        if (op=="!") {
            math.setText(res.toString())
            txtVsp.text = ""
            txtOper.text = ""
        }
        else
        {   math.text.clear()
            txtVsp.text = (res.toString())
            txtOper.text = op}


    }
    private fun action(str: String){
        /*if( "/" in math.getText().toString() || "-" in math.getText().toString()||" " in math.getText().toString())
        {
            Toast.makeText(this@MainActivity, "Введено некорректно!", Toast.LENGTH_SHORT)
                .show()//всплывающее сообщение: контекст, текст и продолжительность
            math.text.clear()
            return//?
        }
        else*/
         if(TextUtils.isEmpty(math.getText().toString()) && TextUtils.isEmpty(txtVsp.text.toString()))
        {
            Toast.makeText(this@MainActivity, "Сначала заполните все поля!", Toast.LENGTH_SHORT)
                .show()//всплывающее сообщение: контекст, текст и продолжительность
            return//?
        }
        else
            if (txtVsp.text.toString().isNotEmpty() && math.getText().toString().isEmpty())
                txtOper.text = str
        else
            if(math.getText().toString().isNotEmpty() && txtVsp.text.toString().isNotEmpty())
                osn_oper(str)
        else
        {
            txtOper.text = str
            txtVsp.text = math.text.toString()
            math.text.clear()
        }
    }


    private fun ravno( ) {

        if (math.getText().toString().isEmpty() || txtVsp.text.toString().isEmpty()) {
            Toast.makeText(this@MainActivity, "Сначала заполните все поля!", Toast.LENGTH_SHORT)
                .show()//всплывающее сообщение: контекст, текст и продолжительность
            return//?
        }
        else
            if( "/" in math.getText().toString() || "-" in math.getText().toString()||" " in math.getText().toString())
            {
                Toast.makeText(this@MainActivity, "Введено некорректно!", Toast.LENGTH_SHORT)
                    .show()//всплывающее сообщение: контекст, текст и продолжительность
                math.text.clear()
                return//?
            }
        else {
            osn_oper("!")

        }
    }
}