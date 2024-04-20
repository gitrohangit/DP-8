//Approach 1: Bottom up dp
//TC: O(n^2)
//SC: O(1)

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        boolean[][] path = new boolean[n][n]; //false -> fall to the same index , else next index

        for(int i = n-2; i >= 0 ; i--){ //row
            for(int j = 0 ; j <= i ; j++){ // i+1 columns in the row

                // triangle.get(i).set(j, triangle.get(i).get(j) + 
                //                     Math.min(triangle.get(i+1).get(j),triangle.get(i+1).get(j+1)));


                if(triangle.get(i+1).get(j) < triangle.get(i+1).get(j+1)){
                    triangle.get(i).set(j, triangle.get(i).get(j) + 
                                    triangle.get(i+1).get(j));
                }
                else{
                    triangle.get(i).set(j, triangle.get(i).get(j) + 
                                    triangle.get(i+1).get(j+1));
                    path[i][j] = true;
                }
                
            }
        }
        System.out.println(Arrays.deepToString(path));
        return triangle.get(0).get(0);
    }
}

//Approach 2: Bottom up dp from top 

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        for(int i = 1; i <  n ; i++){ //row
            for(int j = 0 ; j <= i ; j++){ // i+1 columns in the row
                if(j == 0){
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j));
                }
                else if ( j == i){
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j-1));
                }
                else{
                    triangle.get(i).set(j, triangle.get(i).get(j) + 
                                     Math.min(triangle.get(i-1).get(j),triangle.get(i-1).get(j-1)));
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i : triangle.get(n-1)){
            min = Math.min(min, i);
        }


        return min;
    }
}

//Approach 3: Top down dp
//TC: O(n^2 + n^2)
//SC: O(n^2) , dp array and recursive stack space.

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        return helper(triangle,0,0,dp);
    }

    private int helper(List<List<Integer>> triangle,int i , int j ,int[][] dp){
        //base
        if(j == i+1) return 0;
        if(i == triangle.size()) return 0;

        //logic
        if(dp[i][j] != Integer.MAX_VALUE){
            return dp[i][j];
        }

        int case1 = helper(triangle, i+1, j , dp);
        int case2 = helper(triangle, i+1, j+1, dp);

        dp[i][j] = triangle.get(i).get(j) + Math.min(case1,case2);
        return dp[i][j];
    }
}