package frc.robot;

import java.util.ArrayList;
import java.util.List;


/**
 * A field of arbitrary dimensions
 */
public class Field<N> {

    List<N> field = new ArrayList<N>();

    int[] dims;

    int dofs;

    /**
     * creates the field
     * @param dimensions an integer array that contains the size of values in each dimension 
     */
    public Field(int[] dimensions){
        dims = dimensions;
        dofs = dims.length;
        reset();
    }

    /**
     * replaces each element with null values
     */
    public void reset(){

        field = new ArrayList<N>();
        int totalVals = dims[0];
        for(int s = 1; s < dofs; s++){
            totalVals *= dims[s];
        }
        for(int i = 0; i < totalVals; i++){
            field.add(null);
        }

    }

    /**
     * gets a value at local index 
     * @param index the index at which to get a value
     * @return the value at index 
     */
    private N get(int index){
        if((index < 0) || (index >= field.size())){
            return null;
        }
        return field.get(index);
    }

    /**
     * maps a multidimensional index to a one dimensional one
     * @param index the multidimensional index to map
     * @return the vlue of that index in the local arraylist
     */
    private int Fmap(int[] index){
        int retInd = 0;
        for(int i = 0; i < dofs; i++){
            int indVal = index[i];
            for(int j = 0; j < i; j++){
                indVal *= dims[j];
            }
            retInd+=indVal;
        }
        return retInd;
    }

    /**
     * returns the value at a multidimensional index
     * @param index the index 
     * @return the value
     */
    public N get(int[] index){
        return get(Fmap(index));
    }

    /**
     * puts a value in a multidimensional aray 
     * @param index the index
     * @param putVal the value to put
     */
    public void put(int[] index, N putVal){
        field.set(Fmap(index), putVal);
    }

    /**
     * gets a value from a 2D array
     * @param s1 the index on dimension 1
     * @param s2 the index on dimension 2
     * @return the value
     */
    public N get(int s1, int s2){
        int[] v = {s1,s2};
        return get(v);
    }

    /**
     * gets a list of all elements in the multidimensional array
     * @return a list of all elements
     */
    public List<N> everyElement(){
        return field;
    }


}
