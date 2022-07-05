package com.arturishmaev.documentflow.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

//    @ManyToMany(mappedBy = "documents")
//    private List<AssignmentEntity> assignments;

    public DocumentEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void fromDocumentEntity(DocumentEntity documentEntity) {
        this.setTitle(documentEntity.getTitle());
        this.setContent(documentEntity.getContent());
    }

    @Override
    public String toString() {
        return "DocumentEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
