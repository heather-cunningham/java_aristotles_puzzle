import java.util.Scanner;
import java.util.Arrays;
  
public class AristotlesPuzzle_CTIM16801_Fall_2016
{
   public static void main(String[] args) throws Exception
   {
   	Tile[][] Board = new Tile[5][];
   	Board[0] = new Tile[3];
   	Board[1] = new Tile[4];
   	Board[2] = new Tile[5];
   	Board[3] = new Tile[4];
   	Board[4] = new Tile[3];
   	PopulateBoard(Board);
   	Tile[] AvailableTiles = new Tile[19];
   	
      PopulateArrayOfPlayingTiles(AvailableTiles);
      PrintArrayOfAvailablesTiles(AvailableTiles); 
   	PrintArrayOfPlayedTiles(Board);
      
   	do
   	{
   		PlaceTile(Board, AvailableTiles);
   	}while( TestAllTilesPlaced(AvailableTiles)==false && TestForWinner(Board, AvailableTiles)==false );
      
   	if(TestForWinner(Board, AvailableTiles)==true )
         System.out.println("You win!");
      
      System.out.println("\nGame over.");
   }//end main


//------------------------METHODS----------------------		   

   private static void PopulateArrayOfPlayingTiles(Tile[] arrayOfTilesPassed)
   {
      for(int i=0; i < arrayOfTilesPassed.length; ++i)
         arrayOfTilesPassed[i] = new Tile(false, i+1 );//end for loop  
   }//end PopulateArrayOfPlayingTiles

   
   private static void PlaceTile(Tile[][] boardPassed, Tile[] tilesPassed) throws ArrayIndexOutOfBoundsException
   {
      Scanner keyboard = new Scanner(System.in); 
      //Tile tilePlaced = new Tile();
      int numTile, row, col;
      
      if( TestAllTilesPlaced(tilesPassed) == false)
      {
         System.out.println("\nWhich # tile would you like to place? 1-19");
         numTile = keyboard.nextInt();
         try//Tile # array is in bounds
         {
            if ( tilesPassed[numTile -1].getPlaced() == false)
            {
               tilesPassed[numTile -1].setValue(numTile);
               tilesPassed[numTile -1].setPlaced(true);
               try//Board 2-dim array is in bounds
               {
                  System.out.println("Which row should the # tile be placed in?");
                  row = keyboard.nextInt();
                  System.out.println("Which column should the # tile be placed in?");
                  col = keyboard.nextInt();
                  while( boardPassed[row -1][col-1].getValue() > 0 /*|| row > 5 || col > 5*/ )
                  {
                     System.out.println("Row " + row + ", column " + col + " is taken already. Which row should the # tile be placed in?");
                     row = keyboard.nextInt();
                     System.out.println("Which column should the # tile be placed in?");
                     col = keyboard.nextInt();
                  }//end while
                  boardPassed[row -1][col-1]= tilesPassed[numTile -1];
               }//end try Board 2-dim array is in bounds
               catch(ArrayIndexOutOfBoundsException e)
               {
                  tilesPassed[numTile -1].setPlaced(false);
                  System.out.println("Row must be 1-5 and column must be 1-3, 1-4, or 1-5 respectively.");
               }//end catch Board 2-dim array is in bounds                  
            }//end if
            else
            {
               System.out.println("Tile #" + numTile + " is used already. Please pick another tile #.");
               PlaceTile(boardPassed, tilesPassed);
               tilesPassed[numTile -1].setPlaced(false);
            }//end if / else validate if Tile placed already
         }//end try Tile # array is in bounds
         catch(ArrayIndexOutOfBoundsException e)
         {
            System.out.println("Tile # must be 1-19.");
         }//end catch if Tile # array is in bounds
         PrintArrayOfPlayedTiles(boardPassed);
         PrintArrayOfAvailablesTiles(tilesPassed);
         PlaceTile(boardPassed, tilesPassed);
      }//end outermost 1st IF TestAllTilesPlaced
      else
         TestForWinner(boardPassed, tilesPassed);
      //end outermost 1st IF/else TestAllTilesPlaced                     
   }//end PlaceTile
      

   private static void PopulateBoard(Tile[][] arrayPassed)
   {
      for(int i = 0; i < arrayPassed.length; ++i )
      {
            for(int j = 0; j < arrayPassed[i].length; ++j )
               arrayPassed[i][j] = new Tile();//end inner loop
      }//end outer loop
   }//end PopulateBoard
      

   private static void PrintArrayOfPlayedTiles(Tile[][] arrayPassed)
   {
      System.out.println("\nYour board so far:");
      System.out.println("------------------");
      //begin outer FOR loop
      for(int i = 0; i < arrayPassed.length; ++i)
      {
         switch(i)
         {
            case 0:
            case 4:
               System.out.print("  ");
               break;
            case 1:
            case 3:
               System.out.print(" ");
               break;
            default:
               break;         
         }//end switch
         
         //begin inner FOR loop
         for(int j = 0; j < arrayPassed[i].length; ++j)
         {   
            if(j > 0 && j < arrayPassed[i].length)
               System.out.print('|');
            System.out.print(arrayPassed[i][j].getValue());
         }//end inner FOR loop
         System.out.println();   
      }//end outer FOR loop   
   }//end PrintArrayOfPlayedTiles
            

   private static void PrintArrayOfAvailablesTiles(Tile[] arrayPassed)
   {
      if( TestAllTilesPlaced(arrayPassed) == true )
         System.out.println("\nAll of the # tiles have been placed. \nThere are no tiles left.");      
      else
      {
         System.out.println("\nAvailable # Tiles:");
         System.out.println("------------------");
         //for each loop
         for(Tile x: arrayPassed)
         {
            if( x.getPlaced() == false )
               System.out.print(x.getValue() + "  ");//end if
         }//end for EACH loop
      }//end if/else
      System.out.println();            
   }//end PrintArrayOfAvailablesTiles
            

   public static boolean TestAllTilesPlaced(Tile[] arrayPassed)
   {
      boolean allTilesPlaced = false;
      for(int i = 0; i < arrayPassed.length; i++)
      {
         if( arrayPassed[i].getPlaced() == true )
            allTilesPlaced = true;
         else
            allTilesPlaced = false;
      }//end for loop
      return allTilesPlaced;
   }//end TestAllTilesPlaced
      

   private static boolean TestForWinner(Tile[][] lastBoardPassed, Tile[] lastTileArray)
   {
      boolean winnerTF = false;
      
      if(TestAllTilesPlaced(lastTileArray) == false)
      {
         winnerTF = false;
         System.out.println("You can't win yet; you haven't placed all of the # tiles.");
         PlaceTile(lastBoardPassed, lastTileArray);
      }//end if all tiles placed
      else /*if( TestAllTilesPlaced(lastTileArray) == true)*/
      {   
         for(int row = 0; row < lastBoardPassed.length; ++row )
         {
            int sum = 0;
            for(int col = 0; col < lastBoardPassed[row].length; ++col )
               sum = sum + lastBoardPassed[row][col].getValue();
            //end 2nd for loop -- adds up each horizontal row
            if (sum == 38)
               winnerTF = true;
            else
            {
               winnerTF = false;
               System.out.println("Sorry, you don't win. Row " + (row + 1) + " doesn't add up to 38.");
               return winnerTF;
            }//end 1st IF/else if each horizontal row adds up to 38 exactly 
         }//end 1st FOR loop for horizontal rows
         
         if(winnerTF == true)
         {
            for(int rtDiagCol = 0; rtDiagCol < 5; ++rtDiagCol )
            {
               int sum = 0;
               for(int row = 0; row < lastBoardPassed[row].length; ++row)
                  sum = sum + lastBoardPassed[row][rtDiagCol].getValue();
               //end for loop rows rtDiag cols
               if (sum == 38)
                  winnerTF = true;
               else
               {
                  winnerTF = false;
                  System.out.println("Sorry, you don't win. Row " + (rtDiagCol + 1) + " doesn't add up to 38.");
                  return winnerTF;
               }//end 2nd IF/else if each right diagnol row adds up to 38 exactly 
            }//end 2nd for loop for rtDiagCols
         }//end if winner still true
         
         if(winnerTF == true)
         {
            for(int lftDiagCol = 4; lftDiagCol >= 0; --lftDiagCol)
            {
               int sum = 0;
               for(int row = 0; row < lastBoardPassed[row].length; ++row)
                  sum = sum + lastBoardPassed[row][lftDiagCol].getValue();
               //end for loop rows leftDiag cols
               if(sum == 38)
                  winnerTF = true;
               else
               {
                  winnerTF = false;
                  System.out.println("Sorry, you don't win. Diagonal column slanting to the left " +
                                    (lftDiagCol + 1) + " doesn't add up to 38."); 
                  return winnerTF;
               }//end if/else leftDiag cols add up to 38
            }//end for loop leftDiag cols
         }//end if winner still true         
      }//end else if TestAllTilesPlaced
      return winnerTF;
   }//end TestForWinner

}//end class AristotlesPuzzle_CTIM16801_Fall_2016