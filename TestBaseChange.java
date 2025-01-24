/*
  Written by: Jaylen Small
  
  Output:
    Change base of: 53
    Binary value = 110101
    Hex value = 35
    Change base of: 35.6
    Binary value = 100011.10011
    Hex value = 20.99999
    Change base of: .56
    Binary value = .10001
    Hex value = .8F5C2
 */
import java.util.Scanner;

class Base {
    // Method for converting numbers to binary
    public static String binary(String value){
        String result = "";
        String reversedResult = "";
        String decimalResult = ".";
        Double tempValue = Double.parseDouble(value);
        int wholeValue = (int)Math.floor(tempValue);
        Double decimalValue = tempValue - wholeValue;
        boolean doneCalculating = false;
        
        // For whole numbers
        if (wholeValue > 0){
            // Divides the number by 2 until it can't be divided anymore
            while (doneCalculating == false){
                result += wholeValue % 2;

                wholeValue = (wholeValue / 2);

                if (wholeValue == 0){
                    doneCalculating = true;
                }
            }

            // Takes all the remainders and adds them to a string in reverse order to get your binary result
            for (int i = result.length() - 1; i >= 0; i--){
            reversedResult += result.charAt(i);
            }
        }

        if (decimalValue > 0){
            // For decimals
            doneCalculating = false;

            // Same as above except we are multiplying by 2 instead of dividing
            while (doneCalculating == false){
                double tempResult = decimalValue * 2;
                
                int tempWhole = (int)Math.floor(tempResult);
                decimalResult += tempWhole;

                decimalValue = tempResult - tempWhole;
                
                if (decimalValue == 0){
                    doneCalculating = true;
                } else if (decimalResult.length() >= 6){
                    doneCalculating = true;
                }
            }

            // Adds reversed decimal result to the final result and returns it to the user
            reversedResult += decimalResult;
        }

        return reversedResult;
    }

    public static String hex(String value){
        String result = "";
        String decimalResult = ".";
        String hexResult = "";
        String finalResult = "";
        Double tempValue = Double.parseDouble(value);
        int wholeValue = (int)Math.floor(tempValue);
        Double decimalValue = tempValue - wholeValue;
        boolean doneCalculating = false;
        
        // For whole numbers

        // If the whole number is less than zero, it skips all the code below
        if (wholeValue > 0){
            // Divides the number by 16 until it can't be divided anymore
            while (doneCalculating == false){
                result += wholeValue % 16;

                wholeValue = (wholeValue / 16);

                if (wholeValue == 0){
                    doneCalculating = true;
                }
            }

            // Takes the first two pairs in the string, parses them to numbers and passes them into the asciiSearch method
            String lastNums = result.substring(0, 2); 
            int hexNums = Integer.parseInt(lastNums);
            
            hexResult = asciiSearch(hexNums);

            // Reverses the string
            for (int i = result.length() - 1; i >= 2; i--){
                finalResult += result.charAt(i);
            }

            finalResult += hexResult;
        }
        
        if (decimalValue > 0){
            // For decimals
            doneCalculating = false;

            // Same as above except we are multiplying by 16 instead of dividing
            while (doneCalculating == false){
                double tempResult = decimalValue * 16;
                
                int tempWhole = (int)Math.floor(tempResult);

                // If the result is greater than 9, the number is passed to the asciiSearch method
                if (tempWhole > 9){
                    String temp = asciiSearch(tempWhole);
                    decimalResult += temp;
                } else {
                    decimalResult += tempWhole;
                }

                decimalValue = tempResult - tempWhole;
                
                if (decimalValue == 0){
                    doneCalculating = true;
                } else if (decimalResult.length() >= 6){
                    doneCalculating = true;
                }
            }

            // Adds reversed decimal result to the final result and returns it to the user
            finalResult += decimalResult;
        }
        
        return finalResult;
    }

    public static String asciiSearch(int hexNums){
        String hexString = "";
        int remainder = hexNums;

        // Remainder is modded by 16 and if the result is greater than 9 then you add 64 and subtract 9
        while (remainder > 0){
            int modRemainder = remainder % 16;
            String tempHexString = "";

            if (modRemainder > 9){
                tempHexString += (char)(64 + modRemainder - 9);
            } else {
                tempHexString += modRemainder;
            }

            remainder = remainder/16;
            hexString = tempHexString + hexString;
        }

        return hexString;
    }
}

public class TestBaseChange{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        //Get first value
        System.out.print("Change base of: ");
        String value = input.next();
        
        while(!(value.equals("quit"))){
            String binaryValue = Base.binary(value);
            System.out.println("Binary value = " + binaryValue);
            
            String hexValue = Base.hex(value);
            System.out.println("Hex value = " + hexValue);
            
            //Get subsequent values
            System.out.print("Change base of: ");
            value = input.next();
        }//end while
    }//end main
}//end class
