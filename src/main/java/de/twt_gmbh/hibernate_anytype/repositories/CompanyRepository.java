package de.twt_gmbh.hibernate_anytype.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import de.twt_gmbh.hibernate_anytype.model.Company;


public interface CompanyRepository extends CrudRepository<Company, Long>,
        JpaSpecificationExecutor<Company>
{
}
