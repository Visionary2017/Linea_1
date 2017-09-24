package layout;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import pe.edu.sise.applinea1.MenuPrincipalActivity;
import pe.edu.sise.applinea1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Recarga extends Fragment {

    private Button btnSiguiente;
    MenuPrincipalActivity menuPrincipalActivity;
    public Recarga() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_recarga,container,false);
        return view;

    }


}
