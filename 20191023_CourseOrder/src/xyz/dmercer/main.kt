package xyz.dmercer

import java.util.*

fun main(vararg args: String){ //4, [[1,0],[2,0],[3,1],[3,2]]
   val courseCount =  args[0].toInt()
   val pairs = args[1].substring(2, args[1].length - 2).split("],[")
   val dependancies = LinkedList<Pair<Int,Int>>()
   val order = LinkedList<Int>()
   val courses = Array<Course>(courseCount){Course(it)}
   val uncompleteCourses = courses.toList()
   var coursesCompleted = 0
   pairs.forEach {
      val pair = it.split(',')
      courses[pair[0].toInt()].dependencies.add(pair[1].toInt())
   }

   var courseCompleted = false

   do {
      courseCompleted = false
      courses.forEach {
         if (!it.completed) {
            var dependenciesCompleted = true
            it.dependencies.forEach {
               if (!courses[it].completed) dependenciesCompleted = false
            }
            if (dependenciesCompleted) {
               it.completed = true
               courseCompleted = true
               coursesCompleted++
               order.addLast(it.id)
            }
         }
      }
   } while (courseCompleted)

   if(coursesCompleted==courseCount){
      println(order)
   }
   else{
      println("not possible to complete")
   }

}


class Course(val id: Int){
   val dependencies = LinkedList<Int>()
   var completed = false
}