package com.emerald.vitruvian.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String commentText;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity user;

    @OneToOne
    private ImageEntryEntity image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ImageEntryEntity getImage() {
        return image;
    }

    public void setImage(ImageEntryEntity image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", user=" + user +
                ", image=" + image +
                '}';
    }
}
