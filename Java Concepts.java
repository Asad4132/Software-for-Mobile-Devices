import java.io.*;
import java.util.Scanner;

interface characteristics{
        public abstract void sound();
        public abstract void movement();
}
 
interface flyingType{
        public abstract void type();
}

class animal{
    protected String name;
    
    public animal(String n){
        name=n;
        System.out.println("Animal Constructor called");
    };
    
    public String getName(){
        return name;
    } 
}

class birds extends animal implements flyingType {
    protected int speed;
    
    public birds(String n, int s){
        super(n);
        speed=s;
        System.out.println("Birds Constructor called");
    }
    
    @Override 
    public void type(){
        System.out.println("We fly and fly :P");
    }
    
}
    
class mammal extends animal {
    protected int legs;
    protected int wings;
    static String inner=" Inner Class ";
    
    public mammal(String n, int l, int w){
        super(n);
        legs=l;
        wings=w;
        System.out.println("Mammals constructor");

    }
    

    
    class mammalExplanation{
        public void explanation(){
            System.out.println("Mammals have hair. They have sweat glands.");
        }
    }
    
    static class mammalStaticExplanation{
        public void explanation(){
            System.out.println("This is a"+ inner+"class. Mammals have hair. They have sweat glands.");
        }
    }
    
    
}
    
class Lion extends mammal implements characteristics{
    public Lion(String n, int l, int w){
        super(n,l,w);
        System.out.println("Lion Constructor");
    }
    
    @Override
    public void sound(){
        System.out.println("Lion Roar");
    }
    
    @Override 
    public void movement(){
        System.out.println("Lion Runs");
    }
    
    final public void method(){
        System.out.println("This is a final method in Lion");
    }
}
    
    
public class Assignment1{

    
     public static void main(String []args){
        animal a=new Lion("Lion",4,0);
        
        Lion blion = new Lion("Lion",4,0);
        blion.sound();
        blion.movement();
        blion.method();
        mammal.mammalExplanation b = new mammal("animal", 0, 0).new mammalExplanation();
        mammal.mammalStaticExplanation n = new mammal.mammalStaticExplanation();
        b.explanation();
        n.explanation();
        birds q=new birds("Tota",30);
        q.type();
        Scanner inputFile = null;
        File file = new File("file.txt");
        try
        {
            inputFile = new Scanner(file);
            while(inputFile.hasNext())
            {
                System.out.println(inputFile); 
            }
        }
        catch(FileNotFoundException fileException)
        {
            System.out.println(fileException + " Checked Type Exception/compile time exception ");
            System.out.println(" ");
        }
        
        inputFile.close();
        
        try
        {
            int unchecked=1/0;
        }
        catch(ArithmeticException divideByZero)
        {
            System.out.println(divideByZero + " Unchecked Type Exception/runtime exception ");
            System.out.println(" ");
        }
        
     }
     
     
}