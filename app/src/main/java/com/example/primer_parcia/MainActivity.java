package com.example.primer_parcia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final int SALARIO_MINIMO=877803;
    Button btn_calcular;
    EditText edt_salario,edt_horas_extra,edt_vlr_hora_extra,edt_total,edt_pension,edt_salud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_calcular=findViewById(R.id.calcular);
        edt_salario=findViewById(R.id.edt_salario);
        edt_horas_extra=findViewById(R.id.edt_horas_extra);
        edt_vlr_hora_extra=findViewById(R.id.edt_vlr_horas_extra);
        edt_total=findViewById(R.id.total);
        edt_pension=findViewById(R.id.edt_pension);
        edt_salud=findViewById(R.id.edt_salud);

        edt_salud.setFocusable(false);
        edt_salud.setEnabled(false);
        edt_pension.setFocusable(false);
        edt_pension.setEnabled(false);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.salario_minimo:
               edt_salario.setText(String.valueOf(SALARIO_MINIMO));
                return true;
            case R.id.limpiar:
                limpiar_campos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void calcular() {
        int salario;
        int horas_extra;
        int vlr_horas_extra;

        try {
           salario  =Integer.parseInt(edt_salario.getText().toString());
        }catch (Exception e ){
            salario=0;

        }

        try {
         horas_extra=   Integer.parseInt(edt_horas_extra.getText().toString());
        }catch (Exception e ){
            horas_extra=0;

        }

        try {
            vlr_horas_extra=Integer.parseInt(edt_vlr_hora_extra.getText().toString());
        }catch (Exception e ){
            vlr_horas_extra=0;

        }

         double salud=calcular_porcentaje(4,salario);
        double pension=calcular_porcentaje(4,salario);
        int total= calcular_total(salario,horas_extra,vlr_horas_extra,pension,salud);
        enviar_resultado(total,salud,pension);




    }

    private void enviar_resultado(int total, double salud, double pension) {
        edt_total.setText(String.valueOf(total));
        edt_salud.setText(String.valueOf(salud));
        edt_pension.setText(String.valueOf(pension));
    }


    private int calcular_total(int salario, int horas_extra, int vlr_horas_extra, double pension, double salud) {
        int costo_horas_extra=horas_extra*vlr_horas_extra;
        int sub_total=costo_horas_extra+salario;
        double total= sub_total-salud-pension;

        return (int)total;
    }

    private double calcular_porcentaje(int porcentaje,int salario) {


        return ((salario*porcentaje)/100);
    }
    private void limpiar_campos(){
        edt_pension.setText("");
        edt_salud.setText("");
        edt_total.setText("");
        edt_vlr_hora_extra.setText("");
        edt_salario.setText("");
        edt_horas_extra.setText("");


    }
}