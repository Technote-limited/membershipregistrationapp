package com.example.dhl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.dhl.Activities.AgentLoginActivity;
import com.example.dhl.model.Agent;
import com.example.dhl.model.Members;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "mrpsharedpref";
    private static final String KEY_ID= "keyid";
    private static final String KEY_AGENTNAME = "keyagentname";
    private static final String KEY_IDPASSPORT = "keyidpassport";
    private static final String KEY_MEMBERNUMBER = "keymembernumber";
    private static final String KEY_SURNAME = "keysurname";
    private static final String KEY_FIRSTNAME = "keyfirstname";
    private static final String KEY_MIDDLENAME = "keymiddlename";
    private static final String KEY_PHONENUMBER = "keyphonenumber";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_DOB = "keydob";
    private static final String KEY_COUNTY= "keycounty";
    private static final String KEY_CONSTITUENCY = "keyconstituency";
    private static final String KEY_WARD = "keyward";
    private static final String KEY_DATECREATED = "keydatecreated";
    private static final String KEY_ISACTIVE = "keyisactive";
    private static final String KEY_DATEJOINED = "keydatejoined";
    private static final String KEY_MEMBERPICTURE = "keymemberpicture";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }
    //method to let the user login
    //this method will store the user data in shared preferences
    public void saveUser(Members user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_IDPASSPORT, user.getIdPassport());
        editor.putString(KEY_MEMBERNUMBER, user.getMemberNumber());
        editor.putString(KEY_FIRSTNAME, user.getFirstName());
        editor.putString(KEY_SURNAME, user.getSurname());
        editor.putString(KEY_MIDDLENAME, user.getMiddleName());
        editor.putString(KEY_DOB, user.getDob());
        editor.putString(KEY_GENDER, user.getGender());
        editor.putString(KEY_COUNTY, user.getCounty());
        editor.putString(KEY_CONSTITUENCY, user.getConstituency());
        editor.putString(KEY_WARD, user.getWard());

        editor.apply();
    }
    public void saveAgent(Agent agent) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_ID, agent.getId());
        editor.putString(KEY_AGENTNAME, agent.getAgentName());
        editor.putString(KEY_IDPASSPORT, agent.getId_passport());
        editor.putString(KEY_MEMBERNUMBER, agent.getMember_number());
        editor.putString(KEY_PHONENUMBER, agent.getPhone_number());
        editor.putString(KEY_DOB, agent.getDob());
        editor.putString(KEY_GENDER, agent.getGender());
        editor.putString(KEY_COUNTY, agent.getCounty());
        editor.putString(KEY_CONSTITUENCY, agent.getConstituency());
        editor.putString(KEY_WARD, agent.getWard());
        editor.putString(KEY_DATECREATED,agent.getDate_created());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id" ,-1) != -1;
    }
    public boolean isActive() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isactive" ,isActive());
    }






  /*  public Members getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Members(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_IDPASSPORT, null),
                sharedPreferences.getString(KEY_MEMBERNUMBER, null),
                sharedPreferences.getString(KEY_FIRSTNAME, null),
                sharedPreferences.getString(KEY_SURNAME, null),
                sharedPreferences.getString(KEY_MIDDLENAME, null),
                sharedPreferences.getString(KEY_PHONENUMBER, null),
                sharedPreferences.getString(KEY_DOB, null),
                sharedPreferences.getString(KEY_GENDER, null),
                sharedPreferences.getString(KEY_COUNTY, null),
                sharedPreferences.getString(KEY_CONSTITUENCY, null),
                sharedPreferences.getString(KEY_WARD, null),
                sharedPreferences.getString(KEY_DATEJOINED,null)

        );
    }*/
    public Agent getAgent() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Agent(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_AGENTNAME, null),
                sharedPreferences.getString(KEY_IDPASSPORT, null),
                sharedPreferences.getString(KEY_MEMBERNUMBER, null),
                sharedPreferences.getString(KEY_PHONENUMBER, null),
                sharedPreferences.getString(KEY_DOB, null),
                sharedPreferences.getString(KEY_GENDER, null),
                sharedPreferences.getString(KEY_COUNTY, null),
                sharedPreferences.getString(KEY_CONSTITUENCY, null),
                sharedPreferences.getString(KEY_WARD, null),
                sharedPreferences.getString(KEY_DATECREATED, null)
        );
    }
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, AgentLoginActivity.class));
    }

}
