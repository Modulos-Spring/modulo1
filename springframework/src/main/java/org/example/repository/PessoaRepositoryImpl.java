package org.example.repository;

import org.example.entity.Pessoa;
import org.example.exception.InternalServerException;
import org.example.exception.NotFoundException;
import org.example.repository.adapter.PessoaRepositoryAdapter;
import org.example.repository.client.PessoaRepositoryMongoBD;
import org.example.repository.orm.PessoaOrm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository  {
    private static final Logger LOG = LoggerFactory.getLogger(PessoaRepositoryImpl.class);

    private final PessoaRepositoryMongoBD repository;

    public PessoaRepositoryImpl(PessoaRepositoryMongoBD repository) {
        this.repository = repository;
    }

    @Override
    public Pessoa create(Pessoa pessoa) {
        try {
            PessoaOrm orm = PessoaRepositoryAdapter.cast(pessoa);
            return PessoaRepositoryAdapter.cast(repository.save(orm));

        } catch (Exception ex) {
            LOG.error("Erro ao salvar pessoa: {} o erro aconteceu na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public Pessoa update(String id, String nome, String dt_nascimento) {
        try {
            Pessoa pessoa = this.readById(id);
            Pessoa pessoaUpdate = new Pessoa(id, nome, dt_nascimento, true);

            PessoaOrm orm = PessoaRepositoryAdapter.cast(pessoaUpdate);
            return PessoaRepositoryAdapter.cast(repository.save(orm));

        } catch (NotFoundException ex) {
            LOG.info("Pessoa nao encontrada");
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro ao atualizar pessoa: {} o erro aconteceu na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public Pessoa readById(String id) {
        try {
            Optional<PessoaOrm> optional = repository.findByIdAndAtivoTrue(id);
            if (optional.isEmpty()) {
                throw new NotFoundException("A pessoa nao existe");
            }

            return PessoaRepositoryAdapter.cast(repository.save(optional.get()));

        } catch (NotFoundException ex) {
            LOG.info("Pessoa nao encontrada");
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro ao procurar pessoa pelo id: {} o erro aconteceu na data/hora: {}", ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public List<Pessoa> readByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PessoaOrm> pessoaOrmList = repository.findByAtivoTrue(pageable);
        List<Pessoa> pessoaList = pessoaOrmList.stream().map(PessoaRepositoryAdapter::cast).toList();

        return pessoaList;
    }

    @Override
    public void delete(String id) {
        try {
            Pessoa pessoa = this.readById(id);
            Pessoa pessoaDelete = new Pessoa(pessoa.id(), pessoa.nome(), pessoa.dt_nascimento(), false);

            PessoaOrm orm = PessoaRepositoryAdapter.cast(pessoaDelete);
            repository.save(orm);

        } catch (NotFoundException ex) {
            LOG.info("Pessoa nao encontrada");
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro ao deletar pessoa: {} o erro aconteceu na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }
}
