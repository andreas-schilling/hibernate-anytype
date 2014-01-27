package de.twt_gmbh.hibernate_anytype.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PostLoad;
import javax.persistence.Table;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

import com.google.common.base.Objects;


@Entity
@Table(name = "CT_COMMENTS")
public class Comment implements BaseEntity
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "CM_ID")
  private Long id;

  @Any(metaColumn = @Column(name = "CM_TYPE_ID"), fetch = FetchType.EAGER)
  @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
          @MetaValue(value = "Company", targetEntity = Company.class),
          @MetaValue(value = "Person", targetEntity = Person.class) })
  @JoinColumn(name = "CM_OBJECT_ID")
  private BaseEntity commentedObject;

  @Column(name = "CM_COMMENT")
  private String comment;


  @Override
  public int hashCode()
  {
    return Objects.hashCode( id );
  }


  @Override
  public boolean equals( final Object obj )
  {
    if( this == obj )
    {
      return true;
    }
    if( obj == null )
    {
      return false;
    }
    if( getClass() != obj.getClass() )
    {
      return false;
    }
    final Comment otherComment = (Comment) obj;
    return Objects.equal( getId(), otherComment.getId() );
  }


  @PostLoad
  private void postLoad()
  {
    // default mode fetch = EAGER doesn't work for @Any in Hibernate <= 4.1.1
    this.commentedObject.getId();
  }


  @Override
  public Long getId()
  {
    return id;
  }


  public void setId( final Long id )
  {
    this.id = id;
  }


  public String getComment()
  {
    return comment;
  }


  public void setComment( final String comment )
  {
    this.comment = comment;
  }


  public BaseEntity getCommentedObject()
  {
    return commentedObject;
  }


  public void setCommentedObject( final BaseEntity commentedObject )
  {
    this.commentedObject = commentedObject;
  }


  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return "[Comment for " + commentedObject + "=" + comment + "]";
  }
}
