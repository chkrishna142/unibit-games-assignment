import java.util.*;

 class Main {
    public static int[][] findPairs(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<int[]> pairs = new ArrayList<>();

        for (int num : nums) {
            int restTarget = target - num;

            if (map.containsKey(restTarget) && map.get(restTarget) > 0) {
                int[] pair = { num, restTarget };
                pairs.add(pair);  
                
                map.put(restTarget, map.get(restTarget) - 1);
               
            }
           
            map.put(num, map.getOrDefault(num, 0) + 1);
           
            
        }

        int[][] result = new int[pairs.size()][2];
        for (int i = 0; i < pairs.size(); i++) {
            result[i] = pairs.get(i);
        }

        return result;
    }

    public static int[] mergeAndSort(int[][] pairs) {
        List<Integer> mergedList = new ArrayList<>();

        for (int[] pair : pairs) {
            mergedList.add(pair[0]);
            mergedList.add(pair[1]);
        }

        
        Collections.sort(mergedList);

        
        int[] mergedArray = new int[mergedList.size()];
        for (int i = 0; i < mergedList.size(); i++) {
            mergedArray[i] = mergedList.get(i);
        }

        return mergedArray;
    }
    
    public static int[][] findSecondCombination(int[] nums, int target) {
        int doubledTarget = target * 2;
        return findPairs(nums, doubledTarget);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, -4, -6, 2, 8};
        int target = 4;
       
        
        int[][] result = findPairs(nums, target);
        System.out.println("First Combination For \"" + target + "\" : " + Arrays.deepToString(result));

    
        
           int[] mergedArray = mergeAndSort(result);
        System.out.println("Merge Into a single Array : " + Arrays.toString(mergedArray));
        
         int[][] secondResult = findSecondCombination(nums, target);
        System.out.println("Second Combination For \"" + (target * 2) + "\" : " + Arrays.deepToString(secondResult));
    }
}