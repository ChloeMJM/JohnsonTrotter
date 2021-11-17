import java.util.Scanner;
import java.util.ArrayList;
public class JTTT {

        private static ArrayList<Integer> arrL;
        private static ArrayList<Boolean> arrow;
        private static int maxMobile = 0;
        private static int mobile = 0;
        private static int mobilePos = 0;

        public static void johnsonTrotter(int num) {

            // Generate arraylist
            generateArrL(num);
            // Print initial arraylist
            System.out.println("Pass Number 1 ---------");
            printArrL(arrL, arrow);
            int i = 1;
            // Loop number of times of factorial
            while(i < factorial(num)) {
                System.out.println("Pass Number " + (i+1) + " ---------");

                // Calculate the largest mobile number
                calculateMobile(num);

                // Shift largest mobile number left
                // !arrow=false means pointing left
                if (!arrow.get(mobilePos) && mobilePos != 0) {
                    int temp;
                    boolean arrowTemp;

                    // Swap numbers
                    temp = arrL.get(mobilePos - 1);
                    arrL.set((mobilePos - 1), arrL.get(mobilePos));
                    arrL.set((mobilePos), temp);

                    // Swap arrows
                    arrowTemp = arrow.get(mobilePos - 1);
                    arrow.set((mobilePos - 1), arrow.get(mobilePos));
                    arrow.set((mobilePos), arrowTemp);

                }
                // Shift largest mobile number right
                // arrow=true means pointing right
                else if (arrow.get(mobilePos) && mobilePos < num - 1) {
                    int temp;
                    boolean arrowTemp;

                    // Swap numbers
                    temp = arrL.get(mobilePos + 1);
                    arrL.set((mobilePos + 1), arrL.get(mobilePos));
                    arrL.set((mobilePos), temp);

                    // Swap arrows
                    arrowTemp = arrow.get(mobilePos + 1);
                    arrow.set((mobilePos + 1), arrow.get(mobilePos));
                    arrow.set((mobilePos), arrowTemp);

                }
                // Flip arrow on element larger than mobile element
                for (int a = 0; a < num; a++) {
                    if (maxMobile < arrL.get(a)) {
                        if (!arrow.get(a)) {
                            arrow.set(a, true);
                        } else if (arrow.get(a)) {
                            arrow.set(a, false);
                        }
                    }
                }
                // Print arraylist
                printArrL(arrL, arrow);
                // Increase i
                i++;
            }

        }

        // Calculates largest mobile number
        public static void calculateMobile(int num){
            maxMobile = 0;

            // Loop through all numbers in arraylist
            for (int i = 0; i < num; i++){

                // Arrow pointing right and larger than right element excluding edges
                if (arrow.get(i) && i < num-1 && arrL.get(i) > arrL.get(i+1)) {
                    mobile = arrL.get(i);

                    // Check if larger than stored maxMobile
                    if (mobile > maxMobile){
                        // Set new max and index of maxMobile
                        maxMobile = mobile;
                        mobilePos = arrL.indexOf(maxMobile);
                    }
                }
                // Arrow pointing left and larger than left element excluding edges
                else if (!arrow.get(i) && i > 0 && arrL.get(i) > arrL.get(i-1)) {
                    mobile = arrL.get(i);

                    // Check if larger than stored maxMobile
                    if (mobile > maxMobile) {
                        // Set new max and index of maxMobile
                        maxMobile = mobile;
                        mobilePos = arrL.indexOf(maxMobile);
                    }
                }
            }
        }

        // Generate initial array list 1...n
        public static void generateArrL(int num) {
            arrL = new ArrayList<Integer>();
            arrow = new ArrayList<Boolean>();
            // Place values in array
            for (int i = 0; i < num; i++) {
                arrL.add(i + 1);
                arrow.add(false);
            }
        }

        // Compute n! (# of permutations)
        public static int factorial(int num) {
            int fact = 1;
            for (int i = 1; i <= num; i++) {
                fact *= i;
            }
            return fact;
        }

    // Print the array list
    public static void printArrL(ArrayList<Integer> arr, ArrayList<Boolean> arro) {
        System.out.print("                ");
        // Print arrow arraylist
        for (boolean arrow: arro) {
            if (arrow) {
                System.out.print(">");
            } else if (!arrow) {
                System.out.print("<");
            }
            System.out.print(" ");
        }
        System.out.println("");
        System.out.print("                ");
        // Print elements of arraylist
        for (Integer element : arr) {
            System.out.print(element + " ");
        }
        System.out.println("");
    }

        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Please enter a number: ");
            int userInt = scan.nextInt();

            // Call the johnson trotter algorithm
            johnsonTrotter(userInt);
        }
    }