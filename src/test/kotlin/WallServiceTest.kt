package ru.netology

import org.junit.Test
import org.junit.Assert.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertThrows


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


    @Test
    fun testCreateComment() {
        // Arrange
        val post = Post(1, 1, 0, 0, "Hello, world!")
        val comment = Comment(1, "Nice post!")

        // Act
        WallService.add(post)
        WallService.createComment(1, comment)

        // Assert
        val expectedPost = Post(1, 1, 0, 0, "Hello, world!")
        assertEquals(
            expectedPost,
            WallService.getPostById(1)
        )
    }

    @Test
    fun testCreateCommentForNonExistingPost() {
        // Создаем новый комментарий
        val newComment = Comment(1, "New comment")

        // Проверяем, что при попытке добавить комментарий к несуществующему посту с id=5 выбрасывается исключение
        assertThrows(PostNotFoundException::class.java) {
            WallService.createComment(5, newComment)
        }
    }
}
