package structure2;

public class function2 {

    public static void OOP(String string) {

        Input input = new Input();
        input.input(string);
        Shift shift = new Shift(input.getLineTxt());
        shift.shift();
        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
        alphabetizer.sort();
        Output output = new Output(alphabetizer.getKwicList());
        output.output("D:\\output.txt");

    }
}
