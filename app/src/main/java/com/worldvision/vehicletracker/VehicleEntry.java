package com.worldvision.vehicletracker;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * Created by mjohanso on 5/23/2016.
 */
public class VehicleEntry implements Parcelable {
    String id;
    int startMileage;
    int endMileage;
    int noOfPassengers;
    String generalComments;
    String dateCreated;
    String dateOfEntry;
    double latitude;
    double longitude;
    String purposeOfVisit;
    String passengersFrom;
    int ben_men;
    int ben_women;
    int ben_boys_05;
    int ben_girls_05;
    int ben_rc_05;
    int ben_boys_612;
    int ben_girls_612;
    int ben_rc_612;
    int ben_boys_1318;
    int ben_girls_1318;
    int ben_rc_1318;
    boolean chkMobileClinic;
    boolean chkPresentation;
    boolean chkPresentation_Health;
    boolean chkPresentation_Education;
    boolean chkPresentation_ChildProtection;
    boolean chkPresentation_EconomicDevelopment;
    boolean chkPresentation_Other;
    String  txtPresentation_Other;
    boolean chkADPEverydayBusiness;
    boolean chkSponsorshipMonitoring;
    boolean chkADPProjectRelated;
    boolean chkADPProjectRelated_Health;
    boolean chkADPProjectRelated_Education;
    boolean chkADPProjectRelated_ChildProtection;
    boolean chkADPProjectRelated_EconomicDevelopment;
    boolean chkADPProjectRelated_Other;
    String  txtProjectRelated_Other;
    boolean chkStakeholder;
    boolean chkStakeholder_Health;
    boolean chkStakeholder_Education;
    boolean chkStakeholder_ChildProtection;
    boolean chkStakeholder_EconomicDevelopment;
    boolean chkStakeholder_Other;
    String  txtStakeholder_Other;
    boolean chkPurpose_Other;
    String  txtPurpose_Other;

    boolean chkDoH;
    boolean chkDoSD;
    boolean chkDoE;
    boolean chkDoA;
    boolean chkCBO;
    String  txtCBONGO;
    boolean chkCommunityMember;
    boolean chkADPStaff;
    boolean chkPassengersFrom_Other;
    String  txtPassengersFrom_Other;


    public VehicleEntry(){

    }
    public VehicleEntry(Parcel in){
        readFromParcel(in);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public VehicleEntry createFromParcel(Parcel in ) {
            return new VehicleEntry( in );
        }

        public VehicleEntry[] newArray(int size) {
            return new VehicleEntry[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(startMileage);
        dest.writeInt(endMileage);
        dest.writeInt(noOfPassengers);
        dest.writeString(generalComments);
        dest.writeString(dateCreated);
        dest.writeString(dateOfEntry);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(purposeOfVisit);
        dest.writeString(passengersFrom);
        dest.writeInt(ben_men);
        dest.writeInt(ben_women);
        dest.writeInt(ben_boys_05);
        dest.writeInt(ben_girls_05);
        dest.writeInt(ben_rc_05);
        dest.writeInt(ben_boys_612);
        dest.writeInt(ben_girls_612);
        dest.writeInt(ben_rc_612);
        dest.writeInt(ben_boys_1318);
        dest.writeInt(ben_girls_1318);
        dest.writeInt(ben_rc_1318);
        dest.writeByte((byte) (chkMobileClinic ? 1 : 0));
        dest.writeByte((byte) (chkPresentation_Health ? 1 : 0));
        dest.writeByte((byte) (chkPresentation_Education ? 1 : 0));
        dest.writeByte((byte) (chkPresentation_ChildProtection ? 1 : 0));
        dest.writeByte((byte) (chkPresentation_EconomicDevelopment ? 1 : 0));
        dest.writeByte((byte) (chkPresentation_Other ? 1 : 0));
        dest.writeByte((byte) (chkADPEverydayBusiness ? 1 : 0));
        dest.writeByte((byte) (chkSponsorshipMonitoring ? 1 : 0));
        dest.writeByte((byte) (chkADPProjectRelated ? 1 : 0));
        dest.writeByte((byte) (chkADPProjectRelated_Health ? 1 : 0));
        dest.writeByte((byte) (chkADPProjectRelated_Education ? 1 : 0));
        dest.writeByte((byte) (chkADPProjectRelated_ChildProtection ? 1 : 0));
        dest.writeByte((byte) (chkADPProjectRelated_EconomicDevelopment ? 1 : 0));
        dest.writeByte((byte) (chkADPProjectRelated_Other ? 1 : 0));
        dest.writeByte((byte) (chkStakeholder ? 1 : 0));
        dest.writeByte((byte) (chkStakeholder_Health ? 1 : 0));
        dest.writeByte((byte) (chkStakeholder_Education ? 1 : 0));
        dest.writeByte((byte) (chkStakeholder_ChildProtection ? 1 : 0));
        dest.writeByte((byte) (chkStakeholder_EconomicDevelopment ? 1 : 0));
        dest.writeByte((byte) (chkStakeholder_Other ? 1 : 0));
        dest.writeByte((byte) (chkPresentation ? 1 : 0));
        dest.writeByte((byte) (chkPurpose_Other ? 1 : 0));
        dest.writeByte((byte) (chkDoH ? 1 : 0));
        dest.writeByte((byte) (chkDoSD ? 1 : 0));
        dest.writeByte((byte) (chkDoE ? 1 : 0));
        dest.writeByte((byte) (chkDoA ? 1 : 0));
        dest.writeByte((byte) (chkCBO ? 1 : 0));
        dest.writeByte((byte) (chkCommunityMember ? 1 : 0));
        dest.writeByte((byte) (chkADPStaff ? 1 : 0));
        dest.writeByte((byte) (chkPassengersFrom_Other ? 1 : 0));

        dest.writeString(txtPresentation_Other);
        dest.writeString(txtProjectRelated_Other);
        dest.writeString(txtStakeholder_Other);
        dest.writeString(txtCBONGO);
        dest.writeString(txtPassengersFrom_Other);
        dest.writeString(txtPurpose_Other);


    }
    public void readFromParcel(Parcel in){
        id = in.readString();
        startMileage = in.readInt();
        endMileage = in.readInt();
        noOfPassengers = in.readInt();
        generalComments = in.readString();
        dateCreated = in.readString();
        dateOfEntry = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        purposeOfVisit = in.readString();
        passengersFrom = in.readString();
        ben_men = in.readInt();
        ben_women = in.readInt();
        ben_boys_05 = in.readInt();
        ben_girls_05 = in.readInt();
        ben_rc_05 = in.readInt();
        ben_boys_612 = in.readInt();
        ben_girls_612 = in.readInt();
        ben_rc_612 = in.readInt();
        ben_boys_1318 = in.readInt();
        ben_girls_1318 = in.readInt();
        ben_rc_1318 = in.readInt();
        chkMobileClinic = in.readByte() != 0;
        chkPresentation_Health = in.readByte() != 0;
        chkPresentation_Education = in.readByte() != 0;
        chkPresentation_ChildProtection = in.readByte() != 0;
        chkPresentation_EconomicDevelopment = in.readByte() != 0;
        chkPresentation_Other = in.readByte() != 0;
        chkADPEverydayBusiness = in.readByte() != 0;
        chkSponsorshipMonitoring = in.readByte() != 0;
        chkADPProjectRelated = in.readByte() != 0;
        chkADPProjectRelated_Health = in.readByte() != 0;
        chkADPProjectRelated_Education = in.readByte() != 0;
        chkADPProjectRelated_ChildProtection = in.readByte() != 0;
        chkADPProjectRelated_EconomicDevelopment = in.readByte() != 0;
        chkADPProjectRelated_Other = in.readByte() != 0;
        chkStakeholder = in.readByte() != 0;
        chkStakeholder_Health = in.readByte() != 0;
        chkStakeholder_Education = in.readByte() != 0;
        chkStakeholder_ChildProtection = in.readByte() != 0;
        chkStakeholder_EconomicDevelopment = in.readByte() != 0;
        chkStakeholder_Other = in.readByte() != 0;
        chkPresentation = in.readByte() != 0;
        chkPurpose_Other = in.readByte() != 0;
        chkDoH = in.readByte() != 0;
        chkDoSD = in.readByte() != 0;
        chkDoE = in.readByte() != 0;
        chkDoA = in.readByte() != 0;
        chkCBO = in.readByte() != 0;
        chkCommunityMember = in.readByte() != 0;
        chkADPStaff = in.readByte() != 0;
        chkPassengersFrom_Other = in.readByte() != 0;
        txtPresentation_Other = in.readString();
        txtProjectRelated_Other = in.readString();
        txtStakeholder_Other = in.readString();
        txtCBONGO = in.readString();
        txtPassengersFrom_Other = in.readString();
        txtPurpose_Other = in.readString();
    }
}
