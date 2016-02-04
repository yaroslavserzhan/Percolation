import prinstonlib.StdOut;
import prinstonlib.StdRandom;
import prinstonlib.StdStats;

public class PercolationStats {

    private int exp_�ount; 
    private Percolation pr;
    private double[] P; //����� � ����� ����������� �������� ������� �������� ����� ������� �� ������� ���


 // ��������� T ������� ������������ � N �� N 
    public PercolationStats(int N, int T) 
    { 
        if ((N <= 0) || (T <= 0)) 
        {
            throw new IllegalArgumentException("Given N <= 0 || T <= 0");
        }
        exp_�ount = T;
        P = new double[exp_�ount];
        for (int expNum = 0; expNum < exp_�ount; expNum++) 
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



 // ���� ������
    public double mean() 
    {
        return StdStats.mean(P);
    }



 // ���� ���������
    public double stddev() 
    {
        return StdStats.stddev(P);
    }

    //�������� � 95% ����� ��� ������ ��������� min
    public double confidenceLo() 
    {
        return mean() - ((1.96 * stddev()) / Math.sqrt(exp_�ount));
    }



    //�������� � 95% ����� ��� ������ ��������� max
    public double confidenceHi() 
    {
        return mean() + ((1.96 * stddev()) / Math.sqrt(exp_�ount));
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


