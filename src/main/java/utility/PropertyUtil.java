package utility;

import enums.ConfigPropertyKeys;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class PropertyUtil {
    private static Properties prop = null;
    private static Object INSTANCE = null;
    private static Map<String , String> map =null;

    private static void readProperty(){
        if( Objects.isNull(prop) ){
            prop = new Properties();
            try(FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/configuration.properties")){
                prop.load(fis);
                map = new HashMap<>();
                prop.entrySet().forEach(entry -> {
                    map.put(String.valueOf(entry.getKey()).toLowerCase(), String.valueOf(entry.getValue()));
                });
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Unable to find properties file at ");
            } catch (IOException e) {
                throw new RuntimeException("Unable to read proprty file");
            }
            System.out.println("Configuration.properties values are : "+map);
        }
    }

    public synchronized static String getProperty(ConfigPropertyKeys keys){
        if(Objects.isNull(map))
            readProperty();
        if(Objects.isNull(map.get(keys.name().toLowerCase())))
            throw new RuntimeException(keys + " not found in configuration.properties");
        return map.get(keys.name().toLowerCase());
    }
}
