package cloudsystem.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader {

    public void downloadFile(String fileUrl, File path) throws IOException {
        if(!path.exists())
            path.createNewFile();
        int BUFFER_SIZE = 4096;
        URL url = new URL(fileUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        int code = urlConnection.getResponseCode();
        if (code == HttpURLConnection.HTTP_OK) {
            String fileName = null;
            String disposition = urlConnection.getHeaderField("Content-Disposition");
            String contentType = urlConnection.getContentType();
            int contentLength = urlConnection.getContentLength();


            if (disposition != null) {
                int index = disposition.indexOf("filename=");

                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            }
            InputStream inputStream = urlConnection.getInputStream();
            String saveFilePath = path.toPath().toString() + File.separator + fileName;

            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();
            System.out.println(fileName + " is downloaded!");

        } else {
            System.out.println("No file to download");
        }
        System.out.println(urlConnection.getPermission().toString());
    }
}
