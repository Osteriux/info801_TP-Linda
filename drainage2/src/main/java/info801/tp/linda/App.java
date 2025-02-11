package info801.tp.linda;

import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Space space = new SequentialSpace();
        
        space.put("Hello World!");
        Object[] tuple = space.get(new FormalField(String.class));				
        System.out.println(tuple[0]);
        
    }
}