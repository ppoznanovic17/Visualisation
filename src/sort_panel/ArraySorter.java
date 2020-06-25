package sort_panel;

import main.MainFrame;

public class ArraySorter
{
    /*
    Collection of methods for array sorting
     */

    private SortPanel sp;

    public ArraySorter(SortPanel sp)
    {
        this.sp = sp;
    }

    private int sleepTime = 0;
    private void update(int[] array) throws InterruptedException
    {
        sp.setArray(array);
        sp.getSortAnimation().getThread().sleep(sleepTime);

    }

    public void setSleepTime(int sleepTime)
    {
        this.sleepTime = sleepTime;
    }

    private void returnToStartState()
    {
        sp.getSortAnimation().setThreadRunning(false);
        MainFrame.getInstance().returnToStartState();
        MainFrame.getInstance().getSortPanelOne().setRunning(false);
        MainFrame.getInstance().getSortPanelTwo().setRunning(false);
    }

    private int i = 0;
    private int j = 0;


    public int[] bubbleSort(int[] array, boolean ascending) throws InterruptedException
    {
        int n = array.length;
        int temp = 0;
        for(; i <= n; i++)
        {
            for(; j <= (n - 1); j++)
            {
                if(j == 0)
                    j++;
                if(ascending)
                {
                    if (array[j - 1] > array [j])
                    {
                        temp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = temp;

                    }
                }
                else
                {
                    if (array[j - 1] < array [j])
                    {
                        temp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = temp;

                    }
                }
                update(array);
            }
            j = 0;
        }

        i = 0;
        j = 0;

        returnToStartState();
        return array;
    }




    public int[] mergeSort(int[] array, boolean ascending) throws InterruptedException
    {
        sortM(array, 0, array.length-1, ascending);
        returnToStartState();
        return array;
    }

    private void merge(int arr[], int l, int m, int r, boolean ascending) throws InterruptedException
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int [n1];
        int R[] = new int [n2];

        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2)
        {
            if(ascending)
            {
                if (L[i] <= R[j])
                {
                    arr[k] = L[i];
                    i++;
                }
                else
                {
                    arr[k] = R[j];
                    j++;
                }
            }
            else
            {
                if (L[i] >= R[j])
                {
                    arr[k] = L[i];
                    i++;
                }
                else
                {
                    arr[k] = R[j];
                    j++;
                }
            }


            k++;
            update(arr);
        }

        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
            update(arr);
        }

        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
            update(arr);
        }
    }

    private void sortM(int arr[], int l, int r, boolean ascending) throws InterruptedException
    {
        if (l < r)
        {
            int m = (l+r)/2;
            sortM(arr, l, m, ascending);
            sortM(arr , m+1, r, ascending);
            merge(arr, l, m, r, ascending);
            update(arr);
        }
    }

    public int[] quickSort(int[] array, boolean ascending) throws InterruptedException
    {
        sortQ(array, 0, array.length-1, ascending);
        update(array);
        returnToStartState();
        return array;
    }

    private int partition(int arr[], int low, int high, boolean ascending) throws InterruptedException
    {
        int pivot = arr[high];
        int i = (low-1);
        for (int j = low; j <= high - 1; j++)
        {
            if(ascending)
            {
                if (arr[j] <= pivot)
                {
                    i++;

                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    update(arr);
                }
            }
            else
            {
                if (arr[j] >= pivot)
                {
                    i++;

                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    update(arr);
                }
            }

        }

        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        update(arr);

        return i+1;
    }

    private void sortQ(int arr[], int low, int high, boolean ascending) throws InterruptedException
    {
        if (low < high)
        {
            int pi = partition(arr, low, high, ascending);


            sortQ(arr, low, pi-1, ascending);
            update(arr);
            sortQ(arr, pi+1, high, ascending);
            update(arr);
        }
        update(arr);
    }

}