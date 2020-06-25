package sort_panel;


import javax.swing.*;
import java.awt.*;

public class SortAnimationPanel extends JPanel implements Runnable
{

    private int[] array = null;

    private SortPanel sp;

    private Thread thread;
    private boolean threadRunning = false;

    /*
    Initialises the SortingAnimationPanel and sets its size
     */
    public SortAnimationPanel(SortPanel sp)
    {
        this.sp = sp;
        setPreferredSize(new Dimension(400, 400));
        setMinimumSize(new Dimension(400, 400));
        setMaximumSize(new Dimension(400, 400));
    }

    /*
    Method paint is used to draw the lines according to the starting array or the sorted ones.
     */
    @Override
    public void paint(Graphics g)
    {
        super.paintComponents(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, 400, 400);
        if(array != null)
        {

            g.setColor(Color.blue);
            for(int i = 0; i < 400; i ++)
            {
                g.drawLine(i, 400, i,  400-array[i]);
            }
        }
    }

    @Override
    public void run()
    {

        try
        {
            sp.sort();
        } catch (InterruptedException e)
        {

        }
    }

    public void start(String name)
    {
        thread = new Thread(this, name);
        threadRunning = true;
        thread.start();
    }

    public Thread getThread()
    {
        return thread;
    }

    public void setThreadRunning(boolean threadRunning)
    {
        this.threadRunning = threadRunning;
    }

    public boolean isThreadRunning()
    {
        return threadRunning;
    }

    public void setThread(Thread thread)
    {
        this.thread = thread;
    }

    /*
            Sets the array to be used and repaints the panel accordingly
             */
    public void setArray(int[] array) {
        this.array = array;
        this.repaint();
    }
}
