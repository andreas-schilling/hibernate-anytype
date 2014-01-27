package de.twt_gmbh.hibernate_anytype.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Objects;


@Entity
@Table(name = "CT_COMPANIES")
public class Company implements BaseEntity
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "COM_ID")
  private Long id;

  @Column(name = "COM_NAME", unique = true)
  private String name;

  @OneToMany(mappedBy = "company")
  private List<Person> employees;


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
    final Company otherCompany = (Company) obj;
    return Objects.equal( getId(), otherCompany.getId() );
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


  public List<Person> getEmployees()
  {
    return employees;
  }


  public void setEmployees( final List<Person> employees )
  {
    this.employees = employees;
  }


  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return getName();
  }
}
