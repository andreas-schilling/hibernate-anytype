package de.twt_gmbh.hibernate_anytype.service;

import org.springframework.data.repository.CrudRepository;

import de.twt_gmbh.hibernate_anytype.model.Comment;
import de.twt_gmbh.hibernate_anytype.model.Company;
import de.twt_gmbh.hibernate_anytype.model.Person;
import de.twt_gmbh.hibernate_anytype.repositories.CommentRepository;
import de.twt_gmbh.hibernate_anytype.repositories.CompanyRepository;
import de.twt_gmbh.hibernate_anytype.repositories.PersonRepository;


public class Repositories
{
  private PersonRepository personRepository;

  private CompanyRepository companyRepository;

  private CommentRepository commentRepository;


  public Repositories()
  {
    super();
  }


  public void setPersonRepository( final PersonRepository personRepository )
  {
    this.personRepository = personRepository;
  }


  public void setCompanyRepository( final CompanyRepository companyRepository )
  {
    this.companyRepository = companyRepository;
  }


  public void setCommentRepository( final CommentRepository commentRepository )
  {
    this.commentRepository = commentRepository;
  }


  public <T, R extends CrudRepository<T, Long>> R getRepository( final Class<T> clazz )
  {
    if( Person.class.isAssignableFrom( clazz ) )
    {
      return (R) personRepository;
    }
    else if( Company.class.isAssignableFrom( clazz ) )
    {
      return (R) companyRepository;
    }
    else if( Comment.class.isAssignableFrom( clazz ) )
    {
      return (R) commentRepository;
    }

    throw new IllegalArgumentException();
  }
}
