package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static String fileName;

    private static void setFileName() {
        fileName = "used.properties";
        String usedTechProp = getProperties("usedTech");
        if (usedTechProp.equals("hibernate")) {
            fileName = "hibernate.properties";
        }
        if (usedTechProp.equals("jdbc")) {
            fileName = "jdbc.properties";
        }

    }


    public static String getProperties(String name) {
        if (fileName==null){
            setFileName();
        }
        String value = null;
        PropertiesReader reader = new PropertiesReader();
        Properties properties = new Properties();

        try {
            InputStream is = reader.getClass().getClassLoader().getResourceAsStream(fileName);
            properties.load(is);
            value = properties.getProperty(name);
            is.close();
        } catch (IOException e) {
            System.out.println("Error propertiesReader");
        }

        return value;
    }

    public static String propFactory(String name){
        DBHelper dbHelper = DBHelper.getInstance();
        Properties prop = new Properties();
        String value=null;
        try {
          prop.load(dbHelper.getClass().getClassLoader().getResourceAsStream("used.properties"));
          value = prop.getProperty(name);

        } catch (IOException e) {
            System.out.println("Error propFactory");
        }
        return value;
    }
}
