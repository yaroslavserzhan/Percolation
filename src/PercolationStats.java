import prinstonlib.StdOut;
import prinstonlib.StdRandom;
import prinstonlib.StdStats;

public class PercolationStats {

    private int exp_сount; 
    private Percolation pr;
    private double[] P; //масив в якому зберігаються значення кількості відкритих клітин поділене на кількість усіх


 // проведемо T окремих експериментів в N на N 
    public PercolationStats(int N, int T) 
    { 
        if ((N <= 0) || (T <= 0)) 
        {
            throw new IllegalArgumentException("Given N <= 0 || T <= 0");
        }
        exp_сount = T;
        P = new double[exp_сount];
        for (int expNum = 0; expNum < exp_сount; expNum++) 
        {
            pr = new Percolation(N);
            int openedCells = 0;
            while (!pr.percolates()) 
            {
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (!pr.isOpen(i, j)) 
                {
                    pr.open(i, j);
                    openedCells++; 
                }
            }
            double p = (double) openedCells / (N * N);
            P[expNum] = p;
        } 
    }



 // рахує середнє
    public double mean() 
    {
        return StdStats.mean(P);
    }



 // рахує відхилення
    public double stddev() 
    {
        return StdStats.stddev(P);
    }

    //інтервал з 95% довіри для порога протікання min
    public double confidenceLo() 
    {
        return mean() - ((1.96 * stddev()) / Math.sqrt(exp_сount));
    }



    //інтервал з 95% довіри для порога протікання max
    public double confidenceHi() 
    {
        return mean() + ((1.96 * stddev()) / Math.sqrt(exp_сount));
    }

    
    
    public static void main(String[] args) 
    {
        int N = 100;
        int T = 100;
        PercolationStats ps = new PercolationStats(N, T);

        
        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}


