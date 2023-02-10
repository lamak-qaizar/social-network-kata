import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

class SocialNetworkTest {

   private lateinit var userService: UserService
    @BeforeEach
    fun setup(){
         userService = UserService()
    }
    @Test
    fun `alice can publish a single message to timeline`() {
        val message = "hello world"

        userService.post("alice", message)

        val timeline = userService.getTimeline("alice")
        Assert.assertEquals(timeline, listOf("hello world"))
    }

    @Test
    fun `alice can publish multiple messages to timeline`() {
        val messages = listOf("hello", "hello", "world")

        for (message in messages) {
            userService.post("alice", message)
        }

        val timeline = userService.getTimeline("alice")
        Assert.assertEquals(timeline, listOf("hello", "hello", "world"))
    }

    @Test
    fun `bob can publish multiple messages to timeline`() {
        val messages = listOf("hello", "world", "sees")

        for (message in messages) {
            userService.post("bob", message)
        }

        val timeline = userService.getTimeline("bob")
        Assert.assertEquals(timeline, listOf("hello", "world", "sees"))
    }

    @Test
    fun `bob can publish a single message to timeline`() {
        val message = "hello world"

        userService.post("bob", message)

        val timeline = userService.getTimeline("bob")
        Assert.assertEquals("hello world", timeline.last())
    }

    @Test
    fun `bob and alice both can publish a single message to their own timeline`() {
        val message_alice = "alice message"
        val message_bob = "bob message"

        userService.post("bob", message_bob)
        userService.post("alice", message_alice)

        val timeline_alice = userService.getTimeline("alice")
        val timeline_bob = userService.getTimeline("bob")

        Assert.assertEquals(listOf("alice message"), timeline_alice)
        Assert.assertEquals(listOf("bob message"), timeline_bob)
    }

    @Test
    fun `bob can view all messages on alice timeline`(){
        val alice_messages = listOf<String>("Hello bob", "Message 2", "bye bye")

        alice_messages.forEach{ msg ->
            userService.post("alice", msg)
        }

        val timeline_alice = userService.getTimeline("alice")

        Assertions.assertEquals(alice_messages,timeline_alice)
    }

    @Test
    fun `bob cannot view messages on alice timeline when alice did not post anything`(){

        val timeline_alice = userService.getTimeline("alice")

        Assertions.assertEquals(emptyList<String>(),timeline_alice)
    }


    //Following: Charlie can subscribe to Alice’s and Bob’s timelines,
    // and view an aggregated list of all subscriptions
    //Charlie
    // Alice and bob
    // alice only
    // bob only
    // both not available

    @Test
    fun `charlie subscribed alice and bob and get updates from both timelines`(){
        `bob can publish multiple messages to timeline`()
        `alice can publish multiple messages to timeline`()

        val charlie = User(subscribedList = mutableListOf<String>("alice", "bob"))

        val subscribedTimeline = userService.getSubscribedTimeline(charlie.subscribedList)

        Assertions.assertEquals(subscribedTimeline.size,6)

    }



}