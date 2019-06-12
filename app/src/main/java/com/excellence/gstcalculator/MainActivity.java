package com.excellence.gstcalculator;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout mDemoSlider;
    Button btnSave, buttonBizwinger, buttonRestro, buttonCable;
    EditText et_gst_per, et_sellingamt, et_tax_value, et_basic_value, et_total_amt;
    TextView textViewCall1,textViewCall2;
    private static final int PERMISSION_REQUEST_CODE = 200;
    ArrayList<DataList> dataLists =new ArrayList<DataList>();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle item selection
        switch (item.getItemId()) {
            case R.id.actionHistory:

                GetHistory();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void GetHistory() {

        CustomAdapter customAdapter = null;
            final Dialog dialog=new Dialog(MainActivity.this);
            dialog.setTitle("Select Products");
            dialog.setContentView(R.layout.act_history);

            Toast.makeText(MainActivity.this, "list : "+dataLists.size(), Toast.LENGTH_SHORT).show();
            ListView listview_history= (ListView) dialog.findViewById(R.id.listview_history);
            Button buttonok= (Button) dialog.findViewById(R.id.ok);

            customAdapter=new CustomAdapter(MainActivity.this,dataLists);
            listview_history.setAdapter(customAdapter);

                    buttonok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testting);
        et_total_amt = (EditText) findViewById(R.id.et_total_amt);
        textViewCall1 = (TextView) findViewById(R.id.textViewCall1);
        textViewCall2= (TextView) findViewById(R.id. textViewCall2);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        et_basic_value = (EditText) findViewById(R.id.et_basic_value);
        et_tax_value = (EditText) findViewById(R.id.et_tax_value);
        et_sellingamt = (EditText) findViewById(R.id.et_sellingamt);
        et_gst_per = (EditText) findViewById(R.id.et_gst_per);
        btnSave = (Button) findViewById(R.id.btnSave);
        buttonBizwinger = (Button) findViewById(R.id.buttonBizwinger);
        buttonRestro = (Button) findViewById(R.id.buttonRestro);
        buttonCable = (Button) findViewById(R.id.buttonCable);

        if (!checkPermission()) {

            requestPermission();

        }
        et_sellingamt.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_sellingamt.getWindowToken(), 0);
                }
            }
        });
        textViewCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("mytag","onclick...11");

                String uri = "tel:" + "8237712343";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                startActivity(intent);
            }
        });


        textViewCall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("mytag","onclick...");
                String uri = "tel:" + "8237712344";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                startActivity(intent); }
        });
        buttonBizwinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),URLViewActivity.class);
                intent.putExtra("url","http://www.bizwinger.com/");
                startActivity(intent);
            }
        });
        buttonRestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),URLViewActivity.class);
                intent.putExtra("url","http://www.restocaptain.com/");
                startActivity(intent);
            }
        });
        buttonCable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),URLViewActivity.class);
                intent.putExtra("url","http://www.ccwale.com/");
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_sellingamt.getWindowToken(), 0);

                if (et_sellingamt.getText().toString().equals("")||et_sellingamt.getText().toString().equals("  ")||et_sellingamt.getText().toString().equals(" ")){
                    et_sellingamt.setError("Enter a value...");
                }else if (et_gst_per.getText().toString().equals("")||et_gst_per.getText().toString().equals("  ")||et_gst_per.getText().toString().equals(" ")){
                    et_gst_per.setError("Enter a value...");
                }
                else {
                    et_sellingamt.setError(null);
                    et_gst_per.setError(null);

                    Float percentage = Float.valueOf("" + et_gst_per.getText().toString()) + 100;
                    Float calculate_amt = (float) ((Float.valueOf("" + "" + et_sellingamt.getText().toString())) / percentage) * 100;
                    Float tax_amt = (float) ((calculate_amt) / 100) * Float.valueOf("" + et_gst_per.getText().toString());


                    et_basic_value.setText("" + new DecimalFormat("##.##").format(calculate_amt));
                    et_tax_value.setText("" + new DecimalFormat("##.##").format(tax_amt));

                    Float total_amt = calculate_amt + tax_amt;

                    et_total_amt.setText("" + new DecimalFormat("##.##").format(total_amt));

                    DataList dataList=new DataList();
                    dataList.setSelling_amt(""+et_sellingamt.getText().toString());
                    dataList.setPercentage(""+et_gst_per.getText().toString());
                    dataList.setTax(""+et_tax_value.getText().toString());
                    dataList.setBasicPrice(""+et_basic_value.getText().toString());
                    dataLists.add(dataList);

                    Toast.makeText(MainActivity.this,"Gst Calculated successfully",Toast.LENGTH_SHORT).show();

                }//else





            }
        });

        //for call


        /* posted_by = "111-333-222-4";

 String uri = "tel:" + posted_by.trim() ;
 Intent intent = new Intent(Intent.ACTION_CALL);
 intent.setData(Uri.parse(uri));
 startActivity(intent);*/


        //for slider
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Aim",R.drawable.add_first);
        file_maps.put("Cable Care",R.drawable.cablecare);
        file_maps.put("Bizwinger",R.drawable.add_bizbanner);
        file_maps.put("Resto Captain",R.drawable.add_restob);
        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getApplicationContext());
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit) ;

            mDemoSlider.addSlider(textSliderView);
            mDemoSlider.setDuration(4000);
            mDemoSlider.addOnPageChangeListener(MainActivity.this);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

/*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.logout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){

            case R.id.actionAdd:
                new android.support.v7.app.AlertDialog.Builder(getActivity())
                        .setTitle("Logout Warning")
                        .setIcon(R.drawable.info)
                        .setCancelable(false)
                        .setMessage("Are you sure do this proccess?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                //clear
                                *//*sharedPreferences.edit().clear().commit();
                                getActivity().stopService(new Intent(getActivity(), SyncService.class));
                                DatabaseHelper dbHelper = DatabaseHelper.getInstance(getActivity().getApplicationContext());
                                //delete database
                                  dbHelper.deletedb("newdb.sqlite");*//*
                                *//* Intent intent=new Intent(getActivity(), LoginActForLogOut.class);
                                startActivity(intent);
                                getActivity().finish();*//*
                                Fragment ftBack1 = new LoginActForLogOut();
                                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                                ft1.replace(R.id.container_body, ftBack1);
                                ft1.addToBackStack(null);
                                ft1.commit();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }*/

    private boolean checkPermission() {

        int CALL_PHONE2 = ContextCompat.checkSelfPermission(MainActivity.this, CALL_PHONE);


        return	CALL_PHONE2 == PackageManager.PERMISSION_GRANTED ;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{CALL_PHONE}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {


                    boolean call = grantResults[0] == PackageManager.PERMISSION_GRANTED;


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(CALL_PHONE)) {
                            showMessageOKCancel("You need to allow access the permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{CALL_PHONE},
                                                        PERMISSION_REQUEST_CODE);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }


                break;
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}

