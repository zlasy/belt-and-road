import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CommandTest {

    public static void main(String[] args){
        printTableHeader();
    }

    private static void printTableHeader() {
        List<String> sourceList = Arrays.asList("order", "reverse", "shop", "product","help");
        List<String> fromPlatformList = Arrays.asList("0","2","3","21");
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019,9,20);
        for(int i = 0; i<40; i++){
            for (String from: fromPlatformList) {
                for (String source: sourceList) {
                    System.out.println(df.format(calendar.getTime()) + "\t" + from + "\t" + source);
                }
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
}
