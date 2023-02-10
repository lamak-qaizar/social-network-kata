class UserService {

    val postings = mutableMapOf<String, MutableList<String>>()

    fun post(userId: String, message: String) {
        if(postings.containsKey(userId)){
            postings[userId]?.add(message)
        } else {
            postings[userId] = mutableListOf(message)
        }
    }

    fun getTimeline(userId: String): List<String> {
        return postings[userId]?.let{
            it
        }?: kotlin.run {
            emptyList()
        }
    }

}
