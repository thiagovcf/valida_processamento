package br.com.marketpay.validaapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Emissor;

public interface EmissorRepository extends CrudRepository<Emissor, Long> {

	List<Emissor> findAllByNomeContaining(String nomeEmissor);

	List<Emissor> findAllByStatus(String status);
	
	@Query(value="select * from t_emissor e where e.status = 'A' and e.id_emissor in (select f.id_emissor from t_folder_ftp f where f.id_folder_ftp in (select a.id_folder_ftp from t_file_folder_ftp_emissor a))",nativeQuery=true)
	List<Emissor> getEmissoresAtivoComArquivo();

}
