public class Main {

    private static boolean containNumb(String temp){
        return !temp.contains("1") && !temp.contains("2") && !temp.contains("3") && !temp.contains("4") && !temp.contains("5") && !temp.contains("6") && !temp.contains("7") && !temp.contains("8") &&
                !temp.contains("9") && !temp.contains("0");
    }

    private static int RimToAr(String numb){
        return switch (numb) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }
    private static String ArToRim(int num){
        var keys = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        var vals = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        StringBuilder ret = new StringBuilder();
        int ind = 0;

        while(ind < keys.length)
        {
            while(num >= vals[ind])
            {
                var d = num / vals[ind];
                num = num % vals[ind];
                ret.append(keys[ind].repeat(d));
            }
            ind++;
        }

        return ret.toString();
    }
    public static int calc(String input){
        try {
            int firstGap = input.indexOf(" ");
            int lastGap = input.lastIndexOf(" ");

            if (firstGap == -1 || lastGap == firstGap) throw new Exception("string is not a mathematical operation");

            String firstNumb = input.substring(0, firstGap);
            String sign = input.substring(firstGap + 1, lastGap);
            String secondNumb = input.substring(lastGap + 1, input.length());

            if (sign.contains(" ")) throw new Exception("the format of the mathematical operation does not satisfy the task");

            //false - 0..9, true - I..X
            int numberOne = 0, numberTwo = 0;
            boolean typeNumb1 = containNumb(firstNumb), typeNumb2 = containNumb(secondNumb);

            if(!typeNumb1 && !typeNumb2) {
                numberOne = Integer.parseInt(firstNumb);
                numberTwo = Integer.parseInt(secondNumb);
                return switch (sign) {
                    case "+" -> numberOne + numberTwo;
                    case "-" -> numberOne - numberTwo;
                    case "*" -> numberOne * numberTwo;
                    case "/" -> numberOne / numberTwo;
                    default -> 0;
                };
            }
            if (typeNumb1 && !typeNumb2 || !typeNumb1 && typeNumb2) throw new Exception("different number systems");

            if (typeNumb1 && typeNumb2){
                numberOne = RimToAr(firstNumb);
                numberTwo = RimToAr(secondNumb);

                if (numberOne < numberTwo) throw new Exception("there are no negative numbers in the roman system");
                int res = 0;
                switch (sign){
                    case "+": res = numberOne + numberTwo;
                    case "-": res = numberOne - numberTwo;
                    case "*": res = numberOne * numberTwo;
                    case "/": res = numberOne / numberTwo;
                }
                System.out.println(ArToRim(res));
                return res;
            }
            return 0;

        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return 0;
        }
    }

    public static void main(String args[]){
        String test = "1 + 2";
        System.out.println(calc(test));

        String test1 = "VI / III";
        System.out.println(calc(test1));

        String test2 = "I - II";
        System.out.println(calc(test2));

        String test3 = "I + 1";
        System.out.println(calc(test3));

        String test4 = "1";
        System.out.println(calc(test4));

        String test5 = "1 + 2 + 3";
        System.out.println(calc(test5));
    }
}
