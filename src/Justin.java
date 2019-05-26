
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Justin {
    public static void main(String[] args) throws IOException {
        //declare an array list
        ArrayList<Integer> input = new ArrayList<>();
        Stack<Integer> firstClass = new Stack<>();
        Stack<Integer> business = new Stack<>();
        Stack<Integer> economy = new Stack<>();
        ArrayList<Stack<Integer>> plane = new ArrayList<>();
        //declare and initialize a scanner

        Scanner key = new Scanner(new File("input.txt"));
        //input method
        while(key.hasNextInt()){
            input.add(key.nextInt());
        }

        //make a loop to take the numbers input, then run them through your check function
        //if (isFirstClass(inputNumber))... (and repeat for business and economy)
        //and then the rest
        for (int i=0; i< input.size(); i++){
            if (isFirstClass(input.get(i))) firstClass.push(input.get(i));
            else if (isBusiness(input.get(i)))  business.push(input.get(i));
            else if (isEconomy(input.get(i)))  economy.push(input.get(i));
        }
        //array for each class in stdout 1x3xn
        plane.add(firstClass);
        plane.add(business);
        plane.add(economy);
        System.out.println(plane);



    }
    private static boolean isPrime(int n){
        //check if n is a multiple of 2
        if (n%2==0 && n!=2) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
    private static boolean isFirstClass(int x) {
        return isPrime(x);
    }
    private static boolean isBusiness(int x) {
        return !isPrime(x) && x%2==1;
    }
    private static boolean isEconomy(int x) {
        return !isPrime(x) && x%2==0;
    }

}
