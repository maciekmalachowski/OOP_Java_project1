import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws Exception {
        Logger logger_a = new LoggerFile("data_a.log");

    // Task 1.1
        String dir = "Project1/data/data_a1.txt";
        ArrayList<Readout> list = new ArrayList<>();
        File file = new File(dir);

        try(Scanner myScanner = new Scanner(file))
            {
                while(myScanner.hasNextLine())
                {
                    String line = myScanner.nextLine();
                    double number = Double.parseDouble(line);
                    list.add(new Readout(number));
                }
            }
            catch(FileNotFoundException e)
            {
                logger_a.log(Logger.Level.ERROR, dir);
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        getOutputInfo("Task I.1", list, file.getName(), 0);
        logger_a.flush();

    // Task 1.2
        Logger logger_b = new LoggerFile("data_b.log");
        dir = "Project1/data/data_b3.txt";
        list.clear();
        file = new File(dir);

        int invalid_records = 0;
        try(Scanner myScanner = new Scanner(file))
        {
            while(myScanner.hasNextLine())
            {
                String line = myScanner.nextLine();
                try
                {
                    double number = Double.parseDouble(line);
                    list.add(new Readout(number));
                }
                catch(NumberFormatException e)
                {
                    logger_b.log(Logger.Level.ERROR, "Faulty record in ["+dir+"]:"+line);
                    invalid_records += 1;
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        getOutputInfo("Task I.2", list, file.getName(), invalid_records);
        logger_b.flush();

    // Task 1.3
        Logger logger_c = new LoggerFile("data_c.log");
        dir = "Project1/data/data_c1.txt";
        list.clear();
        file = new File(dir);

        invalid_records = 0;
        try(Scanner myScanner = new Scanner(file))
        {
            while(myScanner.hasNextLine())
            {
                String line = myScanner.nextLine();
                try
                {
                    String[] result = line.split(" id:");
                    double number = Double.parseDouble(result[0]);
                    list.add(new ReadoutWithUuid(number, result[1]));
                }
                catch(NumberFormatException e)
                {
                    logger_c.log(Logger.Level.ERROR, "Faulty record in ["+dir+"]:"+line);
                    invalid_records += 1;
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        getOutputInfo("Task II.1", list, file.getName(), invalid_records);
        logger_c.flush();
    }

    public static void getOutputInfo(String title, ArrayList<Readout> data, String fileName, int invalid_records)
    {
        String output = "";
        output += title + "\n";
        output += "Maciej Malachowski, 292773\n";
        output += "--------------------\n";
        output += "Data filename: "+ fileName + "\n";
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

        System.out.println(output);
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

