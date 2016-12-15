package com.udacity.stockhawk.ui;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;
import com.udacity.stockhawk.models.HistPriceModel;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mostafa_anter on 12/15/16.
 */
public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.chart)LineChart mChart;
    private static final int STOCK_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        getSupportLoaderManager().initLoader(STOCK_LOADER, null, this);


    }

    private void updateChart(List<HistPriceModel> dataObjects) {

        List<Entry> entries = new ArrayList<Entry>();

        for (HistPriceModel data : dataObjects) {
            // turn your data into Entry objects
            entries.add(new Entry(data.getHistory(), data.getPrice()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.CYAN);
        dataSet.setValueTextColor(Color.WHITE);

        LineData lineData = new LineData(dataSet);
        mChart.setData(lineData);
        mChart.invalidate(); // refresh


        // Show chart
        mChart.setVisibility(View.VISIBLE);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                Contract.Quote.URI,
                Contract.Quote.QUOTE_COLUMNS,
                Contract.Quote.COLUMN_SYMBOL + " = ?",
                new String[]{getIntent().getStringExtra("symbol")},
                Contract.Quote.COLUMN_SYMBOL);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        String history = data.getString(
                data.getColumnIndexOrThrow(Contract.Quote.COLUMN_HISTORY)
        );

        Toast.makeText(this, history, Toast.LENGTH_LONG).show();


        CSVReader csvReader = new CSVReader(new StringReader(history), ',');
        //Set column mapping strategy
        List<HistPriceModel> histPriceModelList = new ArrayList<>();

        // read line by line
        String[] record = null;

        try {
            while ((record = csvReader.readNext()) != null) {
                HistPriceModel histPriceModel = new HistPriceModel();
                histPriceModel.setHistory(Float.valueOf(record[0]));
                histPriceModel.setPrice(Float.valueOf(record[1]));
                histPriceModelList.add(histPriceModel);
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // finally show chart :)
        updateChart(histPriceModelList);


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
