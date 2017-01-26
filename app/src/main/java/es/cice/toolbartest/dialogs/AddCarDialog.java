package es.cice.toolbartest.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import es.cice.toolbartest.R;

/**
 * Created by cice on 26/1/17.
 */

public class AddCarDialog extends DialogFragment{

    private EditText modelET,fabricanteET;
    private AddCarInterface mContext;

    public interface AddCarInterface{
        public void addCar(String model,String fabricante);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mContext = (AddCarInterface) context;
        }catch(ClassCastException e){
            throw new ClassCastException("El contexto no implementa el interfaz AddCarInterface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View layout=inflater.inflate(R.layout.add_car_dialog_layout,null);
        modelET= (EditText) layout.findViewById(R.id.modelET);
        fabricanteET= (EditText) layout.findViewById(R.id.fabricanteET);
        builder
                .setMessage(R.string.add_car_dialog_title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String modelo=modelET.getText().toString();
                        String fabricante=fabricanteET.getText().toString();
                        mContext.addCar(modelo,fabricante);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setView(layout);
        return builder.create();
    }
}
