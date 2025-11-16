package org.example.repository.client;

import org.example.repository.orm.PessoaOrm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepositoryMongoBD extends MongoRepository<PessoaOrm, String> {
    Optional<PessoaOrm> findByIdAndAtivoTrue(String id);

    Page<PessoaOrm> findByAtivoTrue(Pageable pageable);
}
