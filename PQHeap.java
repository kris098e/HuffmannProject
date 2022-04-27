/** 
 * Gruppe:
 * Philip Jos Rosenlund Andersen, Phand18@student.sdu.dk
 * Kristian Degn Abrahamsen, Krabr21@student.sdu.dk
 * Andreas Viby Jensen, Andje21@student.sdu.dk
 */

import java.util.ArrayList;

public class PQHeap implements PQ {

    private ArrayList< Element > list;

    public PQHeap() {
        list = new ArrayList<>();
    }

    public Element extractMin() {
        Element key = list.get( 0 );
        list.set( 0, list.get( list.size() - 1) );
        list.remove(list.size() - 1);
        minHeapify( 0 );
        return key;
    }

    private void minHeapify( int index ) {
            int l = left( index );
            int r = right( index );
            int smallest;
            if( l <= ( list.size() - 1 ) && list.get( l ).getKey() < list.get( index ).getKey() )
                smallest = l;
            else smallest = index;
            if( r <= ( list.size() - 1) && list.get( r ).getKey() < list.get( smallest ).getKey() )
                smallest = r;
            if( smallest != index ) {
                exchange( index, smallest );
                minHeapify( smallest );
            }
    }

    private static int left( int index ) {
        return (2 * index + 1);
    }
    private static int right( int index ) {
        return (2 * index + 2);
    }

    public void insert( Element key ) {
        list.add( key );
        int index = list.size() - 1;
        while( index > 0 && list.get( parent( index ) ).getKey() > list.get( index ).getKey() ) {
            int parent = parent( index );
            exchange( index, parent );
            index = parent;
        }
    }

    private static int parent( int index ) {
        return ((index - 1) / 2);
    }

    private void exchange( int index1, int index2 ) {
        Element temp = list.get( index1 );
        list.set( index1, list.get( index2 ) );
        list.set( index2, temp );
    }
    public void printHeap() {
        for(int i = 0; i < list.size(); i++) {
            System.out.print(list.get( i ).getKey() + ", ");
        }
    }
}
