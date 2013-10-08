import java.io.*;
import java.util.*;
import static java.util.Arrays.binarySearch;

public class bytesm1
{
    private static Reader in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new Reader();
        out = new PrintWriter(System.out, true);
        int t = in.nextInt (), n, i, j, k;
        int MAX = 500000;
        int [] ones = new int [MAX],
               threes = new int [MAX], 
               nines = new int [MAX];
        ones[0] = 1; threes[0] = 3; nines[0] = 9;
        for (int p = 1; p < MAX; p++) {
            ones [p] = ones [p-1] + sumDig (ones [p-1]);
            threes [p] = threes [p-1] + sumDig (threes [p-1]);
            nines [p] = nines [p-1] + sumDig (nines[p-1]);
        }
        while (t-->0) {
            n = in.nextInt ();
            while (true) {
                
                if (binarySearch (ones, n) >= 0) {
                    out.printf ("%d %d\n", 1, n);
                    break;
                }
                if (binarySearch (threes, n) >= 0) {
                    out.printf ("%d %d\n", 3, n);
                    break;
                }
                if (binarySearch (nines, n) >= 0) {
                    out.printf ("%d %d\n", 9, n);
                    break;
                }
                n += sumDig (n);
            }
        }
    }
    
    public static int sumDig (int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}

/** Faster input **/
class Reader
{
    final private int BUFFER_SIZE = 1 << 16;
    
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;
    
    public Reader()
    {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }
    
    public Reader(String file_name) throws IOException
    {
        din = new DataInputStream(new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }
    
    public String readLine() throws IOException
    {
        byte[] buf = new byte[64]; // line length
        int cnt = 0, c;
        while( (c=read()) != -1) {
            if(c == '\n') break;
            buf[cnt++] = (byte)c;
        }
        return new String(buf,0,cnt);
    }
    
    public int nextInt() throws IOException
    {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = c == '-';
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }
    
    public long nextLong() throws IOException
    {
        long ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = c == '-';
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }
    
    public double nextDouble() throws IOException
    {
        double ret = 0, div = 1;
        byte c = read();
        while(c <= ' ') c = read();
        boolean neg = c == '-';
        if(neg) c = read();
        do {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c >= '0' && c <= '9');
        if(c == '.')
            while((c=read()) >= '0' && c <= '9') {
                div *= 10;
                ret = ret + (c - '0') / div;
            }
        if (neg) return -ret;
        return ret;
    }
    
    private void fillBuffer() throws IOException
    {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) buffer[0] = -1;
    }
    
    private byte read() throws IOException
    {
        if (bufferPointer == bytesRead) fillBuffer();
        return buffer[bufferPointer++];
    }
    
    public void close() throws IOException
    {
        if(din == null) return;
        din.close();
    }
}