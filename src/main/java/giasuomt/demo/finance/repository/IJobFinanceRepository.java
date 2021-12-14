package giasuomt.demo.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.finance.model.JobFinance;

@Repository
public interface IJobFinanceRepository extends JpaRepository<JobFinance, Long> {

}
