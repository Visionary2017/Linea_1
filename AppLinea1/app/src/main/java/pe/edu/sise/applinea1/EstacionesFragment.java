package pe.edu.sise.applinea1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstacionesFragment extends Fragment {


    public EstacionesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_estaciones, container, false);

//        Intent i=new Intent(getActivity(),Lista_Estaciones.class);
//        getActivity().startActivity(i);
        return  view;
    }

}
