package com.cellani.sample.infrastructure.config

import static groovyx.gpars.actor.Actors.actor

/**
 * Created by ecellani on 12/05/17.
 */
class Mapper {

    static mapperActor = actor {
        loop {
            react { msg ->
                def mapped = [:]
                inscricaoColaborarMapper.each { key, value ->
                    mapped << ["$value": msg.remove(key)]
                }
                reply mapped
            }
        }
    }

    static inscricaoColaborarMapper = [
            'sistema'            : 'sistema',
            'protocolo'          : 'protocolo',
            'inscricao'          : 'inscricao',
            'unidade'            : 'unidade',
            'concurso'           : 'concurso',
            'datainscricao'      : 'datainscricao',
            'localprova'         : 'localprova',
            'tipocandidato'      : 'tipocandidato',
            'opcao'              : 'opcao',
            'modalidadeInscricao': 'modalidadeInscricao',
            'dataNascimento'     : 'dataNascimento',
            'cpf'                : 'cpf',
            'rg'                 : 'rg',
            'nome'               : 'nome',
            'cep'                : 'cep',
            'rua'                : 'rua',
            'numero'             : 'numero',
            'complemento'        : 'complemento',
            'bairro'             : 'bairro',
            'cidade'             : 'cidade',
            'estado'             : 'estado',
            'telefone'           : 'telefone',
            'celular'            : 'celular',
            'celularAdicional'   : 'celularAdicional',
            'email'              : 'email',
            'emailAdicional'     : 'emailAdicional',
            'anoConclusao'       : 'anoConclusao',
            'usarNotaEnem'       : 'usarNotaEnem',
            'numProtNotaEnem'    : 'numProtNotaEnem',
            'deficiencia'        : 'deficiencia',
            'tipoDeficiencia'    : 'tipoDeficiencia',
            'outraDeficiencia'   : 'outraDeficiencia',
            'multiDeficiencia'   : 'multiDeficiencia',
            'receberEmail'       : 'receberEmail',
            'recebersms'         : 'recebersms'
    ].asImmutable()
}
