import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
    // Task I.1
    processOneFile("data_a1.txt","data_a.log","Task I.1");

    // Task I.2
    processOneFile("data_b1.txt","data_b.log","Task I.2");

    // Task II.2
    processOneFile("data_c1.txt","data_c.log","Task II.2");
        
    }

    static void processOneFile(String filename, String logFilename, String title) throws IOException{
        Logger logger = new LoggerFile(logFilename);
        FileContent fContent = IOHelper.readFile(filename, logger);
        System.out.println(getOutputInfo(fContent, title));
        logger.flush();
    }

    public static String getOutputInfo(FileContent fContent, String title)
    {
        ArrayList<Readout> data = fContent.getData();
        String filename = fContent.getFileName();
        int invalid_records = fContent.getNoOfInvalidRecords();
        String output = "";
        output += title + "\n";
        output += "Maciej Malachowski, 292773\n";
        output += "--------------------\n";
        output += "Data filename: "+ filename + "\n";
        output += "Length of the series: "+ data.size() + "\n";
        output += "Max value: " + getMax(data).toString() + "\n";
        output += "Min value: " + getMin(data).toString() + "\n";
        output += String.format("Mean value: %.3f\n", getMean(data));
        output += "Median: " + getMedian(data).toString() + "\n";
        output += "Number of central elements: " + noOfCentralElements(data) + "\n";
        if (invalid_records != 0)
        {
            output += "Number of invalid records: "+ invalid_records + "\n";
        }
        output += "--------------------\n";

        // System.out.println(output);
        return output;
    }


    static Readout getMax(ArrayList<Readout> list)
    {
        Collections.sort(list);
        return list.get(list.size() - 1);
    }

    static Readout getMin(ArrayList<Readout> list)
    {
        Collections.sort(list);
        return list.get(0);
    }

    static double getMean(ArrayList<Readout> list)
    {
        double sum = 0.0;
        for(int i=0; i<list.size(); i++)
        {
            sum += list.get(i).getValue();
        }
        double mean = sum/list.size();
        return mean;
    }

    static MedianWrapper getMedian(ArrayList<Readout> list)
    {
        Collections.sort(list);
        if(list.size()%2==1)
        {
            return new MedianWrapper(list.get(list.size()/2));
        }
        else
        {
            Readout number1 = list.get(list.size()/2);
            Readout number2 = list.get(list.size()/2 - 1);
            return new MedianWrapper(number1, number2);
        }
    }

    static int noOfCentralElements(ArrayList<Readout> list)
    {
        double max = getMax(list).getValue();
        double min = getMin(list).getValue();
        double mean = getMean(list);
        double eps = (max-min)/100;
        int count = 0;
        
        for(int i=0; i < list.size(); i++)
        {
            double number = list.get(i).getValue();
            if(number < (mean+eps) && number > (mean-eps))
            {
                count += 1;
            }
        }
        return count;
    }
}

