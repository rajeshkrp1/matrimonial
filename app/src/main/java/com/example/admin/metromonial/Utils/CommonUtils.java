package com.example.admin.metromonial.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.metromonial.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Admin on 11/24/2017.
 */

public class CommonUtils {

    public final static String DISPLAY_DATE_FORMAT = "yyyy dd, MM";
    public final static String DISPLAY_DATE_FORMAT_2 = "EEE, MMM d";
    public final static String DISPLAY_TIME_FORMAT = "h:mm a ";
    public final static String DISPLAY_TIME_FORMAT_WORD_CLOCK = "hh:mm";
    public final static String PICK_DATE_FORMAT2 = "dd-MMMM-yyyy";
    public final static String PICK_DATE_FORMAT = "MMM d";
    public final static String PICK_DATE_FORMAT_2 = "EEE, MMM d";
    public final static String PICK_DATE_FORMAT_1 = "EEE";

    private final static String SERVICE_DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public static String Tag;
    public static boolean accept;
    public static String imageNameLocal;
    public static TextView tvYes, tvNo, tvEnableGPS;
    private static Context context;
    private static ProgressDialog progressDialog;
    private static String TAG=CommonUtils.class.getSimpleName();

    public static double kmtoMph(String kmh) {
        try {

            return (Float.valueOf(kmh)) / 1.6;

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0.0;
    }


    public static void exitDialog(final Activity context) {
        TextView tvYes,tvNo,tvMsg;
        LayoutInflater inflater = LayoutInflater.from(context);
        final Dialog mDialog = new Dialog(context,
                android.R.style.Theme_Translucent_NoTitleBar);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mDialog.getWindow().setGravity(Gravity.CENTER);
//        mDialog.getWindow().getAttributes().windowAnimations = R.anim;
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.dimAmount = 0.75f;
        mDialog.getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow();

        View dialoglayout = inflater.inflate(R.layout.exit_dialog, null);
        mDialog.setContentView(dialoglayout);


        tvMsg = (TextView) mDialog.findViewById(R.id.tvMsg);
//        tvMsg.setTypeface(CommonUtils.setFontText(mContext));
        tvYes = (TextView) mDialog.findViewById(R.id.tvYes);
        tvNo = (TextView) mDialog.findViewById(R.id.tvNo);

        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.dismiss();
                //   context.finish();
                context.moveTaskToBack(true);
            }
        });
        mDialog.show();
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mDialog.show();
    }



    public static double kmtoMps(String kmh) {
        try {

            return (Float.valueOf(kmh)) / 3.6;

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static double kmtoKnots(String kmh) {
        try {

            return (Float.valueOf(kmh) * 1.85);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0.0;
    }


    public static void saveStringPreferences(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void snackBar(String s,View v) {

        try {
            Snackbar snackbar=  Snackbar.make(v, s, Snackbar.LENGTH_LONG)
                    .setAction("Action", null);
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.colorAccent));
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getDateFormat(Context context, String serverDate) {
        Log.e("server date", "server date" + serverDate);
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat(SERVICE_DATE_FORMAT_1, Locale.ENGLISH);

            SimpleDateFormat targetFormat = new SimpleDateFormat(PICK_DATE_FORMAT);
            Date date = originalFormat.parse(serverDate);
            String formattedDate = targetFormat.format(date);
            return formattedDate;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    public static float convertFahrenheitToCelsius(Context context, float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }

    // Converts to fahrenheit
    public static float convertCelsiusToFahrenheit(String celsius) {
        try {
            return (Float.valueOf(celsius) * 9 / 5) + 32;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0.0f;
    }

    public static double convertMilibarToInch(String milibar) {
        try {

            return (Float.valueOf(milibar) * 3) / 1013;

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static String getDayFormat1(Context context, String serverDate) {
        //   Log.e("server date","server date"+serverDate);
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat(SERVICE_DATE_FORMAT_1, Locale.ENGLISH);

            SimpleDateFormat targetFormat = new SimpleDateFormat(PICK_DATE_FORMAT_1);
            Date date = originalFormat.parse(serverDate);
            String formattedDate = targetFormat.format(date);
            //  Toast.makeText(context, "date" +formattedDate, Toast.LENGTH_SHORT).show();
            return formattedDate;


        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }


    public static String getDate(long milliSeconds) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(PICK_DATE_FORMAT2);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String getDateFormate(Context context, String serverDate) {
        //   Log.e("server date","server date"+serverDate);
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat(SERVICE_DATE_FORMAT_1, Locale.ENGLISH);

            SimpleDateFormat targetFormat = new SimpleDateFormat(PICK_DATE_FORMAT2);
            Date date = originalFormat.parse(serverDate);
            String formattedDate = targetFormat.format(date);

            //  Toast.makeText(context, "date" +formattedDate, Toast.LENGTH_SHORT).show();

            ;

            return formattedDate;


        } catch (Exception ex) {
            ex.printStackTrace();
            return "exceptioon";
        }
    }


    public static String getDayFormat(Context context, String serverDate) {
        //  Log.e("server date","server date"+serverDate);
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat(SERVICE_DATE_FORMAT_1, Locale.ENGLISH);

            SimpleDateFormat targetFormat = new SimpleDateFormat(PICK_DATE_FORMAT_2);
            Date date = originalFormat.parse(serverDate);
            String formattedDate = targetFormat.format(date);

            //    Toast.makeText(context, "date" +formattedDate, Toast.LENGTH_SHORT).show();

            ;

            return formattedDate;


        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }


    public static String getTimeFormat(Context context, String serverDate) {
        //  Log.e("server date","server date"+serverDate);
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat(SERVICE_DATE_FORMAT_1, Locale.ENGLISH);

            SimpleDateFormat targetFormat = new SimpleDateFormat(DISPLAY_TIME_FORMAT);
            Date date = originalFormat.parse(serverDate);
            String formattedDate = targetFormat.format(date);

            /// Toast.makeText(context, "date" +formattedDate, Toast.LENGTH_SHORT).show();

            ;

            return formattedDate;


        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }
    public static String getTimeFormatWordClock(Context context, String serverDate) {
        //  Log.e("server date","server date"+serverDate);
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat(SERVICE_DATE_FORMAT_1, Locale.ENGLISH);

            SimpleDateFormat targetFormat = new SimpleDateFormat(DISPLAY_TIME_FORMAT_WORD_CLOCK);
            Date date = originalFormat.parse(serverDate);
            String formattedDate = targetFormat.format(date);

            /// Toast.makeText(context, "date" +formattedDate, Toast.LENGTH_SHORT).show();

            ;

            return formattedDate;


        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }


    public static boolean isOnline(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {

            return false;
        }
        return true;
    }


    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }




    public static String getTimestampOfDate(String str_date) {
        java.text.DateFormat formatter = new SimpleDateFormat(SERVICE_DATE_FORMAT_1);
        formatter.setTimeZone(TimeZone.getDefault());
        Date date = null;
        try {
            date = (Date) formatter.parse(str_date);

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        long value = (date.getTime()) / 1000L;
        String timestampValue = String.valueOf(value);
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        Log.d("Time zone: ", tz.getDisplayName());
        Log.e("TimeZone Time", timestampValue);
        return timestampValue;
    }

    public static void hideKeyPad(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);


            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDateTimeOfTimestamp(long timeStamp) {
        java.text.DateFormat objFormatter = new SimpleDateFormat("EEEE, dd MMMM, hh:mm a");

        Calendar objCalendar = Calendar.getInstance();

        objCalendar.setTimeInMillis(timeStamp * 1000);//edit
        String result = objFormatter.format(objCalendar.getTime());
        objCalendar.clear();
        return result;
    }

    public static String getDateTimeOfTimestamp2(long timeStamp) {
        java.text.DateFormat objFormatter = new SimpleDateFormat("EEE, dd/MM/yyyy");

        Calendar objCalendar = Calendar.getInstance();

        objCalendar.setTimeInMillis(timeStamp * 1000);//edit
        String result = objFormatter.format(objCalendar.getTime());
        objCalendar.clear();
        return result;
    }

    public static String getDateOfTimestamp(long timeStamp) {
        java.text.DateFormat objFormatter = new SimpleDateFormat("EEEE, dd MMMM");

        Calendar objCalendar = Calendar.getInstance();

        objCalendar.setTimeInMillis(timeStamp * 1000);//edit
        String result = objFormatter.format(objCalendar.getTime());
        objCalendar.clear();
        return result;
    }

    public static void hide_keyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused com.blockworkout.view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no com.blockworkout.view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

/*
    public static String getTimestampOfDate(String str_date) {
        java.text.DateFormat formatter = new SimpleDateFormat("dd-MM-yyy");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = (Date) formatter.parse(str_date);

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        long value = (date.getTime()) / 1000L;
        String timestampValue = String.valueOf(value);
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        Log.d("Time zone: ", tz.getDisplayName());
        Log.e("TimeZone Time", timestampValue);
        return timestampValue;
    }
*/

    public static String getTimestampOfDateYYYMMDD(String str_date) {
        java.text.DateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        Date date = null;
        try {
            date = (Date) formatter.parse(str_date);

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        long value = (date.getTime()) / 1000L;
        String timestampValue = String.valueOf(value);
        return timestampValue;
    }


    public static String getformattedDateWithTime(String date) {

        try {
            SimpleDateFormat serverFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            serverFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date d = serverFormat.parse(date);

            long currentTimeStamp = System.currentTimeMillis();
            long server = d.getTime();
            Log.e("current" + currentTimeStamp, "Server" + server);


           /* (new Date((currentTimeStamp * 1000));
             new SimpleDateFormat(" dd mmm yyyy ")
                    .format(new Date((currentTimeStamp * 1000)));*/
            /*if((currentTimeStamp-(25*60*60*1000))>server){
                SimpleDateFormat clientformate = new SimpleDateFormat("dd-MMM-yyyy");
                return clientformate.format(d);
            }else{
                SimpleDateFormat time = new SimpleDateFormat("hh:mm aa");

                return time.format(d);
            }*/
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE,MMM yy");
            // SimpleDateFormat clientformate = new SimpleDateFormat("dd-MMM-yyyy");


            // Toast.makeText(context, "date" +d, Toast.LENGTH_SHORT).show();

            return formatter.format(d);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String addDayToDate(String date, int noOfDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        Date dtStartDate = null;
        try {
            dtStartDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(dtStartDate);
        c.add(Calendar.DATE, noOfDay);  // number of days to add
        String resultDate = sdf.format(c.getTime());  // dt is now the new date
        return resultDate;
    }

    public static String addDayToDateOtherFormat(String date, int noOfDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        Date dtStartDate = null;
        try {
            dtStartDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(dtStartDate);
        c.add(Calendar.DATE, noOfDay);  // number of days to add
        String resultDate = sdf.format(c.getTime());  // dt is now the new date
        return resultDate;
    }


    public static void saveIntPreferences(Context context, String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getIntPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, 0);
    }

    public static int getIntPreferences1(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, -1);
    }

    public static void savePreferencesBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getPreferencesBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(key, false);
    }


    public static void setFragment(Fragment fragment, boolean removeStack, FragmentActivity activity, int mContainer) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction ftTransaction = fragmentManager.beginTransaction();

        if (removeStack) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ftTransaction.replace(mContainer, fragment);
        } else {
            ftTransaction.replace(mContainer, fragment);
            ftTransaction.addToBackStack(null);
        }
        ftTransaction.commit();
    }


    public static void showAlertOk(String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

        try {
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final static boolean isValidPhone(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.PHONE.matcher(target)
                    .matches() && (target.length() >= 10 && target.length() <= 20);
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void SendEmail(Activity context, String To) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, To);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        try {
            context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public static String getTimeStamp() {
        long timestamp = (System.currentTimeMillis() / 1000L);
        String tsTemp = "" + timestamp;
        return "" + tsTemp;
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public static boolean isValidUsername(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.DOMAIN_NAME.matcher(target).matches();
        }
    }
    public static void savePreferencesString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");

    }

    public static void removePreferences(Activity context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
    }

    public static boolean getPreferencesBoolean(Activity context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(key, false);
    }

    public static String getPreferencesString(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

    public static String getDate(Context context, String timestamp_in_string) {
        long dv = (Long.valueOf(timestamp_in_string)) * 1000;// its need to be in milisecond
        Date df = new Date(dv);
        String vv = new SimpleDateFormat("MMM dd/yyyy,hh:mma").format(df);
        /*
            String[] bits = str.split("-");
			 String mnth = bits[0];

		 */
        return vv;
    }

    public static String getTime(String timestamp_in_string) {
        long dv = Long.valueOf(timestamp_in_string) * 1000;// its need to be in milisecond
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.setTimeInMillis(dv);
        String date = DateFormat.format("HH:mm", cal).toString();
        return date;
    }

    public static String getTimeFormat(String timestamp_in_string) {
        long dv = Long.valueOf(timestamp_in_string) * 1000;// its need to be in milisecond
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(dv);
        String date = DateFormat.format("hh:mm aa", cal).toString();
        return date;
    }

    public static boolean isDateToday(long milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);

        Date getDate = calendar.getTime();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startDate = calendar.getTime();

        return getDate.compareTo(startDate) > 0;
    }


    public static String getHoursFromMillis(long milliseconds) {
        return "" + (int) ((milliseconds / (1000 * 60 * 60)) % 24);
    }

    public static Bitmap getBitMapFromImageURl(String imagepath, Activity activity) {

        Bitmap bitmapFromMapActivity = null;
        Bitmap bitmapImage = null;
        try {

            File file = new File(imagepath);
            // We need to recyle unused bitmaps
            if (bitmapImage != null) {
                bitmapImage.recycle();
            }
            bitmapImage = reduceImageSize(file, activity);
            int exifOrientation = 0;
            try {
                ExifInterface exif = new ExifInterface(imagepath);
                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int rotate = 0;

            switch (exifOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
            }

            if (rotate != 0) {
                int w = bitmapImage.getWidth();
                int h = bitmapImage.getHeight();
                Matrix mtx = new Matrix();
                mtx.preRotate(rotate);
                Bitmap myBitmap = Bitmap.createBitmap(bitmapImage, 0, 0, w, h,
                        mtx, false);
                bitmapFromMapActivity = myBitmap;
            } else {
                int SCALED_PHOTO_WIDTH = 150;
                int SCALED_PHOTO_HIGHT = 200;
                Bitmap myBitmap = Bitmap.createScaledBitmap(bitmapImage,
                        SCALED_PHOTO_WIDTH, SCALED_PHOTO_HIGHT, true);
                bitmapFromMapActivity = myBitmap;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bitmapFromMapActivity;
    }

    public static Bitmap reduceImageSize(File f, Context context) {

        Bitmap m = null;
        try {

            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            final int REQUIRED_SIZE = 150;

            int width_tmp = o.outWidth, height_tmp = o.outHeight;

            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            o2.inPreferredConfig = Bitmap.Config.ARGB_8888;
            m = BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        } catch (Exception e) {

        }
        return m;
    }

    public static String printKeyHash(Context context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            String packageName = context.getApplicationContext().getPackageName();

            packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));
                Log.e("Key Hash=", key);
            }

        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());

        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());

        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    public static void dialogSelectDate(final Context context, final EditText textView) {
        // Process to get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        Calendar userAge = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                        Calendar minAdultAge = new GregorianCalendar();
                        minAdultAge.add(Calendar.YEAR, 0);
                        if (minAdultAge.after(userAge)) {
                            textView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        } else {
                            showAlertOk("Please select a valid date of birth.", context);
                        }
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
        dpd.setCancelable(false);
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    public static void Toast(Context context) {
        Toast.makeText(context, "Work in progress", Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, String message) {
        //Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast1(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /*It is used for dynamic progress bar*/
    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static int getScreenWidthResolution(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        return width;
    }

    public static String getScreenHeightResolution(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        return height + "";
    }

    /*Public static void PaymentDialog(final Activity context) {
        TextView tvPayment, tvTotalAmount, pay;
        EditText etName, etHashNo, etAccountNo, etDigits;
        LinearLayout llVisa, llMasterCard, llPayCard, llPayPal;

        LayoutInflater inflater = LayoutInflater.from(context);
        final Dialog mDialog = new Dialog(context,
                android.R.style.Theme_Translucent_NoTitleBar);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mDialog.getWindow().setGravity(Gravity.CENTER);
//        mDialog.getWindow().getAttributes().windowAnimations = R.anim;
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.dimAmount = 0.75f;
        mDialog.getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow();

        View dialoglayout = inflater.inflate(R.layout.dialog_payment_information, null);
        mDialog.setContentView(dialoglayout);

        mDialog.show();
    }*/

    public static Typeface AvenirRoman(Context context) {
        Typeface TextFont = Typeface.createFromAsset(context.getAssets(), "font/Avenir_Roman.ttf");
        return TextFont;
    }

    public static Typeface AvenirHeavy(Context context) {
        Typeface TextFont = Typeface.createFromAsset(context.getAssets(), "font/Avenir-Heavy.ttf");
        return TextFont;
    }

    public static Typeface AvenirMedium(Context context) {
        Typeface TextFont = Typeface.createFromAsset(context.getAssets(), "font/Avenir-Medium.ttf");
        return TextFont;
    }





  /*  public static String printKeyHash(AddClinicActivity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));
                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }*/

/*
    public static void displayProgressDialog(Context mContext, String message) {
        dialogProgress = new ProgressDialog(mContext);
        dialogProgress.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialogProgress.setIndeterminateDrawable(mContext.getResources().getDrawable(R.drawable.my_progress_indeterminate));
        dialogProgress.show();
        dialogProgress.setCancelable(true);
    }

    public static void disMissProgressDialog(Context mContext) {
        if (dialogProgress != null) {
            dialogProgress.dismiss();
            dialogProgress = null;
        }
    }*/

    /**
     * Called for checking internet connection
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /*   public static void showProgressDialog(Context context) {
           if (context != null) {
               dialogProgress = new ProgressDialog(context);
               dialogProgress.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
               //   dialogProgress.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.my_progress_indeterminate));
               dialogProgress.setMessage(context.getResources().getString(R.string.loading));
               if(!dialogProgress.isShowing()) {
                   try {
                       dialogProgress.show();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
               dialogProgress.setCancelable(false);

           }
       }
   */
    public static void printLog(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static String getTimeZone() {
        return String.valueOf(TimeZone.getDefault().getID());
    }








    public static boolean checkPermissionLocation(Activity context){
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {
            return false;

        }
    }


    public static boolean checkPermissionStorage(Activity context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {

            if (result1 == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }


        } else {
            return false;

        }
    }

    public static boolean checkPermissionCamera(Activity context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {
            return false;

        }
    }


    public static boolean checkPermissionUserContact(Activity context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {
            return false;

        }
    }

    public static boolean checkPermissionSendSms(Activity context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {
            return false;

        }
    }

    public static boolean checkPermissionCallPhone(Activity context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {
            return false;

        }
    }




  /*  public static void DisableGps(final Activity context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final Dialog mDialog = new Dialog(context,
                android.R.style.Theme_Translucent_NoTitleBar);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.dimAmount = 0.75f;
        mDialog.getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow();

        View dialoglayout = inflater.inflate(R.layout.gps_disable_pop_up, null);
        mDialog.setContentView(dialoglayout);
        mDialog.show();
    }*/



    public static Dialog getDialog(Context mContext, int dialogLayout) {
        Dialog dialog = new Dialog(mContext, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogLayout);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = .0f;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);

        return dialog;
    }

    public static Dialog getWithoutDimDialog(Context mContext, int dialogLayout) {
        Dialog dialog = new Dialog(mContext, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogLayout);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 7.0f;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);

        return dialog;
    }

    public static Dialog getWrapDialog(Context mContext, int dialogLayout) {
        Dialog dialog = new Dialog(mContext, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogLayout);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = .0f;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;

        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setGravity(Gravity.CENTER);

        return dialog;
    }

    public static void showSettingsAlert(final Context context) {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();


    }
    public static void showProgress(Context context){
        progressDialog = new ProgressDialog(context);
        if(!progressDialog.isShowing()){
            progressDialog.setMessage(context.getResources().getString(R.string.please_wait));
            progressDialog.show();
        }
    }
    public static void dismissProgress(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
