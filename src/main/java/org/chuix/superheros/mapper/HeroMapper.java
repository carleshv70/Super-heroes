package org.chuix.superheros.mapper;

import org.chuix.superheros.model.dto.HeroDto;
import org.chuix.superheros.model.dto.OwnerEnum;
import org.chuix.superheros.model.entities.Hero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = OwnerEnum.class, imports = OwnerEnum.class)
public interface HeroMapper {
	
	HeroMapper INSTANCE = Mappers.getMapper(HeroMapper.class);

	@Mapping(target = "createAt", ignore = true)
	@Mapping(target = "deleteAt", ignore = true)
	@Mapping(target = "owner", expression = "java(dto.getOwner().getValue())")
	Hero mapDtoToEntity(HeroDto dto);


	@Mapping(target = "owner", expression = "java(OwnerEnum.getOwnerEnum(entity.getOwner()))")
	HeroDto mapEntityToDto(Hero entity);
	
	
	@Mapping(target = "createAt", ignore = true)
	@Mapping(target = "deleteAt", ignore = true)
 	@Mapping(target = "owner", expression = "java(dto.getOwner().getValue())")
	void update(@MappingTarget Hero entity, HeroDto dto);

}
