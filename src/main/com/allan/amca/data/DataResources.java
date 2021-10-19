package main.com.allan.amca.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class to retrieve the database information from the properties file. If you are having issues connecting to the
 * database. Please open the res_en.properties file and make sure the database URI, user, password are correct for your
 * specific configuration.
 */
public final class DataResources {

    private String URL;
    private String DB_USER;
    private String PASSWORD;
    private String NEW_DB_URL;
    private static final DataResources instance = new DataResources();

    private final Properties props = new Properties();

    // prevent from allowing any other classes to instantiate this class
    private DataResources(){}

    /**
     * Singleton access
     * @return new instance of DataResources
     */
    public static DataResources getInstance() {
        return instance;
    }

    public String getDBUsername() {
        return DB_USER;
    }

    public String getDBPassword() {
        return PASSWORD;
    }

    public String getDBUrl() {
        return URL;
    }
    public String getNewDbUrl() {
        return NEW_DB_URL;
    }

    public void loadPropsFile() {
        final String propertyFileName = "src/resources/res_en.properties";

        //Try to load the properties file
        try (InputStream input = DataResources.class.getClassLoader().getResourceAsStream("res_en.properties")) {

            if (input == null) {
                // If file not found, throw exception
                throw new FileNotFoundException(String.format("Property file: %s not found.", propertyFileName));
            }

            props.load(input);
            
            URL = props.getProperty("db.url");
            DB_USER = props.getProperty("db.user");
            NEW_DB_URL = props.getProperty("createDB.url");
            PASSWORD = props.getProperty("db.pw");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
