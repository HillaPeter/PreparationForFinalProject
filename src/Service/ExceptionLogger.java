package Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExceptionLogger {

    private static ExceptionLogger exceptionLogger;
    private String path;
    private File file;
    private FileWriter fileWriter;

    public static ExceptionLogger getInstance() throws IOException {
        if(exceptionLogger == null){
            exceptionLogger = new ExceptionLogger();
        }
        return exceptionLogger;
    }

    private ExceptionLogger() throws IOException {
        try{
            path = "log/exceptionLog.txt";
            file = new File(path);
            fileWriter = new FileWriter(file);
        }
        catch(Exception e){
            writeException(e);
        }
    }

    public void writeException(Exception e) throws IOException {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        String message = strDate + " Exception: " + e.toString();
        fileWriter.write(message);
    }
}
