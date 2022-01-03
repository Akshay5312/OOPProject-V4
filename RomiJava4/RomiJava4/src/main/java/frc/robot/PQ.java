package frc.robot;

import java.util.ArrayList;

/**
 * A priority queue that allows you to pull the element with the least cost
 */
public class PQ<T extends Costable> extends ArrayList<T>{
    
    /**
     * gets the element with the least cost, then removes it from the list
     * @return element with the least cost
     */
    public T pullLeast(){
        int minIndex = 0;
        for(int i = 1; i < size(); i++){
            if(get(minIndex).cost() > get(i).cost()){
                minIndex = i;
            }
        }
        T retT = get(minIndex);
        remove(minIndex);
        return retT;
    }

    // /**
    //  * Puts the element into the list at the desired location
    //  * @param t the element to put
    //  */
    // public void put(T t){
    //     put(0,t);
    // }

    // /**
    //  * recursively checks if ... 
    //  * @param i
    //  * @param t
    //  */
    // private void put(int i, T t){
    //     if(i >= size()){
    //         add(t);
    //         return;
    //     }
    //     if( t.cost() > get(i).cost() ){
    //         put(i+1, t);
    //     }else{
    //         add(i,t);
    //     }
    // }
}
