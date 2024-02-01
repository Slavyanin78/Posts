package ru.netology



fun main(){
    WallService.add(Post(1, 0, 0, 0, "Hello",0))
    WallService.add(Post(2, 0, 0, 0,"Bay"))
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
    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val attachments: Array<Attachment> = emptyArray()
)

// Classes for object fields
data class Comments(
    val count: Int = 0,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
)

data class Likes(
    val count: Int = 0,
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
class PhotoAttachment(type: String, val photo: Photo) : Attachment(type)
class VideoAttachment(type: String, val video: Video) : Attachment(type)
class AudioAttachment(type: String, val audio: Audio) : Attachment(type)
class DocumentAttachment(type: String, val document: Document) : Attachment(type)
class LinkAttachment(type: String, val link: Link) : Attachment(type)

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

// WallService object
object WallService {
    private var nextId = 1
    private var posts = emptyArray<Post>()

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

    fun clear() {
        nextId = 1
        posts = emptyArray()
    }

    fun printPosts() {
        for (post in posts) {
            println("id: post.id, text:post.text, attachments: post.attachments.toList()")
        }
    }

}