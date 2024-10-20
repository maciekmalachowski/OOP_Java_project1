import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws Exception {
    // Task 1.1
        String dir = "Project1/data/data_a3.txt";
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
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        displayInfo("Task I.1", list, file.getName(), 0);

    // Task 1.2
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

    // Task 1.3
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
                    invalid_records += 1;
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        displayInfo("Task II.1", list, file.getName(), invalid_records);

    }

    public static void displayInfo(String title, ArrayList<Readout> data, String fileName, int invalid_records)
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
        output += String.format("Median: %.3f\n", getMedian(data));
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

    static double getMedian(ArrayList<Readout> list)
    {
        Collections.sort(list);
        if(list.size()%2==1)
        {
            return list.get(list.size()/2).getValue();
        }
        else
        {
            double number1 = list.get(list.size()/2).getValue();
            double number2 = list.get(list.size()/2 - 1).getValue();
            return (number1+number2)/2;
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

