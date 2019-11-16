package xyz.dmercer

fun main(){
   val houses = intArrayOf(4, 8, 1, 1)
   val stores = intArrayOf(5, 3, 1, 2, 6)
   findClosestStores(houses,stores).forEach { println(it) }
}

fun findClosestStores(houses: IntArray, stores: IntArray): IntArray{
   val storesSorted = stores.sortedArray()
   val closestStores = IntArray(houses.size){-1}
   for(i in houses.indices){
      for(j in storesSorted.indices){
         if(storesSorted[j]>houses[i]){
            closestStores[i] = when{
               j==0->  storesSorted[j]
               (closestStores[i] - storesSorted[j-1])> (storesSorted[j-1] - closestStores[i])-> storesSorted[j]
               else -> storesSorted[j-1]
            }
            break
         }
      }
      if(closestStores[i]<0) closestStores[i] = storesSorted.last()
   }
   return closestStores
}