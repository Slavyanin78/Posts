import org.junit.Test

import org.junit.Assert.*
import ru.netology.Post
import ru.netology.WallService

class WallServiceTest {

    @Test
    fun add() {
        WallService.clear()
        val post = Post(text = "Hello, world!")
        val result = WallService.add(post)
        assertTrue(result.id > 0)
    }

    @Test
    fun updateExistingPost() {
        WallService.clear()
        val post = Post(text = "Hello, world!")
        val addedPost = WallService.add(post)
        val updatedPost = addedPost.copy(text = "Updated text")
        val result = WallService.update(updatedPost)
        assertTrue(result)
    }

    @Test
    fun updateNonExistingPost() {
        WallService.clear()
        val post = Post(text = "Hello, world!")
        val updatedPost = post.copy(id = 1, text = "Updated text")
        val result = WallService.update(updatedPost)
        assertFalse(result)
    }
}