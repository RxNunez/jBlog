package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class PostTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Post.clearAllPosts(); //clear out allll the posts before each test.
    }

    @Test
    public void NewPostObjectGetsCorrectlyCreates_true() throws Exception {
        Post post = newPost();
        assertEquals(true, post instanceof Post);
    }

    @Test
    public void PostInstantiatesWithContent_true() throws Exception {
        Post post = newPost();
        assertEquals("Day 1: Intro", post.getContent());
    }

    @Test
    public void AllPostsAreCorrectlyReturned_true() {
        Post post = newPost();
        Post otherPost = new Post ("How to pair successfully");
        assertEquals(2, Post.getAll().size());
    }

    @Test
    public void AllPostsContainsAllPosts_true() {
        Post post = newPost();
        Post otherPost = new Post ("How to pair successfully");
        assertEquals(true, Post.getAll().contains(post));
        assertEquals(true, Post.getAll().contains(otherPost));
    }

    @Test
    public void getPublished_isFalseAfterInstantiation_false() throws Exception {
        Post myPost = newPost();
        assertEquals(false, myPost.getPublished()); //should never start as published
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception{
        Post myPost = newPost();
        assertEquals(LocalDateTime.now().getDayOfWeek(), myPost.getCreatedAt().getDayOfWeek());
    }
    @Test
    public void getId_postsInstantiateWithAnID_1() throws Exception{
        Post.clearAllPosts();  // Remember, the test will fail without this line! We need to empty leftover Posts from previous tests!
        Post myPost = newPost();
        assertEquals(1, myPost.getId());
    }

    @Test
    public void findById_findReturnsCorrectPost() throws Exception {
        Post testpost = newPost();
        assertEquals(1, Post.findById(testpost.getId()).getId());
    }

    @Test
    public void findById_findReturnsCorrectPostWhenMoreThanOnePostExists() throws Exception {
        Post post = newPost();
        Post otherPost = new Post("How to pair successfully");
        assertEquals(2, Post.findById(otherPost.getId()).getId());
    }
    @Test
    public void updateChangesPostContent() throws Exception {
        Post post = newPost();
        String formerContent = post.getContent();
        LocalDateTime formerDate = post.getCreatedAt();
        int formerId = post.getId();
        post.update("Android: Day 40");
        assertEquals(formerId, post.getId());
        assertEquals(formerDate, post.getCreatedAt());
        assertNotEquals(formerContent, post.getContent());
    }
    @Test
    public void deleteDeletesASpecificPost() throws Exception {
        Post post = newPost();
        Post otherPost = new Post("How to pair successfully");
        post.deletePost();
        assertEquals(1, Post.getAll().size()); //one is left
        assertEquals(Post.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2. Why do we care?
    }
    @Test
    public void deleteAllPostsDeletesAllPosts() throws Exception {
        Post post = newPost();
        Post otherPost = newPost();
        Post.clearAllPosts();
        assertEquals(0, Post.getAll().size());
    }


    //helper
    public Post newPost(){
        return new Post("Day 1: Intro");
    }

}