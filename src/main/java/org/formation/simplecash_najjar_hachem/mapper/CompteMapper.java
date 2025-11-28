package org.formation.simplecash_najjar_hachem.mapper;


import org.formation.simplecash_najjar_hachem.dto.*;
import org.formation.simplecash_najjar_hachem.entity.Client;
import org.formation.simplecash_najjar_hachem.entity.Courant;
import org.formation.simplecash_najjar_hachem.entity.Epargne;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CompteMapper {
    // ===== Courant =====
    CourantDto toDto(Courant entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Courant toEntity(CourantDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    void updateCourant(@MappingTarget Courant entity, CourantDto dto);

    // ===== Epargne =====
    EpargneDto toDto(Epargne entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Epargne toEntity(EpargneDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    void updateEpargne(@MappingTarget Epargne entity, EpargneDto dto);
}
