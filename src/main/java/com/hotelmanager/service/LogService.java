package com.hotelmanager.service;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LogService {
    private static final Logger logger = Logger.getLogger("HotelService");

    public static <SimpleFormatter> void LogService()
    {
    }

    public static void log(String message)
    {
        FileHandler fileHandler = null;
        try{
            fileHandler = new FileHandler("src/main/resources/logfile.log", true);
            logger.addHandler(fileHandler);

            String logMessage = "[" + new Date() + "] " + message;
            logger.info(logMessage);

        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if(fileHandler != null){
                fileHandler.close();
            }
        }
    }
}
