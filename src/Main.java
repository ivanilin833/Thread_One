import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int maxValue = 0;
        int futValue;
        String[] texts = new String[25];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }
        List<Future<Integer>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(25);
        long startTs = System.currentTimeMillis(); // start time
        for (String text : texts) {
            futures.add(executor.submit(new MyCallable(text)));
        }
        for (Future<Integer> future : futures) {
            futValue = future.get();
            if(futValue> maxValue) {
                maxValue = futValue;
            }
        }
        executor.shutdown();
        long endTs = System.currentTimeMillis(); // end time
        System.out.println("Max = " + maxValue);
        System.out.println("Time: " + (endTs - startTs) + "ms");
    }



    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
