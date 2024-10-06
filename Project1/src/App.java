import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Double> data_a1 = readLines("Project1\\data\\data_a1.txt");
        ArrayList<Double> data_a2 = readLines("Project1\\data\\data_a2.txt");
        ArrayList<Double> data_a3 = readLines("Project1\\data\\data_a3.txt");
        
        displayInfo(data_a1, "data_a1.txt");
        displayInfo(data_a2, "data_a2.txt");
        displayInfo(data_a3, "data_a3.txt");

    }

    public static void displayInfo(ArrayList<Double> data, String fileName)
    {
        System.out.println("\nTask I.1");
        System.out.println("Maciej Malachowski 292773");
        System.out.println("--------------------");
        System.out.println("Data filename: "+ fileName);
        System.out.println("Length of the series: "+data.size());
        System.out.format("Max value: %.3f\n", getMax(data));
        System.out.format("Min value: %.3f\n", getMin(data));
        System.out.format("Mean value: %.3f\n", getMean(data));
        System.out.format("Median: %.3f\n", getMedian(data));
        System.out.println("Number of central elements: "+noOfCentralElements(data));
        System.out.println("--------------------\n");
    }

    public static ArrayList<Double> readLines(String dir)
    {
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
        return list;
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

