import java.util.ArrayList;
import java.util.Collections;
public class Sensor {
    private String name;
    private ArrayList<Readout> data=new ArrayList<>();

    public Sensor(String name){
        this.name=name;
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

    public Readout getMax()
    {
        Collections.sort(data);
        return data.get(data.size() - 1);
    }

    public Readout getMin()
    {
        Collections.sort(data);
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

    public int noOfCentralElements(double mean, double eps)
    {
        int count = 0;
        
        for(int i=0; i < data.size(); i++)
        {
            double number = data.get(i).getValue();
            if(number < (mean+eps) && number > (mean-eps))
            {
                count += 1;
            }
        }
        return count;
    }
}