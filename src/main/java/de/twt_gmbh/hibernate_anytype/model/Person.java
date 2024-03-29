package de.twt_gmbh.hibernate_anytype.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;


@Entity
@Table(name = "CT_PERSONS", uniqueConstraints = @UniqueConstraint(columnNames = {
        "PS_NAME", "PS_SURNAME" }))
public class Person implements BaseEntity
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PS_ID")
  private Long id;

  @Column(name = "PS_NAME")
  private String name;

  @Column(name = "PS_SURNAME")
  private String surname;

  @Column(name = "PS_AGE")
  private Long age;

  @ManyToOne
  @JoinColumn(name = "PS_COM_ID")
  private Company company;



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
    final Person otherPerson = (Person) obj;
    return Objects.equal( getId(), otherPerson.getId() );
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


  public String getName()
  {
    return name;
  }


  public void setName( final String name )
  {
    this.name = name;
  }


  public String getSurname()
  {
    return surname;
  }


  public void setSurname( final String surname )
  {
    this.surname = surname;
  }


  public Company getCompany()
  {
    return company;
  }


  public void setCompany( final Company company )
  {
    this.company = company;
  }


  public Long getAge()
  {
    return age;
  }


  public void setAge( final Long age )
  {
    this.age = age;
  }


  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return Joiner.on( ", " ).join( getName(), getSurname() );
  }
}
