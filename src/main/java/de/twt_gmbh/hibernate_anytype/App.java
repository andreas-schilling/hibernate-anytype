package de.twt_gmbh.hibernate_anytype;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import de.twt_gmbh.hibernate_anytype.model.Comment;
import de.twt_gmbh.hibernate_anytype.model.Company;
import de.twt_gmbh.hibernate_anytype.model.Person;
import de.twt_gmbh.hibernate_anytype.repositories.CommentRepository;
import de.twt_gmbh.hibernate_anytype.repositories.CompanyRepository;
import de.twt_gmbh.hibernate_anytype.repositories.PersonRepository;
import de.twt_gmbh.hibernate_anytype.service.Repositories;


public class App
{
  private static final Logger LOGGER = LoggerFactory.getLogger( App.class );


  public static void main( final String[] args )
  {
    BasicConfigurator.configure();
    final ApplicationContext context =
            new FileSystemXmlApplicationContext(
                    "src/main/java/de/twt_gmbh/hibernate_anytype/application-config.xml" );
    final Repositories repos = context.getBean( Repositories.class );
    final CompanyRepository companyRepo = repos.getRepository( Company.class );
    final PersonRepository personRepo = repos.getRepository( Person.class );
    final CommentRepository commentRepo = repos.getRepository( Comment.class );

    final Company acme = new Company();
    acme.setName( "ACME" );
    final Comment acmeComment = new Comment();
    acmeComment.setComment( "Eine Firma die alles herstellt" );
    acmeComment.setCommentedObject( acme );
    final Comment acmeSecondComment = new Comment();
    acmeSecondComment.setComment( "Eine Firma aus einem Cartoon" );
    acmeSecondComment.setCommentedObject( acme );

    final Person person = new Person();
    person.setName( "Hans" );
    person.setSurname( "Wurst" );
    person.setCompany( acme );
    final Comment personComment = new Comment();
    personComment.setComment( "Ein Angestellter" );
    personComment.setCommentedObject( person );

    companyRepo.save( acme );
    personRepo.save( person );
    commentRepo.save( ImmutableList.of( acmeComment, acmeSecondComment, personComment ) );

    final Iterable<Comment> allComments = commentRepo.findAll();
    LOGGER.info( Joiner.on( "\n" ).join( allComments ) );

    final Iterable<Comment> acmeComments =
            commentRepo.findByCommentedObjectIn( ImmutableList.of( acme ) );
    
    LOGGER.info( Joiner.on( "\n" ).join( acmeComments ) );
    
    final Iterable<Comment> againAllComments =
            commentRepo.findByCommentedObjectIn( ImmutableList.of( acme, person ) );
    
    LOGGER.info( Joiner.on( "\n" ).join( againAllComments ) );

    System.exit( 0 );
  }
}
