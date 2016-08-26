package com.facci.proyectoslpl;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivitySLPL extends AppCompatActivity {


    DBHelper baseSQLITE;
    EditText campo_nombre_slpl ,campo_apellido_slpl,campo_recintoelectoral_slpl,campo_añonacimiento_slpl,campo_id_slpl;
    Button btnIngresar, btnBuscarTodos,btnModificar,btnEliminar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_slpl);
        baseSQLITE = new DBHelper(this);

    }
    public void ingresarClick(View v){

        campo_nombre_slpl = (EditText) findViewById(R.id.campo_nombre_slpl);
        campo_apellido_slpl = (EditText) findViewById(R.id.campo_apellido_slpl);
        campo_recintoelectoral_slpl = (EditText) findViewById(R.id.campo_recintoelectoral_slpl);
        campo_añonacimiento_slpl=(EditText)findViewById(R.id.campo_añonacimiento_slpl);

        boolean estaIngresado = baseSQLITE.ingresar(campo_nombre_slpl.getText().toString(),campo_apellido_slpl.getText().toString(),campo_recintoelectoral_slpl.getText().toString(),Integer.parseInt(campo_añonacimiento_slpl.getText().toString()));

        if(estaIngresado)
            Toast.makeText(MainActivitySLPL.this,"Datos Ingresados",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivitySLPL.this,"Lo sentimos ocurrió un error",Toast.LENGTH_SHORT).show();

    }
    public void BuscarTodosClick(View v){

        Cursor res = baseSQLITE.selectBuscarTodos();
        if(res.getCount() == 0){

            mensaje("Error","No se encontraron registros");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral : "+ res.getString(3)+"\n" );
            buffer.append("Año Nacimiento : "+res.getInt(4)+"\n\n");
        }

        mensaje("Registros",buffer.toString());
    }
    public void mensaje(String titulo, String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();

    }
    public void modificarClick(View v){


        campo_nombre_slpl = (EditText) findViewById(R.id.campo_nombre_slpl);
        campo_apellido_slpl = (EditText) findViewById(R.id.campo_apellido_slpl);
        campo_recintoelectoral_slpl = (EditText) findViewById(R.id.campo_recintoelectoral_slpl);
        campo_añonacimiento_slpl = (EditText) findViewById(R.id.campo_añonacimiento_slpl);
        campo_id_slpl = (EditText) findViewById(R.id.campo_id_slpl);


        boolean estaAcutalizado = baseSQLITE.modificar(campo_id_slpl.getText().toString(),campo_nombre_slpl.getText().toString(),campo_apellido_slpl.getText().toString(),campo_recintoelectoral_slpl.getText().toString(),Integer.parseInt(campo_añonacimiento_slpl.getText().toString()));

        if (estaAcutalizado == true){
            Toast.makeText(MainActivitySLPL.this,"Registro Actualizado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivitySLPL.this,"ERROR: Registro NO Actualizado",Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarClick(View v){


        campo_id_slpl = (EditText) findViewById(R.id.campo_id_slpl);


        Integer Eliminados = baseSQLITE.eliminar(campo_id_slpl.getText().toString());

        if(Eliminados > 0 ){
            Toast.makeText(MainActivitySLPL.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivitySLPL.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }
    }
}
