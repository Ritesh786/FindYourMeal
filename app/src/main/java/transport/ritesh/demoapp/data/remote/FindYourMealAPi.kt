package transport.ritesh.demoapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import transport.ritesh.demoapp.data.model.MealItemsDTO

interface FindYourMealAPi {
    @GET("api/json/v1/1/search.php")
    suspend fun getMealItemList(@Query("s")s:String):MealItemsDTO

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealDetails(@Query("i")i:String):MealItemsDTO

}