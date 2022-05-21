package vn.com.greenwich.rental;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Date;


public class AddFragment extends Fragment {

private Button b;
private EditText rip;
private EditText aip;
private EditText pip;
private TextView tx;
private EditText rtip;
private Button errorbtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);
            b = v.findViewById(R.id.buttonsubmit);
            rip = v.findViewById(R.id.rentalnameinput);
            aip = v.findViewById(R.id.addressinput);
            pip = v.findViewById(R.id.priceinput);
            tx = v.findViewById(R.id.texterror);
            errorbtn = v.findViewById(R.id.buttonsubmit);
            rtip = v.findViewById(R.id.reporterinput);
            Date date = Calendar.getInstance().getTime();

            Spinner typelist = v.findViewById(R.id.typeinput);
            String[] items = new String[]{"Property's Type", "Bungalow", "Detached House", "Semi-Detached House", "TownHouse"};
            ArrayAdapter adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
            typelist.setAdapter(adapter);

            Spinner bedlist = v.findViewById(R.id.bedinput);
            String[] bitems = new String[]{"Numbers of Bedroom", "1", "2", "3", "4"};
            ArrayAdapter adapter1 = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, bitems);
            bedlist.setAdapter(adapter1);

            Spinner furlist = v.findViewById(R.id.furinput);
            String[] fitems = new String[]{"Unfurnished","Furnished", "Part Furnished"};
            ArrayAdapter adapter2 = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, fitems);
            furlist.setAdapter(adapter2);

            final MediaPlayer errorbtn = MediaPlayer.create(this.getActivity(), R.raw.alert);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Property Confirmation");
                builder.setMessage("Do you want to confirm your property ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if (rip.length() > 0 && aip.length() >0 && pip.length() > 0 && !typelist.getSelectedItem().toString().equals("Property's Type") &&
                                !bedlist.getSelectedItem().toString().equals("Numbers of Bedroom") && rtip.length() >0 )
                        {

                            String message = "Property's Name:\t" + rip.getText().toString();
                                   message += "\nAddress:\t" + aip.getText().toString();
                                   message += "\nPrice:\t" + pip.getText().toString();
                                   message += "\nType:\t" + typelist.getSelectedItem().toString();
                                   message += "\nFurniture:\t" + furlist.getSelectedItem().toString();
                                   message += "\nBedroom:\t" + bedlist.getSelectedItem().toString();
                                   message += "\nReporter:\t" + rtip.getText().toString();
                                   message += "\nDate:\t" + date.toString();

                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("Property Information");
                            builder.setMessage(message.toString());
                            builder.setCancelable(false);
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();
                                    tx.setText("Rental Add");
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        }
                        else {
                            tx.setText("Uh Oh ! You Missing Some Information !");
                            errorbtn.start();
                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



            }
        });
        return v;
    }

}
