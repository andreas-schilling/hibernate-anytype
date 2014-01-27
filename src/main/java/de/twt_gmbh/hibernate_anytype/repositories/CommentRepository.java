package de.twt_gmbh.hibernate_anytype.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.twt_gmbh.hibernate_anytype.model.BaseEntity;
import de.twt_gmbh.hibernate_anytype.model.Comment;


public interface CommentRepository extends CrudRepository<Comment, Long>,
        JpaSpecificationExecutor<Comment>
{
  @Query("select c from Comment c where c.commentedObject in (?1)")
  List<Comment> findByCommentedObjectIn( final List<? extends BaseEntity> commentedObjects );
}
