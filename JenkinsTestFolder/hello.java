package JenkinsTestFolder;
import java.util.*;
import java.io.*;

public class hello {
    public static void main(String args[]){
        System.out.println("Hello from Java");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // final Properties p = getProperties();
        // System.out.println("subdomain = " + p.getProperty("subdomain"));
        
    }

    private static Properties getProperties(){

        try {
            FileReader reader=new FileReader(System.getProperty("user.dir") + "/a.properties");
            Properties p=new Properties();
            p.load(reader);
            return p;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}