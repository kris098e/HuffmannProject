import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encode {

        public static int[] readBytes(String inputFile) {
            int[] holder = new int[256];
            try{
                FileInputStream in = new FileInputStream(inputFile);
                int read;
                while( (read=in.read()) != -1) {
                    holder[read]++;
                }
                in.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
            return holder;
        }

        private static void WriteHuffmann( int[] occurrences, String inputFile, String outputFile, String[] tree ){
            try {
                FileOutputStream out = new FileOutputStream(outputFile);
                BitOutputStream bitOut = new BitOutputStream(out);
                
                FileInputStream in = new FileInputStream(inputFile);

                for (int i = 0; i < occurrences.length; i++) {
                    int outputByte = occurrences[i];
                    bitOut.writeInt(outputByte);
                }
                int inputByte;
                while( (inputByte=in.read()) != -1 ) {
                    String byteString = tree[inputByte];
                    for (int i = 0; i < byteString.length(); i++) {
                        Character bitString = byteString.charAt(i);
                        int bit = Integer.parseInt(bitString.toString());
                        bitOut.writeBit(bit);
                    }
                }
                bitOut.close();
                in.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }


        public static void main(String[] args) {
            int[] temp = readBytes(args[0]);
            String[] str = Huffmann.huffMannCodes(Huffmann.huffmannTree(temp));
            WriteHuffmann(temp, args[0], args[1], str);
        }
}