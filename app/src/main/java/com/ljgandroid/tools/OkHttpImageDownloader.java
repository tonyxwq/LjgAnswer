package com.ljgandroid.tools;

import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Request;

/**
 * Author:XWQ
 * Time   2019/1/15
 * Descrition: this is OkHttpImageDownloader
 */

public class OkHttpImageDownloader
{
    public static void download(String url)
    {
        final Request request = new Request.Builder().url(url).build();
        HttpUtils.client.newCall(request).enqueue(new okhttp3.Callback()
        {
            @Override
            public void onFailure(okhttp3.Call call, IOException e)
            {
                Log.d("mmp", e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException
            {

                FileUtil.createSdDir();
                String url = response.request().url().toString();
                int index = url.lastIndexOf("/");
                String pictureName = url.substring(index + 1);
                if (FileUtil.isFileExist(pictureName))
                {
                    return;
                }
                Log.d("mmp","pictureName=" + pictureName);
                FileOutputStream fos = new FileOutputStream(FileUtil.createFile(pictureName));
                InputStream in = response.body().byteStream();
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = in.read(buf)) != -1)
                {
                    fos.write(buf, 0, len);
                }
                fos.flush();
                in.close();
                fos.close();
            }
        });
    }
}
