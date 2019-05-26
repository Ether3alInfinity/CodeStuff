// Lab37ast.java
// The Hexidecimal/Binary Translator
// Student Version 


import java.util.*;


public class Lab37ast
{
    public static void main (String args[])
    {
        System.out.println("Lab 37ast 1000 point version");
        String[] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        String[] bin = {"0000","0001","0010","0011","0100","0101","0110","0111",
                "1000","1001","1010","1011","1100","1101","1110","1111"};

        NumberSystems converter = new NumberSystems(hex,bin);

        Scanner input = new Scanner(System.in);

        boolean finished = false;
        do
        {
            System.out.print("Enter a base 16 number or \"end\".  -->  ");
            String base16num = input.nextLine();
            base16num = base16num.toUpperCase();
            if (base16num.equals("END"))
                finished = true;
            else if (converter.validB16(base16num))
            {
                String base2num = converter.getBinary(base16num);
                System.out.println("\n" + base16num + " in hexadecimal converts to " + base2num + " in binary.\n");
            }
            else
            {
                System.out.println("\nError, you did not enter a valid hexadecimal number.");
                System.out.println("Valid hexadecimal numbers can only containt digits from 0-9 or letters from A-F.");
            }
        }
        while (!finished);
    }

}


class NumberSystems
{

    private TreeMap base;

    public NumberSystems(String[] key, String[] val)
    {
        base = new TreeMap();
        for (int i = 0; i<key.length; i++){
            base.put(key[i], val[i]);
        }
    }

    public String getBinary(String base16num)
    {
        String result = "";
        for (int i=0; i<base16num.length(); i++){
            result += base.get(base16num.substring(i, i+1));
        }
        while(result.substring(0,1).equals("0"))
            result = result.substring(1);
        return  result;
    }

    public boolean validB16(String base16num)
    {
        for (int i=0; i<base16num.length(); i++){
            if (!base.keySet().contains(base16num.substring(i, i+1)))
                return false;
        }return true;
    }

}
	