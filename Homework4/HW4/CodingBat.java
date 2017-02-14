
/**
 * Homework 4
 * 
 * @author Andy Liang 
 * @version February 14, 2017
 */
public class CodingBat
{
    //=================================================================================================
    //Array 2 problems

    public int countEvens(int[] nums) {
        int counter = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] % 2 == 0){
                counter++;
            }
        }
        return counter;
    }

    public int bigDiff(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        for(int i = 1; i < nums.length; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        return max - min;
    }

    public int sum13(int[] nums) {
        if(nums.length == 0)
            return 0;
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 13){
                i += 1;
            }
            else{
                sum += nums[i];
            }
        }
        return sum;
    }

    public int sum67(int[] nums) {
        boolean ignore = false;
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            if(nums[i] == 6 && ignore == false){
                ignore = true;
            }
            else if(nums[i] == 7 && ignore == true){
                ignore = false;
            }
            else if(ignore == false){
                sum += nums[i];
            }
        }
        return sum;
    }

    public boolean has22(int[] nums) {
        boolean has22 = false;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 2 && i != nums.length - 1 && nums[i+1] == 2){
                has22 = true;
            }
        }
        return has22;
    }

    public boolean lucky13(int[] nums) {
        boolean no13 = true;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1 || nums[i] == 3){
                no13 = false;
            }
        }
        return no13;
    }

    public boolean sum28(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 2){
                sum += 2;
            }
        }
        return sum == 8;
    }

    
    //=================================================================================================
    // Array 3 Problems
    
    public int maxSpan(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int span = 1;
        for(int i = 0; i < nums.length; i++){
            for(int j = nums.length - 1; j > i; j--){
                if(nums[i] == nums[j] && j - i + 1 > span){
                    span = j - i + 1;
                }
            }
        }
        return span;
    }

    public int[] fix34(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 3 && nums[i+1] != 4){
                int four = -1;
                if(nums[0] == 4){
                    four = 0;
                }
                else {
                    for(int j = 1; j < nums.length; j++){
                        if(nums[j] == 4 && nums[j-1] != 3){
                            four = j;
                            j = nums.length;
                        }
                    }
                }
                nums[four] = nums[i+1];
                nums[i+1] = 4;
            }
        }
        return nums;
    }

    public boolean canBalance(int[] nums) {
        if(nums.length == 0 || nums.length == 1){
            return false;
        }
        boolean splitEqual = false;
        int sumLeft = 0;
        int sumRight = 0;
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                sumLeft += nums[j];
            }
            for(int k = i; k < nums.length; k++){
                sumRight += nums[k];
            }
            if(sumLeft == sumRight){
                splitEqual = true;
                i = nums.length;
            }
            else{
                sumLeft = 0;
                sumRight = 0;
            }
        }
        return splitEqual;
    }

}
