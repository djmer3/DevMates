package xyz.dmercer

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType
import com.sun.org.apache.xpath.internal.operations.Bool
import java.lang.StringBuilder
import java.util.*

//Solution is calculated by performing something like a depth first search in reverse
//, with the number of steps to a particular grid configuration stored in an array with an index generated from
//the grid values.

fun main(){
   val stepsToConfig = IntArray(512){Int.MAX_VALUE}
   stepsToConfig[0] = 0
   val queue = LinkedList<Position>()
   queue.addLast(Position(Array(3){ BooleanArray(3) {false}},0))
   var loops = 0
   while(!queue.isEmpty()){
      loops++
      val position = queue.remove()
      println(position.grid.toArrayString())
      println()
      stepsToPosition(position, stepsToConfig, queue)

   }
   println(loops)
   val initialGrid = arrayOf(
           booleanArrayOf(false, true, false),
           booleanArrayOf(true, true, false),
           booleanArrayOf(false, true, true))

   val index = indexOf(initialGrid)
   if(stepsToConfig[index]==Int.MAX_VALUE) println(-1)
   else println(stepsToConfig[index])
}

fun stepsToPosition(position: Position, stepsToConfig: IntArray, queue: LinkedList<Position>){
   for(i in position.grid.indices){
      for(j in position.grid[i].indices){
         val newGrid = flip(position.grid, i, j)
         val index = indexOf(newGrid)
         if(stepsToConfig[index]>position.steps+1){
            stepsToConfig[index] = position.steps+1
            queue.addLast(Position(newGrid, position.steps+1))
         }
      }
   }

}

fun flip(sourceGrid: Array<BooleanArray>, i: Int, j:Int): Array<BooleanArray>{
   val newGrid = Array<BooleanArray>(sourceGrid.size) { i ->
      BooleanArray(sourceGrid[i].size) {j ->
         sourceGrid[i][j]
      }
   }
   newGrid[i][j] = !sourceGrid[i][j]
   if(i>0) newGrid[i-1][j] = !sourceGrid[i-1][j]
   if(j>0) newGrid[i][j-1] = !sourceGrid[i][j-1]
   if(i<sourceGrid.size-1) newGrid[i+1][j] = !sourceGrid[i+1][j]
   if(j<sourceGrid.size-1) newGrid[i][j+1] = !sourceGrid[i][j+1]
   return newGrid
}

fun indexOf(grid: Array<BooleanArray>): Int{
   var index = 0
   for(i in grid.indices) {
      for (j in grid[i].indices) {
         index = index*2 + grid[i][j].toInt()
      }
   }
   return index
}

class Position(
        val grid: Array<BooleanArray>,
        val steps: Int
)

fun Boolean.toInt(): Int{
   if(this) return 1
   return 0
}

fun Array<BooleanArray>.toArrayString(): String{
   val sb = StringBuilder()
   sb.append('[')
   for(i in this.indices){
      sb.append('[')
      for(j in this[i].indices){
         sb.append(this[i][j].toInt())
         if(j<this[i].size-1) sb.append(',')
      }
      sb.append("]")
      if(i<this.size-1) sb.append('\n')
   }
   sb.append(']')
   return sb.toString()
}