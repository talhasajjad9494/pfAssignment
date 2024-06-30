package Helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utility {
    static Properties prop;
    static FileReader reader;
    public static String getValue(String fileName, String key) throws IOException {
        prop = new Properties();
        reader = new FileReader("src//test//java//Config//" + fileName + ".properties");
        prop.load(reader);
        String value = prop.getProperty(key);
        reader.close();
        return value;
    }
}
