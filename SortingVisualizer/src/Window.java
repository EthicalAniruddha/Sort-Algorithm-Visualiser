import javax.swing.*;
import java.awt.*;
//import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;

public class Window extends JFrame {

    // Some boolean class variables
    public static boolean isSwapping; // To check if the swapping of elements is in procedure or not
    public static boolean isSorted; // To check if the array is sorted or not

    private final ShowBars showBars;

    // Frame dimensions
    int height = 900;
    int width = 1680;

    //Buttons
    JButton resetBtn;
    JButton bubbleBtn;
    JButton selectionButton;

    // Button Panel to add buttons
    JPanel buttonPanel;

    // Our array that we will sort
    private final int[] arrToSort;

    public Window() {

        // Initializing the frame
        setTitle("Sorting Algorithm Visualizer By EthicalAniruddha");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(new Color(0,0,0));

        setLayout(new FlowLayout());

        // For input frame
        JFrame inputFrame = new JFrame();
        inputFrame.setTitle("Sorting Algorithm Visualizer By EthicalAniruddha");
        inputFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        inputFrame.setSize(900, 700);
        inputFrame.setResizable(false);
        inputFrame.setLocationRelativeTo(null);
        inputFrame.setBackground(new Color(0,0,0));

        int arrayLength;
        while(true) {
            String len = JOptionPane.showInputDialog(inputFrame, "Enter array length (cannot be changed) [Plz enter integer value or else the program will crash :( ]  ", JOptionPane.QUESTION_MESSAGE);
            try {
                arrayLength = Integer.parseInt(len);
                break;
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        }

        // Declaring array and it's length
        arrToSort = new int[arrayLength];
        resetArray(arrToSort); // resetArray to get random elements
        showBars = new ShowBars(arrToSort);
        //System.out.println("Array's initial Elements:\n"+Arrays.toString(arrToSort)+"\n");

        // For buttonPanel
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(1660,55));
        buttonPanel.setBackground(new Color(0,0,0));
        buttonPanel.setBounds(0,5, 50,1660);

        // For reset button
        resetBtn = new JButton();
        resetBtn.setText("Reset");
        resetBtn.setVisible(true);
        resetBtn.setSize(new Dimension(120,70));
        resetBtn.setBackground(new Color(76, 227, 65, 230));
        resetBtn.setFocusable(false);

        resetBtn.addActionListener(e -> {
            if (!isSwapping) {
                JOptionPane.showMessageDialog(null, "Array is reset.  ", "Array Reset", JOptionPane.INFORMATION_MESSAGE);
                resetArray(arrToSort);
                //System.out.println("Rested array elements: ");
                //System.out.println(Arrays.toString(arrToSort) + "\n");
                isSorted = false;
                showBars.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Array was rest OR Swapping is in procedure, plz don't disturb.", "Array Reset", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // For bubble button
        bubbleBtn = new JButton();
        bubbleBtn.setText("Bubble Sort");
        bubbleBtn.setVisible(true);
        bubbleBtn.setSize(new Dimension(120,70));
        bubbleBtn.setBackground(new Color(50, 187, 229, 230));
        bubbleBtn.setFocusable(false);

        bubbleBtn.addActionListener(e -> {
            if (!isSwapping && !isSorted) {
                JOptionPane.showMessageDialog(null, "Time Complexity:  \"O(n^2)\", worst case.  ", "Bubble Sort Info", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Space Complexity:  \"O(1)\".  "  , "Bubble Sort Info", JOptionPane.INFORMATION_MESSAGE);
                new BubbleSort(arrToSort, showBars);
                //System.out.println("\nBubble Sort");
                //System.out.println();
            } else {
                JOptionPane.showMessageDialog(null, "Swapping is in procedure, plz don't disturb OR the array is sorted try resetting it.  ", "Bubble Sort Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // For Selection Button
        selectionButton = new JButton();
        selectionButton.setText("Selection Sort");
        selectionButton.setVisible(true);
        selectionButton.setSize(new Dimension(120,70));
        selectionButton.setBackground(new Color(91, 111, 229, 230));
        selectionButton.setFocusable(false);

        selectionButton.addActionListener(e -> {
            if (!isSwapping && !isSorted) {
                JOptionPane.showMessageDialog(null, "Time Complexity:  \"O(n^2)\", worst case.  ", "Selection Sort Info", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Space Complexity:  \"O(1)\".  ", "Selection Sort Info", JOptionPane.INFORMATION_MESSAGE);
                new SelectionSort(arrToSort, showBars);
                //System.out.println("\nSelection Sort");
                System.out.println();
            } else {
                JOptionPane.showMessageDialog(null, "Swapping is in procedure, plz don't disturb OR the array is sorted try resetting it.  ", "Selection Sort Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Adding buttons to buttonPanel
        buttonPanel.add(resetBtn, BorderLayout.CENTER);
        buttonPanel.add(bubbleBtn, BorderLayout.CENTER);
        buttonPanel.add(selectionButton, BorderLayout.CENTER);

        // Adding panels to frame
        add(buttonPanel, BorderLayout.CENTER);
        add(showBars, BorderLayout.CENTER);

        setVisible(true); // Setting the frame visible
    }

    // To get random elements inside the array
    private void resetArray(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(150, 1001);
        }
    }
}
