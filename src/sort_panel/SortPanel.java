package sort_panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SortPanel extends JPanel
{
    private JComboBox<String> sortStyleComboBox;
    private String[] sortStyle = {"Ascending", "Descending"};

    private JComboBox<String> sortComboBox;
    private String[] sortOptions = {"Quick sort", "Bubble sort", "Merge sort"};

    private JComboBox<String> sortingSpeedComboBox;
    private String[] sortingSpeed = {"Fast", "Medium", "Slow"};

    private SortAnimationPanel sortAnimation;

    private JPanel panelOne;
    private JPanel panelTwo;

    private int[] array;

    private static boolean running = false;

    private ArraySorter as;
    /*
    Initialises the SortPanel, adds required buttons, comboBoxes and panels to match the requierment
     */
    public SortPanel()
    {
        as = new ArraySorter(this);
        sortComboBox = new JComboBox<>(sortOptions);
        sortStyleComboBox = new JComboBox<>(sortStyle);
        sortingSpeedComboBox = new JComboBox<>(sortingSpeed);

        panelOne = new JPanel();
        panelTwo = new JPanel();

        sortAnimation = new SortAnimationPanel(this);

        sortStyleComboBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                if(running)
                {
                    sortAnimation.getThread().interrupt();
                    sortAnimation.start("Thread I");
                }

            }
        });

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        panelOne.add(sortAnimation);

        panelTwo.add(sortComboBox);
        panelTwo.add(sortStyleComboBox);
        panelTwo.add(sortingSpeedComboBox);
        add(panelOne);
        add(panelTwo);

    }

    /*
    Begins sorting and starts the animation
     */
    public void sort() throws InterruptedException
    {

        switch (sortingSpeedComboBox.getSelectedIndex())
        {
            case 0:
                as.setSleepTime(10);
                break;
            case 1:
                as.setSleepTime(50);
                break;
            case 2:
                as.setSleepTime(100);
                break;
        }
        switch (sortComboBox.getSelectedIndex())
        {
            case 0:
                if(sortStyleComboBox.getSelectedIndex() == 0)
                    as.quickSort(array, true);
                else
                    as.quickSort(array, false);
                break;
            case 1:
                if(sortStyleComboBox.getSelectedIndex() == 0)
                    as.bubbleSort(array, true);
                else
                    as.bubbleSort(array, false);
                break;
            case 2:
                if(sortStyleComboBox.getSelectedIndex() == 0)
                    as.mergeSort(array, true);
                else
                    as.mergeSort(array, false);
                break;
        }
    }

    /*
    Sets the array to be used and initialises repainting of the SortAnimationPanel so it matches the array
     */
    public void setArray(int[] array)
    {
        this.array = array;
        sortAnimation.setArray(array);
    }

    public JComboBox<String> getSortComboBox()
    {
        return sortComboBox;
    }

    public JComboBox<String> getSortingSpeedComboBox()
    {
        return sortingSpeedComboBox;
    }

    public JComboBox<String> getSortStyleComboBox()
    {
        return sortStyleComboBox;
    }

    public SortAnimationPanel getSortAnimation()
    {
        return sortAnimation;
    }

    public static void setRunning(boolean running)
    {
        SortPanel.running = running;
    }

    public int[] getArray()
    {
        return array;
    }

    public ArraySorter getAs()
    {
        return as;
    }
}