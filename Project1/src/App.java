import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws Exception {

        String dir = "Project1/data/data_a3.txt";
        ArrayList<Double> list = new ArrayList<>();
        File file = new File(dir);

        try(Scanner myScanner = new Scanner(file))
            {
                while(myScanner.hasNextLine())
                {
                    String line = myScanner.nextLine();
                    double number = Double.parseDouble(line);
                    list.add(number);
                }
            }
            catch(FileNotFoundException e)
            {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        displayInfo("Task I.1", list, file.getName(), 0);


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
                    list.add(number);
                }
                catch(NumberFormatException e)
                {
                    invalid_records += 1;
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        displayInfo("Task I.2", list, file.getName(), invalid_records);

    }

    public static void displayInfo(String title, ArrayList<Double> data, String fileName, int invalid_records)
    {
        String output = "";
        output += title + "\n";
        output += "Maciej Malachowski, 292773\n";
        output += "--------------------\n";
        output += "Data filename: "+ fileName + "\n";
        output += "Length of the series: "+ data.size() + "\n";
        output += String.format("Max value: %.3f\n", getMax(data));
        output += String.format("Min value: %.3f\n", getMin(data));
        output += String.format("Mean value: %.3f\n", getMean(data));
        output += String.format("Median: %.3f\n", getMedian(data));
        output += "Number of central elements: "+noOfCentralElements(data) + "\n";
        if (invalid_records != 0)
        {
            output += "Number of invalid records: "+ invalid_records + "\n";
        }
        output += "--------------------\n";

        System.out.println(output);
    }


    public static double getMax(ArrayList<Double> list)
    {
        Collections.sort(list);
        return list.get(list.size() - 1);
    }

    public static double getMin(ArrayList<Double> list)
    {
        Collections.sort(list);
        return list.get(0);
    }

    public static double getMean(ArrayList<Double> list)
    {
        double sum = 0.0;
        for(int i=0; i<list.size(); i++)
        {
            sum += list.get(i);
        }
        double mean = sum/list.size();
        return mean;
    }

    public static double getMedian(ArrayList<Double> list)
    {
        Collections.sort(list);
        if(list.size()%2==1)
        {
            return list.get(list.size()/2);
        }
        else
        {
            double number1 = list.get(list.size()/2);
            double number2 = list.get(list.size()/2 - 1);
            return (number1+number2)/2;
        }
    }

    public static int noOfCentralElements(ArrayList<Double> list)
    {
        double max = getMax(list);
        double min = getMin(list);
        double mean = getMean(list);
        double eps = (max-min)/100;
        int count = 0;
        
        for(double number : list)
        {
            if(number < (mean+eps) && number > (mean-eps))
            {
                count += 1;
            }
        }
        return count;
    }
}

