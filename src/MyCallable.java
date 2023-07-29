import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private String text;

    public MyCallable(String text) {
        this.text = text;
    }

    @Override
    public Integer call() throws Exception {
        return logic(text);
    }

    private static int logic(String text) {
        int maxSize = 0;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < text.length(); j++) {
                if (i >= j) {
                    continue;
                }
                boolean bFound = false;
                for (int k = i; k < j; k++) {
                    if (text.charAt(k) == 'b') {
                        bFound = true;
                        break;
                    }
                }
                if (!bFound && maxSize < j - i) {
                    maxSize = j - i;
                }
            }
        }
        return maxSize;
    }
}
