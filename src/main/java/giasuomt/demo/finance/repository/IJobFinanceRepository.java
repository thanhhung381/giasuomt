package giasuomt.demo.finance.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import giasuomt.demo.finance.model.JobFinance;

@Repository
public interface IJobFinanceRepository extends JpaRepository<JobFinance, Long> {

	@Query("SELECT j FROM JobFinance j WHERE j.billImg IS NOT NULL")
	Set<JobFinance> findAllJobFinanceSynchronized();

}
