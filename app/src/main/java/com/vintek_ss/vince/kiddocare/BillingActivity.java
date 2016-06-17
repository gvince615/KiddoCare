package com.vintek_ss.vince.kiddocare;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class BillingActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private static final int MILS_IN_INCH = 1000;
    private static int ID = 0;
    private static Context context;
    Spinner cSpin;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        getContext();
        cSpin = (Spinner) findViewById(R.id.s_childSpinner);
        cSpin.setOnItemSelectedListener(this);
        loadSpinnerData();

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
//        cSpin = (Spinner) findViewById(R.id.s_childSpinner);
//        if (position == 0) {
//
//        } else {
//            TextView BdateStart = (TextView) findViewById(R.id.tv_selBeginDate);
//            String start = BdateStart.getText().toString();
//            TextView BdateEnd = (TextView) findViewById(R.id.tv_SelEndDate);
//            String end = BdateEnd.getText().toString();
//
//            if (TextUtils.isEmpty(end) || TextUtils.isEmpty(start)) {
//                BdateStart.setBackgroundResource(R.drawable.red_box);
//                BdateEnd.setBackgroundResource(R.drawable.red_box);
//
//                int duration = Toast.LENGTH_SHORT;
//                Toast toast = Toast.makeText(this,
//                        "Missing Date Info\nRed fields are Required!", duration);
//                toast.show();
//                cSpin.setSelection(0, true);
//            } else {
//                daycaremanagerDB BillingData = new daycaremanagerDB(this);
//                BillingData.open();
//
//                String ChildName = cSpin.getSelectedItem().toString();
//                String[] splitFandL = ChildName.split("\\s+");
//                String cfn = splitFandL[0];
//                String cln = splitFandL[1];
//
//                //SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd H:mm a");
//
//
////                String[] discountData = BillingData.getDiscount(cfn, cln);
//
////                float discount_amountF = Float.valueOf(discountData[1].trim()).floatValue();
////                float convertedDiscountF = discount_amountF * 100;
////                String[] result = BillingData.FetchAttenData(ChildName, start, end);
//
//                TextView attenData = (TextView) findViewById(R.id.tv_billingData);
////                attenData.setText(result[0]);
//
//                TextView total = (TextView) findViewById(R.id.tv_total2bBilled);
//
////                String billRep_total = result[1];
//
//                //billRep_total = billRep_total.split(" ")[1];
//                TextView DisAmount = (TextView) findViewById(R.id.tv_discountAmount);
//                float billBeforeDiscount = Float.valueOf(billRep_total.trim()).floatValue();
//                float bill_discount = billBeforeDiscount * discount_amountF;
//                String sDiscount_amount = String.format("%.2f", bill_discount);
//                float billMinus_discount = billBeforeDiscount - bill_discount;
//                String sDiscountApplied_Bill = String.format("%.2f", billMinus_discount);
//
//                DisAmount.setText("$ " + sDiscount_amount);
//                total.setText("$ " + sDiscountApplied_Bill);
//                BillingData.close();
//            }
//        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_billing, menu);
        return true;
    }

    private void loadSpinnerData() {
//        // database handler
//        daycaremanagerDB db = new daycaremanagerDB(getApplicationContext());
//        db.open();
//        // Spinner Drop down elements
//        List<String> children = db.getAllChildren();
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, children);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter
//                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        cSpin.setAdapter(dataAdapter);
//        db.close();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        TextView BdateStart = (TextView) findViewById(R.id.tv_selBeginDate);
        String start = BdateStart.getText().toString();
        TextView BdateEnd = (TextView) findViewById(R.id.tv_SelEndDate);
        String end = BdateEnd.getText().toString();

        if (TextUtils.isEmpty(end) || TextUtils.isEmpty(start)) {
            BdateStart.setBackgroundResource(R.drawable.red_box);
            BdateEnd.setBackgroundResource(R.drawable.red_box);

            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this,
                    "Missing Date Info\nRed fields are Required!", duration);
            toast.show();
            cSpin.setSelection(0, true);
        } else {
            //noinspection SimplifiableIfStatement
            if (id == R.id.action_saveBill) {

                doPrint();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void pickDate(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        Dialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;
                String newMonth = "";
                switch (monthOfYear) {
                    case 1:
                        newMonth = "Jan";
                        break;
                    case 2:
                        newMonth = "Feb";
                        break;
                    case 3:
                        newMonth = "Mar";
                        break;
                    case 4:
                        newMonth = "Apr";
                        break;
                    case 5:
                        newMonth = "May";
                        break;
                    case 6:
                        newMonth = "Jun";
                        break;
                    case 7:
                        newMonth = "Jul";
                        break;
                    case 8:
                        newMonth = "Aug";
                        break;
                    case 9:
                        newMonth = "Sep";
                        break;
                    case 10:
                        newMonth = "Oct";
                        break;
                    case 11:
                        newMonth = "Nov";
                        break;
                    case 12:
                        newMonth = "Dec";
                        break;
                }
                if (ID == (R.id.tv_selBeginDate)) {
                    TextView tv = (TextView) findViewById(R.id.tv_selBeginDate);
                    tv.setText(year + "-" + newMonth + "-" + dayOfMonth + " 12:00 am");
                    //tv.setText(year + "-" + monthOfYear + "-" + dayOfMonth + " 00:00");
                }
                if (ID == (R.id.tv_SelEndDate)) {
                    TextView tv = (TextView) findViewById(R.id.tv_SelEndDate);
                    tv.setText(year + "-" + newMonth + "-" + dayOfMonth + " 11:59 pm");
                    //tv.setText(year + "-" + monthOfYear + "-" + dayOfMonth + " 23:59");
                }

            }
        }, mYear, mMonth, mDay);
        dateDialog.show();
        ID = v.getId();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void doPrint() {


////////////////////////////////////////////////////////////////////////////////////////////////////
        // BEGIN PRINT ROUTINE
        PrintManager printManager = (PrintManager) this
                .getSystemService(Context.PRINT_SERVICE);

        String jobName = this.getString(R.string.app_name) +
                " Document";

        printManager.print(jobName, new MyPrintDocumentAdapter(this),
                null);

        // END PRINT ROUTINE
////////////////////////////////////////////////////////////////////////////////////////////////////


    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private boolean pageInRange(PageRange[] pageRanges, int page) {
        for (int i = 0; i < pageRanges.length; i++) {
            if ((page >= pageRanges[i].getStart()) &&
                    (page <= pageRanges[i].getEnd()))
                return true;
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
//    private void drawPage(PdfDocument.Page page,
//                          int pagenumber) throws ParseException {
//        Canvas canvas = page.getCanvas();
//
//        pagenumber++; // Make sure page numbers start at 1
//
//        int titleBaseLine = 140;
//        int leftMargin = 50;
//        int rightMargin = 370;
//        int bottomBaseLine = 755;
//        int dataBaseline = 215;
//
//
//        // DATA TO PRINT ///////////////////////////////////////////////////////////////////////////
//        String billRep_Child;
//        String billRep_attenData;
//        String billRep_total;
//
///////// GET DATA FOR BILL
//        Spinner BillforChild = (Spinner) findViewById(R.id.s_childSpinner);
//        billRep_Child = BillforChild.getSelectedItem().toString();
//
//        TextView totDiscount = (TextView) findViewById(R.id.tv_discountAmount);
//        String Discount_total = totDiscount.getText().toString();
//
//        TextView total = (TextView) findViewById(R.id.tv_total2bBilled);
//        billRep_total = total.getText().toString();
//
//        TextView begin = (TextView) findViewById(R.id.tv_selBeginDate);
//        String billRep_begin = begin.getText().toString();
//
//        TextView end = (TextView) findViewById(R.id.tv_SelEndDate);
//        String billRep_end = end.getText().toString();
//
//        String BillingDateInfo = billRep_begin + " to " + billRep_end;
////////// GET PREFERENCES
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        daycaremanagerDB ChildAge = new daycaremanagerDB(this);
//        ChildAge.open();
//        String cAge = ChildAge.get_Child_Age(billRep_Child);
//        ChildAge.close();
//
//        // Get Prefs
//        ///////////////////GET AGE GROUP AMOUNTS
//        String billAmountHR = "";
//        String billAmountDAY = "";
//        switch (cAge) {
//            case "Infant":
//                billAmountHR = sharedPrefs.getString("prefBilling_Hourly_Infants", "2.00");
//                billAmountDAY = sharedPrefs.getString("prefBilling_Daily_Infants", "20.00");
//                break;
//            case "Toddler":
//                billAmountHR = sharedPrefs.getString("prefBilling_Hourly_Toddler", "2.00");
//                billAmountDAY = sharedPrefs.getString("prefBilling_Daily_Toddlers", "20.00");
//                break;
//            case "Preschool":
//                billAmountHR = sharedPrefs.getString("prefBilling_Hourly_Preschool", "2.00");
//                billAmountDAY = sharedPrefs.getString("prefBilling_Daily_Preschool", "20.00");
//                break;
//            case "School Age":
//                billAmountHR = sharedPrefs.getString("prefBilling_Hourly_SchoolAge", "2.00");
//                billAmountDAY = sharedPrefs.getString("prefBilling_Daily_SchoolAge", "20.00");
//                break;
//        }
//        String billHrInDay = sharedPrefs.getString("prefBilling_numHrs41Day", "3");
//        String ccenterName = sharedPrefs.getString("prefUserCenterName", "");
//        String ccenterAddress = sharedPrefs.getString("prefUserCenterAddress", "");
//        String providerName = sharedPrefs.getString("prefUserName", "");
//
//        ////////////////////////////////////////////////////////////////////////////////////////////
//
////////// PRINT HEADER
//        Paint paint = new Paint();
//
//        Bitmap myIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
//        Bitmap smalMyIcon = Bitmap.createScaledBitmap(myIcon, 65, 65, false);
//        canvas.drawBitmap(smalMyIcon, 50, 50, null);
//
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(16);
//        canvas.drawText(
//                "ChildData Care Center:", leftMargin + 80, titleBaseLine - 70, paint);
//        paint.setColor(Color.BLUE);
//        canvas.drawText(ccenterName, leftMargin + 220, titleBaseLine - 70, paint);
//
//        //canvas.drawText(
//        //       "ChildData Care Center Address:", leftMargin + 80, titleBaseLine - 40, paint);
//        paint.setColor(Color.BLUE);
//        canvas.drawText(ccenterAddress, leftMargin + 80, titleBaseLine - 50, paint);
//
//        paint.setColor(Color.BLACK);
//        canvas.drawText(
//                "ChildData Care Provider:", leftMargin + 80, titleBaseLine - 30, paint);
//        paint.setColor(Color.BLUE);
//        canvas.drawText(providerName, leftMargin + 220, titleBaseLine - 30, paint);
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(12);
//        canvas.drawText("Hourly Charge: $" + billAmountHR, rightMargin + 30, titleBaseLine - 5, paint);
//        canvas.drawText("Daily Charge: $" + billAmountDAY, rightMargin + 30, titleBaseLine + 8, paint);
//        canvas.drawText("Hrs per billable Day: " + billHrInDay, rightMargin + 30, titleBaseLine + 21, paint);
//
//        paint.setTextSize(16);
//        canvas.drawText(
//                "Billing Report for: " + billRep_Child, leftMargin, titleBaseLine, paint);
//        float startX = 50.0f;
//        float endX = 525.0f;
//        float startY = 175.0f;
//        float endY = 175.0f;
//
//        paint.setTextSize(14);
//        canvas.drawText(
//                BillingDateInfo,
//                leftMargin,
//                titleBaseLine + 20,
//                paint);
//
//        canvas.drawLine(startX, startY, endX, endY, paint);
//        canvas.drawText("Date", 85, 187, paint);
//        canvas.drawText("Checked In", 175, 187, paint);
//        canvas.drawText("Checked Out", 285, 187, paint);
//        canvas.drawText("Billable Amount", 400, 187, paint);
//        canvas.drawLine(startX, startY + 15, endX, endY + 15, paint);
//
////////// END HEADER
//        /////////////// Begin Atten Data
//        int lineNumber = 0;
//        daycaremanagerDB BillingData = new daycaremanagerDB(this);
//        BillingData.open();
//
////        Cursor c = BillingData.get_Child_AttenData(billRep_Child);
//        String[] splitP1FandL = billRep_Child.split("\\s+");
//        String cfn = splitP1FandL[0];
//        String cln = splitP1FandL[1];
//
//        /// GET ATTEN INFO & Print
////        int NumRecords = c.getCount();
////        c.moveToFirst();
////        while (!c.isAfterLast()) {
//
////            String inDate = c.getString(c
////                    .getColumnIndex(daycaremanagerDB.KEY_ATTEN_DATE_TIME_IN));
////            String outDate = c.getString(c
////                    .getColumnIndex(daycaremanagerDB.KEY_ATTEN_DATE_TIME_OUT));
////
////            String BillStart = billRep_begin;
////            String BillEnd = billRep_end;
////            String in = inDate;
////            String out = outDate;
////            SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-d h:mm a");
////            Date d1 = null;
////            Date d2 = null;
////            Date b1 = null;
////            Date b2 = null;
////            try {
////                if (TextUtils.isEmpty(outDate)) {
////                    break;
////                } else {
////                    d2 = format.parse(out);
////                    b2 = format.parse(BillEnd);
////                }
////                d1 = format.parse(in);
////                b1 = format.parse(BillStart);
////
////            } catch (ParseException e) {
////                e.printStackTrace();
////            }
////            if (d1.after(b1) && d1.before(b2)) {
////                long diff = d2.getTime() - d1.getTime();
////                //long diffSec = diff / 1000 % 60;
////                long diffMin = diff / (60 * 1000) % 60;
////                long diffHr = diff / (60 * 60 * 1000) % 24;
////                long diffDay = diff / (24 * 60 * 60 * 1000);
////                // Get Prefs
////
////
////                //Convert Prefs to float
////                float f_BillAmountHR = 0;
////                float f_BillAmountDAY = 0;
////                float f_BillHrInDAY = 0;
////                try {
////                    f_BillAmountHR = Float.valueOf(billAmountHR.trim()).floatValue();
////                    f_BillAmountDAY = Float.valueOf(billAmountDAY.trim()).floatValue();
////                    f_BillHrInDAY = Float.valueOf(billHrInDay.trim()).floatValue();
////                } catch (NumberFormatException e) {
////                    e.printStackTrace();
////                }
////
////                float billAmount = 0;
////                if (diffDay > 0) {
////                    billAmount = f_BillAmountDAY * diffDay;
////                }
////                if (diffHr >= f_BillHrInDAY) {
////                    billAmount = billAmount + f_BillAmountDAY;
////                }
////                if (diffHr < f_BillHrInDAY) {
////                    billAmount = f_BillAmountHR * diffHr;
////
////                    if (diffMin >= 45) {
////                        billAmount = billAmount + f_BillAmountHR;
////                    }
////                    if (diffMin < 45 && diffMin > 30) {
////                        billAmount = billAmount + (f_BillAmountHR / 4) * 3;
////                    }
////                    if (diffMin <= 30 && diffMin > 15) {
////                        billAmount = billAmount + (f_BillAmountHR / 2);
////                    }
////                    if (diffMin <= 15 && diffMin > 0) {
////                        billAmount = billAmount + (f_BillAmountHR / 4);
////                    }
////                }
////                //String totalTime = diffHr + "." + diffMin;
////
////                String dayBillResult = String.format("%.2f", billAmount);
////
////                int addLine = lineNumber * 13;
////                addLine = addLine - 10;
////                paint.setColor(Color.BLACK);
////                paint.setTextSize(12);
////                SimpleDateFormat newFormatDate = new SimpleDateFormat("EEE MM/dd/yyyy");
////                SimpleDateFormat newFormatTime = new SimpleDateFormat("hh:mm a");
////                Date newDATE = null;
////                Date newTIME = null;
////
////
////                String time = newFormatTime.format(d1);
////                String date = newFormatDate.format(d1);
////                String timeOut = newFormatTime.format(d2);
////                String dateOut = newFormatDate.format(d2);
////
////                canvas.drawText(date, leftMargin + 10, dataBaseline + addLine, paint);
////                canvas.drawText(time, leftMargin + 135, dataBaseline + addLine, paint);
////
////                //canvas.drawText("to", leftMargin+180, dataBaseline+addLine, paint);
////
////                canvas.drawText(timeOut, leftMargin + 250, dataBaseline + addLine, paint);
////                canvas.drawText("Hr: " + diffHr + " Min: " + diffMin,
////                        leftMargin + 340, dataBaseline + addLine, paint);
////
////
////                paint.setColor(Color.RED);
////                canvas.drawText("$ " + dayBillResult, rightMargin + 105, dataBaseline + addLine, paint);
////
////
////                lineNumber++;
////                c.moveToNext();
////            } else {
////                c.moveToNext();
////            }
////        }
//
//
////        String[] discountData = BillingData.getDiscount(cfn, cln);
//
////        float discount_amountF = Float.valueOf(discountData[1].trim()).floatValue();
////        float convertedDiscountF = discount_amountF * 100;
//
//        billRep_total = billRep_total.split(" ")[1];
//        Discount_total = Discount_total.split(" ")[1];
//
//
//        float startYbottom = 735.0f;
//        float endYbottom = 735.0f;
//
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(18);
//        canvas.drawLine(startX, startYbottom - 25, endX, endYbottom - 25, paint);
////        canvas.drawText("Discount Applied: " + discountData[0] + " (" + convertedDiscountF + "%)",
////                leftMargin + 25, bottomBaseLine - 25, paint);
//        paint.setColor(Color.rgb(00, 99, 00));
//        canvas.drawText("$ " + Discount_total, rightMargin + 75, bottomBaseLine - 25, paint);
//
//        BillingData.close();
//        c.close();
//        PdfDocument.PageInfo pageInfo = page.getInfo();
//
////////// PRINT TOTAL OWED
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(18);
//        canvas.drawLine(startX, startYbottom, endX, endYbottom, paint);
//        canvas.drawLine(startX, startYbottom + 25, endX, endYbottom + 25, paint);
//
//        canvas.drawText("Total to be billed:", leftMargin + 25, bottomBaseLine, paint);
//        paint.setColor(Color.RED);
//        canvas.drawText("$ " + billRep_total, rightMargin + 75, bottomBaseLine, paint);
//
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public Context getContext() {
        context = getApplicationContext();
        return context;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // PRINT ADAPTER
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public class MyPrintDocumentAdapter extends PrintDocumentAdapter {
        public PdfDocument myPdfDocument;
        public int totalpages = 1;
        Context context;
        private int pageHeight;
        private int pageWidth;

        public MyPrintDocumentAdapter(Context context) {
            this.context = context;
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void onLayout(PrintAttributes oldAttributes,
                             PrintAttributes newAttributes,
                             CancellationSignal cancellationSignal,
                             LayoutResultCallback callback,
                             Bundle metadata) {
            myPdfDocument = new PrintedPdfDocument(context, newAttributes);

            pageHeight =
                    newAttributes.getMediaSize().getHeightMils() / 1000 * 72;
            pageWidth =
                    newAttributes.getMediaSize().getWidthMils() / 1000 * 72;

            if (cancellationSignal.isCanceled()) {
                callback.onLayoutCancelled();
                return;
            }

            if (totalpages > 0) {
                PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                        .Builder("BILLING print_output.pdf")
                        .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                        .setPageCount(totalpages);

                PrintDocumentInfo info = builder.build();
                callback.onLayoutFinished(info, true);
            } else {
                callback.onLayoutFailed("Page count is zero.");
            }
        }


        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void onWrite(final PageRange[] pageRanges,
                            final ParcelFileDescriptor destination,
                            final CancellationSignal cancellationSignal,
                            final WriteResultCallback callback) {
            for (int i = 0; i < totalpages; i++) {
                if (pageInRange(pageRanges, i)) {
                    PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth,
                            pageHeight, i).create();

                    PdfDocument.Page page =
                            myPdfDocument.startPage(newPage);

                    if (cancellationSignal.isCanceled()) {
                        callback.onWriteCancelled();
                        myPdfDocument.close();
                        myPdfDocument = null;
                        return;
                    }
//                    try {
//                        drawPage(page, i);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
                    myPdfDocument.finishPage(page);
                }
            }

            try {
                myPdfDocument.writeTo(new FileOutputStream(
                        destination.getFileDescriptor()));
            } catch (IOException e) {
                callback.onWriteFailed(e.toString());
                return;
            } finally {
                myPdfDocument.close();
                myPdfDocument = null;
            }

            callback.onWriteFinished(pageRanges);
        }

    }


}
