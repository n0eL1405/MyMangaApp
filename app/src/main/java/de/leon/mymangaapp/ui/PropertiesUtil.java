package de.leon.mymangaapp.ui;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public enum SecretKey {
        APP_ID, APP_ACCESS_KEY;
    }

    public static String getSecret(Context context, SecretKey key) throws IOException {
        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("secrets.properties");
        properties.load(inputStream);
        return properties.getProperty(key.toString());
    }
}
