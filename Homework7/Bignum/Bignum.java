import java.util.ArrayList;

/**
 * A class that creates a data type of arbitrarily many bits.
 * 
 * @author Andy Liang
 * @version 20170307
 */
public class Bignum
{
    private ArrayList<Integer> bigNum;
    /**
     * Constructor for objects of class Bignum.
     * @param num  An integer value to be converted into a Bignum.
     */
    public Bignum(int num)
    {
        bigNum = new ArrayList<Integer>();
        if(num == 0){
            bigNum.add(0);
        }
        else{
            while(num > 0){
                bigNum.add(num % 10);
                num /= 10;
            }
        }
    }
    
    /**
     * Add a given Bignum to this Bignum, returning the result as a new
     * Bignum.
     * @param num  The Bignum to be added to this Bignum.
     * @return  The sum of this Bignum with the given Bignum.
     */
    public Bignum add(Bignum num) {
        int carryOver = 0;
        Bignum bigger, smaller;
        if(this.getSize() < num.getSize()){
            int difference = num.getSize() - this.getSize();
            while(difference > 0){
                this.addValue(0);
                difference--;
            }
        }
        for(int i = 0; i < num.getSize(); i++){
            int temp = this.getValueAt(i);
            this.setValue((num.getValueAt(i) + temp + carryOver) % 10, i);
            carryOver = (num.getValueAt(i) + temp) / 10;
        }
        if(carryOver == 1){
            this.addValue(carryOver, num.getSize());
        }
        return this;
    }

    /**
     * Multiply this Bignum by a given Bignum, returning the result
     * as a new Bignum.
     * @param num  The Bignum by which to multiply this Bignum.
     * @return The product of this Bignum and the given Bignum.
     */
    public Bignum mul(Bignum num) {
        Bignum ret = new Bignum(0);
        if((this.getSize() == 1 && this.getValueAt(0) == 0)
            || (num.getSize() == 1 && num.getValueAt(0) == 0)
            ){}
        else{
            for(int i = 0; i < num.getSize(); i++){
                ret.add(this.mulOneDigit(num.getValueAt(i), i));
            }
        }
        this.changeArray(ret);
        return this;
    }
    
    private Bignum mulOneDigit(int num, int offset){ 
        //Offset is what base ten position the integer is in
        Bignum ret = new Bignum(0);
        if(offset != 0){
            offset--;
            if(offset > 0)
            for(int i = 0; i < offset; i++){
                ret.addValue(0);
            }
        }
        else{
            ret.removeAtIndex(0);
        }
        int carryOver = 0;
        for(int i = 0; i < this.getSize(); i++){
            int temp = this.getValueAt(i);
            ret.addValue((temp * num + carryOver) % 10);
            carryOver = (temp * num + carryOver) / 10;
        }
        if(carryOver != 0){
            ret.addValue(carryOver);
        }
        return ret;
    }
    
    /**
     * Raise this Bignum to a specified integer power, returning the result
     * as a new Bignum.
     * @param p  The power.
     * @return The result.
     */
    public Bignum pow(int p) {
        Bignum original = new Bignum(0);
        original.changeArray(this);
        if(p == 0){
            this.changeArray(new Bignum(1));
        }
        else{
            while(p > 1){
                this.mul(original);
                System.out.println("Temp = " + original.toString());
                System.out.println(this.toString());
                p--;
            }
        }
        return this;
    }
    
    /**
     * Create a string representation of this Bignum.
     */
    public String toString() {
        String ret = "";
        for(int i = 0; i < bigNum.size(); i++){
            ret = bigNum.get(i) + ret;
        }
        return ret;
    } 
    
    // Accessor
    public int getValueAt(int i){
        return bigNum.get(i);
    }
    
    public int getSize(){
        return bigNum.size();
    }
    
    public ArrayList<Integer> getArray(){
        return bigNum;
    }
    
    // Modifier
    public void changeArray(Bignum x){
        bigNum = x.getArray();
    }
    
    public void setValue(int x, int i){
        bigNum.set(i, x);
    }
    
    public void addValue(int x){
        bigNum.add(x);
    }
    
    public void addValue(int x, int i){
        bigNum.add(i, x);
    }
    
    public void removeAtIndex(int i){
        bigNum.remove(0);
    }
    
    public static void main(String[] args){
        Bignum x = new Bignum(2);
        Bignum y = new Bignum(2);
        //System.out.println(x.mulOneDigit(9, 0).toString());
        //System.out.println(x.toString());
        /*int i = 10;
        while (i > 1){
            System.out.println(x.mul(y).toString());
            i--;
        }*/
        System.out.println(x.pow(10).toString());
        
    }
}
