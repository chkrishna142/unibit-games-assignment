

import java.util.*;

 class Main {
    public static int[][] findPairs(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(); //HashMap to store array numbers and its occurance
        Set<List<Integer>> pairSet = new HashSet<>();    //HashSet to add only unique pairs to our result
        List<int[]> pairs = new ArrayList<>();           //ArrayList to add required unique pairs of combinations

        for (int num : nums) {
            int restTarget = target - num;               // to check whether the required number  is present in HashMap or not

            if (map.containsKey(restTarget) && map.get(restTarget) > 0) { //condition for required number is present or not in HashMap
                List<Integer> pair = Arrays.asList(num, restTarget);      //created a dummy pair to insert [combination]
                if (!pairSet.contains(pair)) {                            //checking for unique pair
                    int[] pairArr = { num, restTarget };                  
                    pairs.add(pairArr);                                   //if pair not present in set add it to pairs ArrayList
                    pairSet.add(pair);                                    //Also add it to HashSet to avoid duplicates
                }
                map.put(restTarget, map.get(restTarget) - 1);             //decrease the occurace from HashMap
            }

            map.put(num, map.getOrDefault(num, 0) + 1);                   //if required number not present in HashMap add init 
        }

        int[][] result = new int[pairs.size()][2];                        //Store answers into an Array [Result]
        for (int i = 0; i < pairs.size(); i++) {
            result[i] = pairs.get(i);
        }

        return result;
    }

    public static int[] mergeAndSort(int[][] pairs) {
        List<Integer> mergedList = new ArrayList<>();     //ArrayList to add all elements of pairs Array

        for (int[] pair : pairs) {                        //Iterate inside pairs
            mergedList.add(pair[0]);                      //In single iteration add both the elements of pair inside ArrayList [mergeList]
            mergedList.add(pair[1]);                      
        }

        
        Collections.sort(mergedList);                     //Sort the ArrayList

        
        int[] mergedArray = new int[mergedList.size()];  //chnage ArrayList into Array
        for (int i = 0; i < mergedList.size(); i++) {
            mergedArray[i] = mergedList.get(i);
        }

        return mergedArray;
    }
    
    public static List<List<Integer>> findFourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

       
        Arrays.sort(nums);                                 // Sort the array to handle duplicates more easily

        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            
            if (i > 0 && nums[i] == nums[i - 1]) {         // Avoid duplicates in the outer loop
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                
                if (j > i + 1 && nums[j] == nums[j - 1]) { // Avoid duplicates in the second loop
                    continue;
                }

                int left = j + 1;                       
                int right = n - 1;

                while (left < right) {                     //two pointers to get different combinations
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];//sum=sum of 4 elements 

                    if (sum == target) {                   //if sum is equal to target value then add all sum values to ArrayList
                        List<Integer> combination = new ArrayList<>();
                        combination.add(nums[i]);
                        combination.add(nums[j]);
                        combination.add(nums[left]);
                        combination.add(nums[right]);
                        result.add(combination);           // add the ArrayList inside result ArrayList

                        
                        while (left < right && nums[left] == nums[left + 1]) {  // Avoid duplicates in the inner loop
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;                            //move two pointers by increasing left by 1 and decreasing right by -1
                        right--;
                    } else if (sum < target) {
                        left++;                            //if sum is less than target then we need to increase the sum value by increasing left by 1
                    } else {
                        right--;                           ////if sum is greater than target then we need to decrease the sum value by decreaseing right by 1
                    }
                }
            }
        }

        return result;
    }
    
   

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, -4, -6, 2, 8};
        int target = 4;
       
        //findPairs will give us required combinations in 2D array
        int[][] result = findPairs(nums, target);
        System.out.println("First Combination For " + target + " : " + Arrays.deepToString(result));

    
        //mergeAndSort will give us an Array by sorting the result array
        int[] mergedArray = mergeAndSort(result);
        System.out.println("Merge Into a single Array : " + Arrays.toString(mergedArray));
        
        //findFourSum return a ArrayList of ArrayList of combinations which sums equal to target*2
        List<List<Integer>> doubleArray = findFourSum(nums, target*2);
        System.out.println("Output: " + doubleArray);
    }
}