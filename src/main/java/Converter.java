import java.util.List;

public class Converter {
    List<Function> allCode;

    Converter(List<Function> programm){
        allCode = programm;
    }

    public void convert(){
        System.out.println("Out: ");
        System.out.println(convertToString(allCode.get(0)));
    }
    private String convertToString(Function f){
        switch (f.name){
            case ("+"), ("-"), ("*"), ("/") -> {
                return convertPrimaryMath(f);
            }
            default -> {
                return convertPrimaryFun(f);
            }
        }
    }

    private String convertPrimaryFun(Function f){
        if (f.isEelementary()){
            return " " + f.name;
        }
        return " " + f.name + "(" + f.arguments.stream().map(this::convertToString).reduce((x, y)->x + ", " + y).get() + ")" ;
    }

    private String convertPrimaryMath(Function f){
        if (f.arguments.isEmpty()){
            throw new RuntimeException("Math trouble.");
        }
        return " [" + f.arguments.stream().map(this::convertToString).reduce((x, y)->x + " " + f.name + y).get() + " ]" ;
    }

}
