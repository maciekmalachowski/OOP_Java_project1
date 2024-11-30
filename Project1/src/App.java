import java.io.IOException;
import java.io.FileWriter;


public class App {
    public static void main(String[] args) throws Exception {
    // // Task I.1
    // processOneFile("data_a1.txt","data_a.log","Task I.1", "out_a.txt");

    // // Task I.2
    // processOneFile("data_b1.txt","data_b.log","Task I.2", "out_b.txt");

    // // Task II.2
    // processOneFile("data_c1.txt","data_c.log","Task II.2", "out_c.txt");

    // // Task III.3
    // processOneFile("data_d1.txt","data_d.log","Task III.3", "out_d.txt");

    // Task III.4
    processOneFile("data_e1.txt","data_e.log","Task III.4", "out_e.txt");
        
    }

    static void processOneFile(String filename, String logFilename, String title, String out_name) throws IOException{
        FileWriter file = new FileWriter("Project1/outputData/" + out_name);
        Logger logger = new LoggerFile(logFilename);
        FileContent fContent = IOHelper.readFile(filename, logger);
        file.write(IOHelper.getOutputInfo(fContent, title, logger));
        file.close();
        logger.flush();
    }
}

