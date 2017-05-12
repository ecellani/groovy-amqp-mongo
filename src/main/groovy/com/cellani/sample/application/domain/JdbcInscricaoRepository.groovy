package com.cellani.sample.application.domain

import com.cellani.sample.application.domain.repository.InscricaoRepository
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

import javax.sql.DataSource

/**
 * Created by ecellani on 11/05/17.
 */
@Repository
class JdbcInscricaoRepository implements InscricaoRepository {

    private final Sql sql

    @Autowired
    JdbcInscricaoRepository(DataSource dataSource) {
        sql = Sql.newInstance dataSource as DataSource
    }

    @Override
    def findAllInscricao(closure) {
        try {
            def results = []
            sql.eachRow("""
                QUERY
            """) { row ->
                results << [
                        sistema            : row.sistema,
                        protocolo          : row.protocolo,
                        inscricao          : row.inscricao,
                        unidade            : row.unidade,
                        concurso           : row.concurso,
                        datainscricao      : row.datainscricao,
                        localprova         : row.localprova,
                        tipocandidato      : row.tipocandidato,
                        opcao              : row.opcao,
                        modalidadeInscricao: row.modalidade_inscricao,
                        dataNascimento     : row.datanascimento,
                        cpf                : row.cpf,
                        rg                 : row.rg,
                        nome               : row.nome,
                        cep                : row.cep,
                        rua                : row.rua,
                        numero             : row.numero,
                        complemento        : row.complemento,
                        bairro             : row.bairro,
                        cidade             : row.cidade,
                        estado             : row.estado,
                        telefone           : row.telefone,
                        celular            : row.celular,
                        celularAdicional   : row.celularadicional,
                        email              : row.email,
                        emailAdicional     : row.emailadicional,
                        anoConclusao       : row.anoconclusao,
                        usarNotaEnem       : row.usarnotaenem,
                        numProtNotaEnem    : row.numprotnotaenem,
                        deficiencia        : row.deficiencia,
                        tipoDeficiencia    : row.tipodeficiencia,
                        outraDeficiencia   : row.outradeficiencia,
                        multiDeficiencia   : row.multideficiencia,
                        receberEmail       : row.receberemail,
                        recebersms         : row.recebersms
                ]
            }
            closure null, results
        } catch (Exception e) {
            closure e
        }
    }
}
