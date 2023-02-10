import org.junit.Assert
import org.junit.Test

class SocialNetworkTest {

    val userService = UserService()

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
}