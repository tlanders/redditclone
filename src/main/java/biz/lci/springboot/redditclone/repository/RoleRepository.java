package biz.lci.springboot.redditclone.repository;

import biz.lci.springboot.redditclone.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}
