package org.formation.simplecash_najjar_hachem.mapper;

import org.formation.simplecash_najjar_hachem.dto.ClientCreateDto;
import org.formation.simplecash_najjar_hachem.dto.ClientDto;
import org.formation.simplecash_najjar_hachem.dto.ClientUpdateDto;
import org.formation.simplecash_najjar_hachem.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ClientMapper {

    ClientDto toDto(Client entity);

    @Mapping(target= "id" , ignore=true)
    Client toEntity(ClientCreateDto dto);

    @Mapping(target= "id" , ignore=true)
    void updateEntity(@MappingTarget Client entity, ClientUpdateDto dto);



}
