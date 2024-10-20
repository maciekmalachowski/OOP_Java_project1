public class ReadoutWithUuid extends Readout {
    private String uuid;
    
    public ReadoutWithUuid(double value, String uuid){
        super(value); //call the parent constructor
        this.uuid=uuid;
    }

    @Override
    public String toString() {
        return String.format("%.3f", getValue())+" ["+uuid+"]";
    }

}
    