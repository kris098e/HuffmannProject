import java.io.FileInputStream;
import java.io.IOException;

public class Encode {
        public static int[] readBytes() {
            int[] holder = new int[256];
            try{
                FileInputStream in = new FileInputStream("test.txt");
                int read;
                while( (read=in.read()) != -1) {
                    holder[read]++;
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
            
            return holder;
        }
        public static void main(String[] args) {
            int[] temp = readBytes();
            for(int x : temp) {
                System.out.print(x + ", ");
            }
        }
}