import java.util.ArrayList;
import java.util.Collections;
public class Sensor implements Comparable<Sensor>{
    private String name;
    private ArrayList<Readout> data=new ArrayList<>();

    public Sensor(String name){
        this.name=name;
    }

    @Override
    public int compareTo(Sensor other) {
        return this.name.compareTo(other.name);
    }

    public String getName() {
        return name;
    }

    public void addReadout(Readout readout){
        data.add(readout);
    }

    public int getLengthOfData(){
        return data.size();
    }

    public Readout getMax(Logger logger, Boolean log)
    {
        Collections.sort(data);
        if (log==true){
            logger.log(Logger.Level.MAX_ELEM, "Min. element for sensor ["+name+"]: " + data.get(data.size() - 1).toString());
        }
        return data.get(data.size() - 1);
    }

    public Readout getMin(Logger logger, Boolean log)
    {
        Collections.sort(data);
        if (log==true){
            logger.log(Logger.Level.MIN_ELEM, "Min. element for sensor ["+name+"]: " + data.get(0).toString());
        }
        return data.get(0);
    }

    public double getMean()
    {
        double sum = 0.0;
        for(int i=0; i<data.size(); i++)
        {
            sum += data.get(i).getValue();
        }
        double mean = sum/data.size();
        return mean;
    }

    public MedianWrapper getMedian()
    {
        Collections.sort(data);
        if(data.size()%2==1)
        {
            return new MedianWrapper(data.get(data.size()/2));
        }
        else
        {
            Readout number1 = data.get(data.size()/2);
            Readout number2 = data.get(data.size()/2 - 1);
            return new MedianWrapper(number1, number2);
        }
    }

    public int noOfCentralElements(double mean, double eps, Logger logger)
    {
        int count = 0;
        
        for(int i=0; i < data.size(); i++)
        {
            double number = data.get(i).getValue();
            if(number < (mean+eps) && number > (mean-eps))
            {
                count += 1;
                logger.log(Logger.Level.CENTRAL_ELEM, "Central element for sensor ["+name+"]: " + data.get(i).toString());
            }
        }
        return count;
    }
}