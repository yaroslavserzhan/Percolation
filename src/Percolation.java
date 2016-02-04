public class Percolation 
{

    private boolean[][] A;
    private int top = 0;
    private int bot;
    private int size; 
    private WeightedQuickUnionUF qf;


    
 // ��������� ������� N-��-N, � ���� ������������� �ᒺ����� 
    public Percolation(int N) 
    {
        size = N;
        bot = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        A = new boolean[size][size];
    }


 // ������� �ᒺ�� (row i, column j) ���� �� �� �� �������� 
    public void open(int i, int j) 
    {
        A[i - 1][j - 1] = true;
        if (i == 1) 
        {
            qf.union(getQFIndex(i, j), top);
        }
        if (i == size) 
        {
            qf.union(getQFIndex(i, j), bot);
        }

        if ((j > 1) && (isOpen(i, j - 1))) 
        {
            qf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
        }
        if ((j < size) && (isOpen(i, j + 1))) 
        {
            qf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
        }
        if ((i > 1) && (isOpen(i - 1, j))) 
        {
            qf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
        }
        if ((i < size) && (isOpen(i + 1, j))) 
        {
            qf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
        }
    }

    
 // �� ������� �ᒺ�� (row i, column j)? 
    public boolean isOpen(int i, int j) 
    {
        return A[i - 1][j - 1];
    }



 // �� ������ �������? 
    public boolean percolates() 
    {
        return qf.connected(top, bot);
    }

    
    
    private int getQFIndex(int i, int j) 
    {
        return size * (i - 1) + j;
    }
}


