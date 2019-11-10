package xyz.dmercer

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*

//Solution is calculated by performing something like a depth first search in reverse
//, with the number of steps to a particular grid configuration stored in an array with an index generated from
//the grid values.
//Search ends after at depth of n, so may end up considering some positions unsolvable which are actually solvable

fun main(){
   val initialGrid = arrayOf(
           booleanArrayOf(false, true, false),
           booleanArrayOf(true, true, false),
           booleanArrayOf(false, true, true))

   val stepsToConfig = IntArray(512){-1}
   stepsToConfig[0] = 0
   val queue = LinkedList<Position>()
}

fun stepsToPosition(prevSteps: Int, grid: Array<BooleanArray>, stepsToConfig: IntArray){
   for(i in grid.indices){
      for(j in grid[i].indices){
         val newGrid = flip(grid, i, j)
         val index = indexOf(newGrid)
         if(stepsToConfig[index]<0||stepsToConfig[index]>prevSteps+1){
            stepsToConfig[index] = prevSteps+1
            stepsToPosition(prevSteps+1, newGrid, stepsToConfig)
         }
      }
   }

}

fun flip(sourceGrid: Array<BooleanArray>): Array<BooleanArray>{

}

fun indexOf(grid: Array<BooleanArray>): Int{


}

class Position(
        val grid: Array<BooleanArray>,
        val steps: Int
)