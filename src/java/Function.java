import java.util.ArrayList;
import java.util.List;

public class Function {
    public String name;

    public int nestingLevel;

    public List<Function> arguments;

    public Function usedIn;

    Function(String name, int nestingLevel, Function nestUpLevelFunction){
        this.name = name;
        this.nestingLevel = nestingLevel;
        arguments = new ArrayList<>();
        usedIn = nestUpLevelFunction;
    }

    public boolean isEelementary() {
        //Константа; переменная; функция без аргументов.
        return arguments.isEmpty();
    }
}
