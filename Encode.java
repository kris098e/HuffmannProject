import java.io.FileInputStream;
import java.io.IOException;

public class Encode {
        private static final int PARENT = -1; 
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

        public static Element huffMann(int[] v) {
            PQHeap heap = new PQHeap();
            for(int i = 0; i < v.length; i++) {
                heap.insert(new Element(v[i], i));
            }
            for(int i = 0; i < 256; i++) {
                Element temp1 = heap.extractMin();
                Element temp2 = heap.extractMin();
                
                Node left = new Node(temp1.getData()));
                Node right = new Node(temp2.getData()));

                heap.insert(new Element(temp1.getKey() + temp2.getKey(), new Node(PARENT, left, right)));
            }
            return heap.extractMin();
        } 

        public static void main(String[] args) {
            int[] temp = readBytes();
            // for(int x : temp) {
            //     System.out.print(x + ", ");
            // }
            huffMann(temp);
        }

        private static class Node {
            
            private Integer _byte;
            private Node left;
            private Node right;

            private Node(int _byte) {
                this._byte = _byte;
            }

            private Node(int _byte, Node left, Node right) {
                this._byte = _byte;
                this.left = left;
                this.right = right;
            }

            public Integer getByte() {
                return _byte;
            }
        }
}