package fr.eni.geekoquizz.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.util.List;

public class FileStorageService {
    private File imgFolder;

    public FileStorageService(Context context) {
        String path = context.getFilesDir()+"/img";
        imgFolder = new File(path);
        if(!imgFolder.exists())
        {
            imgFolder.mkdir();
        }
    }

    public Bitmap getFile(String nameFile)
    {
        File fichierImage = new File(imgFolder,nameFile);
        Bitmap bitmapImage = null;
        if(fichierImage.exists())
        {
            bitmapImage = BitmapFactory.decodeFile(fichierImage.getPath());
        }
        return bitmapImage;
    }

    public void saveFile(File fileImage)
    {
        File imgDest = new File(imgFolder.getPath());
        fileImage.renameTo(imgDest);
    }

    public void saveAllFile(List<File> filesImage)
    {
        for (File uneImage:filesImage)
        {
            saveFile(uneImage);
        }
    }
}
