import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IOHelper {
    
    static FileContent readFile(String filename, Logger logger) throws IOException{
        ArrayList<Sensor> sensors = new ArrayList<>();
        String dir = "Project1/data/" + filename;
        File file = new File(dir);
        int invalid_records = 0;


        try(Scanner myScanner = new Scanner(file)) {
            while(myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                try {
                    String[] result = line.split(" ");
                    if (result.length == 3) {
                        double value = Double.parseDouble(result[0]);
                        String id = result[1].replace("id:", "");
                        String sensorName = result[2];
                        addReadoutToSensor(sensors, sensorName, new ReadoutWithUuid(value, id));
                    }
                    else if (result.length == 2) {
                        addReadoutToSensor(sensors, "<N/A>", new ReadoutWithUuid(Double.parseDouble(result[0]), result[1].replace(" id:", "")));
                    } 
                    else {
                        addReadoutToSensor(sensors, "<N/A>", new Readout(Double.parseDouble(result[0])));
                    }
                }
                catch(NumberFormatException e) {
                    logger.log(Logger.Level.ERROR, "Faulty record in ["+dir+"]:"+line);
                    invalid_records += 1;
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return new FileContent(sensors, invalid_records, filename);
    }

    public static String getOutputInfo(FileContent fContent, String title)
    {
        String filename = fContent.getFileName();
        int invalid_records = fContent.getNoOfInvalidRecords();
        String output = "";
        output += title + "\n";
        output += "Maciej Malachowski, 292773\n";
        output += "--------------------\n";
        output += "Data filename: "+ filename + "\n";
        for(Sensor sensor: fContent.getSensors()){
            output += "----\n";
            output += "Sensor name: " + sensor.getName() + "\n";
            output += "Length of the series: "+ sensor.getLengthOfData() + "\n";
            output += "Max value: " + sensor.getMax().toString() + "\n";
            output += "Min value: " + sensor.getMin().toString() + "\n";
            output += String.format("Mean value: %.3f\n", sensor.getMean());
            output += "Median: " + sensor.getMedian().toString() + "\n";
            double eps = (sensor.getMax().getValue()-sensor.getMin().getValue())/100;
            output += "Number of central elements: " + sensor.noOfCentralElements(sensor.getMean(), eps) + "\n";
            if (invalid_records != 0){
                output += "Number of invalid records: "+ invalid_records + "\n";
            }
        }
        output += "--------------------\n";
        return output;
    }

    private static void addReadoutToSensor(ArrayList<Sensor> sensorsList, String sensorName, Readout readout){
        Sensor target = null;
        for (Sensor sensor: sensorsList){
            if (sensor.getName().equals(sensorName)) {
                target = sensor;
                break;
            }
        }

        if (target == null) {
            target = new Sensor(sensorName);
            sensorsList.add(target);
        }

        target.addReadout(readout);
    }
}
