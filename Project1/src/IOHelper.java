import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IOHelper {
    
    static FileContent readFile(String filename, Logger logger) throws IOException{
        ArrayList<Readout> list = new ArrayList<>();
        String dir = "Project1/data/" + filename;
        File file = new File(dir);
        int invalid_records = 0;
        try(Scanner myScanner = new Scanner(file))
        {
            while(myScanner.hasNextLine())
            {
                String line = myScanner.nextLine();
                try
                {
                    String[] result = line.split(" id:");

                    if (result.length > 1) {
                        list.add(new ReadoutWithUuid(Double.parseDouble(result[0]), result[1]));
                    } 
                    else {
                        list.add(new Readout(Double.parseDouble(result[0])));
                    }
                }
                catch(NumberFormatException e)
                {
                    logger.log(Logger.Level.ERROR, "Faulty record in ["+dir+"]:"+line);
                    invalid_records += 1;
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return new FileContent(list, invalid_records, filename);
    }
}
