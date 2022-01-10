package giasuomt.demo.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.uploadfile.model.AwsAvatar;

@Repository
public interface IAWSAvatarRepository extends JpaRepository<AwsAvatar, Long> {
	void deleteByUrlImage(String urlImage);
}
