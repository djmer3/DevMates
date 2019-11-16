import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*

fun main(){
   val matrix = arrayOf(arrayOf(1, 0, 1, 1, 1, 1, 0, 1, 1, 1 ),
           arrayOf(1, 0, 1, 0, 1, 1, 1, 0, 1, 1 ),
           arrayOf(1, 1, 1, 0, 1, 1, 0, 1, 0, 1 ),
           arrayOf(0, 0, 0, 0, 1, 0, 0, 0, 0, 1 ),
           arrayOf(1, 1, 1, 0, 1, 1, 1, 0, 1, 0 ),
           arrayOf(1, 0, 1, 1, 1, 1, 0, 1, 0, 0 ),
           arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1 ),
           arrayOf(1, 0, 1, 1, 1, 1, 0, 1, 1, 1 ),
           arrayOf(1, 1, 0, 0, 0, 0, 1, 0, 0, 1 ))

   val source = Location(0,0, 0)
   val destination = Pair(3,4)

   val visited = Array<BooleanArray>(matrix.size){i -> BooleanArray(matrix[i].size){false} }
   val queue = LinkedList<Location>()
   queue.add(source)

   while(!queue.isEmpty()){
      search(queue.remove(), queue, matrix, destination)
      val location = queue.remove()
      testAndAdd(location.i-1, location.j, queue)
      testAndAdd(location.i, location.j-1, queue)
      testAndAdd(location.i+1, location.j, queue)
      testAndAdd(location.i, location.j+1, queue)
   }
}

fun testAndAdd(i: Int, j: Int, search: Search){
   if(i>=0&&j>=0&&i<search.matrix.size&&j<search.matrix[i].size){
      if(search.matrix[i][j]==1) {
         if (!search.visited[i][j]) {
            search.visited[i][j] = true
            search.queue.addLast(Location(i, j))
         }
      }
   }
}

fun search(location: Location, queue: LinkedList<Location>, matrix: Array<Array<Int>>, destination: Pair<Int, Int>){

}

class Location(val i: Int, val j: Int, val steps: Int)

class Search(val matrix: Array<Array<Int>>, val visited: Array<BooleanArray>, val queue: LinkedList<Location>, val destination: Pair<Int, Int>)