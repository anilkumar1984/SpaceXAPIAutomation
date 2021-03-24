package dataProviders;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	File file;
	Properties properties;
	FileInputStream fileInputStream;
	
	public ConfigFileReader(){
		
		file=new File("configs/Config.properties");
		try {
			fileInputStream=new FileInputStream(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		properties=new Properties();
		
		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public String getEndPointUrl() {
		String url=properties.getProperty("url");
		if(url!=null) {
			return url;
		}
		else {
			throw new RuntimeException("Url property is not defined");
		}
	}
}
