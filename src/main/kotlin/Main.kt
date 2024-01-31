package ru.netology



fun main(){
    WallService.add(Post(1, 0, 0, 0, "Hello",0))
    WallService.add(Post(2, 0, 0, 0,"Bay"))
    WallService.printPosts()


}

// Data class for Post
data class Post(
    val id: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    val text: String = "",
    val replyOwnerId: Int = 0,
    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views()

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

// WallService object
object WallService {
    private var nextId = 1
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        val newPost = post.copy(id = nextId++)
        posts += newPost
        return newPost
    }

    fun update(post: Post): Boolean {
        val index = posts.indexOfFirst() { it.id == post.id }
        return if (index >= 0) {
            posts[index] = post
            true
        } else {
            false
        }
    }


    fun clear() {
        nextId = 1
        posts = emptyArray()
    }

    fun printPosts(){
        for (post in posts) {
            print(post)
            print(" ")
        }
        println()
    }
}