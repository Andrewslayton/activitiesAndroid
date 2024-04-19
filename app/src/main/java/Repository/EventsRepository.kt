package Repository

import com.example.activitys.Event
import com.example.activitys.TicketmasterApiService

class EventsRepository(private val apiService: TicketmasterApiService) {

    suspend fun fetchEvents(lat: Double, lng: Double, radius: Int, hobbies: List<String>): List<Event> {
        val response = apiService.getEvents("VNHzOfnzhWz7553H1dhFDeHZaJNU4NZW", "$lat,$lng", radius.toString(), "miles", hobbies.joinToString(","))
        return response.body()?.embedded?.events ?: emptyList()
    }
}
