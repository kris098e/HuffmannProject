import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decode {
    private static final int PARENT = -1;
    public static void main(String[] args) {
        //Read the 256 occurences from the beginning of the file
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
            // pointer node used for going down the path to the leafs
            Huffmann.Node currentNode = (Huffmann.Node)rootElement.getData();
            int writtenLength = 0;
            // while we have not output written all the letters to the new file, continue
            while(writtenLength < length) {
                bit = bitInput.readBit();
                // go right
                if(bit==1){
                    currentNode = currentNode.right;
                // go left
                } else {
                    currentNode = currentNode.left;
                }
                // if in leaf node, write character and reset pointer to root
                if (currentNode.getByte() != PARENT ) {
                    outStream.write(currentNode.getByte());
                    currentNode = (Huffmann.Node)rootElement.getData();
                    writtenLength += 1;
                }
            }
            
            outStream.close();
            bitInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
