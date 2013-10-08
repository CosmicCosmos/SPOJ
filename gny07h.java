import java.io.*;
import java.util.*;

public class gny07h
{
    private static Reader in;
    private static PrintWriter out;
    
    public static void main (String [] args) throws IOException {
        in = new Reader ();
        out = new PrintWriter (System.out, true);
        int [] dp = new int [22];
        int sumA, even, odd, prev;
        dp [0] = 1;
        dp [1] = 1;
        sumA = 1; even = 1; odd = 1; prev = 1;
        for (int i = 2; i <= 21; i++) {
            dp [i] = dp [i - 2] + dp [i - 1] + 2 * sumA;
            if (i % 2 == 0) { 
                dp [i] += even;
                even += dp [i];
            } else {
                dp [i] += odd;
                odd += dp [i];
            }
            sumA += prev;
            prev = dp [i];
            if (dp [i] < dp [i - 1]) break;
        }
        int t = in.nextInt (), j = 1;
        while (t-- > 0) {
            out.println (j++ + " " + dp [in.nextInt ()]);
        }
    }
}

/** Faster input **/
class Reader {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;
    public Reader(){
        din=new DataInputStream(System.in);
        buffer=new byte[BUFFER_SIZE];
        bufferPointer=bytesRead=0;
    }

    public Reader(String file_name) throws IOException{
        din=new DataInputStream(new FileInputStream(file_name));
        buffer=new byte[BUFFER_SIZE];
        bufferPointer=bytesRead=0;
    }

    public String readLine() throws IOException{
        byte[] buf=new byte[64]; // line length
        int cnt=0,c;
        while((c=read())!=-1){
            if(c=='\n')break;
            buf[cnt++]=(byte)c;
        }
        return new String(buf,0,cnt);
    }

    public int nextInt() throws IOException{
        int ret=0;byte c=read();
        while(c<=' ')c=read();
        boolean neg=(c=='-');
        if(neg)c=read();
        do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
        if(neg)return -ret;
        return ret;
    } 

    public long nextLong() throws IOException{
        long ret=0;byte c=read();
        while(c<=' ')c=read();
        boolean neg=(c=='-');
        if(neg)c=read();
        do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
        if(neg)return -ret;
        return ret;
    }

    public double nextDouble() throws IOException{
        double ret=0,div=1;byte c=read();
        while(c<=' ')c=read();
        boolean neg=(c=='-');
        if(neg)c = read();
        do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
        if(c=='.')while((c=read())>='0'&&c<='9')
            ret+=(c-'0')/(div*=10);
        if(neg)return -ret;
        return ret;
    }
    
    private void fillBuffer() throws IOException{
        bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);
        if(bytesRead==-1)buffer[0]=-1;
    }
    
    private byte read() throws IOException{
        if(bufferPointer==bytesRead)fillBuffer();
        return buffer[bufferPointer++];
    }
    
    public void close() throws IOException{
        if(din==null) return;
        din.close();
    }
}