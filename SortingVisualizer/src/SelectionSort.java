import javax.swing.*;
import java.time.Duration;
import java.time.Instant;
//import java.util.Arrays;

public class SelectionSort {
    private final int[] arr;

    private final ShowBars showBars;

    private final Timer timer;

    private Instant start;

    private int i = 0, j = 0, minIndex = 0;

    private int swaps = 0;

    public SelectionSort(int[] arr, ShowBars showBars) {
        this.arr = arr;
        this.showBars = showBars;

        Window.isSwapping = true;

        timer = new Timer(1, e ->  {
            start = Instant.now();
            sortStep();
        });
        timer.start();
    }

    // Selection sort algorithm
    private void sortStep() {
        int barWidth = showBars.getWidth() / arr.length;

        // Find the minimum element in unsorted array
        if (i < arr.length - 1) {
            if (j < arr.length) {
                if (arr[j] > arr[minIndex]) {
                    minIndex = j;
                }
                j++;
            } else {
                // Swap the minimum element with the first element if minimum element is found
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;

                // Updating the necessary bars
                int startIndex = Math.max(0, minIndex - 1);
                int endIndex = Math.min(arr.length - 1, i + 1);
                showBars.setData(arr);
                showBars.repaint(startIndex * barWidth, 0, (endIndex - startIndex + 1) * barWidth, showBars.getHeight());

                //System.out.println("Swapped " + arr[i] + " with " + arr[minIndex]);

                i++;
                j = i + 1;
                minIndex = i;

                showBars.repaint();

                swaps++;
            }
        } else {
            Instant end = Instant.now();
            timer.stop();
            showBars.repaint();
            //System.out.println("\nArray sorted!");
            //System.out.println(Arrays.toString(arr) + "\n");
            JOptionPane.showMessageDialog(null, "     Array sorted.  ","Selection sort info", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Number of swaps: "+swaps,"Selection sort info", JOptionPane.INFORMATION_MESSAGE);
            Duration timeElapsed = Duration.between(start, end);
            JOptionPane.showMessageDialog(null, "Time taken: "+timeElapsed.toNanos()+" nano secs","Selection sort info", JOptionPane.INFORMATION_MESSAGE);
            Window.isSorted = true;
            Window.isSwapping = false;
        }
    }
}
