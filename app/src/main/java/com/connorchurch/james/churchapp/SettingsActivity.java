package com.connorchurch.james.churchapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.RingtonePreference;
import android.view.Menu;
import android.view.MenuItem;


public class SettingsActivity extends AppCompatPreferenceActivity  {

    private static final String TAG = SettingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        Configuration config = getResources().getConfiguration();

        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.contact_us_tablet);
        } else {
            setContentView(R.layout.contact_us);
        }

        */
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MainPreferenceFragment()).commit();

    }

    public static class MainPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            addPreferencesFromResource(R.xml.pref_main);


            // feedback preference click listener
            Preference myPref = findPreference(getString(R.string.key_send_feedback));
            myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    sendFeedback(getActivity());
                    return true;
                }
            });

            //wifi/data click listener
            Preference myData = findPreference(getString(R.string.key_data));
            myData.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setComponent(new ComponentName("com.android.settings",
                            "com.android.settings.Settings$DataUsageSummaryActivity"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    return true;
                }
            });


            Preference socialShare = findPreference("key_social");

            socialShare.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent();
                    intent.setAction(android.content.Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, "Hey, check out this app at: google.com");
                    intent.setType("text/plain");
                    startActivity(Intent.createChooser(intent, "Share App"));
                    return true;
                }
            });


            /* help preference click listener
            Preference myHelp = findPreference(getString(R.string.key_help));
            myHelp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    final Intent intent = new Intent(Intent.ACTION_MAIN, null);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    final ComponentName cn = new ComponentName("com.example.james.testapp", "com.example.james.testapp.HelpActivity" );
                    intent.setComponent(cn);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return true;
                }
            });

            */
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    private static void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }
*/
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
            } else if (preference instanceof EditTextPreference) {
                if (preference.getKey().equals("key_gallery_name")) {
                    // update the changed gallery name to summary filed
                    preference.setSummary(stringValue);
                }
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    public static void sendFeedback(Context context) {
        String body = null;
        try {
            body = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            body = "\n\n-----------------------------\nPlease don't remove this information\n Device OS: Android \n Device OS version: " +
                    Build.VERSION.RELEASE + "\n App Version: " + body + "\n Device Brand: " + Build.BRAND +
                    "\n Device Model: " + Build.MODEL + "\n Device Manufacturer: " + Build.MANUFACTURER;
        } catch (PackageManager.NameNotFoundException e) {
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"james.mcc90@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Connor Church app query");
        intent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.choose_email_client)));
    }


}