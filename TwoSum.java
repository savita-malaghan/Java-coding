import java.util.HashMap;
public class TwoSum {
    
    public static void main (String args[]){
        int[] num = {2,7,11,15};
        int target  = 17;
        int[] result  = findIndices(num, target);
        System.out.println("Result "+ result[0] + " and " + result[1]);
    }
    
    public static int[] findIndices(int[] num, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i= 0; i< num.length; i++){
            int complement = target - num[i];
            if(map.containsKey(complement)){
                return new int[] { map.get(complement), i};
            }
            map.put(num[i], i);
        }
        throw  new IllegalArgumentException("No number having this target");
    }
}