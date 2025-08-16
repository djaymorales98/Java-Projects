public class SmartPhone extends Phone {
    private final String email;

    public SmartPhone(String name, long number) {
        super(name, number);
        this.email = "null";
    }
    public SmartPhone(String name, long number, String email){
        super(name, number);
        this.email = email;
    }

    public String toString(){
        return toString();
    }

    public String getAreaCode(){
        String areaC = String.valueOf(number);
        String areaCode = areaC.substring(0, 3);
        return areaCode;
    }

    public String getPrefix(){
        String preF = String.valueOf(number);
        String fullPre = preF.substring(3, 6);
        return fullPre;
    }

    public String getLineNumber(){
        String Line = String.valueOf(number);
        String lineNum = Line.substring(6, 10);
        return lineNum;
    }

    public String getEmail(){
        return email;
    }
    public String getTelephoneNeighbor(){
        long numPlus1 = number + 1;
        String neighNum = String.valueOf(numPlus1);

        String first3 = neighNum.substring(0, 3);
        String next3 = neighNum.substring(3, 6);
        String last4 = neighNum.substring(6, 10);

        return "(" + first3 + ") " + next3 + "-" + last4;
    }
}