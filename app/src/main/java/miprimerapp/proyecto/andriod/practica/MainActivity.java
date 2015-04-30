package miprimerapp.proyecto.andriod.practica;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    EditText Ed_id,Edt_user,Et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ed_id = (EditText) findViewById(R.id.ed_id);
        Edt_user = (EditText) findViewById(R.id.edt_user);
        Et_name = (EditText)findViewById(R.id.et_name);


    }



    public void alta (View v) {
        try {

            segunda admin = new segunda(this, "tienda", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String id = Ed_id.getText().toString();
            String user = Edt_user.getText().toString();

            String name = Et_name.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("id", id);
            registro.put("user", user);

            registro.put("name", name);


            bd.insert("tienda", null, registro);
            bd.close();


            Ed_id.setText("");
            Edt_user.setText("");

            Et_name.setText("");


            Toast.makeText(this, "Se agrego un nuevo usuario", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, "ERROR:"+ex, Toast.LENGTH_SHORT).show();
        }
    }


    public void consulta(View v) {
        try {
            segunda admin = new segunda(this, "tienda", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String id = Ed_id.getText().toString();
            Cursor fila = bd.rawQuery("select user, name from tienda where id=" + id, null);
            if (fila.moveToFirst()) {
                Edt_user.setText(fila.getString(0));

                Et_name.setText(fila.getString(1));

            } else {
                Toast.makeText(this, "No existe el usuario que indico", Toast.LENGTH_SHORT).show();
            }

            bd.close();
        }
        catch (Exception ex )
        {
            Toast.makeText(this, "ERROR:"+ex, Toast.LENGTH_SHORT).show();

        }
    }






    public void baja(View v) {
        segunda admin = new segunda(this, "tienda", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = Ed_id.getText().toString();
        int cant = bd.delete("tienda","id=" + id, null);
        bd.close();

        Ed_id.setText("");
        Edt_user.setText("");

        Et_name.setText("");


        if (cant == 1) {
            Toast.makeText(this, "Se borró el usuario que indico",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el usuario que indico",Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion (View v) {
        segunda admin = new segunda(this, "tienda", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String id = Ed_id.getText().toString();
        String user = Edt_user.getText().toString();

        String name = Et_name.getText().toString();


        ContentValues registro = new ContentValues();

        registro.put("id", id);
        registro.put("user", user);

        registro.put("name", name);

        int cant = bd.update("tienda", registro, "id=" + id, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modificaron los datos del usuario",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el usuario que indico",Toast.LENGTH_SHORT).show();
        }

    }

    public void limpia (View v){
        Ed_id.setText("");
        Edt_user.setText("");

        Et_name.setText("");



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
