package tools;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;



public class PropertyLoader {
    private static final String PROPERTIES_FILE = "/application.properties";
    private static final Properties PROPERTIES = getPropertiesInstance();

    public static Properties getPropertiesInstance(){
        var instance = new Properties();

        try (
                var resourceStream = PropertyLoader.class.getResourceAsStream(PROPERTIES_FILE);
                var inputStream = new InputStreamReader(Objects.requireNonNull(resourceStream), UTF_8);
        ) {
            instance.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return instance;
    }

    public static String loadSystemProperty(String propertyName) {
        return PROPERTIES.getProperty(propertyName);
    }

}
