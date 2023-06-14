package com.example.async;


import android.os.AsyncTask;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;

class DownloadWebpageTask extends AsyncTask<String, Void, String> {
    private BreakIterator textView;

    @Override
    protected String doInBackground(String... urls) {
        String response = "";
        for (String url : urls) {
            try {
                URL myurl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) myurl.openConnection();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                InputStream in = connection.getInputStream();
                int bytesRead = 0;
                byte[] buffer = new byte[1024];
                while ((bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                    out.close();
                    response = new String(out.toByteArray());
                }
            } catch (IOException e) {
            }
        }

        return response;
    }
    @Override
    protected void onPostExecute (String result){
        textView.setText(result);
    }
    public void onClick(View view) {
        DownloadWebpageTask task=new DownloadWebpageTask();
        task.execute(new String[]{"https://dku.kz/"});
    }
}
