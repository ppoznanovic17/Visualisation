package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopulateButtonActionListener implements ActionListener
{

    /*
    PopulateButton action is to populate an array, send it to the sortPanels where the panel will be painted according to the
    array. It also enables/disables according to the requierment.
     */

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        MainFrame.getInstance().setIterationCounter(0);
        int[] arrayOne = MainFrame.getInstance().populateArray();
        int[] arrayTwo = new int[400];
        for(int i = 0; i < 400; i++)
            arrayTwo[i] = arrayOne[i];
        MainFrame.getInstance().getSortPanelOne().setArray(arrayOne);
        MainFrame.getInstance().getSortPanelTwo().setArray(arrayTwo);

        MainFrame.getInstance().getPopulateArrayButton().setEnabled(false);
        MainFrame.getInstance().getSortButton().setEnabled(true);
    }
}