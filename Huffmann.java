public class Huffmann {

    public static final int PARENT = -1;

    //Create the huffman tree from the list of occurences
    public static Element huffmannTree(int[] v) {
        PQHeap heap = new PQHeap();
        for(int i = 0; i < v.length; i++) {
            heap.insert(new Element(v[i], new Node(i)));
        }
        for(int i = 0; i < 255; i++) {
            Element temp1 = heap.extractMin();
            Element temp2 = heap.extractMin();
            
            Node left = (Node)(temp1.getData());
            Node right = (Node)(temp2.getData());

            heap.insert(new Element(temp1.getKey() + temp2.getKey(), new Node(PARENT, left, right)));
        }
        return heap.extractMin();
    } 
    
    //Create the list of codes for each byte
    public static String[] huffMannCodes(Element e) {
        String temp = "";
        String[] v = new String[256];
        auxHuffMannCodes((Node)e.getData(), v, temp);
        return v;
    }

    private static void auxHuffMannCodes(Node node, String[] v, String holder) {
        if(node != null) {
            auxHuffMannCodes(node.left, v, holder + "0");
            if(node._byte != PARENT) {
                v[node._byte] = holder;
            }
            auxHuffMannCodes(node.right, v, holder + "1");
        }
    }


    public static class Node {
            
        private Integer _byte;
        public Node left;
        public Node right;

        private Node(int _byte) {
            this._byte = _byte;
        }

        public Node(int _byte, Node left, Node right) {
            this._byte = _byte;
            this.left = left;
            this.right = right;
        }

        public Integer getByte() {
            return _byte;
        }
    }
}
