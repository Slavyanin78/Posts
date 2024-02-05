package ru.netology




fun main(){
    WallService.add(Post(1, 0, 0, 0, "Hello",0))
    WallService.add(Post(2, 0, 0, 0,"Bay"))
    WallService.printPosts()

    WallService.createComment(1, Comment(1, "First comment"))
    WallService.createComment(1, Comment(2, "Second comment"))
    WallService.printPosts()


}

// Data class for Post
data class Post(
    val id: Int = 0,
    val fromId: Int? = null,
    val createdBy: Int? = null,
    val date: Int = 0,
    val text: String = "",
    val replyOwnerId: Int? = null,
    val comments: MutableList<Comment> = mutableListOf(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val attachments: Array<Attachment> = emptyArray()
)

// Classes for object fields
data class Comment(
    val id: Int = 0,
    val text: String


)

data class Likes(
    var counter: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = false,
    val canPublish: Boolean = false
)

data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class Views(
    val count: Int = 0
)

// Abstract class for Attachment with type field
abstract class Attachment(val type: String)

// Classes for specific types of Attachment
class PhotoAttachment( val photo: Photo) : Attachment("photo")
class VideoAttachment( val video: Video) : Attachment("video")
class AudioAttachment( val audio: Audio) : Attachment("audio")
class DocumentAttachment( val document: Document) : Attachment("document")
class LinkAttachment( val link: Link) : Attachment("link")

// Classes for data inside Attachments
data class Photo(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String
)

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int
)

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int
)

data class Document(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int
)

data class Link(
    val url: String,
    val title: String,
    val caption: String
)

class PostNotFoundException(message: String) : Exception(message)


// WallService object
object WallService {
    private var nextId = 1
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()



    fun add(post: Post): Post {
        val newPost = post.copy(id = nextId)
        posts += newPost
        nextId++
        return newPost
    }

    fun update(post: Post): Boolean {
        for ((index, currentPost) in posts.withIndex()) {
            if (currentPost.id == post.id) {
                posts[index] = post.copy(
                    id = currentPost.id,
                    attachments = currentPost.attachments
                )
                return true
            }
        }
        return false
    }

    var commentIdCounter = 1


    fun createComment(postId: Int, comment: Comment): Comment {
        val postIndex = posts.indexOfFirst { it.id == postId }
        if (postIndex != -1) {
            val updatedPost = posts[postIndex].copy(comments = posts[postIndex].comments.toMutableList().apply { add(comment) })
            posts[postIndex] = updatedPost
            commentIdCounter++
            return comment.copy(id = commentIdCounter)
        } else {
            throw PostNotFoundException("Post with id $postId not found")
        }
    }

    fun getPostById(postId: Int): Post? {
        return posts.find { it.id == postId }
    }



    fun clear() {
        nextId = 1
        posts = emptyArray()
    }

    fun printPosts() {
        for (post in posts) {
            println("id: ${post.id}, text: ${post.text}, attachments: ${post.attachments.joinToString()}")
            for (comment in post.comments) {
                println("  Comment id: ${comment.id}, text: ${comment.text}")
            }
        }
    }

}