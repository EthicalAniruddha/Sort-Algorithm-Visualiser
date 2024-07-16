import javax.swing.*;
import java.time.Duration;
import java.time.Instant;
//import java.util.Arrays;

public class BubbleSort {

    private final int[] arr;

    private final ShowBars showBars;

    private final Timer timer;

    private Instant start;

    private int i = 0, j = 0;

    private int swaps = 0;

    public BubbleSort(int[] arr, ShowBars showBars) {
        this.arr = arr;
        this.showBars = showBars;
        Window.isSwapping = true;
        timer = new Timer(1, e -> {
            start = Instant.now();
            sortStep();
        });
        timer.start();
    }

    // Bubble sort algorithm
    private void sortStep() {
        int barWidth = showBars.getWidth()/arr.length;
        if (i < arr.length) {
            if (j < arr.length - i - 1) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // Updating the necessary bars
                    int startIndex = Math.max(0, j - 1);
                    int endIndex = Math.min(arr.length - 1, j + 2);
                    showBars.setData(arr);
                    showBars.repaint(startIndex * barWidth, 0, (endIndex - startIndex + 1) * barWidth, showBars.getHeight());

                    //System.out.println("Swapped " + arr[j] + " with " + arr[j+1]);

                    swaps++;
                }
                j++;
            } else {
                i++;
                j = 0;
            }
        } else {
            Instant end = Instant.now();
            timer.stop();
            //System.out.println("\nArray sorted!");
            //System.out.println(Arrays.toString(arr)+"\n");
            JOptionPane.showMessageDialog(null, "     Array sorted.  ","Bubble sort info", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Number of swaps: "+swaps,"Bubble sort info", JOptionPane.INFORMATION_MESSAGE);
            Duration timeElapsed = Duration.between(start, end);
            JOptionPane.showMessageDialog(null, "Time taken: "+timeElapsed.toNanos()+" nano secs","Bubble sort info", JOptionPane.INFORMATION_MESSAGE);
            Window.isSorted = true;
            Window.isSwapping = false;
        }
    }
}
