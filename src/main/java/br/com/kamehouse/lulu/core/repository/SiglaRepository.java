package br.com.kamehouse.lulu.core.repository;

import br.com.kamehouse.lulu.core.domain.Autor;
import br.com.kamehouse.lulu.core.domain.Sigla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiglaRepository extends JpaRepository<Sigla, Long> {


    @Query(value = "select * from tbl_lulu_sigla order by id desc limit 1", nativeQuery = true)
    Optional<Sigla> ultimaSiglaCadastrada();

    @Query(value = "select * from tbl_lulu_sigla order by id asc limit 1", nativeQuery = true)
    Optional<Sigla> primeiraSiglaCadastrada();

    @Query(value = "select autor.*, count(sigla.tbl_lulu_autor_id) as total from tbl_lulu_sigla as sigla \n" +
            "inner join tbl_lulu_autor as autor \n" +
            "on autor.id = sigla.tbl_lulu_autor_id \n" +
            "group by autor.id, sigla.tbl_lulu_autor_id\n" +
            "order by total desc limit 1", nativeQuery = true)
    Optional<SiglasPorAutor> findAutorQueMaisCriouSiglas();

}
