import java.util.Scanner;
  
public class test2DimArray
{
   public static void main(String[] args) throws Exception
   {
      //test if each row adds up to 6 using 3! (3 factoria: 3 + 2 + 1
      int[][] testArr = { {1,2,3} ,
                          {1,2,1} ,
                          {2,3,1} 
                         };
      boolean winnerTF = false;
      
      for(int row = 0; row < testArr.length; ++row)
      {
         int sum = 0;
         
         for(int col = 0; col < testArr[row].length; ++col)
         {
            sum += testArr[row][col];
         }//end inner loop
         System.out.println("Row: " + row + ", Sum: " + sum);
         if(sum == 6)
         {
            winnerTF = true;
            System.out.println("You win");
         }
         else
         {
            winnerTF = false;
            System.out.println("You lose");
            break;
         }  
      }//end outer loop 
      
   }//end main
}//end test2DimArray
