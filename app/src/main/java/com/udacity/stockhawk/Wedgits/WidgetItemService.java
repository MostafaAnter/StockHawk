package com.udacity.stockhawk.Wedgits;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;
import com.udacity.stockhawk.data.PrefUtils;
import com.udacity.stockhawk.sync.QuoteSyncJob;
import com.udacity.stockhawk.ui.MainActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by mostafa_anter on 12/16/16.
 */

public class WidgetItemService extends IntentService {

    private final DecimalFormat dollarFormatWithPlus;
    private final DecimalFormat dollarFormat;
    private final DecimalFormat percentageFormat;

    {
        dollarFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        dollarFormatWithPlus = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        dollarFormatWithPlus.setPositivePrefix("+$");
        percentageFormat = (DecimalFormat) NumberFormat.getPercentInstance(Locale.getDefault());
        percentageFormat.setMaximumFractionDigits(2);
        percentageFormat.setMinimumFractionDigits(2);
        percentageFormat.setPositivePrefix("+");
    }

    public WidgetItemService() {
        super("WidgetItemService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (QuoteSyncJob.ACTION_DATA_UPDATED.equals(intent.getAction())) {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            getDataFromContentProvider(this, appWidgetManager,
                    appWidgetManager.getAppWidgetIds(new ComponentName(this, StockItemWidget.class)));
        }

    }

    private void getDataFromContentProvider(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        Cursor cursor = context.getContentResolver().query(
                Contract.Quote.URI,
                Contract.Quote.QUOTE_COLUMNS,
                null,
                null,
                Contract.Quote.COLUMN_SYMBOL);

        // do some checks before bind data :)
        if (cursor == null || cursor.getCount() < 1 || !cursor.moveToFirst() ){
            return;
        }

        // get first item in cursor
        cursor.moveToPosition(0);



        for (int appWidgetID : appWidgetIds){
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_item_view);

            remoteViews.setTextViewText(R.id.symbol, cursor.getString(Contract.Quote.POSITION_SYMBOL));
            remoteViews.setTextViewText(R.id.price, dollarFormat.format(cursor.getFloat(Contract.Quote.POSITION_PRICE)));


            float rawAbsoluteChange = cursor.getFloat(Contract.Quote.POSITION_ABSOLUTE_CHANGE);
            float percentageChange = cursor.getFloat(Contract.Quote.POSITION_PERCENTAGE_CHANGE);

            if (rawAbsoluteChange > 0) {
                remoteViews.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_green);
            } else {
                remoteViews.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_red);
            }

            String change = dollarFormatWithPlus.format(rawAbsoluteChange);
            String percentage = percentageFormat.format(percentageChange / 100);

            if (PrefUtils.getDisplayMode(context)
                    .equals(context.getString(R.string.pref_display_mode_absolute_key))) {
                remoteViews.setTextViewText(R.id.change, change);
            } else {
                remoteViews.setTextViewText(R.id.change, percentage);
            }

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context, 0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.item_widget, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetID, remoteViews);
        }
    }
}
