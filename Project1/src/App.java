import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
    // Task I.1
    processOneFile("data_a1.txt","data_a.log","Task I.1");

    // Task I.2
    processOneFile("data_b1.txt","data_b.log","Task I.2");

    // Task II.2
    processOneFile("data_c1.txt","data_c.log","Task II.2");

    // Task III.3
    processOneFile("data_d1.txt","data_d.log","Task III.3");
        
    }

    static void processOneFile(String filename, String logFilename, String title) throws IOException{
        Logger logger = new LoggerFile(logFilename);
        FileContent fContent = IOHelper.readFile(filename, logger);
        System.out.println(IOHelper.getOutputInfo(fContent, title));
        logger.flush();
    }
}

