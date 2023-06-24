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
    
    public static List<List<Integer>> findFourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // Sorting the array to handle duplicates more easily
        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // Avoid duplicates in the outer loop
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                // Avoid duplicates in the second loop
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        List<Integer> combination = new ArrayList<>();
                        combination.add(nums[i]);
                        combination.add(nums[j]);
                        combination.add(nums[left]);
                        combination.add(nums[right]);
                        result.add(combination);

                        // Avoid duplicates in the inner loop
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
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
        
    List<List<Integer>> doubleArray = findFourSum(nums, target*2);

        System.out.println("Output: " + doubleArray);
    }
}