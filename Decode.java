import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decode {
    
    public static void main(String[] args) {
        //Read the 256 occurences from the beginning og the file
        try {
            FileInputStream input = new FileInputStream(args[0]);
            BitInputStream bitInput = new BitInputStream(input);
            int[] occurances = new int[256];
            int length = 0;
            for (int i = 0; i < 256; i++) {
                occurances[i] = bitInput.readInt();
                length += occurances[i];
            }
            
            //Create a Huffmann tree to use for decoding
            Element rootElement = Huffmann.huffmannTree(occurances);

            FileOutputStream outStream = new FileOutputStream(args[1]);
            int bit;
            Huffmann.Node currentNode = (Huffmann.Node)rootElement.getData();
            int writtenLength = 0;
            while(writtenLength < length) {
                bit = bitInput.readBit();
                if(bit==1){
                    currentNode = currentNode.right;
                } else {
                    currentNode = currentNode.left;
                }
                if (currentNode.getByte() != -1) {
                    outStream.write(currentNode.getByte());
                    currentNode = (Huffmann.Node)rootElement.getData();
                    writtenLength += 1;
                }
            }
            
            outStream.close();
            bitInput.close();
        } catch (IOException e) {
            System.out.println("FUckd up");
        }
    }

}
