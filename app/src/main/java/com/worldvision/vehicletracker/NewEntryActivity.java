package com.worldvision.vehicletracker;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.paperdb.Paper;



public class NewEntryActivity extends AppCompatActivity {
    public static final String EXTRA_VEHICLE_ENTRY = "vehicleEntry";
    private LocationManager locationManager = null;
    private Location lastKnownLocation = null;
    private VehicleEntry ve;
    double latitude; // Latitude
    double longitude; // Longitude

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();
        final TextView lblLatLong = (TextView) findViewById(R.id.lblLatLong);
        lblLatLong.setText(latitude + ", " + longitude);
        Intent theIntent = this.getIntent();
        CheckBox chkPresentation = ( CheckBox ) findViewById( R.id.chkPresentation );
        chkPresentation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    LinearLayout one = (LinearLayout) findViewById(R.id.chkPresentationSubLayout);
                    one.setVisibility(View.VISIBLE);
                }else{
                    LinearLayout one = (LinearLayout) findViewById(R.id.chkPresentationSubLayout);
                    one.setVisibility(View.GONE);
                }
            }
        });
        CheckBox chkADPProjectRelated = ( CheckBox ) findViewById( R.id.chkADPProjectRelated );
        chkADPProjectRelated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    LinearLayout one = (LinearLayout) findViewById(R.id.chkADPProjectRelatedSubLayout);
                    one.setVisibility(View.VISIBLE);
                }else{
                    LinearLayout one = (LinearLayout) findViewById(R.id.chkADPProjectRelatedSubLayout);
                    one.setVisibility(View.GONE);
                }
            }
        });
        CheckBox chkStakeholder = ( CheckBox ) findViewById( R.id.chkStakeholder );
        chkStakeholder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    LinearLayout one = (LinearLayout) findViewById(R.id.chkStakeholderSubLayout);
                    one.setVisibility(View.VISIBLE);
                }else{
                    LinearLayout one = (LinearLayout) findViewById(R.id.chkStakeholderSubLayout);
                    one.setVisibility(View.GONE);
                }
            }
        });
        if(theIntent.hasExtra(EXTRA_VEHICLE_ENTRY)) {
            ve = theIntent.getExtras().getParcelable(EXTRA_VEHICLE_ENTRY);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            try {
                Date date = format.parse(ve.dateOfEntry);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);

                ((DatePicker) findViewById(R.id.datePicker)).updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ((EditText) findViewById(R.id.nbrStartMileage)).setText(Integer.toString(ve.startMileage));
            ((EditText) findViewById(R.id.nbrEndMileage)).setText(Integer.toString(ve.endMileage));
            ((EditText) findViewById(R.id.nbrNoOfPassengers)).setText(Integer.toString(ve.noOfPassengers));
            ((EditText) findViewById(R.id.txtMen)).setText(Integer.toString(ve.ben_men));
            ((EditText) findViewById(R.id.txtWomen)).setText(Integer.toString(ve.ben_women));
            ((EditText) findViewById(R.id.txtBoys_05)).setText(Integer.toString(ve.ben_boys_05));
            ((EditText) findViewById(R.id.txtGirls_05)).setText(Integer.toString(ve.ben_girls_05));
            ((EditText) findViewById(R.id.txtBoys_612)).setText(Integer.toString(ve.ben_boys_612));
            ((EditText) findViewById(R.id.txtGirls_612)).setText(Integer.toString(ve.ben_girls_612));
            ((EditText) findViewById(R.id.txtBoys_1318)).setText(Integer.toString(ve.ben_boys_1318));
            ((EditText) findViewById(R.id.txtGirls_1318)).setText(Integer.toString(ve.ben_girls_1318));
            ((EditText) findViewById(R.id.txtRC_05)).setText(Integer.toString(ve.ben_rc_05));
            ((EditText) findViewById(R.id.txtRC_612)).setText(Integer.toString(ve.ben_rc_612));
            ((EditText) findViewById(R.id.txtRC_1318)).setText(Integer.toString(ve.ben_rc_1318));
            ((EditText) findViewById(R.id.txtComments)).setText(ve.generalComments);
            ((CheckBox) findViewById(R.id.chkMobileClinic)).setChecked(ve.chkMobileClinic);
            ((CheckBox) findViewById(R.id.chkPresentation)).setChecked(ve.chkPresentation);
            ((CheckBox) findViewById(R.id.chkPresentation_Health)).setChecked(ve.chkPresentation_Health);
            ((CheckBox) findViewById(R.id.chkPresentation_Education)).setChecked(ve.chkPresentation_Education);
            ((CheckBox) findViewById(R.id.chkPresentation_ChildProtection)).setChecked(ve.chkPresentation_ChildProtection);
            ((CheckBox) findViewById(R.id.chkPresentation_EconomicDevelopment)).setChecked(ve.chkPresentation_EconomicDevelopment);
            ((CheckBox) findViewById(R.id.chkPresentation_Other)).setChecked(ve.chkPresentation_Other);

            ((CheckBox) findViewById(R.id.chkADPEverydayBusiness)).setChecked(ve.chkADPEverydayBusiness);
            ((CheckBox) findViewById(R.id.chkSponsorshipMonitoring)).setChecked(ve.chkSponsorshipMonitoring);
            ((CheckBox) findViewById(R.id.chkADPProjectRelated)).setChecked(ve.chkADPProjectRelated);
            ((CheckBox) findViewById(R.id.chkADPProjectRelated_Health)).setChecked(ve.chkADPProjectRelated_Health);
            ((CheckBox) findViewById(R.id.chkADPProjectRelated_Education)).setChecked(ve.chkADPProjectRelated_Education);
            ((CheckBox) findViewById(R.id.chkADPProjectRelated_ChildProtection)).setChecked(ve.chkADPProjectRelated_ChildProtection);
            ((CheckBox) findViewById(R.id.chkADPProjectRelated_EconomicDevelopment)).setChecked(ve.chkADPProjectRelated_EconomicDevelopment);
            ((CheckBox) findViewById(R.id.chkADPProjectRelated_Other)).setChecked(ve.chkADPProjectRelated_Other);

            ((CheckBox) findViewById(R.id.chkStakeholder)).setChecked(ve.chkStakeholder);
            ((CheckBox) findViewById(R.id.chkStakeholder_Health)).setChecked(ve.chkStakeholder_Health);
            ((CheckBox) findViewById(R.id.chkStakeholder_Education)).setChecked(ve.chkStakeholder_Education);
            ((CheckBox) findViewById(R.id.chkStakeholder_ChildProtection)).setChecked(ve.chkStakeholder_ChildProtection);
            ((CheckBox) findViewById(R.id.chkStakeholder_EconomicDevelopment)).setChecked(ve.chkStakeholder_EconomicDevelopment);
            ((CheckBox) findViewById(R.id.chkStakeholder_Other)).setChecked(ve.chkStakeholder_Other);
            ((CheckBox) findViewById(R.id.chkPurpose_Other)).setChecked(ve.chkPurpose_Other);

            ((CheckBox) findViewById(R.id.chkDoH)).setChecked(ve.chkDoH);
            ((CheckBox) findViewById(R.id.chkDoSD)).setChecked(ve.chkDoSD);
            ((CheckBox) findViewById(R.id.chkDoE)).setChecked(ve.chkDoE);
            ((CheckBox) findViewById(R.id.chkDoA)).setChecked(ve.chkDoA);
            ((CheckBox) findViewById(R.id.chkCBO)).setChecked(ve.chkCBO);

            ((CheckBox) findViewById(R.id.chkCommunityMember)).setChecked(ve.chkCommunityMember);
            ((CheckBox) findViewById(R.id.chkADPStaff)).setChecked(ve.chkADPStaff);
            ((CheckBox) findViewById(R.id.chkPassengersFrom_Other)).setChecked(ve.chkPassengersFrom_Other);
            ((EditText) findViewById(R.id.txtPresentation_Other)).setText(ve.txtPresentation_Other);
            ((EditText) findViewById(R.id.txtProjectRelated_Other)).setText(ve.txtProjectRelated_Other);
            ((EditText) findViewById(R.id.txtCBONGO)).setText(ve.txtCBONGO);
            ((EditText) findViewById(R.id.txtStakeholder_Other)).setText(ve.txtStakeholder_Other);
            ((EditText) findViewById(R.id.txtPassengersFrom_Other)).setText(ve.txtPassengersFrom_Other);
            ((EditText) findViewById(R.id.txtPurpose_Other)).setText(ve.txtPurpose_Other);


        }
        else{
            ve = new VehicleEntry();
        }
    }

    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getBaseContext()
                .getContentResolver();
        // Getting GPS status
        boolean gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // Getting network status
        //boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (gpsStatus) {
            return true;
        } else {
            return false;
        }
    }

    private void getLocation() {
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                latitude = lastKnownLocation.getLatitude();
                longitude = lastKnownLocation.getLongitude();
            }
        }
    }

    public void onBtnCancelClick(View v){
        finish();
    }
    public void onBtnSaveClick(View v){
        if(validState()) {
            ve.startMileage = Integer.parseInt(((EditText) findViewById(R.id.nbrStartMileage)).getText().toString());
            ve.endMileage = Integer.parseInt(((EditText) findViewById(R.id.nbrEndMileage)).getText().toString());
            ve.noOfPassengers = Integer.parseInt(((EditText) findViewById(R.id.nbrNoOfPassengers)).getText().toString());
            ve.generalComments = ((EditText) findViewById(R.id.txtComments)).getText().toString();

            ve.ben_men = Integer.parseInt(((EditText) findViewById(R.id.txtMen)).getText().toString());
            ve.ben_women = Integer.parseInt(((EditText) findViewById(R.id.txtWomen)).getText().toString());
            ve.ben_boys_05 = Integer.parseInt(((EditText) findViewById(R.id.txtBoys_05)).getText().toString());
            ve.ben_girls_05 = Integer.parseInt(((EditText) findViewById(R.id.txtGirls_05)).getText().toString());
            ve.ben_boys_612 = Integer.parseInt(((EditText) findViewById(R.id.txtBoys_612)).getText().toString());
            ve.ben_girls_612 = Integer.parseInt(((EditText) findViewById(R.id.txtGirls_612)).getText().toString());
            ve.ben_boys_1318 = Integer.parseInt(((EditText) findViewById(R.id.txtBoys_1318)).getText().toString());
            ve.ben_girls_1318 = Integer.parseInt(((EditText) findViewById(R.id.txtGirls_1318)).getText().toString());
            ve.ben_rc_05 = Integer.parseInt(((EditText) findViewById(R.id.txtRC_05)).getText().toString());
            ve.ben_rc_612 = Integer.parseInt(((EditText) findViewById(R.id.txtRC_612)).getText().toString());
            ve.ben_rc_1318 = Integer.parseInt(((EditText) findViewById(R.id.txtRC_1318)).getText().toString());
            ve.dateCreated = getCurrentTimeStamp();
            ve.dateOfEntry = getDateFromDatePicker((DatePicker) findViewById(R.id.datePicker));
            ve.chkMobileClinic = ((CheckBox) findViewById(R.id.chkMobileClinic)).isChecked();
            ve.chkPresentation = ((CheckBox) findViewById(R.id.chkPresentation)).isChecked();
            ve.chkPresentation_Health = ((CheckBox) findViewById(R.id.chkPresentation_Health)).isChecked();
            ve.chkPresentation_Education = ((CheckBox) findViewById(R.id.chkPresentation_Education)).isChecked();
            ve.chkPresentation_ChildProtection = ((CheckBox) findViewById(R.id.chkPresentation_ChildProtection)).isChecked();
            ve.chkPresentation_EconomicDevelopment = ((CheckBox) findViewById(R.id.chkPresentation_EconomicDevelopment)).isChecked();
            ve.chkPresentation_Other = ((CheckBox) findViewById(R.id.chkPresentation_Other)).isChecked();
            ve.chkADPEverydayBusiness = ((CheckBox) findViewById(R.id.chkADPEverydayBusiness)).isChecked();
            ve.chkSponsorshipMonitoring = ((CheckBox) findViewById(R.id.chkSponsorshipMonitoring)).isChecked();
            ve.chkADPProjectRelated = ((CheckBox) findViewById(R.id.chkADPProjectRelated)).isChecked();
            ve.chkADPProjectRelated_Health = ((CheckBox) findViewById(R.id.chkADPProjectRelated_Health)).isChecked();
            ve.chkADPProjectRelated_Education = ((CheckBox) findViewById(R.id.chkADPProjectRelated_Education)).isChecked();
            ve.chkADPProjectRelated_ChildProtection = ((CheckBox) findViewById(R.id.chkADPProjectRelated_ChildProtection)).isChecked();
            ve.chkADPProjectRelated_EconomicDevelopment = ((CheckBox) findViewById(R.id.chkADPProjectRelated_EconomicDevelopment)).isChecked();
            ve.chkADPProjectRelated_Other = ((CheckBox) findViewById(R.id.chkADPProjectRelated_Other)).isChecked();
            ve.chkStakeholder = ((CheckBox) findViewById(R.id.chkStakeholder)).isChecked();
            ve.chkStakeholder_Health = ((CheckBox) findViewById(R.id.chkStakeholder_Health)).isChecked();
            ve.chkStakeholder_Education = ((CheckBox) findViewById(R.id.chkStakeholder_Education)).isChecked();
            ve.chkStakeholder_ChildProtection = ((CheckBox) findViewById(R.id.chkStakeholder_ChildProtection)).isChecked();
            ve.chkStakeholder_EconomicDevelopment = ((CheckBox) findViewById(R.id.chkStakeholder_EconomicDevelopment)).isChecked();
            ve.chkStakeholder_Other = ((CheckBox) findViewById(R.id.chkStakeholder_Other)).isChecked();
            ve.chkPurpose_Other = ((CheckBox) findViewById(R.id.chkPurpose_Other)).isChecked();

            ve.chkDoH = ((CheckBox) findViewById(R.id.chkDoH)).isChecked();
            ve.chkDoSD = ((CheckBox) findViewById(R.id.chkDoSD)).isChecked();
            ve.chkDoE = ((CheckBox) findViewById(R.id.chkDoE)).isChecked();
            ve.chkDoA = ((CheckBox) findViewById(R.id.chkDoA)).isChecked();
            ve.chkCBO = ((CheckBox) findViewById(R.id.chkCBO)).isChecked();
            ve.chkCommunityMember = ((CheckBox) findViewById(R.id.chkCommunityMember)).isChecked();
            ve.chkADPStaff = ((CheckBox) findViewById(R.id.chkADPStaff)).isChecked();
            ve.chkPassengersFrom_Other = ((CheckBox) findViewById(R.id.chkPassengersFrom_Other)).isChecked();
            ve.txtPresentation_Other = ((EditText) findViewById(R.id.txtPresentation_Other)).getText().toString();
            ve.txtProjectRelated_Other = ((EditText) findViewById(R.id.txtProjectRelated_Other)).getText().toString();
            ve.txtStakeholder_Other = ((EditText) findViewById(R.id.txtStakeholder_Other)).getText().toString();
            ve.txtCBONGO = ((EditText) findViewById(R.id.txtCBONGO)).getText().toString();
            ve.txtPassengersFrom_Other = ((EditText) findViewById(R.id.txtPassengersFrom_Other)).getText().toString();
            ve.txtPurpose_Other = ((EditText) findViewById(R.id.txtPurpose_Other)).getText().toString();

            ArrayList<VehicleEntry> data = Paper.book().read("entries");
            if (data == null) {
                data = new ArrayList<VehicleEntry>();
            }
            if (ve.id == null) {
                ve.id = UUID.randomUUID().toString();
                data.add(ve);
            } else {
                //find in data and replace, not the most efficient, but should work...
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).id.equals(ve.id)) {
                        data.set(i, ve);
                    }
                }
            }

            Paper.book().write("entries", data);

            Intent result = new Intent();
            setResult(RESULT_OK);

            finish();
        }else{
            new AlertDialog.Builder(NewEntryActivity.this)
                    .setTitle("Missing Required Fields")
                    .setMessage("You have missed a required field. Please make sure you have entered a start/end mileage, the purpose of your visit and the passengers in your vehicle")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
    private boolean validState(){
        if(((EditText) findViewById(R.id.nbrStartMileage)).getText().toString().equals("")) return false;
        if(((EditText) findViewById(R.id.nbrEndMileage)).getText().toString().equals("")) return false;
        if(((EditText) findViewById(R.id.nbrNoOfPassengers)).getText().toString().equals("")) return false;

        return true;
    }
    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }
    /**
     *
     * @param datePicker
     * @return a java.util.Date
     */
    public String getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");//dd/MM/yyyy

        return sdf.format(calendar.getTime());
    }
}
