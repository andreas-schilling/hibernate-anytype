package de.twt_gmbh.hibernate_anytype;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.common.collect.ImmutableList;

import de.twt_gmbh.hibernate_anytype.model.Comment;
import de.twt_gmbh.hibernate_anytype.model.Company;
import de.twt_gmbh.hibernate_anytype.repositories.CommentRepository;
import de.twt_gmbh.hibernate_anytype.repositories.CompanyRepository;
import de.twt_gmbh.hibernate_anytype.service.Repositories;


public class TestApp
{
  private static CommentRepository commentRepo;
  private static CompanyRepository companyRepo;


  @BeforeClass
  public static void initializeTest()
  {
    BasicConfigurator.configure();
    final ApplicationContext context =
            new FileSystemXmlApplicationContext(
                    "src/main/java/de/twt_gmbh/hibernate_anytype/application-config.xml" );
    final Repositories repos = context.getBean( Repositories.class );
    commentRepo = repos.getRepository( Comment.class );
    companyRepo = repos.getRepository( Company.class );
  }


  @Before
  public void clearRepositories()
  {
    commentRepo.deleteAll();
    companyRepo.deleteAll();
  }


  @Test
  public void testInitialRepositoriesAreEmpty()
  {
    Assert.assertEquals( 0L, commentRepo.count() );
    Assert.assertEquals( 0L, companyRepo.count() );
  }


  @Test
  public void testInsertOneComment()
  {
    final Company acme = new Company();
    acme.setName( "ACME" );
    final Comment acmeComment = new Comment();
    acmeComment.setComment( "Eine Firma die alles herstellt" );
    acmeComment.setCommentedObject( acme );

    companyRepo.save( acme );
    commentRepo.save( acmeComment );

    Assert.assertEquals( 1L, companyRepo.count() );
    Assert.assertEquals( 1L, commentRepo.count() );
  }


  @Test
  public void testInsertTwoComments()
  {
    final Company acme = new Company();
    acme.setName( "ACME" );
    final Comment acmeComment = new Comment();
    acmeComment.setComment( "Eine Firma die alles herstellt" );
    acmeComment.setCommentedObject( acme );
    final Comment acmeSecondComment = new Comment();
    acmeSecondComment.setComment( "Eine Firma aus einem Cartoon" );
    acmeSecondComment.setCommentedObject( acme );

    companyRepo.save( acme );
    commentRepo.save( ImmutableList.of( acmeComment, acmeSecondComment ) );

    Assert.assertEquals( 1L, companyRepo.count() );
    Assert.assertEquals( 2L, commentRepo.count() );
    Assert.assertEquals( 2L, commentRepo
            .findByCommentedObjectIn( ImmutableList.of( acme ) ).size() );
  }
}
