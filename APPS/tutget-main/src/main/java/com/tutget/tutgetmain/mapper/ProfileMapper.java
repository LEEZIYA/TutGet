package com.tutget.tutgetmain.mapper;

import com.tutget.tutgetmain.dto.ProfileDTO;
import com.tutget.tutgetmain.model.profile.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {
  ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

  ProfileDTO toDTO(Profile profile);
  Profile toEntity(ProfileDTO profileDTO);
}
