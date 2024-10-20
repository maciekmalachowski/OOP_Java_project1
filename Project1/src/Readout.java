public class Readout implements Comparable<Readout>{
    private double value;

    @Override
    public int compareTo(Readout readout) {
    return Double.compare(value, readout.getValue());
    }

    public Readout(double value){ //constructor
    this.value=value;
    }
    
    public double getValue(){ //standard getter
    return value;
    }
}