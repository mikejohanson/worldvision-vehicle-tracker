package com.worldvision.vehicletracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.paperdb.Paper;

public class ExportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        sendEmail();

    }

    public Uri GenerateCsv() {
        String columnString = "\"Date\"" +
                ",\"Ward\"" +
                ",\"Village\"" +
                ",\"GPS\"" +
                ",\"Starting Mileage\"" +
                ",\"Ending Mileage\"" +
                ",\"Purpose of Visit\"" +
                ",\"Passengers From\"" +
                ",\"# Of Passengers in Vehicle\"" +
                ",\"Comments\"" +
                ",\"Beneficiaries-Men\"" +
                ",\"Beneficiaries-Women\"" +
                ",\"Beneficiaries-Boys (0-5)\"" +
                ",\"Beneficiaries-Boys (6-12)\"" +
                ",\"Beneficiaries-Boys (13-18)\"" +
                ",\"Beneficiaries-Girls (0-5)\"" +
                ",\"Beneficiaries-Girls (6-12)\"" +
                ",\"Beneficiaries-Girls (13-18)\"" +
                ",\"Beneficiaries-RC (0-5)\"" +
                ",\"Beneficiaries-RC (6-12)\"" +
                ",\"Beneficiaries-RC (13-18)\"";
        StringBuilder dataString = new StringBuilder();
        ArrayList<VehicleEntry> mList =  Paper.book().read("entries");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        for(int i = 0; i < mList.size(); i++){
            VehicleEntry ve = mList.get(i);

            try {
                Date date = format.parse(ve.dateOfEntry);
                dataString.append(date.toString());
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                //    e.printStackTrace();
                dataString.append("");
            }
            dataString.append(",\"").append(ve.ward).append("\"");
            dataString.append(",\"").append(ve.village).append("\"");
            dataString.append(",\"").append(ve.latitude).append(",").append(ve.longitude).append("\""); // GPS

            dataString.append(",").append(ve.startMileage);
            dataString.append(",").append(ve.endMileage);
            dataString.append(",\""); // Purpose of Visit
            if(ve.chkMobileClinic) dataString.append("Mobile Clinic\n");
            if(ve.chkPresentation) dataString.append("Presentation - ");
                if(ve.chkPresentation_Health) dataString.append("\tHealth\n");
                if(ve.chkPresentation_Education) dataString.append("\tEducation\n");
                if(ve.chkPresentation_ChildProtection) dataString.append("\tChild Protection\n");
                if(ve.chkPresentation_EconomicDevelopment) dataString.append("\tEconomic Development\n");
                if(ve.chkPresentation_Other) dataString.append("\t").append(ve.txtPresentation_Other).append("\n");
            if(ve.chkADPEverydayBusiness) dataString.append("ADP Everyday Business - ");
                if(ve.chkADPProjectRelated_Health) dataString.append("\tHealth\n");
                if(ve.chkADPProjectRelated_Education) dataString.append("\tEducation\n");
                if(ve.chkADPProjectRelated_ChildProtection) dataString.append("\tChild Protection\n");
                if(ve.chkADPProjectRelated_EconomicDevelopment) dataString.append("\tEconomic Development\n");
                if(ve.chkADPProjectRelated_Other) dataString.append("\t").append(ve.txtPresentation_Other).append("\n");
            if(ve.chkStakeholder) dataString.append("Stakeholder Meeting - ");
                if(ve.chkStakeholder_Health) dataString.append("\tHealth\n");
                if(ve.chkStakeholder_Education) dataString.append("\tEducation\n");
                if(ve.chkStakeholder_ChildProtection) dataString.append("\tChild Protection\n");
                if(ve.chkStakeholder_EconomicDevelopment) dataString.append("\tEconomic Development\n");
                if(ve.chkStakeholder_Other) dataString.append(ve.txtPresentation_Other).append("\n");
            if(ve.chkPurpose_Other) dataString.append("\t").append("Other - ").append(ve.txtPurpose_Other);
            dataString.append("\",\""); // Passengers From
            if(ve.chkDoH) dataString.append("Department of Health\n");
            if(ve.chkDoSD) dataString.append("Department of Social Development\n");
            if(ve.chkDoE) dataString.append("Department of Education\n");
            if(ve.chkDoA) dataString.append("Department of Agriculture\n");
            if(ve.chkCBO) dataString.append("CBO/NGO - ").append(ve.txtCBONGO).append("\n");
            if(ve.chkCommunityMember) dataString.append("Community Member\n");
            if(ve.chkADPStaff) dataString.append("ADP Staff\n");
            if(ve.chkPassengersFrom_Other) dataString.append("Other - ").append(ve.txtPassengersFrom_Other);

            dataString.append("\",").append(Integer.toString(ve.noOfPassengers));
            dataString.append(",\"").append(ve.generalComments).append("\"");
            //beneficiaries
            dataString.append(",").append(ve.ben_men);
            dataString.append(",").append(ve.ben_women);
            dataString.append(",").append(ve.ben_boys_05);
            dataString.append(",").append(ve.ben_boys_612);
            dataString.append(",").append(ve.ben_boys_1318);
            dataString.append(",").append(ve.ben_girls_05);
            dataString.append(",").append(ve.ben_girls_612);
            dataString.append(",").append(ve.ben_girls_1318);
            dataString.append(",").append(ve.ben_rc_05);
            dataString.append(",").append(ve.ben_rc_612);
            dataString.append(",").append(ve.ben_rc_1318);

            //next line/next record
            dataString.append("\n");

        }
        // String dataString   =   "\"" + currentUser.userName +"\",\"" + currentUser.gender + "\",\"" + currentUser.street1 + "\",\"" + currentUser.postOFfice.toString()+ "\",\"" + currentUser.age.toString() + "\"";
        String combinedString = columnString + "\n" + dataString.toString();

        File file = null;
        File root = Environment.getExternalStorageDirectory();
        if (root.canWrite()) {
            File dir = new File(root.getAbsolutePath() + "/VehicleDataExport");
            dir.mkdirs();
            file = new File(dir, "Data.csv");
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                out.write(combinedString.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return Uri.fromFile(file);

    }
    private void sendEmail(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"mikejohanson@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Vehicle Tracker Export - ");
        i.putExtra(Intent.EXTRA_TEXT, "body of email");
        Uri dataExport = GenerateCsv();
        i.putExtra(Intent.EXTRA_STREAM, dataExport);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ExportActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
    public void onBtnExportClick(View v){
        sendEmail();
    }
    public void onBtnClearClick(View v) {
        new AlertDialog.Builder(ExportActivity.this)
                .setTitle("Delete All Entries")
                .setMessage("Are you sure you want to delete all entries? You cannot undo this operation.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Paper.book().destroy();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


}
