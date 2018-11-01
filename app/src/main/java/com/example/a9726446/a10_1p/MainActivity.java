package com.example.a9726446.a10_1p;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        TextView status = findViewById(R.id.status);
        new CountTimerTask().execute(status);
    }

    private static class CountTimerTask extends AsyncTask<TextView, Object, Void>{
        @Override
        protected Void doInBackground(TextView... status) {
            //TextView status = (TextView)params[0];
            try {
                for (int i = 3; i >= 0; i--) {
                    Thread.sleep(1000);
                    //status[0].setText(Integer.toString(i));
                    publishProgress(status[0], Integer.toString(i));
                }
            } catch (InterruptedException ie) {
                Log.e("Interrupt!", ie.toString());
            }
            return null;
        }
        protected void onPostExecute() {}

        /**
         * Updates the main thread. Since the point is to report updates,
         * it follows suit to send the parameters and process them here.
         * Keep work to a minimum though, it's only an update, not a finale!
         * @param params - materials to work with.
         *               [0] - Text View to be updated
         *               [1] - String to update the text view with.
         */
        protected void onProgressUpdate(Object... params){
            ((TextView)params[0]).setText(params[1].toString());
        }
    }
}