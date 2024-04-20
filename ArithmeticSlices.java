//Approach 1:
// TC: O(n^2)
// SC: O(1) 
//Approach: For each number find all the sub arrays 

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int count = 0 ;
        for(int i = 0; i <= n-3; i++){
            int diff = nums[i+1] - nums[i];
            for(int j  = i+1; j <= n-2 ; j++){
                if(nums[j+1] - nums[j] == diff){
                    count++;
                }
                else{
                    break;
                }
            }
        }
        return count;
    }
}

//Approach:
// TC: O(n)
// SC: O(1) 

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int res = 0;
        int count = 0 ;
        for(int i = 1; i < n-1; i++){
            if(nums[i+1] - nums[i] == nums[i] - nums[i-1]){
                res += ++count;
            }
            else{
                count = 0;
            }

        }

        return res;
    }
}